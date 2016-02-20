package logic;

import logic.service.CalcService;
import logic.service.CalculationService;
import logic.service.SearchService;
import model.ManagerSettings;
import view.View;
import view.reader.ConsoleReader;
import view.reader.FileReader;
import view.reader.Reader;
import view.writer.ConsoleWriter;

import java.io.FileNotFoundException;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */

public class Controller {
    //todo Controller need writer?
    private Reader consoleReader;
    private Reader exampleReader;
    private View view;
    private ConsoleWriter consoleWriter;
    private String userAns;

    public Controller() {
        consoleReader = new ConsoleReader();
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
        boolean z=true;
        while (z) {
            view.printMainMenu();
            try {
                userAns = consoleReader.getString();
            } catch (NumberFormatException e) {
                consoleWriter.write("You input incorrect value\n Try again\n");
                continue;
            }
            switch (userAns) {
                case ("0"):
                    view.Buy();
                    z=false;
                    break;
                case ("1"):
                    doCalc();
                    break;
                case ("2"):
                    settingsMenu();
                    break;
                default:
                    view.incorrectInput();
            }
        }
    }

    private void workWithFile() {
        boolean z=true;
        while (z) {
            view.printFileMenu();
            try {
                userAns = consoleReader.getString();
            } catch (NumberFormatException e) {
                consoleWriter.write("You input incorrect value\n Try again\n");
                continue;
            }
            switch (userAns) {
                case "0":
                    view.Buy();
                    break;
                case ("1"):
                    if (exampleReader.canRead()) {
                        doCalc();
                    } else {
                        consoleWriter.write("I read all your file!");
                        view.Buy();

                    }
                    break;
                default:
                    consoleWriter.write("You input incorrect value\n Try again\n");
                    z=false;
                    break;
            }
        }
    }

    private void doCalc() {
        consoleWriter.write("Input you example:\n");
        String temp = exampleReader.getString();
        if (temp.length() == 0) {
            return;
        }
        CalcService calcService = calcServiceFactory(temp);
        consoleWriter.write("Your answer: \n" + calcService.calculate(temp) + "\n");
    }

    private CalcService calcServiceFactory(String s) {

        if (s.contains("findText")) {
            return new SearchService();
        } else {
            return new CalculationService();
        }

    }

    //todo This is Reader Factory?
    private boolean createExampleReader() {
        if ("1".equals(userAns)) {
            exampleReader = new ConsoleReader();
            return true;
        } else if ("2".equals(userAns)) {
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
        } else {
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
                    break;
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
        if (!ManagerSettings.addOverloadOperation(overOper, baseOper)) {
            consoleWriter.write("You input incorrect operation");
        }
    }

    private void removeOper() {
        consoleWriter.write("Input you over Sing");
        ManagerSettings.removeOperation(consoleReader.getString());
    }


}
