/**
 * Created by CraZy_IVAN on 15.02.16.
 */
public class Priory {
    private String sign;
    private int priory;
    private String  mathSing;

    public Priory(String sign,int priory, String mathSing){
        this.sign=sign;
        this.priory=priory;
        this.mathSing=mathSing;
    }

    public String getSign(){
        return this.sign;
    }

    public String getMathSing(){
        return this.mathSing;
    }

    public int getPriory(){
        return this.priory;
    }
}
