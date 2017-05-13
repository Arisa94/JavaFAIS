
import java.util.Random;

public class HDDTest implements HDDTestInterface {

    public HDDInterface myHDD;
    public HDDIO myHDDIO = new HDDIO();
    private boolean tested = false;

    int dataCorruptionCount = 0, diskTooHotCount = 0, badSectorCount = 0;

    private void test() {
        int numberOfSectors = myHDD.getNumberOfSectors();
        Random rand = new Random();
        for (int i = 0; i < numberOfSectors; i++) {
            try {
                myHDDIO.writeAndTest(i, (short) rand.nextInt(255));
            } catch (Exception e) {
                if (e instanceof BadSectorException) {
                    badSectorCount++;
                } else if (e instanceof DiskTooHotException) {
                    diskTooHotCount++;
                    i--;
                } else if (e instanceof DataCorruptionException) {
                    dataCorruptionCount++;
                }
            }
        }
        tested = true;
    }

    @Override
    public void setHDD(HDDInterface hddInterface) {
        this.myHDD = hddInterface;
        myHDDIO.setHDD(hddInterface);
    }

    @Override
    public int getNumberOfBadSectorsEvents() {
//        try {
//            myHDDIO.writeAndTest(0, (short) 0); // nie weim czy tutaj ta metoda ma być używana
//        } catch (BadSectorException e) {
//            e.printStackTrace();
//        } catch (DataCorruptionException e) {
//            e.printStackTrace(); // a tu ten sam wynik przy wielokrotnym użyciu  jest inny
//        } catch (DiskTooHotException e) {
//            TimeHelper.sleepThread(e.getCoolingTime());
//        }
//        return 0;
        if (!tested) {
            test();
        }
        return badSectorCount;
    }

    @Override
    public int getNumberOfDataCorruptionEvents() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (!tested) {
            test();
        }
        return dataCorruptionCount;
    }

    @Override
    public int getNumberOfDiscTooHotEvents() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (!tested) {
            test();
        }
        return diskTooHotCount;
    }

}
