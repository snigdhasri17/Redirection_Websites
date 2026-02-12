package comparsion_demo;


	

	import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class FilpkartPage {

		    WebDriver driver;
		    WebDriverWait wait;

		    // Locators
		    By closeLoginPopup = By.xpath("//button[text()='✕']");
		    By searchBox = By.name("q");
		    By firstProductPrice = By.xpath("(//div[@class='hZ3P6w DeU9vF'])[1]");

		    public FilpkartPage(WebDriver driver) {
		        this.driver = driver;
		        wait = new WebDriverWait(driver,Duration.ofMillis(1));
		    }

		    public double searchProduct(String product) {

		        driver.get("https://www.flipkart.com");

		        // Close login popup if displayed
		        try {
		            wait.until(ExpectedConditions.visibilityOfElementLocated(closeLoginPopup));
		            driver.findElement(closeLoginPopup).click();
		        } catch (Exception e) {
		            System.out.println("Login popup not displayed");
		        }

		        // Search product
		        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
		        driver.findElement(searchBox).sendKeys(product + Keys.ENTER);

		        // Get first product price
		        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductPrice));

		        String price = driver.findElement(firstProductPrice)
		                             .getText()
		                             .replace("₹", "")
		                             .replace(",", "");

		        return Double.parseDouble(price);
		    }
	}
  
	


