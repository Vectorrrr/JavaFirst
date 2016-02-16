package Model;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */
public class Operation {
   static public int MAX_PRIORITY=1000;
    private String overrideSing;
    private int priory;
    private String  defaultSing;


    public Operation(String overrideSing, int priory, String defaultSing){
        this.overrideSing = overrideSing;
        this.priory=priory;
        this.defaultSing=defaultSing;
    }

    public String getOverrideSing(){
        return this.overrideSing;
    }

    public String getDefaulSing(){
        return this.defaultSing;
    }

    public int getPriory(){
        return this.priory;
    }
}
