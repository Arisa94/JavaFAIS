import org.junit.Before;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class Test {

	//////////////////////////////////////////////////////////////////////////
	private static final Map<String, Double> tariff = new HashMap<>();
	static {
		tariff.put("simpleTest", 1.6);
		tariff.put("simpleBadSector", 1.2);
		tariff.put("simpleDataCorruption", 1.2);
		tariff.put("simpleTooHotOnlyA", 1.0);
		tariff.put("simpleTooHotOnlyB", 1.0);
		tariff.put("tooHotAndDataCorruptionA", 0.2);
		tariff.put("tooHotAndDataCorruptionB", 0.2);
	}

	public static double getTariff(String testName) {
		return tariff.get(testName);
	}
	//////////////////////////////////////////////////////////////////////////

	private HDDIOInterface hddIO;
	private HDDTestInterface hddTest;

	@Before
	public void createArrayParserObject() {
		PMO_SystemOutRedirect.startRedirectionToNull();
		hddIO = TestHelper.tryExecute(HDDIO::new, "Konstruktor klasy HDDIO");
		hddTest = TestHelper.tryExecute(HDDTest::new, "Konstruktor klasy HDDTest");
	}

	private void setDisk2IO(PMO_HDD disk) {
		TestHelper.tryExecute(() -> hddIO.setHDD(disk), "SetHDD z klasy HDDIO");
	}

	private void setDisk2Test(PMO_HDD disk) {
		TestHelper.tryExecute(() -> hddTest.setHDD(disk), "SetHDD z klasy HDDTest");
	}

	private void setDisk(PMO_HDD disk) {
		setDisk2IO(disk);
		setDisk2Test(disk);
	}

	@org.junit.Test(timeout = 2000)
	public void simpleTest() {
		TestHelper.showMethodName();
		PMO_HDD disk = new PMO_HDD(100);
		// dysk bez bladow
		disk.setBadSectors(-1);
		disk.setCurruptedSectors(-1);
		disk.setTooHotExceptions(-1);

		setDisk(disk);

		PMO_SystemOutRedirect.returnToStandardStream();
		try {
			hddIO.writeAndTest(10, (short) 100);
		} catch (Exception e) {
			PMO_SystemOutRedirect.returnToStandardStream();
			fail("W trakcie testu sprawnego dysku pojawil sie wyjatek " + e.toString());
			TestHelper.showException(e);
		}

		assertEquals("Dysk jest sprawny liczba bad sectors events jest bledna", 0,
				hddTest.getNumberOfBadSectorsEvents());
		assertEquals("Dysk jest sprawny liczba data corruption events jest bledna", 0,
				hddTest.getNumberOfDataCorruptionEvents());
		assertEquals("Dysk jest sprawny liczba too hot events jest bledna", 0, hddTest.getNumberOfDiscTooHotEvents());
	}

	@org.junit.Test(timeout = 2000)
	public void simpleBadSector() {
		TestHelper.showMethodName();
		PMO_HDD disk = new PMO_HDD(100);
		disk.setBadSectors(10, 20);
		disk.setCurruptedSectors(-1);
		disk.setTooHotExceptions(-1);

		setDisk(disk);

		PMO_SystemOutRedirect.returnToStandardStream();
		try {
			hddIO.writeAndTest(10, (short) 100);
		} catch (BadSectorException e) {
			PMO_SystemOutRedirect.println(" - OK zlapano wyjatek bad sector");
		} catch (Exception e) {
			PMO_SystemOutRedirect.returnToStandardStream();
			fail("W trakcie testu dysku pojawil sie wyjatek " + e.toString());
			TestHelper.showException(e);
		}

		try {
			hddIO.writeAndTest(20, (short) 100);
		} catch (BadSectorException e) {
			PMO_SystemOutRedirect.println(" - OK zlapano wyjatek bad sector");
		} catch (Exception e) {
			PMO_SystemOutRedirect.returnToStandardStream();
			fail("W trakcie testu dysku pojawil sie wyjatek " + e.toString());
			TestHelper.showException(e);
		}

		assertEquals("Dysk jest uszkodzony, liczba bad sectors events jest bledna", 2,
				hddTest.getNumberOfBadSectorsEvents());
		assertEquals("Dysk jest uszkodzony, ale nie zglasza data corruption events", 0,
				hddTest.getNumberOfDataCorruptionEvents());
		assertEquals("Dysk jest uszkodzony, ale sie nie przegrzewa", 0, hddTest.getNumberOfDiscTooHotEvents());
	}

	@org.junit.Test(timeout = 2000)
	public void simpleDataCorruption() {
		TestHelper.showMethodName();
		PMO_HDD disk = new PMO_HDD(100);
		disk.setBadSectors(-1);
		disk.setCurruptedSectors(20, 30);
		disk.setTooHotExceptions(-1);

		setDisk(disk);

		PMO_SystemOutRedirect.returnToStandardStream();
		try {
			hddIO.writeAndTest(30, (short) 100);
		} catch (DataCorruptionException e) {
			PMO_SystemOutRedirect.println(" - OK zlapano wyjatek data corruption");
		} catch (Exception e) {
			PMO_SystemOutRedirect.returnToStandardStream();
			fail("W trakcie testu dysku pojawil sie wyjatek " + e.toString());
			TestHelper.showException(e);
		}

		try {
			hddIO.writeAndTest(20, (short) 100);
		} catch (DataCorruptionException e) {
			PMO_SystemOutRedirect.println(" - OK zlapano wyjatek data corruption");
		} catch (Exception e) {
			PMO_SystemOutRedirect.returnToStandardStream();
			fail("W trakcie testu dysku pojawil sie wyjatek " + e.toString());
			TestHelper.showException(e);
		}

		assertEquals("Dysk jest uszkodzony, liczba data corruption events jest bledna", 2,
				hddTest.getNumberOfDataCorruptionEvents());
		assertEquals("Dysk jest uszkodzony, ale sie nie przegrzewa", 0, hddTest.getNumberOfDiscTooHotEvents());
	}

//	@org.junit.Test(timeout = 10000)
	@org.junit.Test(timeout = 100000000)
	public void simpleTooHotOnlyA() {
		TestHelper.showMethodName();
		PMO_HDD disk = new PMO_HDD(100);
		disk.setBadSectors(-1);
		disk.setCurruptedSectors(-1);
		disk.setTooHotExceptions(20, 40);

		setDisk2IO(disk);

		PMO_SystemOutRedirect.returnToStandardStream();
		try {
			hddIO.writeAndTest(20, (short) 100);
		} catch (DiskTooHotException e) {
			PMO_SystemOutRedirect.println(" - OK zlapano wyjatek too hot");
		} catch (Exception e) {
			PMO_SystemOutRedirect.returnToStandardStream();
			fail("W trakcie testu dysku pojawil sie wyjatek " + e.toString());
			TestHelper.showException(e);
		}

		try {
			hddIO.writeAndTest(40, (short) 100);
		} catch (DiskTooHotException e) {
			PMO_SystemOutRedirect.println(" - OK zlapano wyjatek too hot");
		} catch (Exception e) {
			PMO_SystemOutRedirect.returnToStandardStream();
			fail("W trakcie testu dysku pojawil sie wyjatek " + e.toString());
			TestHelper.showException(e);
		}
	}

	@org.junit.Test(timeout = 10000)
	public void simpleTooHotOnlyB() {
		TestHelper.showMethodName();
		PMO_HDD disk = new PMO_HDD(100);
		disk.setBadSectors(-1);
		disk.setCurruptedSectors(-1);
		disk.setTooHotExceptions(20, 40);

		setDisk2Test(disk);

		PMO_SystemOutRedirect.returnToStandardStream();

		assertEquals("Dysk jest uszkodzony, liczba too hot events jest bledna", 2,
				hddTest.getNumberOfDiscTooHotEvents());
	}

	@org.junit.Test(timeout = 10000)
	public void tooHotAndDataCorruptionA() {
		TestHelper.showMethodName();
		PMO_HDD disk = new PMO_HDD(100);
		disk.setBadSectors(-1);
		disk.setCurruptedSectors(20);
		disk.setTooHotExceptions(20, 40);

		setDisk2IO(disk);

		PMO_SystemOutRedirect.returnToStandardStream();
		try {
			hddIO.writeAndTest(20, (short) 100);
		} catch (DataCorruptionException e) {
			PMO_SystemOutRedirect.println(" - OK zlapano wyjatek data corruption");
		} catch (DiskTooHotException e) {
			fail("Ten sektor zglasza wprawdzie too hot, ale przede wszystkim nie przechowuje poprawnie danych");
		} catch (Exception e) {
			PMO_SystemOutRedirect.returnToStandardStream();
			fail("W trakcie testu dysku pojawil sie wyjatek " + e.toString());
			TestHelper.showException(e);
		}

		try {
			hddIO.writeAndTest(40, (short) 100);
		} catch (DiskTooHotException e) {
			PMO_SystemOutRedirect.println(" - OK zlapano wyjatek too hot");
		} catch (Exception e) {
			PMO_SystemOutRedirect.returnToStandardStream();
			fail("W trakcie testu dysku pojawil sie wyjatek " + e.toString());
			TestHelper.showException(e);
		}
	}

	@org.junit.Test(timeout = 10000)
	public void tooHotAndDataCorruptionB() {
		TestHelper.showMethodName();
		PMO_HDD disk = new PMO_HDD(100);
		disk.setBadSectors(-1);
		disk.setCurruptedSectors(20);
		disk.setTooHotExceptions(20, 40);

		setDisk2Test(disk);

		PMO_SystemOutRedirect.returnToStandardStream();

		assertEquals("Dysk jest uszkodzony, liczba wyjatkow data corruption", 1,
				hddTest.getNumberOfDataCorruptionEvents());
		assertEquals("Dysk jest uszkodzony, liczba too hot events jest bledna", 1,
				hddTest.getNumberOfDiscTooHotEvents());
	}

}