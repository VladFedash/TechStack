package test_runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
@CucumberOptions (
        features = "src/test/resources/features",
        glue = "step_definitions")
public class TestRunner {
}