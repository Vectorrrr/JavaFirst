package model.operation;

/**
 * Created by igladush on 17.02.16.
 */
public class OverloadOperation {
    private String overloadSing;
    private String defualtSing;

    public OverloadOperation(String overSing, BasicOperation defSing){
        overloadSing=overSing;
        defualtSing=defSing.getSing();
    }
    public String getOverloadSing(){
        return this.overloadSing;
    }
    public String getDefualtSing(){
        return this.defualtSing;
    }

}
