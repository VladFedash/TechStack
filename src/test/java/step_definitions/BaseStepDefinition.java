package step_definitions;

import browser_factory.BrowserFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class BaseStepDefinition {
    protected WebDriver driver;

    @Before
    public void testSetUp() {
        driver = new BrowserFactory().getBrowser();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}