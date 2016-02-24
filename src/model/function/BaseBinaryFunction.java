package model.function;

/**
 * Created by igladush on 18.02.16.
 * Enum implements Function and determines binary function in the
 * program.
 * @author Gladush Ivan
 * @see Function
 * @see BaseUnaryFunction
 */
public enum BaseBinaryFunction implements Function {
    SUM("sum") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MIN("min") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    MUL("mul") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIV("div") {
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String function;

    BaseBinaryFunction(String function) {
        this.function = function;
    }

    @Override
    public String getFunction() {

        return this.function;
    }
    /**
     * Get two arguments  and return result of execution function
     * @param x first operand
     * @param y second operand
     * @return execution function of two operands
     * */
    public abstract double apply(double x, double y);
}
