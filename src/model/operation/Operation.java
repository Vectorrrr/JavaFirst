package model.operation;

/**
 * Created by igladush on 17.02.16.
 */

/**
 * If class want to be operations and calc in CalculationService he need implement this interface
 * @see Sing
 * @see Command
 * @author Gladush Ivan
 * */
interface Operation extends Sing,Command {
     double apply(double x, double y);
}
