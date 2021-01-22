package tests;

import enums.Languages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.BasePage;

public class BaseTest {

    protected WebDriver driver = new ChromeDriver();
    private static final String ROZETKA_URL = "https://rozetka.com.ua/";
    BasePage basePage = new BasePage(driver);

    static {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeClass
    public void testSetUp() {
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
        basePage.setAppLanguage(Languages.RU);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}