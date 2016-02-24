package model.operation;

import java.util.Deque;

/**
 * Created by igladush on 19.02.16.
 * Class created for add calue in mainSequnce
 * @see logic.service.CalcService
 * @author Gladush Ivan
 *
 */
public class ValueCommand<T extends Number> implements Command {


    private double value;

    public ValueCommand(double value) {
        this.value = value;
    }

    public ValueCommand(int value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void apply(Deque<Double> sequence) {
        sequence.push(value);
    }
}
