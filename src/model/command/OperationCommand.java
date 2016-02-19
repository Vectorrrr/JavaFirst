package model.command;

import model.operation.BasicOperation;

import java.util.Deque;

/**
 * Created by igladush on 19.02.16.
 */
public class OperationCommand implements Command {


    BasicOperation basicOperation;
    public OperationCommand(BasicOperation basicOperation) {
        this.basicOperation = basicOperation;
    }

    public BasicOperation getBasicOperation() {
        return basicOperation;
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
        sequence.push(basicOperation.apply(x,y));

    }
}
