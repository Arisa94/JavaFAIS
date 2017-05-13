
import java.util.LinkedList;

public class HDDIO implements HDDIOInterface {

    /// teraz Test używa HDDIO
    private LinkedList<Integer> bads = new LinkedList();

    static int BadSectorsEvents = 0;
    static int DataCorruptionEventsNum = 0;
    static int DiskTooHotNum = 0;

    public HDDInterface myHDD;

    @Override
    public void setHDD(HDDInterface hddInterface) {
        myHDD = hddInterface;
    }

    private boolean badsContains(int sector) {
        for (int s : bads) {
            if (s == sector) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void writeAndTest(int sector, short value) throws BadSectorException, DataCorruptionException, DiskTooHotException {
        /// 3 razy może się wykonać ok
//        while (attempt_num < NUMBER_OF_ATTEMPTS) {
//            myHDD.write(sector, value);
//            myHDD.read(sector);
//            attempt_num++;
//        }

        if (badsContains(sector)) {
            throw new BadSectorException();
        }

        int attempt_num = 0;
        boolean pass = false;
        while (!pass && attempt_num++ < NUMBER_OF_ATTEMPTS) {
            try {
                myHDD.write(sector, value);
            } catch (Exception e) {
                if (e instanceof BadSectorException) {
                    bads.add(sector);
                    throw e;
                } else if (e instanceof DiskTooHotException) {
//                DiskTooHotNum++;
                    TimeHelper.sleepThread(((DiskTooHotException) e).getCoolingTime());
                    throw e;
                } else {
                    throw e;
                }
            }
            pass = value == myHDD.read(sector);
        }

        if (!pass) {
            throw new DataCorruptionException();
        }

    }

}
