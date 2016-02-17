package logic;

import model.*;
import model.operation.BasicOperation;
import model.operation.Bracket;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */
public class Parser {
    //todo May be const I don't know

    private Settings settings;
    //show the last time we read Number
    private  boolean flagNumber = false;

    //show we read dot,or not
    private boolean flagDot = false;
    //show we read operation last or not
     private boolean flagOper = false;

    private double tempValue = 0;

    private int countSing = 0;
    //May be make one place for this
    private int countAfterDot = 0;
    private double mulAfterDot = 0.1;

    public Parser() {

        settings = new Settings();
    }

    //flase if incorrect String
    //true if correct
    public boolean createStacks(String s) {
        clearStack();
        for (int i = 0; i < s.length(); ++i) {
            //todo How better transfor to string
            String val = new Character(s.charAt(i)).toString();

            if (" ".equals(val)) {
                 continue;
            }
            if (".".equals(val)) {
                flagDot = true;
                continue;
            }
            if (Character.isDigit(s.charAt(i))) {
                flagNumber = true;
                if (flagDot) {
                    if (++countAfterDot > Settings.MAX_LENGTH_AFTER_DOT) {
                        System.out.println("You whant a lot of sing after dot!!!");
                        return false;
                    }
                    tempValue += Double.valueOf(val) * mulAfterDot;
                    mulAfterDot /= 10;

                } else {
                    if (++countSing > Settings.MAX_SING) {
                        System.out.println("You whant a lot of sing!!!");
                        return false;
                    }
                    tempValue = tempValue * 10 + Double.valueOf(val);
                }
            } else {
                if (flagNumber) {
                    resetFlagNumber();
                }
                if(isBracket(val)){
                    addBracket(val);
                    continue;
                }
                else if (!isOperation(val)) {
                    System.out.println("You input incorrect operation!!!");
                    return false;
                }
                addOperation(val);
            }


        }
        if (flagNumber) {
            resetFlagNumber();
        }
        while (Sequnce.tempSingStack.size() > 0) {
            if ("(".equals(Sequnce.tempSingStack.peek().getSing())) {
                System.out.println("You input invalid data!!!");
                return false;
            }
            Sequnce.mainSequence.add(new Data(Sequnce.tempSingStack.peek()));
            Sequnce.tempSingStack.pop();

        }
        return true;
    }


    private void resetFlagNumber(){
        flagNumber = false;
        Sequnce.mainSequence.add(new Data(tempValue));
        flagDot = false;
        countAfterDot = 0;
        countSing = 0;
        mulAfterDot = 0.1;
        tempValue = 0;
    }

    private void addOperation(String val) {
        BasicOperation oper=getBasicOperation(val);
        while (Sequnce.tempSingStack.size() > 0 &&
                Sequnce.tempSingStack.peek().getPriory() >= oper.getPriory() &&
                Sequnce.tempSingStack.peek().getSing() != "(") {
            Sequnce.mainSequence.add(new Data(Sequnce.tempSingStack.peek()));
            Sequnce.tempSingStack.pop();

        }
        Sequnce.tempSingStack.push(oper);

    }
    private void addBracket(String val){
        if ("(".equals(val)) {
            //todo May be this is not right?
            Sequnce.tempSingStack.push(Bracket.OPENBRACKET);

        }
        else if ( ")".equals(val)) {

            while (Sequnce.tempSingStack.size() != 0 &&
                    Sequnce.tempSingStack.peek().getSing() !="(") {
                Sequnce.mainSequence.add(new Data(Sequnce.tempSingStack.peek()));
                Sequnce.tempSingStack.pop();
            }

            if (Sequnce.tempSingStack.size() == 0 ||
                    Sequnce.tempSingStack.peek().getSing() != "(") {
                System.out.println("Stack clear but you put ) Try again");
                throw new IllegalArgumentException("You input incorrect operation");
            }
            Sequnce.tempSingStack.pop();

        }

    }
    //check is operation or not
    private boolean isOperation(String oper) {
        for (BasicOperation data : BasicOperation.values()) {
            if(data.getSing().equals(oper)){
                return true;
            }
        }
        return false;
    }

    private boolean isBracket(String oper){
        for(Bracket b:Bracket.values()){
            if(b.getSing().equals(oper)){
                return true;
            }
        }
        return false;
    }


    //return defualtSing or string empty
    private BasicOperation getBasicOperation(String val) {
        for(BasicOperation b: BasicOperation.values()){
            if(b.getSing().equals(val)){
                return b;
            }
        }

        throw new IllegalArgumentException("You input incorrect operation");
    }


    private void clearStack() {
        Sequnce.mainSequence.clear();
        Sequnce.tempSingStack.clear();
        Sequnce.tempValueStack.clear();
    }
}
