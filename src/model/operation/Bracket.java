package model.operation;

/**
 * Created by igladush on 17.02.16.
 */
public enum Bracket implements Sing {
    OPENBRACKET("(", MAX_PRIORY), CLOSEBRACKET(")",MIN_PRIORY);

    private String sing;
    private int priory;

    Bracket(String sing,int priory) {
        this.sing = sing;
        this.priory=priory;
    }
    public int getPriory(){return this.priory;}
    public String getSing() {
        return this.sing;
    }

}

