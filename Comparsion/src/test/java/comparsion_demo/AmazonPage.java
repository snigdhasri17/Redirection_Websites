package comparsion_demo;

import java.time.Duration;

//import javax.xml.datatype.Duration;

//import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class AmazonPage {
WebDriver driver;
	    WebDriverWait wait;

	    By searchBox = By.id("twotabsearchtextbox");
	    By firstPrice = By.xpath("(//span[@class='a-price-whole'])[1]");
	    public AmazonPage(WebDriver driver) {
	        this.driver = driver;
	        wait = new WebDriverWait(driver,Duration.ofSeconds(15));
	    }

	    public double searchProduct(String product) {
	        driver.get("https://www.amazon.in");
	        driver.findElement(searchBox).sendKeys(product+ Keys.ENTER);

	        wait.until(ExpectedConditions.visibilityOfElementLocated(firstPrice));
	        String price = driver.findElement(firstPrice).getText().replace(",", "");

	        return Double.parseDouble(price);
	    }}
	    
		      
	
	
	

    /*WebDriver driver;

    By firstProductLink = By.xpath("(//div[@data-component-type='s-search-result']//h2/a)[1]");
    //By addToCartButton = By.id("add-to-cart-button");
    By goToCartButton = By.xpath("//a[contains(@href,'cart')]");

    public AmazonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openFirstProduct() {

        driver.findElement(firstProductLink).click();

        // Switch to new tab
        Set<String> windows = driver.getWindowHandles();
        for (String win : windows) {
            driver.switchTo().window(win);
        }
    }

    public void addToCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement addBtn = wait.until(
                ExpectedConditions.elementToBeClickable(addToCartButton));

        addBtn.click();
    }

    public void goToCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement cartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(goToCartButton));

        cartBtn.click();

        System.out.println("Redirected to Amazon Cart Page");
    }*/

