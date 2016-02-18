package view.reader;

import java.util.Scanner;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */
public class ConsoleReader implements Reader {
    Scanner sc;

    public ConsoleReader() {
        sc = new Scanner(System.in);
    }


    public String getString() {

            return sc.nextLine();

    }

    @Override
    public boolean canRead() {
        return sc.hasNext();
    }
}
