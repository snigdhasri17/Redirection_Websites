package StepsDef;

	import java.time.Duration;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class DriverFactory {
		
		    public static WebDriver driver;

		    public static WebDriver initDriver() {
		        driver = new ChromeDriver();
		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		        return driver;
		    }
	}