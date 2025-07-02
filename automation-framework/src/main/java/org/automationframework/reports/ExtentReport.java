package org.automationframework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReport {
    private static ExtentReports report;
    public static ExtentReports getReportObject() {
        if(report==null) {
            ExtentSparkReporter extent = new ExtentSparkReporter(new File(System.getProperty("user.dir") + "/target/index.html"));
            extent.config().setReportName("Automation Report");
            extent.config().setDocumentTitle("NBA Automation Suite");
            report = new ExtentReports();
            report.attachReporter(extent);
        }
        return report;
    }
}
