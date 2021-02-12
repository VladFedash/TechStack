package steps;

import enums.Languages;
import helpers.BaseOperations;
import helpers.WaitUtils;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.HomePage;

import java.util.Random;

import static org.testng.Assert.*;

public class HomeSteps {
    WebDriver driver;
    private static String expectedResult;
    private static final String ROZETKA_URL = "https://rozetka.com.ua/";

    public HomeSteps(WebDriver driver) {
        this.driver = driver;
    }

    HomePage homePage;
    WaitUtils waitUtils;
    BaseOperations baseOperations;

    @Step
    public void openHomePage() {
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
        new BasePage(driver).setAppLanguage(Languages.RU);
    }

    @Step
    public void waitForHomePageDisplayed() {
        new WaitUtils(driver).waitForPageLoading();
    }

    @Step
    public void clickChangeCity() {
        waitUtils = new WaitUtils(driver);
        baseOperations = new BaseOperations(driver);

        homePage = new HomePage(driver);
        baseOperations.clickButton(homePage.menuButton);
        baseOperations.clickButton(homePage.changeCitiesButton);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.modalHeader);
    }

    @Step
    public void selectRandomCity() {
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.modalHeader);
        Random rand = new Random();
        int randomCity = rand.nextInt(homePage.popularCityList.size());

        expectedResult = homePage.popularCityList.get(randomCity).getText();
        baseOperations.clickButton(homePage.popularCityList.get(randomCity));
    }

    @Step
    public void acceptCityChoice() {
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.acceptCityChoiceButton);
        baseOperations.clickButton(homePage.acceptCityChoiceButton);
    }

    @Step
    public void checkCityLocationChanged() {
        baseOperations.clickButton(homePage.menuButton);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.changeCitiesButton);
        String actualResult = homePage.changeCitiesButton.getText();
        assertEquals(actualResult, expectedResult,
                "Actual chosen city doesn't equals expected city. Actual city: " + actualResult
                        + ". Expected city: " + expectedResult);
    }

    @Step
    public void getTagName(final String sortingType) {
        homePage = new HomePage(driver);
        assertEquals(homePage.catalog.getTagName(), sortingType);
    }

    @Step
    public void getAttributeName(final String attributeName) {
        assertEquals(homePage.catalog.getAttribute("aria-label"), attributeName);
    }

    @Step
    public void getCssValue(final String pixels) {
        assertEquals(homePage.catalog.getCssValue("font-size"), pixels);

    }

    @Step
    public void checkCorrectElementLocation() {
        assertTrue(homePage.siteLogo.getLocation().x
                + homePage.siteLogo.getSize().width < homePage.catalog.getLocation().x);
        assertNotEquals(homePage.siteLogo.getLocation().y, homePage.catalog.getLocation().y);
    }
}