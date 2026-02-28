package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentUtil {

	public static ExtentReports extent;
    public static ExtentTest test;

    public static ExtentReports getReport() {

        if (extent == null) {
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("target/ExtentReport.html");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

	
}