package comparsion_demo;


	
	import java.time.Duration;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class DiverFactory {
		
		    public static WebDriver driver;

		    public static WebDriver initDriver() {
		        driver = new ChromeDriver();
		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		        return driver;
		    }
	}


