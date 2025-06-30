package org.automationframework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.cucumber.testng.PickleWrapper;
import org.automationframework.reports.ExtentReport;
import org.automationframework.utils.TestUtility;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {
    ExtentReports report = ExtentReport.getReportObject();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        Object[] parameters = result.getParameters();
        String scenarioName = "";

        if (parameters != null && parameters.length > 0) {
            if (parameters[0] instanceof PickleWrapper) {
                PickleWrapper pickleWrapper = (PickleWrapper) parameters[0];
                scenarioName = pickleWrapper.getPickle().getName();
            }
        }
        ExtentTest test = report.createTest(scenarioName);
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        extentTest.get().log(Status.PASS, result.getThrowable());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        try {
            extentTest.get().log(Status.FAIL, result.getThrowable());
            String path = TestUtility.takeScreenShot();
            extentTest.get().log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        report.flush();
    }
}
