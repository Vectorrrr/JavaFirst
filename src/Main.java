import logic.Controller;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import test.Tester;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by CraZy_IVAN on 15.02.16.
 */

public class Main {

    public static void main(String[] args) {

        JUnitCore runner = new JUnitCore();
        Result result = runner.run(Tester.class);
        System.out.println("run test: " + result.getRunCount());
        System.out.println("failed test: " + result.getFailureCount());
        System.out.println("ignore test: " + result.getIgnoreCount());
        System.out.println("success: " + result.wasSuccessful());

        if (result.getFailureCount() > 0) {
            System.out.println("You programm bad");
            return;
        }

        Controller contrl = new Controller();
        contrl.run();

    }
}
