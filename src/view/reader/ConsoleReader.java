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
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            System.out.println("You don't input line");
            return new String("");
        }
    }
}
