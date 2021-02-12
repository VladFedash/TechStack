package step_definitions;

import browser_factory.BrowserFactory;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

public class BaseStepDefinition {
    public WebDriver driver;

    @Before
    public void testSetUp() {
        driver = new BrowserFactory().getBrowser();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}