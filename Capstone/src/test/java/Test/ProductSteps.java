package Test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

//import comparision_demo.MyntraPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Utilities.ExtentUtil;
import Utilities.ScreenshotUtil;
import StepsDef.AmazonPage;
import StepsDef.DriverFactory;
import StepsDef.Cromapage;



public class ProductSteps {
	

	    WebDriver driver;
	    double amazonPrice,  cromaPrice;
	    private static final Logger logger = LogManager.getLogger(ProductSteps.class);
	    ExtentReports extent = ExtentUtil.getReport();
	    ExtentTest test;

	    AmazonPage amazon;
	    //FlipcartPage flipkart;
	    Cromapage croma;
	    String finalURL;
	    String lowestSite;
	    @Test
	    @Given("user launches the browser")
	    public void user_launches_the_browser() {
	    	//logger.trace("Initializing WebDriver");
	    	driver = DriverFactory.initDriver();
	    	test = extent.createTest("Compare Product Price Across Websites");

	    	//test.info("browser launced");
	    	logger.info("Browser Launched Successfully");

	     
	    }
	    @Test
	    @Given("user searches {string} on Amazon")
	    public void user_searches_on_amazon(String product) throws IOException {
	    	
	    	amazon = new AmazonPage(driver);

	        amazonPrice = amazon.searchProduct(product);
	        
	       System.out.println(driver.getCurrentUrl());
	       String amazonPath = ScreenshotUtil.capture(driver, "amazon");
	       test.pass("Amazon Screenshot",
	               MediaEntityBuilder.createScreenCaptureFromPath(amazonPath).build());
	        logger.info("Amazon Price: " + amazonPrice);

	        
	    }
	    @Test
	    @Given("user searches {string} on croma")
	    public void user_searches_on_croma(String product) throws IOException {
	    	//logger.trace("Creating Croma Page Object");
	    	 croma = new Cromapage(driver);
		      
			  cromaPrice = croma.searchProduct(product);
			 System.out.println( driver.getCurrentUrl());
			// System.out.println("croma Price: " + cromaPrice);
		     ScreenshotUtil.capture(driver, "croma");
		     String cromaPath = ScreenshotUtil.capture(driver, "croma");
		     test.pass("Croma Screenshot",
		             MediaEntityBuilder.createScreenCaptureFromPath(cromaPath).build());
		       logger.debug(cromaPath);
		       logger.trace("the product url is: "+product);
		        logger.info("Croma Price: " + cromaPrice);

	       
	    }
	    @Test
	    @When("user compares price on all websites")
	    public void user_compares_price_on_all_websites() {
	    	//logger.trace("Starting price comparison logic");
	       // System.out.println("Amazon Price: " + amazonPrice);
	       // System.out.println("Flipkart Price: " + flipkartPrice);
	        //System.out.println("croma Price: " + cromaPrice);
	        
			if ( amazonPrice <= cromaPrice) {
	            lowestSite = "Amazon";
	        } 
        
	        else {
	           lowestSite = "croma";
	       }
			//for extent report visibility
			test.info("Amazon Price: " + amazonPrice);
		    test.info("Croma Price: " + cromaPrice);
		    test.pass("Lowest Price Found On: " + lowestSite);

		    Assert.assertNotNull(lowestSite);
		    test.pass("Lowest price found on: " + lowestSite);

		    // console visibility
		logger.info("Amazon Price: " + amazonPrice);

			logger.info("Croma Price: " + cromaPrice);
			logger.info("Lowest price site is: " + lowestSite);
//			logger.trace("Minimum price calculated successfully");


	        //System.out.println("Lowest price site is: " + lowestSite);

	        AssertJUnit.assertTrue(lowestSite != null);
	    }


	    @Test
	    @Then("user Should navigate to lowest price site")
	    public void user_should_navigate_to_lowest_price_site() throws IOException {
	    
	    
	    	//Math.min is java in-build function
	    	double min = Math.min(amazonPrice,cromaPrice);
	    	logger.trace("Finding minimum value using Math.min()" + min);
                    // Math.min(cromaPrice));
	    	//logger.info("Navigating to site with lowest price");


        if(min == amazonPrice) {
            driver.get("https://www.amazon.in");
           
            logger.info("Redirecting to Amazon");
           
        }

            else {
            driver.get("https://www.croma.com/");
            logger.info("Redirecting to Croma");
        }
        finalURL = driver.getCurrentUrl();
        test.info("Final Navigated URL: " + finalURL);

        String finalPath = ScreenshotUtil.capture(driver, "lowest_price_site");
        test.pass("Lowest Price Site Screenshot",
                MediaEntityBuilder.createScreenCaptureFromPath(finalPath).build());

        //  URL Validation(Assertion)
        if (lowestSite.equals("Amazon")) {
            AssertJUnit.assertTrue(finalURL.contains("amazon"));
        } else {
            AssertJUnit.assertTrue(finalURL.contains("croma"));
        }

        test.pass("URL Validation Successful");
    }
    

	    @Test
	    @Then("close browser")
	    public void close_browser() {
	    	//logger.info("Closing Browser");	
       extent.flush();
        driver.close();
        
	    	

    }
	       
	    
	   

}