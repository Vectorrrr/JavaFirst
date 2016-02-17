package model;

import model.operation.OverloadOperation;
import model.operation.Sing;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by igladush on 16.02.16.
 */

//todo Do I need this class?
public class Sequnce {

    //todo How correct give name?
    public static ArrayList<Data> mainSequence = new ArrayList<Data>();

    //temp stack for operation
    //todo May be make get set for this stack?
    public static Stack<Sing> tempSingStack = new Stack<Sing>();

    //temp value Stack
    public static Stack<Double> tempValueStack = new Stack<Double>();

    //todo where save overloading operation if save in Settings I violate singl responsibility
    public static ArrayList<OverloadOperation> overloadOperations=new ArrayList<OverloadOperation>();



}
