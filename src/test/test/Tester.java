package test;

import logic.service.CalculationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by igladush on 16.02.16.
 */
public class Tester {
    private final double ACCURACY = 0.00000001;
    private CalculationService calSer;

    @Before
    public void init() {
        calSer = new CalculationService();
    }


    @Test
    public void binaryOperationTest() {
        assertEquals(2.2, Double.valueOf(calSer.calculate("1.1+      0000001.1")), ACCURACY);
        assertEquals(2.2, Double.valueOf(calSer.calculate("1.1   + 1.1")), ACCURACY);
        assertEquals(81, Double.valueOf(calSer.calculate("9^   2.0")), ACCURACY);
        assertEquals(0, Double.valueOf(calSer.calculate("1.1   -1.1")), ACCURACY);
        assertEquals(11, Double.valueOf(calSer.calculate("1.1 *10")), ACCURACY);
        assertEquals(11, Double.valueOf(calSer.calculate("121/11")), ACCURACY);
        assertEquals(0, Double.valueOf(calSer.calculate("(1.1   + 1.1-2)-   0.2")), ACCURACY);
        assertEquals(0, Double.valueOf(calSer.calculate("(23)-23")), ACCURACY);
        assertEquals(0, Double.valueOf(calSer.calculate("(((23)-23.0))")), ACCURACY);
        assertEquals(0, Double.valueOf(calSer.calculate("(((23)-23.000))")), ACCURACY);
        assertEquals(2, Double.valueOf(calSer.calculate("(23 -13.5-0.5+0.2*5)/5")), ACCURACY);
        assertEquals(76.3, Double.valueOf(calSer.calculate("(17.3     + 18)-   15+14  *2^2")), ACCURACY);
        assertEquals(28.638571428571428, Double.valueOf(calSer.calculate("(15.22*2-  17.43)+54/7*2+0.2")), ACCURACY);
        assertEquals(7, Double.valueOf(calSer.calculate("(1-2.2-1.2+2.4)^5+7")), ACCURACY);
        assertEquals(200.608358581, Double.valueOf(calSer.calculate("27^2/9*15.3^0.3+17")), ACCURACY);
        assertEquals(-1,  Double.valueOf(calSer.calculate("((((1-2))*5))/5")), ACCURACY);
        assertEquals(0, Double.valueOf(calSer.calculate("(((((1-2))*5))/5)^2-1")), ACCURACY);
        assertEquals(6, Double.valueOf(calSer.calculate("((((1.2-2.2+7))*5))/5-0.")), ACCURACY);

    }
    @Test
    public void unaryOperationTest(){
        assertEquals(2, Double.valueOf(calSer.calculate("--2")), ACCURACY);
        assertEquals(2, Double.valueOf(calSer.calculate("-(- 2)")), ACCURACY);
//        assertEquals(2, Double.valueOf(calSer.calculate("-2--2")), ACCURACY);
    }
    @Test
    public void baseUnaryFunctionTest(){
        assertEquals(2, Double.valueOf(calSer.calculate("inc(1)")), ACCURACY);
        assertEquals(16.3, Double.valueOf(calSer.calculate("iNC(15.3   )")), ACCURACY);
        assertEquals(16.333, Double.valueOf(calSer.calculate("DeC(    17.3330  )")), ACCURACY);
        assertEquals(0, Double.valueOf(calSer.calculate("DEC   (   1    )")), ACCURACY);
    }

    @Test
    public void baseBinaryFunctionTest(){
        assertEquals(11, Double.valueOf(calSer.calculate("mUL( 2.00  5.5  )")), ACCURACY);
        assertEquals(1, Double.valueOf(calSer.calculate("DIV (15/15)")), ACCURACY);
    }

    @Test
    public void hybridExcerciseTest(){
        assertEquals(7.5,Double.valueOf( calSer.calculate("(DIV(18.3/6.1) + 3^2*2/4)")), ACCURACY);
        assertEquals(38, Double.valueOf(calSer.calculate("(MuL(12 3)+DIV(18/6) + ((inc(15.5)/dec(17.5))^5))-SuM(1 1)")), ACCURACY);
    }

    @Test(expected = IllegalStateException.class)
    public void accuracyExceptionTest0(){
        calSer.calculate("1.2222222");

    }
    @Test(expected = IllegalStateException.class)
    public void accuracyExceptionTest2(){
        calSer.calculate("1.0234521");

    }
    public void maxLengthInputTest(){
        calSer.calculate("1234567890987654321+2");
    }
    @Test(expected = IllegalArgumentException.class)
    public void incorrectInputTest0(){
        calSer.calculate("(121.");
    }


    @Test(expected = IllegalArgumentException.class)
    public void incorrectInputTest3(){
        calSer.calculate("13-234.43)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void incorrectInputTest4(){
        calSer.calculate("( (222+ 22)");
    }

    @Test(expected = IllegalStateException.class)
    public void incorrectInputTest5(){
        calSer.calculate("asd-123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void incorrectInputTest7(){
        calSer.calculate("15-12-4-");
    }

    @After
    public void tearDown() {
        calSer = null;
    }


}
