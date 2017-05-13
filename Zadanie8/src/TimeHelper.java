
public class TimeHelper {

    public static void sleepThread(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (Exception e) {
            System.out.println("Powstal wyjatek, ktorego byc nie powinno " + e.getMessage());
        }
    }

}
