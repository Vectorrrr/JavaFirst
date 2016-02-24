package model.operation;

/**
 * Created by igladush on 17.02.16.
 * Enum implements Sing and contains bracket that
 * compatible in program
 * @see Sing
 * @author Gladush Ivan
 */
public enum Bracket implements Sing {
    OPENBRACKET("(", MAX_PRIORY), CLOSEBRACKET(")", MIN_PRIORY);

    private final String sing;
    private final int priory;

    Bracket(String sing, int priory) {
        this.sing = sing;
        this.priory = priory;
    }

    public int getPriory() {
        return this.priory;
    }

    public String getSing() {
        return this.sing;
    }

}

