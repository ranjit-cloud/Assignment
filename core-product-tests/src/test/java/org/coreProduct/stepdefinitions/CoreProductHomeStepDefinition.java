package org.coreProduct.stepdefinitions;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.automationframework.reports.ExtentReport;
import org.automationframework.utils.TestUtility;
import org.coreProduct.hooks.Hooks;
import org.coreProduct.pages.CoreProductHomePage;
import org.coreProduct.pages.CoreProductShopPage;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CoreProductHomeStepDefinition {
    CoreProductHomePage coreProductHomePage;
    CoreProductShopPage coreProductShopPage;
    private static final Logger logger = LoggerFactory.getLogger(CoreProductHomeStepDefinition.class);

    public CoreProductHomeStepDefinition() {
        coreProductHomePage = new CoreProductHomePage();
        TestUtility.inItTestUtility(Hooks.getDriver());
    }

    @Given("User lands on core product home page")
    public void user_lands_on_core_product_home_page() {
        coreProductHomePage.navigate();
    }

    @Then("Scroll to shop menu")
    public void scroll_to_shop_menu() {
        coreProductHomePage.scrollToShopMenu();
    }
    @And("Click on Men's option")
    public void click_on_men_s_option() {
        coreProductShopPage = coreProductHomePage.clickOnMenOption();
    }

    @Then("Find all jackets from all paginated pages")
    public void find_all_jackets_from_all_paginated_pages() throws InterruptedException {
        coreProductShopPage.findAllJackets();
    }

    @Then("Store each jacket price, title and top seller's message to text file")
    public void store_each_jacket_price_title_and_top_seller_s_message_to_text_file() throws IOException {
        coreProductShopPage.storeDetailsToFile();
    }

    @And("Attach text file to report")
    public void attach_text_file_to_report() {
        ExtentTest test = ExtentReport.getReportObject().createTest("Attach data");

        try {
            String output = new String(Files.readAllBytes(Paths.get("Output.txt")));
            test.info(MarkupHelper.createLabel("Output.txt content:", ExtentColor.BLUE));
        } catch (IOException e) {
            test.warning("Could not attach Output.txt: " + e.getMessage());
        }
    }
}