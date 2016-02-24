package view.writer;

/**
 * Created by CraZy_IVAN on 15.02.16.
 * Class implements Writer and created for
 * write string in output Stream
 * @author Gladush Ivan
 * @see view.reader.Reader
 * @see Writer
 */

public class ConsoleWriter implements Writer {

    public void write(String... s) {
        for (String x : s) {
            System.out.println(x);
        }
    }
}
