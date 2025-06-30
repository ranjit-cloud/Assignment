package org.automationframework.utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;

public class TestUtility {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void inItTestUtility(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriverWait explicitWait(int sec) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(sec));
    }

    public static String takeScreenShot() throws IOException {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }
    public static void actionsUtility(WebDriver webDriver, WebElement element)
    {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element).build().perform();
    }
}

