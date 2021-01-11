package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

public class BaseTest {

    private WebDriver driver;
    private static final String ROZETKA_URL = "https://rozetka.com.ua/";

    @Test
    public void plug(){}

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
    }

    public BasePage getBasePage(){
        return new BasePage(driver);
    }
    public HomePage getHomePage(){
        return new HomePage(driver);
    }
    public SearchResultsPage getSearchPage(){
        return new SearchResultsPage(driver);
    }
    public NotebooksPage getNotebooksPage(){
        return new NotebooksPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
