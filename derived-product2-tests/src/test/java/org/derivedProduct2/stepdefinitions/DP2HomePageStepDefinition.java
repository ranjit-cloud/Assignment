package org.derivedProduct2.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.automationframework.utils.TestUtility;
import org.derivedProduct2.hooks.Hooks;
import org.derivedProduct2.pages.DP2HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

public class DP2HomePageStepDefinition {
    DP2HomePage dp2HomePage;
    private static final Logger logger = LoggerFactory.getLogger(DP2HomePageStepDefinition.class);
    public DP2HomePageStepDefinition() {
        dp2HomePage = new DP2HomePage();
        TestUtility.inItTestUtility(Hooks.getDriver());
    }

    @Given("User lands on home page")
    public void user_lands_on_home_page() {
        dp2HomePage.navigate();
    }

    @Then("Scroll down to footer")
    public void scroll_down_to_footer() {
        dp2HomePage.scrollToElement();
    }

    @Then("Find different hyperlinks for various footer link categories")
    public void find_different_hyperlinks_for_various_footer_link_categories() throws IOException {
        dp2HomePage.findAllHyperLinks();
    }


    @And("Insert hyperlinks to CSV file")
    public void insert_hyperlinks_to_csv_file() throws IOException {
       dp2HomePage.insertData();
       logger.info("Inserted data into CSV");
    }

    @And("Report if any duplicate hyperlinks are present")
    public void report_if_any_duplicate_hyperlinks_are_present() {
        logger.info("Duplicates count: {}",dp2HomePage.duplicates());
    }
}
