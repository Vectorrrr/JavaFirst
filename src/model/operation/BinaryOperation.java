package model.operation;

import java.util.Deque;

/**
 * Created by igladush on 17.02.16.
 * Enum implements Operation and contains base Binary operation
 * @see Operation
 * @author Gladush Ivan
 *
 */
public enum BinaryOperation implements Operation {
    PLUS("+", 1) {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-", 1) {
        @Override
        public double apply(double x, double y) {
            return y - x;
        }
    },
    MULTIPLY("*", 2) {
        @Override
        public double apply(double x, double y) {
            return y * x;
        }
    },
    DIVIDE("/", 2) {
        @Override
        public double apply(double x, double y) {
            return y / x;
        }
    },
    //todo if change sequence x and y I'll can check in class Calc
    POW("^", 3) {
        @Override
        public double apply(double x, double y) {
            return Math.pow(y, x);
        }
    };


    private final String sing;
    private final int priory;

    BinaryOperation(String sing, int priory) {
        this.sing = sing;
        this.priory = priory;
    }

    public String getSing() {
        return this.sing;
    }

    public int getPriory() {
        return this.priory;
    }

    public abstract double apply(double x, double y);

    @Override
    public String toString() {
        return this.sing;
    }

    @Override
    public void apply(Deque<Double> sequence) {
        if (sequence.size() < 2) {
            throw new IllegalArgumentException("You want do binary operation, but you don't have two operands!!!");
        }
        double first = sequence.peek();
        sequence.pop();
        double second = sequence.peek();
        sequence.pop();
        sequence.push(apply(first, second));
    }

}
