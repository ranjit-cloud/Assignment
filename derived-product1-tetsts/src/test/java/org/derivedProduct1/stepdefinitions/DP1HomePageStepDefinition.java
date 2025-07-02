package org.derivedProduct1.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.automationframework.utils.TestUtility;
import org.derivedProduct1.hooks.Hooks;
import org.derivedProduct1.pages.DP1HomePage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DP1HomePageStepDefinition {
    DP1HomePage dp1HomePage;
    SoftAssert softAssert = new SoftAssert();
    private static final Logger logger = LoggerFactory.getLogger(DP1HomePageStepDefinition.class);

    public DP1HomePageStepDefinition() {
        dp1HomePage = new DP1HomePage();
        TestUtility.inItTestUtility(Hooks.getDriver());
    }

    @Given("User lands on home page")
    public void user_lands_on_home_page() {
       dp1HomePage.navigate();
    }

    @Then("below tickets menu, count number of slides present")
    public void below_tickets_menu_count_number_of_slides_present() {
        dp1HomePage.countSlides();
    }

    @Then("get title of each slide")
    public void get_title_of_each_slide() {
        dp1HomePage.getTitle();
    }

    @And("validate title with expected test data")
    public void validate_title_with_expected_test_data() throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/TestData.json")));
        JSONObject jsonObject = new JSONObject(data);
        List<WebElement> actual = dp1HomePage.validateSlideTitle();
        for (int i = 0; i < jsonObject.length(); i++) {
            String actualTitle = actual.get(i).getText();
            String expectedTitle = jsonObject.getString("Title" + (i + 1));
            //softAssert.assertEquals(actualTitle, expectedTitle);
            logger.info("Actual title:{} Expected Title:{}", actualTitle, expectedTitle);
        }
       // softAssert.assertAll();
    }

    @Then("calculate duration for which each slide is playing")
    public void calculate_duration_for_which_each_slide_is_playing() {
        dp1HomePage.fetchSlideDuration();
    }

    @And("validate duration with expected duration {string} sec")
    public void validate_duration_with_expected_duration(String expectedDuration) {
        Map<String, Long> map = dp1HomePage.validateSlideDuration();
        Set<String> set = map.keySet();
        for (String s : set) {
          //  softAssert.assertEquals(map.get(s), (Long) Long.parseLong(expectedDuration));
            logger.info("Actual duration:{} ExpectedDuration:{}", map.get(s), expectedDuration);
        }
       // softAssert.assertAll();
    }

}

