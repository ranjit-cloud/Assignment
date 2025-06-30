package org.automationframework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    public BaseTest(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static WebDriver setup(String browser) {
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                chromeOptions.addArguments("--disable-notifications");
                //chromeOptions.addArguments("--headless=new");
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications");
                firefoxOptions.addArguments("--disable-infobars");
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--disable-infobars");
                edgeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                driver.set(new EdgeDriver(edgeOptions));
                break;
            default:
                logger.error("Unsupported browser: {}", browser);
                throw new RuntimeException("Unsupported browser: " + browser);
        }
        return driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void removeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
