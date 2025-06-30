package org.derivedProduct1.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features/TC3.feature"}, glue = {"org.derivedProduct1.stepdefinitions","org.derivedProduct1.hooks"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
