package logic;

import model.Settings;
import model.operation.BasicOperation;
import model.Data;

import model.operation.Bracket;
import model.operation.Sing;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by CraZy_IVAN on 16.02.16.
 */
public class Calc {


    private static List<Data> mainSequence = new ArrayList<Data>();

    //temp stack for operation
    private static Stack<Sing> tempSingStack = new Stack<Sing>();

    //temp value Stack
    private static Stack<Double> tempValueStack = new Stack<Double>();

    //show the last time we read Number
    private boolean flagNumber = false;

    //show we read dot,or not
    private boolean flagDot = false;
    //show we read operation last or not
    private boolean flagOper = false;

    private double tempValue = 0;

    private int countSing = 0;
    //May be make one place for this
    private int countAfterDot = 0;
    private double mulAfterDot = 0.1;


    public double getAnswer(String s) {
        if (!createStacks(Settings.normalaizeString(s))) {
            throw new IllegalArgumentException("\n You input incorrect sequence!!!");
        }
        for (Data data : mainSequence) {
            if (data.isOperand()) {
                tempValueStack.push(data.getValue());
            } else {
                if (checkWeCanDoOperations()) {
                    Double op1 = tempValueStack.peek();
                    tempValueStack.pop();
                    Double op2 = tempValueStack.peek();
                    tempValueStack.pop();
                    doOperation(op1, op2, data.getSing());
                }
            }
        }
        return tempValueStack.peek();
    }

    private boolean checkWeCanDoOperations() {
        if (tempValueStack.size() < 2) {
            throw new IllegalArgumentException("\n You input incorrect sequence!!!");
        }
        return true;
    }


    private void doOperation(Double op1, Double op2, String sing) {
        for (BasicOperation b : BasicOperation.values()) {
            if (b.getSing().equals(sing)) {
                tempValueStack.push(b.apply(op1, op2));
            }
        }
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
                if (isBracket(val)) {
                    addBracket(val);
                    continue;
                } else if (!isOperation(val)) {
                    System.out.println("You input incorrect operation!!!");
                    return false;
                }
                addOperation(val);
            }
        }

        if (flagNumber) {
            resetFlagNumber();
        }
        while (tempSingStack.size() > 0) {
            if ("(".equals(tempSingStack.peek().getSing())) {
                System.out.println("You input invalid data!!!");
                return false;
            }
            mainSequence.add(new Data(tempSingStack.peek()));
            tempSingStack.pop();

        }
        return true;
    }

    private void resetFlagNumber() {
        flagNumber = false;
        mainSequence.add(new Data(tempValue));
        flagDot = false;
        countAfterDot = 0;
        countSing = 0;
        mulAfterDot = 0.1;
        tempValue = 0;
    }

    private void addOperation(String val) {
        BasicOperation oper = getBasicOperation(val);
        while (tempSingStack.size() > 0 &&
                tempSingStack.peek().getPriory() >= oper.getPriory() &&
                tempSingStack.peek().getSing() != "(") {
            mainSequence.add(new Data(tempSingStack.peek()));
            tempSingStack.pop();

        }
        tempSingStack.push(oper);

    }

    private void addBracket(String val) {
        if ("(".equals(val)) {
            //todo May be this is not right?
            tempSingStack.push(Bracket.OPENBRACKET);

        } else if (")".equals(val)) {

            while (tempSingStack.size() != 0 &&
                    tempSingStack.peek().getSing() != "(") {
                mainSequence.add(new Data(tempSingStack.peek()));
                tempSingStack.pop();
            }

            if (tempSingStack.size() == 0 ||
                    tempSingStack.peek().getSing() != "(") {
                System.out.println("Stack clear but you put ) Try again");
                throw new IllegalArgumentException("You input incorrect operation");
            }
            tempSingStack.pop();

        }

    }

    //check is operation or not
    private boolean isOperation(String oper) {
        for (BasicOperation data : BasicOperation.values()) {
            if (data.getSing().equals(oper)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBracket(String oper) {
        for (Bracket b : Bracket.values()) {
            if (b.getSing().equals(oper)) {
                return true;
            }
        }
        return false;
    }

    //return defualtSing or string empty
    private BasicOperation getBasicOperation(String val) {
        for (BasicOperation b : BasicOperation.values()) {
            if (b.getSing().equals(val)) {
                return b;
            }
        }

        throw new IllegalArgumentException("You input incorrect operation");
    }

    private void clearStack() {
        mainSequence.clear();
        tempSingStack.clear();
        tempValueStack.clear();
    }

}
