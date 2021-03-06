package view.reader;

import java.util.Scanner;

/**
 * Created by CraZy_IVAN on 15.02.16.
 * Method implements Reader/ And created for
 * read string from console
 *
 * @see FileReader
 * @author Gladush Ivan
 */
public class ConsoleReader implements Reader {
    private Scanner sc;

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

    @Override
    public void close() {
        System.out.println("I close1");
    }
}
