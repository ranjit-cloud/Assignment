package org.coreProduct.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(features ={"src/test/resources/features/TC2.feature"}, glue = {"org.coreProduct.stepdefinitions","org.coreProduct.hooks"})
public class TestRunner2 extends AbstractTestNGCucumberTests {
    @BeforeClass(alwaysRun = true)
    @Parameters({"cucumber.filter.tags"})
    public void setTestRunner2(String tags){
        System.setProperty("cucumber.filter.tags", tags);
    }
}
