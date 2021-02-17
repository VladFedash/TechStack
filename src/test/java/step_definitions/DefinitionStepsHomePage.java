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

import static org.testng.Assert.*;

public class DefinitionStepsHomePage {
    private final BaseStepDefinition baseStepDefinition;
    private static String expectedResult;
    private static final String ROZETKA_URL = "https://rozetka.com.ua/";

    protected HomePage homePage;
    protected BasePage basePage;
    protected WaitUtils waitUtils;
    protected BaseOperations baseOperations;

    public DefinitionStepsHomePage(BaseStepDefinition baseStepDefinition) {
        this.baseStepDefinition = baseStepDefinition;
        homePage = new HomePage(baseStepDefinition.driver);
        basePage = new BasePage(baseStepDefinition.driver);
        waitUtils = new WaitUtils(baseStepDefinition.driver);
        baseOperations = new BaseOperations(baseStepDefinition.driver);
    }

    @Given("User opens home page")
    public void openHomePage() {
        baseStepDefinition.driver.get(ROZETKA_URL);
        basePage.setAppLanguage(Languages.RU);
    }

    @Then("Home page is displayed for user")
    public void waitForHomePageDisplayed() {
        waitUtils.waitForPageLoading();
    }

    @When("User clicks on the city button")
    public void clickChangeCity() {
        baseOperations.clickButtonForStaleElements(homePage.menuButton);
        baseOperations.clickButtonForStaleElements(homePage.changeCitiesButton);
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
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.acceptCityChoiceButton);
        baseOperations.clickButton(homePage.acceptCityChoiceButton);
    }

    @Then("User checks changed city location")
    public void checkCityLocationChanged() {
        baseOperations.clickButtonForStaleElements(homePage.menuButton);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.changeCitiesButton);
        String actualResult = homePage.changeCitiesButton.getText();
        assertEquals(actualResult, expectedResult,
                String.format("Actual chosen city doesn't equals expected city. Actual city: %s. Expected city: %s",
                        actualResult, expectedResult));
    }

    @Then("User checks that tag name equals {string}")
    public void getTagName(String sortingType) {
        assertEquals(homePage.catalog.getTagName(), sortingType);
    }

    @Then("User checks that attribute name 'arial-label' equals {string}")
    public void getAttributeName(String attributeName) {
        assertEquals(homePage.catalog.getAttribute("aria-label"), attributeName);
    }

    @Then("User checks that css value 'font-size' equals {string}")
    public void getCssValue(String pixels) {
        waitUtils.waitForStaleElements(homePage.catalog);
        assertEquals(homePage.catalog.getCssValue("font-size"), pixels);

    }

    @Then("User checks correct site logo and product catalog elements location")
    public void checkCorrectElementLocation() {
        assertTrue(homePage.siteLogo.getLocation().x
                + homePage.siteLogo.getSize().width < homePage.catalog.getLocation().x);
        assertNotEquals(homePage.siteLogo.getLocation().y, homePage.catalog.getLocation().y);
    }
}