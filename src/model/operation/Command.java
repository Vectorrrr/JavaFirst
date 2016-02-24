package model.operation;

import java.util.Deque;

/**
 * Created by igladush on 19.02.16.
 * Interface that need to implement in class
 * that want to come in mainSequence in CalculationService
 * @see logic.service.CalcService
 * @see ValueCommand
 * @author Gladush Ivan
 */
public interface Command {
    void apply(Deque<Double> sequence);
}
