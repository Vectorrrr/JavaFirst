package model.function;

/**
 * Created by igladush on 18.02.16.
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

    private String function;

    BaseBinaryFunction(String function) {
        this.function = function;
    }

    @Override
    public String getFunction() {

        return this.function;
    }

    public abstract double apply(double x, double y);
}
