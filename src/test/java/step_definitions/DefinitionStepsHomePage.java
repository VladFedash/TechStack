package step_definitions;

import enums.Languages;
import helpers.BaseOperations;
import helpers.WaitUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.HomePage;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DefinitionStepsHomePage {
    private final BaseStepDefinition baseStepDefinition;
    private static String expectedResult;
    private static final String ROZETKA_URL = "https://rozetka.com.ua/";

    public DefinitionStepsHomePage(BaseStepDefinition baseStepDefinition) {
        this.baseStepDefinition = baseStepDefinition;
    }

    HomePage homePage;
    WaitUtils waitUtils;
    BaseOperations baseOperations;

    @Given("User opens home page")
    public void openHomePage() {
        baseStepDefinition.driver.manage().window().maximize();
        baseStepDefinition.driver.get(ROZETKA_URL);
        new BasePage(baseStepDefinition.driver).setAppLanguage(Languages.RU);
    }

    @Then("Home page are displayed for user")
    public void waitForHomePageDisplayed() {
        baseStepDefinition.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }


    @When("User clicks on the city button")
    public void clickChangeCity() {
        waitUtils = new WaitUtils(baseStepDefinition.driver);
        baseOperations = new BaseOperations(baseStepDefinition.driver);

        homePage = new HomePage(baseStepDefinition.driver);
        baseOperations.clickButton(homePage.changeCitiesButton);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.modalHeader);
    }

    @When("User selects random city")
    public void selectRandomCity() {
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.modalHeader);
        Random rand = new Random();
        int randomCity = rand.nextInt(homePage.popularCityList.size());

        expectedResult = homePage.popularCityList.get(randomCity).getText();
        baseOperations.clickButton(homePage.popularCityList.get(randomCity));
    }

    @When("User clicks accept city choice button")
    public void acceptCityChoice() {
        baseOperations.clickButton(homePage.acceptCityChoiceButton);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.changeCitiesButton);
    }

    @When("User checks changed city location")
    public void checkCityLocationChanged() {
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.changeCitiesButton);
        String actualResult = homePage.changeCitiesButton.getText();
        assertEquals(actualResult, expectedResult,
                "Actual chosen city doesn't equals expected city. Actual city: " + actualResult
                        + ". Expected city: " + expectedResult);
    }

    @When("User checks that tag name equals {string}")
    public void getTagName(final String sortingType) {
        homePage = new HomePage(baseStepDefinition.driver);
        assertEquals(homePage.catalog.getTagName(), sortingType);
    }

    @When("User checks that attribute name 'arial-label' equals {string}")
    public void getAttributeName(final String attributeName) {
        assertEquals(homePage.catalog.getAttribute("aria-label"), attributeName);
    }

    @When("User checks that css value 'font-size' equals {string}")
    public void getCssValue(final String pixels) {
        assertEquals(homePage.catalog.getCssValue("font-size"), pixels);

    }

    @When("User checks correct site logo and product catalog elements location")
    public void checkCorrectElementLocation() {
        assertTrue(homePage.siteLogo.getLocation().x
                + homePage.siteLogo.getSize().width < homePage.catalog.getLocation().x);
        assertEquals(homePage.siteLogo.getLocation().y, homePage.catalog.getLocation().y);
    }
}