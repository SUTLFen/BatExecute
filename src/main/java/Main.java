import java.util.Timer;

/**
 * Created by Fairy_LFen on 2016/11/7.
 */
public class Main {
    public static void main(String[] args) {
        new Timer().schedule(new ExecutBatTimerTask(), 0,
            5*60*60*1000);
    }
}
