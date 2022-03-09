package cucumberRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features1/basic.feature",
glue={"RestAssureJava"},
/*glue={"classpath:cucumberRunner"},*/

dryRun=false,monochrome=true /*,tags= {"@RohithShettyExample"}*/
)

public class TestRunner {


}
