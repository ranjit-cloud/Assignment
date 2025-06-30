package org.coreProduct.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.automationframework.utils.TestUtility;
import org.coreProduct.hooks.Hooks;
import org.coreProduct.pages.CoreProductHomePage;
import org.coreProduct.pages.CoreProductNewFeaturesPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreProductNewFeaturesStepDefinition {
    CoreProductHomePage coreProductHomePage;
    CoreProductNewFeaturesPage coreProductNewFeaturesPage;
    private static final Logger logger = LoggerFactory.getLogger(CoreProductNewFeaturesStepDefinition.class);

    public CoreProductNewFeaturesStepDefinition() {
        coreProductHomePage = new CoreProductHomePage();
        TestUtility.inItTestUtility(Hooks.getDriver());
    }

    @Given("User lands on home page")
    public void user_lands_on_home_page() throws InterruptedException {
        coreProductHomePage.navigate();
    }

    @Then("hover on menu icon")
    public void hover_on_menu_icon() {
        coreProductHomePage.scrollToMenu();
    }

    @Then("Click on New&Features")
    public void click_on_new_features() throws InterruptedException {
        coreProductNewFeaturesPage = coreProductHomePage.clickOnNewsFeatures();
    }

    @Then("Count total no of video feeds")
    public void count_total_no_of_video_feeds() {
        int count = coreProductNewFeaturesPage.countVideoFeed();
        logger.info("video feed count: {}", count);
    }

    @Then("Count count the video feeds those are present in the page >3d")
    public void count_count_the_video_feeds_those_are_present_in_the_page_3d() {
        int count = coreProductNewFeaturesPage.videoFeedDuration();
        logger.info("Total videos which are 3d old are: {}", count);
    }
}

