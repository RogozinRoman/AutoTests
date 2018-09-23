package ui.helpers;

import com.codeborne.selenide.WebDriverRunner;

/**
 * Created by Rogozin Roman on 22.09.2018.
 */
public class WebDriverUtils {

    public static void closeBrowserActiveTab(){
        waitForSeconds(1);
        for (String handle : WebDriverRunner.getWebDriver().getWindowHandles()) {
            WebDriverRunner.getWebDriver().switchTo().window(handle);
            WebDriverRunner.getWebDriver().switchTo().activeElement();
        }
        WebDriverRunner.getWebDriver().close();
        waitForSeconds(1);
        for (String handle : WebDriverRunner.getWebDriver().getWindowHandles()) {
            WebDriverRunner.getWebDriver().switchTo().window(handle);
            WebDriverRunner.getWebDriver().switchTo().activeElement();
        }
        WebDriverRunner.getWebDriver().navigate().refresh();
        waitForSeconds(1);
    }

    private static void waitForSeconds(int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
