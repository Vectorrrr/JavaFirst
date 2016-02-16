package Model;

import java.util.ArrayList;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */

public class Settings {
   public static final int MAX_PRIORY=1000;
  static  public ArrayList<Operation> priories=new ArrayList<Operation>();
    static{
        priories.add(new Operation("(",0,")"));
        priories.add(new Operation("+",1,"+"));
        priories.add(new Operation("-",1,"-"));
        priories.add(new Operation("*",2,"*"));
        priories.add(new Operation("/",2,"/"));
        priories.add(new Operation("^",2,"^"));
        priories.add(new Operation(")",MAX_PRIORY,"+"));

    }
}
