import logic.Controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;


/**
 * Created by CraZy_IVAN on 15.02.16.
 */

public class Main {


    public static void main(String[] args) {
        for(String s: args )
            System.out.println(s);
        Controller contrl = new Controller();
        contrl.run();
    }

}

