import org.hamcrest.Matchers;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {

	//////////////////////////////////////////////////////////////////////////
	private static final Map<String, Double> tariff = new HashMap<>();
	static {
		tariff.put("simpleTest", 1.5 );
		tariff.put("notUniqGeneratedStrings", 1.5 );
		tariff.put("notUniqGeneratedStrings2", 1.5 );
		tariff.put( "phoneRepetitions", 1.2 );
		tariff.put( "manyPhonesToAnonimize", 0.9 );
	}

	public static double getTariff(String testName) {
		return tariff.get(testName);
	}
	//////////////////////////////////////////////////////////////////////////

	private AnonymizerInterface ai;
	private List<Contact> contactsToAnonimize;
	private List<PhoneInterface> phonesToAnonimize;
	private List<PhoneInterface> phonesAfterAnonimization;
	private Set<String> stringsUsed;
	private Map<String, String> exchangesMap;
	private String[] magazineUniqStrings;

	private void createAnonymizer() {
		ai = TestHelper.tryExecute(Anonymizer::new, "Konstruktor klasy Anonymizer");
	}

	private void anonimize() {
		createAnonymizer();
		// blokowanie mozliwosci zaminy listy obiektow - maja byc zmienione same
		// obiekty
		phonesToAnonimize = Collections.unmodifiableList(phonesToAnonimize);
		phonesAfterAnonimization = TestHelper.tryExecute(() -> ai.anonimize(phonesToAnonimize), "anonimize");
	}

	private void getResults() {
		stringsUsed = TestHelper.tryExecute(ai::getUsedStrings, "getUsedStrings");
		exchangesMap = TestHelper.tryExecute(ai::getMapping, "getMapping");
	}

	@Before
	public void createArrayParserObject() {
		contactsToAnonimize = new ArrayList<>();
		phonesToAnonimize = new ArrayList<>();
	}

	private void addContact(Contact c) {
		contactsToAnonimize.add(new Contact(c.getId(), c.getPhoneNumber()));
		phonesToAnonimize.add(c);
	}

	private void notNullTests() {
		assertNotNull("Wynikiem anonimize ma byc lista, nie null", phonesAfterAnonimization);
		assertNotNull("Wynikiem stringsUsed ma byc zbior, nie null", stringsUsed);
		assertNotNull("Wynikiem exchangesMap ma byc mapa, nie null", exchangesMap);
	}

	private void doBasicTests(int generateCalls) {
		notNullTests();
		assertThat("Wyznacznie unikalnych przemapowan wymagalo innej liczby wywolan Helper.generate",
				Helper.getNumberOfGeneratedCalls(), Matchers.is(generateCalls));
		assertThat(
				"W przekazanej liscie byla inna liczba unikalnych numerow do przemapowania, rozmiar zbioru uzytych ciagow jest bledny",
				stringsUsed, Matchers.hasSize(magazineUniqStrings.length));
		assertThat(
				"W przekazanej liscie byla inna liczba unikalnych numerow do przemapowania, rozmiar mapy przemapowan jest bledny",
				exchangesMap.size(), Matchers.is(magazineUniqStrings.length));
	}

	private void doContentTests() {
		assertEquals("Przemapowania musza byc jednoznaczne, rozmiar mapy musi byc rowny liczbie wartosci",
				exchangesMap.size(), exchangesMap.values().stream().distinct().count());
		assertThat("Zawartosc zbioru musi byc taka sama jak zbioru unikalnych ciagow helpera", stringsUsed,
				Matchers.containsInAnyOrder(magazineUniqStrings));

		// a teraz test kolejnych przemapowan
		String pi, expected;
		for (int i = 0; i < contactsToAnonimize.size(); i++) {
			pi = contactsToAnonimize.get(i).getPhoneNumber();
			expected = exchangesMap.get(pi);
			assertEquals("Na pozycji " + i + " oryginalnej listy jest " + pi
					+ " powinno byc zamienione wg. mapy w ciag " + expected, expected,
					phonesAfterAnonimization.get(i).getPhoneNumber());
		}
	}

	private void prepareMagazine(String... cs) {
		Set<String> uniq = new HashSet<String>();
		uniq.addAll(Arrays.asList(cs));
		magazineUniqStrings = (uniq.toArray(new String[uniq.size()]));
		Helper.setMagazine(Arrays.asList(cs));
	}

	private void anonimizeAndTest(int generateCalls) {
		anonimize();
		getResults();
		doBasicTests(generateCalls);
		doContentTests();
	}

	@org.junit.Test(timeout = 1000)
	public void simpleTest() {
		prepareMagazine("aa", "ab");
		addContact(ContactsFactory.get("12 123"));
		addContact(ContactsFactory.get("33333"));
		anonimizeAndTest(2);
	}

	@org.junit.Test(timeout = 1000)
	public void notUniqGeneratedStrings() {
		prepareMagazine("aa", "aa", "ab");
		addContact(ContactsFactory.get("12 123"));
		addContact(ContactsFactory.get("33333"));
		anonimizeAndTest(3);
	}

	@org.junit.Test(timeout = 1000)
	public void notUniqGeneratedStrings2() {
		prepareMagazine("aa", "ab", "aa");
		addContact(ContactsFactory.get("12 123"));
		addContact(ContactsFactory.get("33333"));
		anonimizeAndTest(2); // 3-cie wywolanie generate nie jest potrzebne
	}

	@org.junit.Test(timeout = 1000)
	public void phoneRepetitions() {
		prepareMagazine("aa", "ab");
		addContact(ContactsFactory.get("12 123"));
		addContact(ContactsFactory.get("33333"));
		addContact(ContactsFactory.get("12 123"));
		addContact(ContactsFactory.get("33333"));
		anonimizeAndTest(2);
	}

	@org.junit.Test(timeout = 1000)
	public void manyPhonesToAnonimize() {
		prepareMagazine("aa", "aa", "ab", "aa", "ab", "ac", "ad");
		addContact(ContactsFactory.get("12 123"));
		addContact(ContactsFactory.get("+48 +12 664 1234"));
		addContact(ContactsFactory.get("33333"));
		addContact(ContactsFactory.get("12 123"));
		addContact(ContactsFactory.get("44444"));
		addContact(ContactsFactory.get("12 123"));
		addContact(ContactsFactory.get("33333"));
		addContact(ContactsFactory.get("+48 +12 664 1234"));
		addContact(ContactsFactory.get("33333"));
		addContact(ContactsFactory.get("44444"));
		addContact(ContactsFactory.get("33333"));
		addContact(ContactsFactory.get("33333"));
		addContact(ContactsFactory.get("+48 +12 664 1234"));

		anonimizeAndTest(7);
	}
}