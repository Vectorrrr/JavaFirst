package Logic;

import Model.*;
import java.util.Stack;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */
public class Parser {
    private final int MAX_LENGTH_AFTER_DOT = 5;

    // todo Maybe keep it in the other place?
    //todo Проблема, что можно вычислять сразу, когда наткнулся, но это плохо(
    //todo может хранить в отдельном классе?
    static public Stack<Double> operands;
    static public Stack<String> operations;

    private Settings settings;

    public Parser() {
        operands = new Stack<Double>();
        operations = new Stack<String>();
        settings = new Settings();
    }

    //flase if incorrect String
    //true if correct
    public boolean createStacks(String s) {
        clearStack();
        Stack<String> tempStackOper = new Stack<String>();

        boolean flagDot = false;
        boolean flagNumber=false;
        boolean flagUnarOper = false;

        double tempVal = 0;
        //todo how give name variable
        double powAfterDot = 0.1;
        //count digit in Number
        int count = 0;

        for (int i = 0; i < s.length(); ++i) {

            if (s.charAt(i) == ' ') {
                if(flagNumber){
                    powAfterDot = 0.1;
                    operands.push(tempVal);
                    flagNumber=false;
                    tempVal=0;
                }
                tempVal = 0;
                flagDot = false;
                continue;
            }

            if (s.charAt(i) == '.') {
                count = 0;
                flagDot = true;
                continue;
            }

            if (Character.isDigit(s.charAt(i))) {
                flagUnarOper = false;
                flagNumber=true;
                if (flagDot) {
                    if (++count > MAX_LENGTH_AFTER_DOT) {
                        return false;
                    } else {
                        tempVal += Character.getNumericValue(s.charAt(i)) * powAfterDot;
                        powAfterDot /= 10;
                    }
                } else {
                    if (++count >= 19) {
                        return false;
                    } else {
                        tempVal = tempVal * 10 + Character.getNumericValue(s.charAt(i));
                    }
                }
            } else {
                powAfterDot = 0.1;
                if(flagNumber){
                    operands.push(tempVal);
                    flagNumber=false;
                    tempVal=0;
                }
                flagDot=false;


                //todo may be1 one cycle
                if (s.charAt(i) == ')') {
                    if (tempStackOper.size() == 0) {
                        return false;
                    }
                    while (tempStackOper.size() > 0 && tempStackOper.peek() != "(") {
                        operations.push(tempStackOper.peek());
                        tempStackOper.pop();
                    }
                    if (tempStackOper.peek() != "(") {
                        return false;
                    }else{
                        tempStackOper.pop();
                    }
                }
                else if(s.charAt(i)=='('){
                    tempStackOper.push("(");
                }
                else if(s.charAt(i)=='+'){
                    while (tempStackOper.size() > 0 && tempStackOper.peek()!="(" && getPriority(tempStackOper.peek())<getPriority("+") ) {
                        operations.push(tempStackOper.peek());
                        tempStackOper.pop();
                    }
                    tempStackOper.push("+");
                }
                else if(s.charAt(i)=='-'){
                    while (tempStackOper.size() > 0 && getPriority(tempStackOper.peek())<=getPriority("-")) {
                        operations.push(tempStackOper.peek());
                        tempStackOper.pop();
                    }
                    tempStackOper.push("-");
                }
                else if(s.charAt(i)=='*'){
                    while (tempStackOper.size() > 0 && getPriority(tempStackOper.peek())<=getPriority("*")) {
                        operations.push(tempStackOper.peek());
                        tempStackOper.pop();
                    }
                    tempStackOper.push("*");
                }
                else if(s.charAt(i)=='/'){
                    while (tempStackOper.size() > 0 && getPriority(tempStackOper.peek())<=getPriority("/")) {
                        operations.push(tempStackOper.peek());
                        tempStackOper.pop();
                    }
                    tempStackOper.push("/");
                }
                else if(s.charAt(i)=='^'){
                    while (tempStackOper.size() > 0 && getPriority(tempStackOper.peek())<=getPriority("^")) {
                        operations.push(tempStackOper.peek());
                        tempStackOper.pop();
                    }
                    tempStackOper.push("^");
                }
            }
        }
        if(flagNumber){
            operands.push(tempVal);
            flagNumber=false;
            tempVal=0;
        }
        while(tempStackOper.size()!=0){
            if(tempStackOper.peek().equals("(")
                    || tempStackOper.peek().equals(")")){
                return false;
            }
            operations.push(tempStackOper.peek());
            tempStackOper.pop();
        }
        return true;
    }

    private int getPriority(String oper) {
        for (Priory t : Settings.priories){
            if(t.getSign().equals(oper)){
                return t.getPriory();
            }
        }
        return -1;
    }

    private String getSign(String oper){
        for (Priory t : Settings.priories){
            if(t.getSign().equals(oper)){
                return t.getMathSing();
            }
        }
        return new String();
    }

    private void clearStack() {
        operands.clear();
        operations.clear();
    }
}
