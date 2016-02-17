package logic;

import view.reader.ConsoleReader;
import view.*;
import view.writer.ConsoleWriter;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */

public  class Controller {
    //todo Controller need writer?
    private ConsoleReader consoleReader;
    private View view;
    private ConsoleWriter  consoleWriter;
    private Calc calc;

    public Controller() {
        consoleReader = new ConsoleReader();
        calc = new Calc();
        view =new View();
        consoleWriter=new  ConsoleWriter();
    }

    public void run() {

        String userAns;
        view.printMainMenu();
        while (true) {

            try {
                userAns = consoleReader.getString();
            } catch (NumberFormatException e) {
                consoleWriter.write("You input incorrect value\n Try again\n");
                continue;
            }
            if ("0".equals(userAns)) {
                view.Buy();
                break;
            } else if ("1".equals(userAns)) {
                consoleWriter.write("Input your example\n");
                consoleWriter.write("Your answer: " + calc.getAnswer(consoleReader.getString()) + "\n");

            } else if ("2".equals(userAns)) {
                view.printSettings();
            } else {
                view.incorrectInput();
                continue;
            }
            view.printMainMenu();
        }

    }
    private void settingsMenu(){
        view.printSettings();
        String ans= consoleReader.getString();
        while(!"0".equals(ans)){

        }
    }

}
