package view.reader;

import java.util.Scanner;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */
//todo Why I can't give name Reader?
public class ConsoleReader implements Reader {
    Scanner sc;

    public ConsoleReader() {
        sc = new Scanner(System.in);
    }

    //empty string incorrect read
    // todo how i can show human that he doesn't right
    public String getString() {
        if (sc.hasNextLine()) {
            return  sc.nextLine();
        } else {
            System.out.println("You don't input line");
            return new String("");
        }
    }


}
