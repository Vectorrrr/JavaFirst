package Logic;

import Logic.Parser;
import Model.Data;
import Model.Sequnce;

/**
 * Created by CraZy_IVAN on 16.02.16.
 */
public class Calc {
    //todo in the fiture generic
    public double getAnswer() {
        for (Data data : Sequnce.mainSequence) {
            if (data.isOperand()) {
                Sequnce.tempValueStack.push(data.getValue());
            } else {
                if (checkWeCanDoOperations()) {
                    Double op1 = Sequnce.tempValueStack.peek();
                    Sequnce.tempValueStack.pop();
                    Double op2 = Sequnce.tempValueStack.peek();
                    Sequnce.tempValueStack.pop();
                    doOperation(op1, op2, data.getOperation().getDefaulSing());
                }
            }
        }
        return Sequnce.tempValueStack.peek();
    }

    private boolean checkWeCanDoOperations() {
        if (Sequnce.tempValueStack.size() < 2) {
            System.out.println("You input incorrect data");
            throw new Error();
        }
        return true;
    }


    private void doOperation(Double op1, Double op2, String opp) {
        opp = opp.intern();
        if (opp == "+") {
            doAdd(op1, op2);
        } else if (opp == "-") {
            doSub(op1, op2);
        } else if (opp == "*") {
            doMul(op1, op2);
        } else if (opp == "/") {
            doDiv(op1,op2);
        }else if(opp=="^"){
            doDiv(op1,op2);
        }
    }

    //todo  в методы добавить проверку возможна ли эта операция
    //+
    private void doAdd(Double op1, Double op2) {
        Sequnce.tempValueStack.push(op1 + op2);

    }

    //-
    private void doSub(Double op1, Double op2) {
        Sequnce.tempValueStack.push(op1 - op2);
    }

    //*
    private void doMul(Double op1, Double op2) {
        Sequnce.tempValueStack.push(op1 * op2);
    }

    // /
    private void doDiv(Double op1, Double op2) {
        Sequnce.tempValueStack.push(op1 / op2);
    }

    // ^
    private void doPow(Double op1, Double op2) {
        Sequnce.tempValueStack.push(Math.pow(op1, op2));
    }
}
