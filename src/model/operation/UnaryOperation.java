package model.operation;

import java.util.Deque;

/**
 * Created by CraZy_IVAN on 20.02.16.
 */
public enum UnaryOperation implements Sing, Command {
    PLUS("+") {
        @Override
        public double applay(double a) {
            return a;
        }
    },
    MINUS("-") {
        @Override
        public double applay(double a) {
            return a;
        }
    };

    private String sing;

    UnaryOperation(String sing) {
        this.sing = sing;
    }

    @Override
    public String getSing() {
        return sing;
    }

    @Override
    public int getPriory() {
        return MAX_PRIORY;
    }

    public abstract double applay(double a);

    @Override
    public void apply(Deque<Double> sequence) {
        if (sequence.size() == 0) {
            throw new IllegalArgumentException("You want do unary operation,but you don't have operands");
        }
        double x = sequence.peek();
        sequence.pop();
        sequence.push(applay(x));
    }
}
