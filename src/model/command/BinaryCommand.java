package model.command;

import model.operation.BinaryOperation;

import java.util.Deque;

/**
 * Created by igladush on 19.02.16.
 */
public class BinaryCommand implements Command {


    BinaryOperation binaryOperation;
    public BinaryCommand(BinaryOperation binaryOperation) {
        this.binaryOperation = binaryOperation;
    }

    public BinaryOperation getBasicOperation() {
        return binaryOperation;
    }
    @Override
    public void apply(Deque<Double> sequence) {
        if(sequence.size()<2){
            throw  new IllegalStateException("You want do operation, but have one operand");
        }
        Double x =sequence.peek();
        sequence.pop();
        Double y=sequence.peek();
        sequence.pop();
        sequence.push(binaryOperation.apply(x,y));

    }
}
