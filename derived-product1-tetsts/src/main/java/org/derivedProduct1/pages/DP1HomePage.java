package org.derivedProduct1.pages;

import org.automationframework.base.BaseTest;
import org.automationframework.utils.TestUtility;
import org.derivedProduct1.hooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DP1HomePage extends BaseTest {
    public WebDriver driver;
    public Properties properties;
    String currentActive;
    Map<String, Long> slideDuration = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(DP1HomePage.class);
    @FindBy(xpath = "//div[@role='tablist']/button")
    List<WebElement> slides;
    @FindBy(css = ".TileHero_tileLink__VTMPI")
    WebElement activeSlideDetail;

    public DP1HomePage() {
        super(BaseTest.getDriver());
        this.driver = BaseTest.getDriver();
        this.properties = Hooks.getConfig();
    }

    public WebElement getActiveSlide() {
        return driver.findElement(By.xpath("//div[contains(text(),'" + currentActive + "')]/parent::button"));
    }
    public void navigate()
    {
        driver.get(properties.getProperty("url"));
    }

    public void countSlides() {
        logger.info("slide count: {}", slides.size());
    }

    public void getTitle() {
        for (WebElement slide : slides) {
            TestUtility.explicitWait(10).until(ExpectedConditions.visibilityOf(slide));
            logger.info("slide title: {}", slide.getText());
        }
    }

    public List<WebElement> validateSlideTitle() {
        return slides;
    }

    public void fetchSlideDuration() {
        for (int i = 0; i < slides.size(); i++) {
            currentActive = activeSlideDetail.getDomAttribute("aria-label");
            Instant startTime = Instant.now();
            TestUtility.explicitWait(30).until(ExpectedConditions.domAttributeToBe(getActiveSlide(), "aria-selected", "false"));
            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);
            logger.info("Duration of slide: {} is: {}", currentActive, duration.getSeconds());
            slideDuration.put(currentActive, duration.getSeconds());
        }
    }

    public Map<String, Long> validateSlideDuration() {
        return slideDuration;
    }
}

