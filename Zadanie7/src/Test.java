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
		tariff.put("simpleTest", 1.4 );
		tariff.put("notSoSimpleTest", 1.3 );
		tariff.put("notManyFieldsTest", 1.2 );
	}

	public static double getTariff(String testName) {
		return tariff.get(testName);
	}
	//////////////////////////////////////////////////////////////////////////

	private List<PMO_AutoTestable> objects;
	private List<Object> toAnonimize;
	private List<Object> anonimizeResult;
	private Set<String> stringsUsed;
	private Map<String, String> exchangesMap;
	private String[] magazineUniqStrings;
	private ObjectAnonymizerInterface ai;

	private void createAnonymizer() {
		ai = TestHelper.tryExecute(ObjectAnonymizer::new, "Konstruktor klasy ObjectAnonymizer");
	}

	private void anonimize() {
		createAnonymizer();
		// blokowanie mozliwosci zaminy listy obiektow - maja byc zmienione same
		// obiekty
		toAnonimize = Collections.unmodifiableList(objects);
		anonimizeResult = TestHelper.tryExecute(() -> ai.anonimize(toAnonimize), "anonimize");
	}

	private void getResults() {
		stringsUsed = TestHelper.tryExecute(ai::getUsedStrings, "getUsedStrings");
		exchangesMap = TestHelper.tryExecute(ai::getMapping, "getMapping");
	}

	@Before
	public void createArrayParserObject() {
		anonimizeResult = new ArrayList<>();
		objects = new ArrayList<>();
	}

	private void notNullTests() {
		assertNotNull("Wynikiem anonimize ma byc lista, nie null", anonimizeResult);
		assertNotNull("Wynikiem stringsUsed ma byc zbior, nie null", stringsUsed);
		assertNotNull("Wynikiem exchangesMap ma byc mapa, nie null", exchangesMap);
	}

	private void doBasicTests(int generateCalls) {
		notNullTests();
		assertThat("Wyznacznie unikalnych przemapowan wymagalo innej liczby wywolan Helper.generate",
				Helper.getNumberOfGeneratedCalls(), Matchers.greaterThanOrEqualTo(generateCalls));
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
		objects.forEach((o) -> o.autotest(stringsUsed, exchangesMap));
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

	private PMO_AutoTestable setString(PMO_AutoTestable ref, String str) {
		ref.setString(str);
		return ref;
	}

	private PMO_AutoTestable createO(String str) {
		PMO_AutoTestable ref = new Simple();
		return setString(ref, str);
	}

	private PMO_AutoTestable createNSSO(String str) {
		PMO_AutoTestable ref = new NotSoSimple();
		return setString(ref, str);
	}

	private PMO_AutoTestable createMFCO(String str) {
		PMO_AutoTestable ref = new ManyFieldsClass();
		return setString(ref, str);
	}

	@org.junit.Test(timeout = 1000)
	public void simpleTest() {
		prepareMagazine("aa", "ab");

		objects.add(createO("ala ma kota"));
		objects.add(createO("ala ma psa"));

		anonimizeAndTest(2);
	}

	@org.junit.Test(timeout = 1000)
	public void notSoSimpleTest() {
		prepareMagazine("aa", "ab", "ba");

		objects.add(createNSSO("mysz"));
		objects.add(createNSSO("aligator"));
		objects.add(createNSSO("pomidor"));

		anonimizeAndTest(3);
	}
	
	@org.junit.Test(timeout = 1000)
	public void notManyFieldsTest() {
		prepareMagazine("cd", "ab", "ba");

		objects.add(createMFCO("kos"));
		objects.add(createMFCO("Java"));
		objects.add(createMFCO("Wtorek"));

		anonimizeAndTest(3);
	}
	
}