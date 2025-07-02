package org.coreProduct.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@CucumberOptions(features = {"src/test/resources/features/TC1.feature"}, glue = {"org.coreProduct.stepdefinitions", "org.coreProduct.hooks"})
public class TestRunner1 extends AbstractTestNGCucumberTests {
    @BeforeClass(alwaysRun = true)
    @Parameters({"cucumber.filter.tags"})
    public void setTestRunner1(String tags) {
        System.setProperty("cucumber.filter.tags", tags);
    }
}
