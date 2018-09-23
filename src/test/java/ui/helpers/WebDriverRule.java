package ui.helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Rogozin Roman on 22.09.2018.
 */
public class WebDriverRule implements TestRule {

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                setUp();
                try {
                    base.evaluate();
                }
                catch (Throwable t) {
                    tearDown();
                    throw t;
                }
                finally {
                    //--
                }
            }
        };
    }

    private static boolean driverExist = false;

    private static void setUp() {
        if (driverExist){
            WebDriverRunner.getWebDriver();
        }
        else {
            System.out.println("Driver start");
            WebDriverRunner.getAndCheckWebDriver();
            driverExist = true;
        }
    }

    private static void tearDown() {
        System.out.println("Driver quit");
        WebDriverRunner.closeWebDriver();
        driverExist = false;
    }

}