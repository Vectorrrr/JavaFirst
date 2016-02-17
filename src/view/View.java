package view;
//todo import all classes or one by one
import view.writer.*;


/**
 * Created by CraZy_IVAN on 15.02.16.
 */
public class View {
    //menu always print in console
    private ConsoleWriter consoleWriter = new ConsoleWriter();
    private Writer answerWriter;

    public View(){
        answerWriter=new ConsoleWriter();
    }

    public void printMainMenu() {
        //todo  If necessary or I can type System.out....
        consoleWriter.write("If you whant calc input 1");
        consoleWriter.write("If you whant see the settings inpute 2");
        consoleWriter.write("If you whant exit input 0");

    }

    public  void printSettings() {
        consoleWriter.write("\n\n This operration not available\n\n");
    }

    public  void printChangeSettingsMenu() {
        consoleWriter.write("\n\n This operration not available\n\n");

    }

    public  void Buy() {
        consoleWriter.write("Good luck, bro!)");
    }

    public  void incorrectInput() {
        consoleWriter.write("You input incorrect value. \n" +
                "Type in the range from 0 to 3");

    }
}

