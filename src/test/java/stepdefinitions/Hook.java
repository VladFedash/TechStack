package stepdefinitions;

import browser_factory.BrowserFactory;
import enums.Languages;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class Hook {
    WebDriver driver;
    public String ROZETKA_URL = "https://rozetka.com.ua/";

    @Before(order = 0)
    public void testSetUp() {
        driver = new BrowserFactory().getBrowser();
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
        new BasePage(driver).setAppLanguage(Languages.RU);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}