package comparsion_demo;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import comparision_demo.MyntraPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ProductsSteps {
	

	    WebDriver driver;
	    double amazonPrice, flipkartPrice, cromaPrice;
	    private static final Logger logger = LogManager.getLogger(ProductsSteps.class);


	    AmazonPage amazon;
	    FilpkartPage flipkart;
	    Cromapage croma;
//       ExtentReports extent;
//	    ExtentTest test;
	  //extent = ExtentManager.getInstance();
        //test = extent.createTest("compare product price across websites");
	   //String lowerSite="";
        
	    @Test
	    @Given("user launches the browser")
	    public void user_launches_the_browser() {
	    	logger.trace("Initializing WebDriver");
	    	driver = DiverFactory.initDriver();
	    	//test.info("browser launced");
	    	logger.info("Browser Launched Successfully");

	     
	    }
	    
	    @Given("user searches {string} on Amazon")
	    public void user_searches_on_amazon(String product) {
	    	logger.trace("Creating Amazon Page Object");
	    	amazon = new AmazonPage(driver);
	    	
	    	logger.trace("Searching product on Amazon: " + product);
	        amazonPrice = amazon.searchProduct(product);
	       // ScreenshotUtil.capture(driver, "amazon");
	        System.out.println("Amazon Price: " + amazonPrice);
	        logger.info("Amazon Price: " + amazonPrice);

	        
	    }
	    @Given("user searches {string} on Filpcart")
	    public void user_searches_on_filpcart(String product) {
	    	logger.trace("Creating Flipkart Page Object");
	    	flipkart = new FilpkartPage(driver);
	        flipkartPrice = flipkart.searchProduct(product);
	       // ScreenshotUtil.capture(driver, "filpkart");
	        System.out.println("Flipkart Price: " + flipkartPrice);
	        logger.info("Flipkart Price: " + flipkartPrice);

	       
	    }
	    @Given("user searches {string} on croma")
	    public void user_searches_on_myntra(String product) {
	    	logger.trace("Creating Croma Page Object");
	    	 croma = new Cromapage(driver);
		      cromaPrice = croma.searchProduct(product);
		     //ScreenshotUtil.capture(driver, "croma");
		        System.out.println("croma Price: " + cromaPrice);
		        logger.info("Croma Price: " + cromaPrice);

	       
	    }
	    @Given("user compares price on all websites")
	    public void user_compares_price_on_all_websites() {
	    	logger.trace("Starting price comparison logic");
	        System.out.println("Amazon Price: " + amazonPrice);
	        System.out.println("Flipkart Price: " + flipkartPrice);
	        System.out.println("croma Price: " + cromaPrice);

	      

	        String lowestSite;
			if (amazonPrice <= flipkartPrice && amazonPrice <= cromaPrice) {
	            lowestSite = "Amazon";
	        } 
	        else if (flipkartPrice <= amazonPrice && flipkartPrice <= cromaPrice) {
	            lowestSite = "Flipkart";
	        } 
	        else {
	           lowestSite = "croma";
	       }

	       // test.pass("Lowest price found on: " + lowestSite);
			logger.info("Comparing prices from all websites");
			logger.info("Amazon Price: " + amazonPrice);
			logger.info("Flipkart Price: " + flipkartPrice);
			logger.info("Croma Price: " + cromaPrice);
			logger.info("Lowest price site is: " + lowestSite);
			logger.trace("Minimum price calculated successfully");


	        System.out.println("Lowest price site is: " + lowestSite);

	        Assert.assertTrue(lowestSite != null);
	    }


	    
	    @Then("user Should navigate to lowest price site and add to cart")
	    public void user_should_navigate_to_lowest_price_site_and_add_to_cart() {
	    
	    	logger.trace("Finding minimum value using Math.min()");
	    	
	    	double min = Math.min(amazonPrice,
                     Math.min(flipkartPrice, cromaPrice));
	    	logger.info("Navigating to site with lowest price");


        if(min == amazonPrice) {
            driver.get("https://www.amazon.in");
            logger.info("Redirecting to Amazon");
           

        } else if(min == flipkartPrice) {
            driver.get("https://www.flipkart.com");
            logger.info("Redirecting to Flipkart");
           
        } else {
            driver.get("https://www.croma.com/");
            logger.info("Redirecting to Croma");
        }

        Assert.assertTrue(min > 0);
        logger.trace("Minimum matches Amazon price");
    }

	    @Then("close browser")
	    public void close_browser() {
	    	logger.info("Closing Browser");
        driver.navigate().back();
	    	

    }
	       
	    
	   

}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

	   /* @Given("User launches browser")
	    public void launchBrowser() {
	        driver = DriverFactory.initDriver();
	    }

	    @When("User searches {string} on Amazon")
	    public void searchAmazon(String product) {
	        amazon = new AmazonPage(driver);
	        amazonPrice = amazon.searchProduct(product);
	       // ScreenshotUtil.capture(driver, "amazon");
	        System.out.println("Amazon Price: " + amazonPrice);
	    }

	    @When("User searches {string} on Flipkart")
	    public void searchFlipkart(String product) {
	        flipkart = new FilpkartPage(driver);
	        flipkartPrice = flipkart.searchProduct(product);
	       // ScreenshotUtil.capture(driver, "filpkart");
	        System.out.println("Flipkart Price: " + flipkartPrice);
	    }

	    @When("User searches {string} on Myntra")
	    public void searchMyntra(String product) {
	        myntra = new MyntraPage(driver);
	        myntraPrice = myntra.searchProduct(product);
	        //ScreenshotUtil.capture(driver, "myntra");
	        System.out.println("Myntra Price: " + myntraPrice);
	    }

	    @Then("User should navigate to lowest price site and add to cart")
	    public void navigateLowest() {

	        double min = Math.min(amazonPrice,
	                     Math.min(flipkartPrice, myntraPrice));

	        if(min == amazonPrice) {
	            driver.get("https://www.amazon.in");
	        } else if(min == flipkartPrice) {
	            driver.get("https://www.flipkart.com");
	        } else {
	            driver.get("https://www.myntra.com");
	        }

	        Assert.assertTrue(min > 0);
	    }

	    @And("Close browser")
	    public void closeBrowser() {
	        driver.quit();
	    }
	}
*/
