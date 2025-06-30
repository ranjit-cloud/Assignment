package org.coreProduct.pages;

import org.automationframework.base.BaseTest;
import org.automationframework.utils.TestUtility;
import org.coreProduct.hooks.Hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Properties;

public class CoreProductNewFeaturesPage extends BaseTest {
    public WebDriver driver;
    public Properties properties;
    @FindBy(xpath = "//*[local-name()='svg' and @name='video']")
    List<WebElement> videoIcons;
    @FindBy(xpath = "//*[local-name()='svg' and @name='video']/ancestor::li//time")
    List<WebElement> time;

    public CoreProductNewFeaturesPage() {
        super(BaseTest.getDriver());
        this.driver = BaseTest.getDriver();
        this.properties = Hooks.getConfig();
    }

    public int countVideoFeed() {
        int count = 0;
        for (WebElement videoIcon : videoIcons) {
            if (videoIcon.isDisplayed())
                count++;
        }
        return count;
    }

    public int videoFeedDuration() {
        int count1 = 0;
        for (WebElement t : time) {
            if (t.isDisplayed())
                if (Integer.parseInt(t.getText().split("d")[0]) >= 3)
                    count1++;
        }
        return count1;
    }
}

