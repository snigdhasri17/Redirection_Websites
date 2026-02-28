package RunnerFile;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import Test.ProductSteps;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Featues",
glue= {"Test"},monochrome=true,
plugin= {"pretty","html:target/HtmlReports.html","json:target/JSONReports"})

public class TestRunner  extends AbstractTestNGCucumberTests{

}