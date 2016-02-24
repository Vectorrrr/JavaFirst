package model.function;

/**
 * Created by igladush on 18.02.16.
 * Enum implements Function and execute unary function
 * @author Gladush Ivan
 */
public enum BaseUnaryFunction  implements Function {
    INC("inc") {
        @Override
        public double apply(double x) {
            return ++x;
        }
    },
    DEC("dec") {
        public double apply(double x) {
            return --x;
        }
    };

    private final String function;

    BaseUnaryFunction(String function) {
        this.function = function;
    }

    public String getFunction() {
        return this.function;
    }

    public abstract double apply(double x);
}
