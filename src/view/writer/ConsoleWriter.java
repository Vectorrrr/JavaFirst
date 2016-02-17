package view.writer;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */

public class ConsoleWriter implements Writer {

    public  void write(String... s) {
        for (String x : s) {
            System.out.println(x);
        }
    }


}
