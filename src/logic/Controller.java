package logic;

import model.Settings;
import view.reader.ConsoleReader;
import view.*;
import view.reader.FileReader;
import view.reader.Reader;
import view.writer.ConsoleWriter;

import java.io.FileNotFoundException;
import java.util.IllegalFormatException;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */

public  class Controller {
    //todo Controller need writer?
    private Reader consoleReader;
    private Reader exampleReader;
    private View view;
    private ConsoleWriter consoleWriter;
    private CalculationService calc;
    private String userAns;

    public Controller() {
        consoleReader = new ConsoleReader();
        calc = new CalculationService();
        view = new View();
        consoleWriter = new ConsoleWriter();
    }

    public void run() {


        while (true) {
            view.printSettingsRead();
            userAns = consoleReader.getString();
            if ("2".equals(userAns)) {

                if (!createExampleReader()) {
                    continue;
                }
                workWithFile();
                break;
            }
            if ("1".equals(userAns)) {
                createExampleReader();
                workWithConsole();
                break;
            }
        }

    }

    private void workWithConsole() {
        while (true) {
            view.printMainMenu();
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
                consoleWriter.write("Your answer: " + calc.calculate(exampleReader.getString()) + "\n");

            } else if ("2".equals(userAns)) {
                settingsMenu();

            } else {
                view.incorrectInput();
            }

        }

    }

    private void workWithFile() {
        while (true) {
            view.printFileMenu();
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
                if (exampleReader.canRead()) {
                    consoleWriter.write("Your answer: " + calc.calculate(exampleReader.getString()) + "\n");
                } else {
                    consoleWriter.write("I read all your file!");
                    view.Buy();
                    break;
                }

            } else {
                consoleWriter.write("You input incorrect value\n Try again\n");
            }

        }
    }

    //todo This is Reader Factory?
    private boolean createExampleReader() {
        if ("1".equals(userAns)) {
            exampleReader = new ConsoleReader();
            return true;
        }
        else if("2".equals(userAns)){
            try {
                String path;
                consoleWriter.write("Input path to your file");
                path = consoleReader.getString();
                exampleReader = new FileReader(path);
            } catch (FileNotFoundException e) {
                consoleWriter.write("I don't find your file!\n Try again!!!");
                return false;
            }
            return true;
        }else{
            throw new IllegalArgumentException("I don't know this model Reader");

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
