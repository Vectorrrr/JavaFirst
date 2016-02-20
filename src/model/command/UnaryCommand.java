package model.command;

import model.operation.UnaryOperation;
import java.util.Deque;

/**
 * Created by CraZy_IVAN on 20.02.16.
 */
public class UnaryCommand implements Command{

    private UnaryOperation unaryOperation;

    public UnaryCommand(UnaryOperation unaryOperation) {
        this.unaryOperation = unaryOperation;
    }

    public UnaryOperation getUnaryOperation() {
        return unaryOperation;
    }


    @Override
    public void apply(Deque<Double> sequence) {
        if(sequence.size()==0){
            throw new IllegalArgumentException("You want do unary operation but you don't have opearnds!!!");
        }
        Double x=sequence.peek();
        sequence.pop();
        sequence.push(unaryOperation.operation(x));
    }
}
