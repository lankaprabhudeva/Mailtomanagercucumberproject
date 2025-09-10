package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",   // path to your feature files
    glue = {"stepDefinitions", "utils"},        // packages for step defs + hooks
    plugin = {
        "pretty",                               // readable console output
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json"
    },
    monochrome = true,                          // cleaner console logs
    dryRun = false,                             // true = check mapping only, no execution
    publish = true                              // generates a public report link
)
public class TestRunner {
}
