package logic;

import view.Receiver;
import view.*;
import view.writer.Writer;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */
public class Controller {
    private Receiver receiver;
    private Parser parser;
    private Calc calc;

    //todo maby better write  private Parser  parser=new Parser();
    public Controller() {
        receiver = new Receiver();
             calc = new Calc();
    }

    public void run() {

        int userAns;
        View.printMainMenu();
        while (true) {

            try {
                userAns = receiver.getInt();
            } catch (NumberFormatException e) {
                Writer.writeInConsole("You input incorrect value\n Try again\n");
                continue;
            }
            if (userAns == 0) {
                View.Buy();
                break;
            } else if (userAns == 1) {
                Writer.writeInConsole("Input your example\n");
                Writer.writeInConsole("Your answer: " + calc.getAnswer(receiver.getString()) + "\n");

            } else if (userAns == 2) {
                View.printSettings();
            } else if (userAns == 3) {
                View.printChangeSettingsMenu();
            } else {
                View.incorrectInput();
                continue;
            }
            View.printMainMenu();
        }

    }
}
