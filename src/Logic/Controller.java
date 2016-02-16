package Logic;

import Logic.Parser;
import View.Receiver;
import View.*;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */
public class Controller {
    private Receiver receiver;
    private Parser parser;
    private Calc calc;
    //todo maby better write  private Parser  parser=new Parser();
    public Controller() {
        receiver = new Receiver();
        parser=new Parser();
        calc=new Calc();
    }

    public void run() {

        int userAns;
        View.printMainMenu();
        while (true) {

            try {
                userAns = receiver.getInt();
            } catch (NumberFormatException e) {
                Writer.writeInConsole("You input incorrect value\n Try again\n");
                continue;
            }
            if (userAns == 0) {
                View.Buy();
                break;
            }
            else if (userAns == 1) {
                Writer.writeInConsole("Input your example\n");
                receiver.getString();
                if(parser.createStacks(receiver.getString())){
                    Writer.writeInConsole("Your answer: "+ calc.getAnswer()+"\n");
                }else{
                    Writer.writeInConsole("You input incorrect example\n Try again\n");
                    continue;
                }
            }
            else if (userAns == 2) {
                View.printSettings();
            }
            else if (userAns == 3){
                View.printChangeSettingsMenu();
            }
            else{
                View.incorrectInput();
                continue;
            }
                View.printMainMenu();
        }

    }
}
