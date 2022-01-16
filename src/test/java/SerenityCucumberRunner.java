
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/"
        , tags = "@ToDo"
        , glue = {"glue"}
        , snippets = CucumberOptions.SnippetType.CAMELCASE
        , plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json" }
)
public class SerenityCucumberRunner {
}
