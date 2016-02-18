package logic;

import model.Settings;
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
    private ConsoleWriter consoleWriter;
    private CalculationService calc;

    public Controller() {
        consoleReader = new ConsoleReader();
        calc = new CalculationService();
        view = new View();
        consoleWriter = new ConsoleWriter();
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
                consoleWriter.write("Your answer: " + calc.calculate(consoleReader.getString()) + "\n");

            } else if ("2".equals(userAns)) {
                settingsMenu();

            } else {
                view.incorrectInput();
            }
            view.printMainMenu();
        }

    }

    private void settingsMenu() {
        view.printSettings();
        String ans = consoleReader.getString();
        while (!"0".equals(ans)) {
            switch (ans) {
                case "1":
                    view.printAllOverOper();
                    break;
                case "2":
                    overOper();
                    break;
                case "3":
                    removeOper();
                    break;
                default:
                    consoleWriter.write("You input incorrect sing! Please input number in range 0 to 2");
            }
            view.printSettings();
            ans = consoleReader.getString();
        }
    }

    private void overOper() {
        consoleWriter.write("Input you sing");
        String overOper = consoleReader.getString();
        consoleWriter.write("Input base operation one of this");
        view.printBaseOper();
        String baseOper = consoleReader.getString();
        if (!Settings.addOverloadOperation(overOper, baseOper)) {
            consoleWriter.write("You input incorrect operation");
        }
    }

    private void removeOper() {
        consoleWriter.write("Input you over Sing");
        Settings.removeOperation(consoleReader.getString());
    }

}
