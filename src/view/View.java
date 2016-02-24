package view;
//todo import all classes or one by one

import model.ManagerSettings;
import model.operation.BinaryOperation;
import view.writer.ConsoleWriter;
import view.writer.Writer;


/**
 * Created by CraZy_IVAN on 15.02.16.
 * Method created for show user interface in programm
 *
 * @see ConsoleWriter
 * @see ConsoleReader
 * @author Gladush Ivan
 */
public class View {
    //menu always print in console
    private ConsoleWriter consoleWriter = new ConsoleWriter();
    private Writer answerWriter;

    public View() {
        answerWriter = new ConsoleWriter();
    }

    public void printMainMenu() {
        //todo  If necessary or I can type System.out....
        consoleWriter.write("If you want calc input 1");
        consoleWriter.write("If you want see the settings inpute 2");
        consoleWriter.write("If you want exit input 0");

    }
    public void printFileMenu(){
        consoleWriter.write("If you want calc next example input 1");
        consoleWriter.write("If you want exit input 0");
    }
    public void printSettings() {
        consoleWriter.write("If you want see the all overloading operation type 1");
        consoleWriter.write("If you want overloading operation type 2 ");
        consoleWriter.write("If you want delete overloading operation type 3");
        consoleWriter.write("If you want return to main menu type 0");
    }

    public void printSettingsRead(){
        consoleWriter.write("If you want read from console type 1");
        consoleWriter.write("If you want read from file type 2");
    }
    public void printAllOverOper() {
        consoleWriter.write(ManagerSettings.getAllOverloadOper());
    }

    public void printBaseOper() {
        //todo may be one const?
        StringBuffer sb = new StringBuffer();
        for (BinaryOperation bas : BinaryOperation.values()) {
            sb.append(bas.getSing()).append("\n");

        }
        consoleWriter.write(sb.toString());
    }

    public void Buy() {
        consoleWriter.write("Good luck, bro!)");
    }

    public void incorrectInput() {
        consoleWriter.write("You input incorrect value. \n" +
                "Type in the range from 0 to 3");

    }
}

