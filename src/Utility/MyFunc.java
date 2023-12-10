package Utility;

public class MyFunc {

    public static void bekle(int sn)
    {
        try {
            Thread.sleep(1000*sn);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
