package model.operation;

/**
 * Created by CraZy_IVAN on 20.02.16.
 */
public enum UnaryOperation implements Sing {
    PLUS("+"){
        @Override
        public double operation(double a) {
            return a;
        }
    },
    MINUS("-"){
        @Override
        public double operation(double a) {
            return -a;
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
    public abstract double operation(double a);
}
