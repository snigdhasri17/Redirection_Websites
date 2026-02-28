package StepsDef;
import java.time.Duration;

//import javax.xml.datatype.Duration;

//import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import io.cucumber.messages.Messages.Duration;

//import io.cucumber.messages.internal.com.google.protobuf.Duration;aSSAMSSJOIjxaSX\\\]MHUG
//import Driver.DriverFactory;
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
		        driver.findElement(searchBox).sendKeys(product + Keys.ENTER);

		        wait.until(ExpectedConditions.visibilityOfElementLocated(firstPrice));
		        String price = driver.findElement(firstPrice).getText().replace(",", "");

		        return Double.parseDouble(price);
		    }
}



