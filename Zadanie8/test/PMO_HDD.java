import java.util.List;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;


public class PMO_HDD implements HDDInterface {
	private final long SLEEP_BEFORE_BAD_SECTOR_EXCEPTION = 200;
	private final long COOLING_TIME = 350;

	private final int size;
	private List<Integer> badSectors;
	private List<Integer> corruptedSectors;
	private List<Integer> tooHotExceptions;
	private final short[] value;
	private final int readsAfterWriteCounter[];
	private long nextUsage;
	private int badSectorsUsageCounter;
	private final boolean[] tooHotThrown;
	
	public PMO_HDD( int size ) {
		this.size = size;
		value = new short[ size ];
		readsAfterWriteCounter = new int[ size ];
		nextUsage = System.currentTimeMillis();
		tooHotThrown = new boolean[ size] ;
	}
	
	public void setBadSectors( int ... sectors ) {
		badSectors = TestHelper.convert2list( TestHelper.convert( sectors ) );
	}
	
	public void setCurruptedSectors( int ... sectors ) {
		corruptedSectors = TestHelper.convert2list( TestHelper.convert( sectors ) );
	}
	
	public void setTooHotExceptions( int ... sectors ) {
		tooHotExceptions = TestHelper.convert2list( TestHelper.convert( sectors ) );
	}
	
	@Override
	public int getNumberOfSectors() {
		return size;
	}

	private void exception( int sector ) throws BadSectorException {
		if ( badSectors.contains( sector ) ) {
			TimeHelper.sleepThread( SLEEP_BEFORE_BAD_SECTOR_EXCEPTION );
			badSectorsUsageCounter++;
			if ( badSectorsUsageCounter > 2*badSectors.size() ) {
				PMO_SystemOutRedirect.println( "WARINIG: Za duzo uzyc sektorow, ktore zglaszaja BadSectorException");
			}
			throw new BadSectorException();
		}
	}
	
	private void tooHot( int sector ) throws DiskTooHotException {
		if ( (! tooHotThrown[sector] ) &&  tooHotExceptions.contains( sector ) ) {
			nextUsage = System.currentTimeMillis() + COOLING_TIME;
			tooHotThrown[ sector ] = true;
			throw new DiskTooHotException( COOLING_TIME );
		}
	}
	
	private void diskTooHotUsage() {
		if ( System.currentTimeMillis() < nextUsage ) {
			PMO_SystemOutRedirect.returnToStandardStream();
			fail( "Uzyto zbyt goracego dysku");
		}		
	}
	
	@Override
	public void write(int sector, short value) throws BadSectorException, DiskTooHotException {
		diskTooHotUsage();
		exception(sector);
		this.value[ sector ] = value;
		readsAfterWriteCounter[ sector ] = 0;
	}

	@Override
	public short read(int sector) throws BadSectorException, DiskTooHotException {
		diskTooHotUsage();
		exception(sector);
		tooHot( sector );
		
		if ( corruptedSectors.contains( sector ) ) {
			if ( readsAfterWriteCounter[ sector] + 2 > HDDIOInterface.NUMBER_OF_ATTEMPTS ) {
				readsAfterWriteCounter[ sector ]++;
				return (short)( ( value[ sector ] + 10 ) % 255); // przeklamanie
			}
		}
		readsAfterWriteCounter[ sector ]++;
		return value[ sector ];
	}

}