package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"./Features/VardenisiffrorTest.feature"},
		glue = {"stepDefinitions"},
		monochrome = true,
		dryRun = false,
		plugin = {"pretty","html:test-output"}
		)

public class TestRunner {

}
