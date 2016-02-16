/**
 * Created by CraZy_IVAN on 16.02.16.
 */
public class Calc {
    public double getAnswer(){
        while(Parser.operations.size()!=0){
            if(checkWeCanDoOperations()==false){
                System.out.println("I can't do opperation");
                throw new Error();
            }
            String oper=Parser.operations.peek();
            if(oper=="+"){
                doAdd();
            }
            else if(oper=="-"){
                doSub();
            }
            else if(oper=="*"){
                doMul();
            }
            else if(oper=="/"){
                doDiv();
            }
            else if(oper=="^"){
                doPow();
            }

            Parser.operations.pop();
        }
        return Parser.operands.peek();
    }

    private boolean checkWeCanDoOperations(){
        if(Parser.operands.size()<2){
            return false;
        }
        return true;
    }
    //todo Maybe give method to operands ?
    //todo  в методы добавить проверку возможна ли эта операция
    //+
    private void doAdd(){
        double op1=Parser.operands.peek();
        Parser.operands.pop();
        double op2=Parser.operands.peek();
        Parser.operands.pop();
        Parser.operands.push(op1+op2);

    }

    //-
    private void doSub(){
        double op1=Parser.operands.peek();
        Parser.operands.pop();
        double op2=Parser.operands.peek();
        Parser.operands.pop();
        Parser.operands.push(op1-op2);
    }

    //*
    private void doMul(){
        double op1=Parser.operands.peek();
        Parser.operands.pop();
        double op2=Parser.operands.peek();
        Parser.operands.pop();
        Parser.operands.push(op1*op2);
    }
    // /
    private void doDiv(){
        double op1=Parser.operands.peek();
        Parser.operands.pop();
        double op2=Parser.operands.peek();
        Parser.operands.pop();
        Parser.operands.push(op1/op2);
    }

    // ^
    private void doPow(){
        double op1=Parser.operands.peek();
        Parser.operands.pop();
        double op2=Parser.operands.peek();
        Parser.operands.pop();
        Parser.operands.push(Math.pow(op1,op2));
    }
}
