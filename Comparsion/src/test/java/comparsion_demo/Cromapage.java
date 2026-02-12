package comparsion_demo;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

public class Cromapage {


	
		
		    WebDriver driver;
		    WebDriverWait wait;

		    // Locators for Croma website
		    By searchBox = By.id("searchV2"); // Search bar locator
		    By firstPrice = By.xpath("(//span[@class='amount plp-srp-new-amount'])[1]"); // First product price locator

		    public Cromapage(WebDriver driver) {
		        this.driver = driver;
		        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		    }

		    public double searchProduct(String product) {
		        driver.get("https://www.croma.com");
		        
		        driver.findElement(searchBox).sendKeys(product + Keys.ENTER);

		        wait.until(ExpectedConditions.visibilityOfElementLocated(firstPrice));

		        String price = driver.findElement(firstPrice).getText(). replace("₹", "")
	                    .replace(",", "");;

		        return Double.parseDouble(price);
		    }
		}





