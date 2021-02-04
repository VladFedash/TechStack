package tests;

import browserFactory.BrowserFactory;
import enums.Languages;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;

public class BaseTest {

    protected WebDriver driver;
    private static final String ROZETKA_URL = "https://rozetka.com.ua/";

    @BeforeMethod
    public void testSetUp() {
        driver = new BrowserFactory().getBrowser();
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
        new BasePage(driver).setAppLanguage(Languages.RU);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}