package org.coreProduct.pages;

import org.automationframework.base.BaseTest;
import org.coreProduct.hooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class CoreProductShopPage extends BaseTest {
    public WebDriver driver;
    public Properties properties;
    @FindBy(xpath = "//span[contains(text(),'Jackets')]")
    WebElement jackets;
    @FindBy(xpath = "//div[@class='pagination-navigation']//a[@data-talos='linkSearchResultsNextPage']")
    WebElement nextPage;
    private static final Logger logger = LoggerFactory.getLogger(CoreProductShopPage.class);

    public CoreProductShopPage() {
        super(BaseTest.getDriver());
        this.driver = BaseTest.getDriver();
        this.properties = Hooks.getConfig();
    }

    public void findAllJackets() throws InterruptedException {
        Set<String> set = driver.getWindowHandles();
        Iterator<String> itr = set.iterator();
        String parentWindow = itr.next();
        String childWindow = itr.next();
        driver.switchTo().window(childWindow);
        System.out.println(driver.getTitle());
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", jackets);
        jackets.click();
    }

    public void storeDetailsToFile() throws IOException {
        FileWriter writer = new FileWriter("Output.txt");
        String lineSeparator = System.getProperty("line.separator");
        System.out.println("Line separator: " + lineSeparator);
        do {
            logger.info("Next page aria-disabled: {}", nextPage.getDomAttribute("aria-disabled"));
            List<WebElement> products = BaseTest.getDriver().findElements(By.className("product-card"));
            System.out.println("product size " + products.size());
            for (WebElement product : products) {
                logger.info(product.findElement(By.className("product-card-title")).getText());
                logger.info(product.findElement(By.className("lowest")).getText());
                writer.append(product.findElement(By.className("product-card-title")).getText() + " ");
                writer.append(product.findElement(By.className("lowest")).getText() + " ");
                List<WebElement> vibrancyElements = product.findElements(By.className("product-vibrancy"));
                if (!vibrancyElements.isEmpty()) {
                    writer.append(vibrancyElements.get(0).getText() + " ");
                }
                writer.append(lineSeparator);
            }
            if (nextPage.getDomAttribute("aria-disabled").equals("false"))
                nextPage.click();
            else
                break;
        } while (true);
        writer.close();
    }
}
