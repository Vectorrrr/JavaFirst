package logic;

import model.operation.BasicOperation;
import model.Data;
import model.Sequnce;

/**
 * Created by CraZy_IVAN on 16.02.16.
 */
public class Calc {
    //todo in the fiture generic
    Parser parser = new Parser();

    public double getAnswer(String s) {
        if (!parser.createStacks(s)) {
            throw new IllegalArgumentException("\n You input incorrect sequence!!!");
        }
        for (Data data : Sequnce.mainSequence) {
            if (data.isOperand()) {
                Sequnce.tempValueStack.push(data.getValue());
            } else {
                if (checkWeCanDoOperations()) {
                    Double op1 = Sequnce.tempValueStack.peek();
                    Sequnce.tempValueStack.pop();
                    Double op2 = Sequnce.tempValueStack.peek();
                    Sequnce.tempValueStack.pop();
                    doOperation(op1, op2, data.getSing());
                }
            }
        }
        return Sequnce.tempValueStack.peek();
    }

    private boolean checkWeCanDoOperations() {
        if (Sequnce.tempValueStack.size() < 2) {
            throw new IllegalArgumentException("\n You input incorrect sequence!!!");
        }
        return true;
    }


    private void doOperation(Double op1, Double op2, String sing) {
        for (BasicOperation b : BasicOperation.values()) {
            if (b.getSing().equals(sing)) {
                Sequnce.tempValueStack.push(b.apply(op1, op2));
            }
        }
    }


}
