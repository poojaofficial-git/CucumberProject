package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "C:/Users/prajeshp/eclipse-workspace/CucumberDemo/Features/",
	glue = "stepDefinitions", 
	dryRun = false, 
	monochrome=true,
	plugin = {"pretty", "html:target/cucumber-Report.html", "json:target/report.json"})

public class TestRun{

}
