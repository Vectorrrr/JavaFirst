import java.util.Scanner;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */
//todo Why I can't give name Reader?
public class Receiver {
    Scanner sc;

    public Receiver() {
        sc = new Scanner(System.in);
    }

    //empty string incorrect read
    // todo how i can show human that he doesn't right
    public String getString() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            System.out.println("You don't input line");
            return new String("");
        }
    }

    public int getInt() throws NumberFormatException {
        String temp=sc.next();

        try{
          return Integer.parseInt(temp);
        }
        catch(NumberFormatException e){
            throw new NumberFormatException();
        }
    }
}
