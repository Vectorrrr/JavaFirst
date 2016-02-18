package test;

import logic.CalculationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.IllegalFormatException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by igladush on 16.02.16.
 */
public class Tester {
    final double ACCURACY = 0.00000001;
    private CalculationService calSer;

    @Before
    public void init() {
        calSer = new CalculationService();
    }


    @Test
    public void calls() {


        assertEquals(2.2, calSer.calculate("1.1+      0000001.1"), ACCURACY);
        assertEquals(2.2, calSer.calculate("1.1   + 1.1"), ACCURACY);
        assertEquals(81, calSer.calculate("9^   2.0"), ACCURACY);
        assertEquals(0, calSer.calculate("1.1   -1.1"), ACCURACY);
        assertEquals(11, calSer.calculate("1.1 *10"), ACCURACY);
        assertEquals(11, calSer.calculate("121/11"), ACCURACY);
        assertEquals(0, calSer.calculate("(1.1   + 1.1-2)-   0.2"), ACCURACY);
        assertEquals(0, calSer.calculate("(23)-23"), ACCURACY);
        assertEquals(0, calSer.calculate("(((23)-23.0))"), ACCURACY);
        assertEquals(0, calSer.calculate("(((23)-23.000))"), ACCURACY);

        assertEquals(2, calSer.calculate("(23 -13.5-0.5+0.2*5)/5"), ACCURACY);
        assertEquals(76.3, calSer.calculate("(17.3     + 18)-   15+14  *2^2"), ACCURACY);
        assertEquals(28.638571428571428, calSer.calculate("(15.22*2-  17.43)+54/7*2+0.2"), ACCURACY);
        assertEquals(7, calSer.calculate("(1-2.2-1.2+2.4)^5+7"), ACCURACY);
        assertEquals(200.608358581, calSer.calculate("27^2/9*15.3^0.3+17"), ACCURACY);

        assertEquals(2, calSer.calculate("inc(1)"), ACCURACY);
        assertEquals(11, calSer.calculate("mUL( 2.00  5.5  )"), ACCURACY);
        assertEquals(0, calSer.calculate("DEC   (   1    )"), ACCURACY);
        assertEquals(1, calSer.calculate("DIV (15/15)"), ACCURACY);

        assertEquals(16.3, calSer.calculate("iNC(15.3   )"), ACCURACY);
        assertEquals(16.333, calSer.calculate("DeC(    17.3330  )"), ACCURACY);

        assertEquals(-1, calSer.calculate("((((1-2))*5))/5"), ACCURACY);
        assertEquals(0, calSer.calculate("(((((1-2))*5))/5)^2-1"), ACCURACY);
        assertEquals(6, calSer.calculate("((((1.2-2.2+7))*5))/5-0."), ACCURACY);
        assertEquals(7.5, calSer.calculate("(DIV(18.3/6.1) + 3^2*2/4)"), ACCURACY);

        assertEquals(38, calSer.calculate("(MuL(12 3)+DIV(18/6) + ((inc(15.5)/dec(17.5))^5))-SuM(1 1)"), ACCURACY);
    }

    @Test(expected = IllegalStateException.class)
    public void ExceptionTest(){
        calSer.calculate("1.2222222");
        calSer.calculate("(121.");
        calSer.calculate("2342 +22----22");
        calSer.calculate("( (222+ 22)");
        calSer.calculate("13-234.43)");
        calSer.calculate("1234567890987654321+2");
        calSer.calculate("(divv(17+2))");
        calSer.calculate("113");
        calSer.calculate("asd-123");
        calSer.calculate("(-(1-2))");
        calSer.calculate("15-12-4-");
        calSer.calculate("27^div(2 2)");


    }

    @After
    public void tearDown() {
        calSer = null;
    }


}
