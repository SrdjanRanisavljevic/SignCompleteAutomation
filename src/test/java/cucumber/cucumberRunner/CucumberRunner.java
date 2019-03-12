package cucumber.cucumberRunner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
//        features = {"src/test/java/cucumber/features/ConnectLoggedUser.feature:9"},
        features = {"src/test/java/cucumber/features"},
        glue = {"cucumber/cucumberTests", "cucumber/cucumberhooks"},
        plugin = {"pretty", "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports"},
        monochrome = true)

public class CucumberRunner {
}
