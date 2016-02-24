package model.operation;

/**
 * Created by igladush on 17.02.16.
 * Interface for all sings which whant to be recognized in
 * input stream in CalculationService
 * @see logic.service.CalcService
 * @author Gladush Ivan
 */
public interface Sing {
    int MAX_PRIORY = 100000;
    int MIN_PRIORY = -100000;

    int getPriory();

    String getSing();


}
