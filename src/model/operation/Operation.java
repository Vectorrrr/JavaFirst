package model.operation;

/**
 * Created by igladush on 17.02.16.
 */

    //todo May be all interface in other packet
    //todo think about API!!!
interface Operation extends Sing,Command {
     double apply(double x, double y);
}
