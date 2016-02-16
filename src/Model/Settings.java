package Model;

import Model.Priory;

import java.util.ArrayList;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */

public class Settings {
   public static final int MAX_PRIORY=1000;
  static  public ArrayList<Priory> priories=new ArrayList<Priory>();
    static{
        priories.add(new Priory("(",0,")"));
        priories.add(new Priory("+",1,"+"));
        priories.add(new Priory("-",1,"-"));
        priories.add(new Priory("*",2,"*"));
        priories.add(new Priory("/",2,"/"));
        priories.add(new Priory("^",2,"^"));
        priories.add(new Priory(")",MAX_PRIORY,"+"));

    }
}
