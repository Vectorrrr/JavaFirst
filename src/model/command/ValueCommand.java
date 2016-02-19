package model.command;

import java.util.Deque;

/**
 * Created by igladush on 19.02.16.
 */
public class ValueCommand implements Command {



    private Double value;
    public ValueCommand(Double value) {
        this.value = value;
    }
    public Double getValue() {
        return value;
    }

    @Override
    public void apply(Deque<Double> sequence) {
        sequence.push(value);
    }
}
