package logic;

import model.Data;
import model.Settings;
import model.function.BaseBinaryFunction;
import model.function.BaseUnaryFunction;
import model.function.Function;
import model.operation.BasicOperation;
import model.operation.Bracket;
import model.operation.Sing;

import java.util.*;

/**
 * Created by CraZy_IVAN on 16.02.16.
 */
public class CalculationService {


    private static List<Data> mainSequence = new ArrayList<Data>();

    //temp deque for operation
    private static Deque<Sing> temSingDeque = new ArrayDeque<>();

    //temp value deque
    private static Deque<Double> tempValueDeque = new ArrayDeque<>();

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

    private String threadString;
    private int stringPosition = 0;


    public double calculate(String s) {
        stringPosition = 0;
        threadString = s;
        Settings.normalaizeString(threadString);
        if (!createStacks()) {
            throw new IllegalArgumentException("\n You input incorrect sequence!!!");
        }

        for (Data data : mainSequence) {
            if (data.isOperand()) {
                tempValueDeque.push(data.getValue());
            } else {
                if (checkWeCanDoOperations()) {
                    Double op1 = tempValueDeque.peek();
                    tempValueDeque.pop();
                    Double op2 = tempValueDeque.peek();
                    tempValueDeque.pop();
                    doOperation(op1, op2, data.getSing());
                }
            }
        }
        return tempValueDeque.peek();
    }

    private boolean checkWeCanDoOperations() {
        if (tempValueDeque.size() < 2) {
            throw new IllegalArgumentException("\n You input incorrect sequence!!!");
        }
        return true;
    }

    private void doOperation(Double op1, Double op2, String sing) {
        for (BasicOperation b : BasicOperation.values()) {
            if (b.getSing().equals(sing)) {
                tempValueDeque.push(b.apply(op1, op2));
            }
        }
    }

    //flase if incorrect String
    //true if correct
    private boolean createStacks() {
        clearStack();
        for (; stringPosition < threadString.length(); ++stringPosition) {

            String val = new Character(threadString.charAt(stringPosition)).toString();

            if (" ".equals(val)) {
                continue;
            }

            if (Character.isDigit(threadString.charAt(stringPosition))) {
                //todo How much cost a transfer string?
                getNextNumber();
            } else if (Character.isAlphabetic(threadString.charAt(stringPosition))) {
                doOperation();
            } else {

                if (flagNumber) {
                    mainSequence.add(new Data(tempValue));
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
            mainSequence.add(new Data(tempValue));
            resetFlagNumber();
        }
        while (temSingDeque.size() > 0) {
            if ("(".equals(temSingDeque.peek().getSing())) {
                System.out.println("You input invalid data!!!");
                return false;
            }
            mainSequence.add(new Data(temSingDeque.peek()));
            temSingDeque.pop();

        }
        return true;
    }

    private void resetFlagNumber() {
        flagNumber = false;
        flagDot = false;
        countAfterDot = 0;
        countSing = 0;
        mulAfterDot = 0.1;
        tempValue = 0;
    }

    private void getNextNumber() {
        tempValue = 0;
        while (stringPosition < threadString.length() && (threadString.charAt(stringPosition) == '.'
                || Character.isDigit(threadString.charAt(stringPosition)))) {
            if (threadString.charAt(stringPosition) == '.') {
                flagDot = true;
                stringPosition++;
                continue;
            }
            flagNumber = true;
            if (flagDot) {
                if (++countAfterDot > Settings.MAX_LENGTH_AFTER_DOT) {
                    throw new IllegalStateException("You whant a lot of sing after dot!!!");
                }
                tempValue += (Double.valueOf(threadString.charAt(stringPosition)) - 48) * mulAfterDot;
                mulAfterDot /= 10;

            } else {
                if (++countSing > Settings.MAX_SING) {
                    throw new IllegalStateException("You whant a lot of sing!!!");
                }
                tempValue = tempValue * 10 + (Double.valueOf(threadString.charAt(stringPosition)) - 48);
            }
            stringPosition++;
        }

        --stringPosition;
    }

    private void doOperation() {
        if (threadString.length() - stringPosition < 4) {
            throw new IllegalStateException("You input incorrect string!!!");
        }
        String oper = threadString.substring(stringPosition, stringPosition + 3).toLowerCase();
        for (BaseBinaryFunction basFun : BaseBinaryFunction.values()) {
            if (basFun.getFunction().equals(oper)) {
                doBinaryOperation(basFun);
                return;
            }
        }
        for (BaseUnaryFunction unarFun : BaseUnaryFunction.values()) {
            if (unarFun.getFunction().equals(oper)) {
                doUnaryOperation(unarFun);
                return;
            }
        }
        throw new IllegalStateException("You input incorrect operation!!!");
    }

    private void doUnaryOperation(BaseUnaryFunction fun) {
        stringPosition += 3;
        findFirstOpenBrakets();
        findNextNumber();
        getNextNumber();
        double x = tempValue;
        resetFlagNumber();
        stringPosition++;
        tempValue = fun.apply(x);
        mainSequence.add(new Data(tempValue));

        findFirstClosedBrakets();
    }

    private void doBinaryOperation(BaseBinaryFunction basFun) {
        stringPosition += 3;

        findFirstOpenBrakets();
        findNextNumber();
        getNextNumber();
        double x = tempValue;
        resetFlagNumber();
        stringPosition++;

        findNextNumber();
        getNextNumber();
        double y = tempValue;
        resetFlagNumber();
        tempValue = basFun.apply(x, y);
        mainSequence.add(new Data(tempValue));
        resetFlagNumber();

        findFirstClosedBrakets();

    }

    private void findFirstOpenBrakets() {
        while (threadString.charAt(stringPosition++) != '(' && threadString.length() > stringPosition) {
        }
        if (threadString.length() <= stringPosition) {
            throw new IllegalStateException("You input incorrect opperation");
        }
    }

    private void findFirstClosedBrakets() {
        while (true) {
            if (threadString.length() == stringPosition) {
                throw new IllegalStateException("You input incorrect opperation");
            }
            if (threadString.charAt(stringPosition) == ')') {
                return;
            }

            stringPosition++;
        }
    }

    private void findNextNumber() {
        while (!Character.isDigit(threadString.charAt(stringPosition)) &&
                threadString.length() > stringPosition) {
            stringPosition++;
        }
        if (threadString.length() <= stringPosition) {
            throw new IllegalStateException("You input incorrect opperation");
        }
    }

    private void addOperation(String val) {
        BasicOperation oper = getBasicOperation(val);
        while (temSingDeque.size() > 0 &&
                temSingDeque.peek().getPriory() >= oper.getPriory() &&
                temSingDeque.peek().getSing() != "(") {
            mainSequence.add(new Data(temSingDeque.peek()));
            temSingDeque.pop();

        }
        temSingDeque.push(oper);

    }

    private void addBracket(String val) {
        if ("(".equals(val)) {
            //todo May be this is not right?
            temSingDeque.push(Bracket.OPENBRACKET);

        } else if (")".equals(val)) {

            while (temSingDeque.size() != 0 &&
                    temSingDeque.peek().getSing() != "(") {
                mainSequence.add(new Data(temSingDeque.peek()));
                temSingDeque.pop();
            }

            if (temSingDeque.size() == 0 ||
                    temSingDeque.peek().getSing() != "(") {
                System.out.println("Stack clear but you put ) Try again");
                throw new IllegalArgumentException("You input incorrect operation");
            }
            temSingDeque.pop();

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
        temSingDeque.clear();
        tempValueDeque.clear();
    }

}
