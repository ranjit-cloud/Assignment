package org.derivedProduct2.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features/TC4.feature"}, glue = {"org.derivedProduct2.stepdefinitions","org.derivedProduct2.hooks"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
