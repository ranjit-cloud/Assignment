package org.coreProduct.pages;

import org.automationframework.base.BaseTest;
import org.automationframework.utils.TestUtility;
import org.coreProduct.hooks.Hooks;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Properties;


public class CoreProductHomePage extends BaseTest {
    public WebDriver driver;
    public Properties properties;
    @FindBy(xpath = "//a[contains(@class,'accent-primary-border')]/span[contains(text(),'Shop')]")
    WebElement shopMenu;
    @FindBy(xpath = "//a[contains(@class,'_link')]/ancestor::div[contains(@class,'brand-font')]//a[@title=\"Men's\"]")
    WebElement mens;
    @FindBy(xpath = "//li[@class='menu-item']//span[contains(text(),'...')]")
    WebElement menu;
    @FindBy(xpath = "//nav[contains(@id,'nav-dropdown-desktop')]//a[@title='News & Features']")
    WebElement newsFeature;


    @FindBy(xpath = "//div[contains(text(),'x')]")
    WebElement cancelButton;

    public CoreProductHomePage() {
        super(BaseTest.getDriver());
        this.driver = BaseTest.getDriver();
        this.properties = Hooks.getConfig();
    }

    public void navigate() {
        driver.get(properties.getProperty("url"));
        TestUtility.explicitWait(5).until(ExpectedConditions.visibilityOf(cancelButton));
        cancelButton.click();
    }

    public void scrollToShopMenu() {
        TestUtility.actionsUtility(BaseTest.getDriver(), shopMenu);
    }

    public void scrollToMenu() {
        TestUtility.explicitWait(5).until(ExpectedConditions.visibilityOf(menu));
        TestUtility.actionsUtility(BaseTest.getDriver(), menu);
    }

    public CoreProductShopPage clickOnMenOption() {
        mens.click();
        return new CoreProductShopPage();
    }

    public CoreProductNewFeaturesPage clickOnNewsFeatures() throws InterruptedException {
        ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].scrollIntoView();", newsFeature);
        newsFeature.click();
        Thread.sleep(3000);
        return new CoreProductNewFeaturesPage();
    }

}
