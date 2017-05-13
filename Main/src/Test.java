import org.junit.Before;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	// taryfikator
	private static final Map<String, Double> tariff = new HashMap<>();

	static {
		tariff.put( "ruleA", 1.5 ); 
		tariff.put( "ruleB", 1.5 );
		tariff.put( "ruleC", 1.5 );
		tariff.put( "ruleD", 1.5 );
		tariff.put( "ruleD_Byte",  0.1 );
		tariff.put( "ruleD_Short", 0.1 );

		tariff.put( "ruleE", 1.0 ); 

		tariff.put( "ruleF", 0.6 );
		tariff.put( "ruleF_DoubleNull", 0.3 );
		tariff.put( "ruleF_NullString", 0.6 );
		tariff.put( "ruleG", 0.6 );
		tariff.put( "ruleG_DoubleNull", 0.3 );
		
	}
	
	public static double getTariff( String testName ) {
		return tariff.get( testName );
	}
	
	private SuperArrayParser ap;
	private Object[] a;
	private Object[] b;
	private Object[] e; // expected value
	private Object[] r; // result
	
	private String typeToString( Object o ) {
	   if ( o instanceof Byte ) return "Byte";
	   if ( o instanceof Short ) return "Short";
	   if ( o instanceof Integer ) return "Integer";
	   if ( o instanceof Long ) return "Long";
	   if ( o instanceof Float ) return "Float";
	   if ( o instanceof Double ) return "Double";
	   if ( o instanceof String ) return "String";
	   return "Unknown";
	} 
	
	private String getErrorMessage( int i ) {
		return "Indeks " + i + " oczekiwano " + typeToString( e[i]) + " wykryto " + typeToString( r[ i ] ) ;
	}
	
	private boolean isTheSameType( int i ) {
		assertNotNull( "Pod indeksem " + i + " tablicy wynikowej null nie oczekiwano", r[ i ] );
		if ( ( e[i] instanceof Byte ) && ( r[ i ] instanceof Byte ) ) return true;
		if ( ( e[i] instanceof Short ) && ( r[ i ] instanceof Short ) ) return true;
		if ( ( e[i] instanceof Integer ) && ( r[ i ] instanceof Integer ) ) return true;
		if ( ( e[i] instanceof Long ) && ( r[ i ] instanceof Long ) ) return true;
		if ( ( e[i] instanceof Float ) && ( r[ i ] instanceof Float ) ) return true;
		if ( ( e[i] instanceof Double ) && ( r[ i ] instanceof Double ) ) return true;
		
		return false;
	}
	
	private void resultTest() {
		assertNotNull( "Tablica wynikowa nie moze byc null", r );
		assertEquals( "Rozmiar tablicy wynikowej musi byc zgodny z rozmiarem dostarczonych tablic", a.length, r.length );
	}

	private void startParsing() {
		try {
			PMO_SystemOutRedirect.startRedirectionToNull();
			r = ap.parse(a, b);
		} catch ( Exception e ) {
			PMO_SystemOutRedirect.returnToStandardStream();
			
			fail( "W trakcie pracy metody parse doszlo do wyjatku " );
			
		} finally {
			PMO_SystemOutRedirect.returnToStandardStream();			
		}		
	}

	private void parseAndTest() {
		startParsing();		
		resultTest();
	}
	
	private void compareTypes( int i ) {
		if ( ! isTheSameType( i ) ) {
			fail( getErrorMessage( i ));
		}		
	}
	
	private void testIntValueAndType(int i) {
		compareTypes(i);
		long expected = (new Long(e[i].toString())).longValue();
		long received = (new Long(r[i].toString())).longValue();

		assertEquals("Porownanie wartosci calkowitoliczbowych dla typu " + 
		             typeToString(e[i]), expected, received);
	}

	private void testFloatValueAndType(int i) {
		compareTypes(i);
		double expected = (new Double(e[i].toString())).doubleValue();
		double received = (new Double(r[i].toString())).doubleValue();

		assertEquals("Porownanie wartosci zmiennoprzecinkowych dla typu " + 
		             typeToString(e[i]), expected, received, 0.02);
	}
	
	@org.junit.Test
	public void ruleA() {
		a = new Object[] { 1, 2, 3, 10, 11, 30 };
		b = new Object[] { 2, 3, 4, 10, 20, -11 };
		e = new Object[] { 3, 5, 7, 20, 31, 19 };
		parseAndTest();
		for ( int i = 0; i < a.length; i++ ) {
			testIntValueAndType(i );
		}
	}

	@org.junit.Test
	public void ruleB() {
		a = new Object[] { 1.2f, 3.4f, 4.5f, 2.2f, -1.2f, -3f };
		b = new Object[] {   2f,   1f,   2f,  -4f,   -2f,  1f };
		e = new Object[] { 2.4f, 3.4f, 9.0f,-8.8f,  2.4f, -3f };
		parseAndTest();
		for ( int i = 0; i < a.length; i++ ) {
			testFloatValueAndType(i );
		}
	}
	
	@org.junit.Test
	public void ruleC() {
		a = new Object[] { 1.2, 1.0,  3.4, 4.5, -12.2, -1.4,  10.0, 1234.0 };
		b = new Object[] { 3.0, 1.0, -1.0, 2.0,  -4.0, -2.0, 123.0, 2345.1 };
		e = new Object[] { 3.0, 1.0,  3.4, 4.5,  -4.0, -1.4, 123.0, 2345.1 };
		parseAndTest();
		for ( int i = 0; i < a.length; i++ ) {
			testFloatValueAndType(i);
		}
	}

	@org.junit.Test
	public void ruleD_Byte() {
		a = new Object[] { "Ala"            , (byte)1};
		b = new Object[] { (byte)( -3 )     , "ma" };
		e = new Object[] { new Byte((byte)3), (byte)(-1) };
		parseAndTest();
		testIntValueAndType(0);
		testIntValueAndType(1);
	}

	@org.junit.Test
	public void ruleD_Short() {
		a = new Object[] { ".",           (short)(-2) };
		b = new Object[] { (short)123,    "," };
		e = new Object[] { (short)(-123), (short)2};
		parseAndTest();
		testIntValueAndType(0);
		testIntValueAndType(1);
	}
	
	@org.junit.Test
	public void ruleD() {
		a = new Object[] { "Ala", 1.0,  "kota",  4L,   "rybki",  "." };
		b = new Object[] { -3   , "ma", -1.0f,  "oraz",  4,      123 };
		e = new Object[] {  3   , -1.0,  1.0f,  -4L,    -4,     -123};
		parseAndTest();
		testIntValueAndType(0);
		testFloatValueAndType(1);
		testFloatValueAndType(2);
		testIntValueAndType(3);
		testIntValueAndType(4);
		testIntValueAndType(5);
	}
	
	@org.junit.Test
	public void ruleE() {
		a = new Object[] { null, null };
		b = new Object[] { null, null };
		e = new Object[] { null, null };
		parseAndTest();
		assertNull( "null + null -> null", r[0] );
		assertNull( "null + null -> null", r[1] );
	}
	
	@org.junit.Test
	public void ruleF() {
		a = new Object[] { null, 2, 2,    null, 8.0 };
		b = new Object[] { 1,    1, null, 5   , 9.0 };
		e = new Object[] { 3,    3, 9.0 , 9.0 , 9.0 };
		parseAndTest();
		testIntValueAndType(0);
		testIntValueAndType(1);
		testFloatValueAndType( 2 );
		testFloatValueAndType( 3 );
		testFloatValueAndType( 4 );
	}

	@org.junit.Test
	public void ruleF_DoubleNull() {
		a = new Object[] { null, 2,    null, null };
		b = new Object[] { 1,    null, 44,   null };
		e = new Object[] { null, null, null, null };
		parseAndTest();
		for ( int i = 0; i < a.length; i++ )
			assertNull( "Null + null -> null, czyli mozna wyznaczyc wynik", r[i]);
	}
	
	@org.junit.Test
	public void ruleF_NullString() {
		a = new Object[] { null, "Nie", null,  2 };
		b = new Object[] { "A",  null,  "Kot", 8 };
		e = new Object[] { 10,   10,    10,    10 };
		parseAndTest();
		for ( int i = 0; i < a.length; i++ )
			testIntValueAndType(i);
	}
	
	@org.junit.Test
	public void ruleG() {
		a = new Object[] { 2, 1L, 2, 2.0, 8.0f, 1, (short)1 };
		b = new Object[] { 1, 2F, 2, 2  , 9.0 , 2, (byte)1  };
		e = new Object[] { 3, 3,  4, 4  , 4   , 3, 3        };
		parseAndTest();
		for ( int i = 0; i < a.length; i++ )
			testIntValueAndType(i);
	}

	@org.junit.Test
	public void ruleG_DoubleNull() {
		a = new Object[] { null, 1L,   null, 2.0 , 8.0f };
		b = new Object[] { null, 2F,   null, 2   , (byte)9 };
		e = new Object[] { null, null, null, null, null };
		parseAndTest();
		for ( int i = 0; i < a.length; i++ )
			assertNull( "Null + null -> null, czyli mozna wyznaczyc wynik", r[i]);
	}
	
	@Before
	public void createArrayParserObject() {
		ap = new SuperArrayParser();
	}
	
}