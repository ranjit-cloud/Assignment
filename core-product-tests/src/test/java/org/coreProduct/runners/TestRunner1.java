package org.coreProduct.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features ={"src/test/resources/features/TC1.feature"}, glue = {"org.coreProduct.stepdefinitions","org.coreProduct.hooks"})
public class TestRunner1 extends AbstractTestNGCucumberTests {
}
