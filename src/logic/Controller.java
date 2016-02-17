package logic;

import view.Receiver;
import view.*;
import view.writer.ConsoleWriter;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */
public class Controller {
    //todo Controller need writer?
    private Receiver receiver;
    private View view;
    private ConsoleWriter  consoleWriter;
    private Calc calc;

    //todo maby better write  private Parser  parser=new Parser();
    public Controller() {
        receiver = new Receiver();
        calc = new Calc();
        view =new View();
        consoleWriter=new  ConsoleWriter();
    }

    public void run() {

        int userAns;
        view.printMainMenu();
        while (true) {

            try {
                userAns = receiver.getInt();
            } catch (NumberFormatException e) {
                consoleWriter.write("You input incorrect value\n Try again\n");
                continue;
            }
            if (userAns == 0) {
                view.Buy();
                break;
            } else if (userAns == 1) {
                consoleWriter.write("Input your example\n");
                consoleWriter.write("Your answer: " + calc.getAnswer(receiver.getString()) + "\n");

            } else if (userAns == 2) {
                view.printSettings();
            } else if (userAns == 3) {
                view.printChangeSettingsMenu();
            } else {
                view.incorrectInput();
                continue;
            }
            view.printMainMenu();
        }

    }
}
