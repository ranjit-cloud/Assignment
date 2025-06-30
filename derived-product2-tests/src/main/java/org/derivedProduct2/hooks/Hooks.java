package org.derivedProduct2.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.automationframework.base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Hooks {
    public static Properties properties;
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setup() {
        if (properties == null) {
            loadConfig();
        }
        String browser = properties.getProperty("browser", "chrome").toLowerCase();
        logger.info("Initializing webDriver for browser: {}", browser);
        if (BaseTest.getDriver() == null) {
            BaseTest.setup(browser);
        }
        BaseTest.getDriver().manage().window().maximize();
        BaseTest.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(properties.getProperty("implicit.wait"))));
    }

    public static Properties getConfig() {
        if (properties == null) {
            loadConfig();
        }
        return properties;
    }

    public static WebDriver getDriver() {
        return BaseTest.getDriver();
    }

    @AfterAll
    public static void tearDown() {
        BaseTest.removeDriver();
    }

    public static void loadConfig() {
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            logger.error("Error loading properties: {}", e.getMessage());
            throw new RuntimeException("Failed to read properties", e);
        }
    }
}
