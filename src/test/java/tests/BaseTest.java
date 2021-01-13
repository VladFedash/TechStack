package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;
import pages.HomePage;
import pages.NotebooksPage;
import pages.SearchResultsPage;

public class BaseTest {

    protected WebDriver driver = new ChromeDriver();
    private static final String ROZETKA_URL = "https://rozetka.com.ua/";

    static {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testSetUp() {
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
    }

    public BasePage getBasePage() {
        return new BasePage(driver);
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public SearchResultsPage getSearchPage() {
        return new SearchResultsPage(driver);
    }

    public NotebooksPage getNotebooksPage() {
        return new NotebooksPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}