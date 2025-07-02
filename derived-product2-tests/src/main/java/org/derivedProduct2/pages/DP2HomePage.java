package org.derivedProduct2.pages;

import org.automationframework.base.BaseTest;
import org.derivedProduct2.hooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DP2HomePage extends BaseTest {
    public WebDriver driver;
    public Properties properties;
    public int duplicateCount;
    private static final Logger logger = LoggerFactory.getLogger(DP2HomePage.class);
    Map<String, Integer> map = new HashMap<>();
    List<WebElement> webElements;
    @FindBy(xpath = "//footer//div[contains(@class,'md:px-4')]")
    WebElement footer;

    public DP2HomePage() {
        super(BaseTest.getDriver());
        this.driver = BaseTest.getDriver();
        this.properties = Hooks.getConfig();
    }

    public void navigate() {
        BaseTest.getDriver().get(properties.getProperty("url"));
    }

    public void scrollToElement() {
        logger.info("scrolling to footer section");
        ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].scrollIntoView();", footer);
    }

    public void findAllHyperLinks() throws IOException {
        webElements = footer.findElements(By.tagName("a"));
    }

    public void insertData() throws IOException {
        FileWriter writer = new FileWriter("Out.csv");
        for (WebElement element1 : webElements) {
            logger.info("Link: {}", element1.getDomAttribute("href"));
            String href = element1.getDomAttribute("href");
            String linkText = element1.getText();
            if (href != null) {
                writer.append(linkText);
                writer.append(",");
                writer.append(href);
                writer.append(System.lineSeparator());
                if (map.containsKey(href)) {
                    duplicateCount++;
                } else {
                    map.put(href, 0);
                }
            }
        }
        writer.close();
    }

    public int duplicates() {
        return duplicateCount;
    }
}
