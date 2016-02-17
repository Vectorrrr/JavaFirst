package logic;

import view.reader.ConsoleReader;
import view.*;
import view.writer.ConsoleWriter;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */
public  class Controller {
    //todo Controller need writer?
    private ConsoleReader receiver;
    private View view;
    private ConsoleWriter  consoleWriter;
    private Calc calc;

    //todo maby better write  private Parser  parser=new Parser();
    public Controller() {
        receiver = new ConsoleReader();
        calc = new Calc();
        view =new View();
        consoleWriter=new  ConsoleWriter();
    }

    public void run() {

        String userAns;
        view.printMainMenu();
        while (true) {

            try {
                userAns = receiver.getString();
            } catch (NumberFormatException e) {
                consoleWriter.write("You input incorrect value\n Try again\n");
                continue;
            }
            if ("0".equals(userAns)) {
                view.Buy();
                break;
            } else if ("1".equals(userAns)) {
                consoleWriter.write("Input your example\n");
                consoleWriter.write("Your answer: " + calc.getAnswer(receiver.getString()) + "\n");

            } else if ("2".equals(userAns)) {
                view.printSettings();
            } else if ("3".equals(userAns)) {
                view.printChangeSettingsMenu();
            } else {
                view.incorrectInput();
                continue;
            }
            view.printMainMenu();
        }

    }
    private void settingsMenu(){

    }
}
