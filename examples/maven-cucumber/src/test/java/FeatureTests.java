import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue="com.github.grantjforrester.bdd.rest.cucumber", features="features")
public class FeatureTests {

}
