package Logic;

import Model.*;
import java.util.Stack;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */
public class Parser {
    //todo May be const I don't know
    public static int MAX_LENGTH_AFTER_DOT = 5;
    public static int MAX_SING = 18;

    private Settings settings;

    public Parser() {

        settings = new Settings();
    }

    //flase if incorrect String
    //true if correct
    public boolean createStacks(String s) {
        clearStack();
        //show the last time we read Number
        boolean flagNumber = false;

        //show we read dot,or not
        boolean flagDot = false;
        //show we read operation last or not
        boolean flagOper = false;

        double tempValue = 0;

        int countSing = 0;
        //May be make one place for this
        int countAfterDot = 0;
        double mulAfterDot = 0.1;
        for (int i = 0; i < s.length(); ++i) {
            //todo How better transfor to string
            String val = new Character(s.charAt(i)).toString();
            val = val.intern();

            if (val == " ") {


                continue;
            }
            if (val == ".") {
                flagDot = true;
                continue;
            }

            if (Character.isDigit(s.charAt(i))) {
                flagNumber = true;
                if (flagDot) {
                    if (++countAfterDot > MAX_LENGTH_AFTER_DOT) {
                        System.out.println("You whant a lot of sing after dot!!!");
                        return false;
                    }
                    tempValue += Double.valueOf(val) * mulAfterDot;
                    mulAfterDot /= 10;

                } else {
                    if (++countSing > MAX_SING) {
                        System.out.println("You whant a lot of sing!!!");
                        return false;
                    }
                    tempValue = tempValue * 10 + Double.valueOf(val);
                }
            } else {
                //todo May be make it function?
                if (flagNumber) {
                    flagNumber = false;
                    Sequnce.mainSequence.add(new Data(tempValue));
                    flagDot = false;
                    countAfterDot = 0;
                    countSing = 0;
                    mulAfterDot = 0.1;
                    tempValue = 0;
                }
                if (!isOperation(val)) {
                    System.out.println("You input incorrect operation!!!");
                    return false;
                }
                val = getDefualtSign(val);
                val=val.intern();

                if (val == "(") {
                    Sequnce.tempOperStack.push(new Operation("(", getPriority(val), getDefualtSign(val)));
                    continue;
                }
                if (val == ")") {

                    while (Sequnce.tempOperStack.size() != 0 &&
                            Sequnce.tempOperStack.peek().getDefaulSing() != "(") {
                        Sequnce.mainSequence.add(new Data(Sequnce.tempOperStack.peek()));
                        Sequnce.tempOperStack.pop();


                    }

                    if (Sequnce.tempOperStack.size() == 0 || Sequnce.tempOperStack.peek().getDefaulSing() != "(") {
                        System.out.println("Stack clear but you put ) Try again");
                        return false;
                    }
                    Sequnce.tempOperStack.pop();
                    continue;
                }
                while (Sequnce.tempOperStack.size() > 0 &&
                        Sequnce.tempOperStack.peek().getPriory() >= getPriority(val) &&
                        Sequnce.tempOperStack.peek().getDefaulSing() != "(") {
                    Sequnce.mainSequence.add(new Data(Sequnce.tempOperStack.peek()));
                    Sequnce.tempOperStack.pop();

                }
                if (val == "+") {
                    //todo переписать что бы находило в нашей последовательности?
                    Sequnce.tempOperStack.push(new Operation("+", 1, "+"));
                    continue;
                }
                if (val == "-") {
                    Sequnce.tempOperStack.push(new Operation("-", 1, "-"));
                    continue;
                }
                if (val == "/") {
                    Sequnce.tempOperStack.push(new Operation("/", 2, "/"));
                    continue;
                }
                if (val == "*") {
                    Sequnce.tempOperStack.push(new Operation("*", 2, "*"));
                    continue;
                }
                if (val == "^") {

                    Sequnce.tempOperStack.push(new Operation("^", 2, "^"));
                    continue;
                }

            }


        }
        if (flagNumber) {
            flagNumber = false;
            Sequnce.mainSequence.add(new Data(tempValue));
            flagDot = false;
            countAfterDot = 0;
            countSing = 0;
            mulAfterDot = 0.1;
            tempValue = 0;
        }
        while (Sequnce.tempOperStack.size() > 0                 ) {
            if(Sequnce.tempOperStack.peek().getDefaulSing() == "("){
                System.out.println("You input invalid data!!!");
                return false;
            }
            Sequnce.mainSequence.add(new Data(Sequnce.tempOperStack.peek()));
            Sequnce.tempOperStack.pop();

        }

        return true;
    }

    //check is operation or not
    private boolean isOperation(String oper) {
        String temp = oper.intern();
        for (Operation data : Sequnce.allOperation) {
            if (data.getOverrideSing() == temp) {
                return true;
            }
        }
        return false;
    }

    private int getPriority(String oper) {
        String temp = oper.intern();
        //todo foreach?
        for (Operation data : Sequnce.allOperation) {
            if (data.getOverrideSing() == temp) {
                return data.getPriory();
            }
        }
        //todo other way
        throw new Error();
    }

    //return defualtSing or string empty
    private String getDefualtSign(String oper) {
        String temp = oper.intern();
        //todo foreach?
        for (Operation data : Sequnce.allOperation) {
            if (data.getOverrideSing() == temp) {
                return data.getDefaulSing();
            }
        }
        //todo other way
        throw new Error();
    }

    private void clearStack() {
        Sequnce.mainSequence.clear();
        Sequnce.tempOperStack.clear();
        Sequnce.tempValueStack.clear();
    }
}
