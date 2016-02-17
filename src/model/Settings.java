package model;

import model.operation.BasicOperation;
import model.operation.Bracket;
import model.operation.OverloadOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */

public class Settings {
    public static int MAX_LENGTH_AFTER_DOT = 5;
    public static int MAX_SING = 18;
    public static final int MAX_PRIORY = 1000;
    private static List<OverloadOperation> overloadOperations=new ArrayList<OverloadOperation>();

    public static boolean addOverloadOperation(String overOper, String baseOper){

        if(overOper.contains(" ")){return false;}
        if(overOper.contains("$")){return false;}
        for(OverloadOperation oper: overloadOperations){
            if(oper.getOverloadSing().equals(overOper) &&
                    !oper.getDefualtSing().equals(baseOper)){
                return false;
            }
            if(overOper.contains(oper.getOverloadSing())){
                return false;
            }
        }
        for(BasicOperation oper: BasicOperation.values()){
            if(overOper.contains(oper.getSing())){
                return false;
            }
        }
        for(Bracket bracket: Bracket.values()){
            if(overOper.contains(bracket.getSing())){
                return false;
            }
        }
        overloadOperations.add(new OverloadOperation(overOper,baseOper));
        return true;
    }

    public static void removeOperation(String over){
        for(int i=0;i<overloadOperations.size();++i){
            if(overloadOperations.get(i).getOverloadSing().equals(over)){
                overloadOperations.remove(i);
                return;
            }
        }
    }

    public static String getAllOverloadOper(){
        if(overloadOperations.size()==0){
            return "You don't overload operations\n";
        }
        StringBuilder sb=new StringBuilder("You ovverload this operation:\n Overload\t\t\t Base Operation\n");
        for(OverloadOperation op: overloadOperations){
            sb.append(op.getOverloadSing());
            sb.append("\t\t\t");
            sb.append(op.getDefualtSing());
            sb.append("\n");
        }
        return sb.toString();

    }

    //todo may be change on StringBuffer
    public static String normalaizeString(String s){
        for(OverloadOperation overOper: overloadOperations){
            s=s.replaceAll(overOper.getOverloadSing(),overOper.getDefualtSing());
        }
            return s;
    }

}
