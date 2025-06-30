package org.automationframework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReport {
    public static ExtentReports getReportObject() {
        ExtentSparkReporter extent = new ExtentSparkReporter(new File(System.getProperty("user.dir") + "/target/index.html"));
        extent.config().setReportName("Automation Report");
        extent.config().setDocumentTitle("NBA Automation Suite");
        ExtentReports report = new ExtentReports();
        report.attachReporter(extent);
        report.setSystemInfo("Executed on ", "MAC");
        return report;
    }
}
