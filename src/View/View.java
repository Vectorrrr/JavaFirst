package View;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */
public class View {
    public static void printMainMenu(){
        //todo  If necessary or I can type System.out....
        Writer.writeInConsole("If you whant calc input 1");
        Writer.writeInConsole("If you whant see the settings inpute 2");
        Writer.writeInConsole( "If you whant change the settings inpute 3");
        Writer.writeInConsole("If you whant exit input 0");

    }

    public static void printSettings(){
        Writer.writeInConsole("\n\n This operration not available\n\n");

    }

    public static void printChangeSettingsMenu(){
        Writer.writeInConsole("\n\n This operration not available\n\n");

    }

    public static void Buy(){
        Writer.writeInConsole("Good luck, bro!)");
    }

    public static void incorrectInput(){
        Writer.writeInConsole("You input incorrect value. \n" +
                "Type in the range from 0 to 3");

    }
}

