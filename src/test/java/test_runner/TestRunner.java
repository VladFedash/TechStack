package test_runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "src/test/java/step_definitions"
)
public class TestRunner {
}