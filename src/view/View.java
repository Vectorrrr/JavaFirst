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
        consoleWriter.write("If you want see the all overloading operation type 1");
        consoleWriter.write("If you want overloading operation type 2 ");
        consoleWriter.write("If you want return to main menu type 0");

    }



    public  void Buy() {
        consoleWriter.write("Good luck, bro!)");
    }

    public  void incorrectInput() {
        consoleWriter.write("You input incorrect value. \n" +
                "Type in the range from 0 to 3");

    }
}

