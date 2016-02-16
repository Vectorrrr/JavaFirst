package Model;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by igladush on 16.02.16.
 */

//todo Do I need this class?
public class Sequnce {

    //todo How correct give name?
    public static ArrayList<Data> mainSequence=new ArrayList<Data>();

    //temp stack for operation
    //todo May be make get set for this stack?
    public static Stack<Operation> tempOperStack=new Stack<Operation>();

    //temp value Stack
    public static Stack<Double> tempValueStack=new Stack<Double>();

    //stack for all operation
    public static ArrayList<Operation> allOperation=new ArrayList<Operation>();

    //initialization
    static{
        allOperation.add(new Operation("(",0,"("));
        allOperation.add(new Operation("+",1,"+"));
        allOperation.add(new Operation("-",1,"-"));
        allOperation.add(new Operation("*",2,"*"));
        allOperation.add(new Operation("/",2,"/"));
        allOperation.add(new Operation("^",2,"^"));
        allOperation.add(new Operation(")",Operation.MAX_PRIORITY,")"));
    }



}
