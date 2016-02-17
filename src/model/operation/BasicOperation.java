package model.operation;

/**
 * Created by igladush on 17.02.16.
 */
public enum BasicOperation  implements Operation {
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
            return x * y;
        }
    },
    DIVIDE("/", 2) {
        @Override
        public double apply(double x, double y) {
            return y / x;
        }
    },
    //todo if change sequence x and y I'll can check in class Calc
    POW("^", 2) {
        @Override
        public double apply(double x, double y) {
            return Math.pow(y, x);
        }
    };


    final String sing;
    final int priory;

    BasicOperation(String sing, int priory) {
        this.sing = sing;
        this.priory = priory;
    }


    public abstract double apply(double x, double y);

    @Override
    public String toString() {
        return this.sing;
    }

    public String getSing() {
        return this.sing;
    }

    public int getPriory() {
        return this.priory;
    }
}
