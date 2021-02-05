package stepdefinitions;

import helpers.BaseOperations;
import helpers.WaitUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

import java.util.Random;

import static org.testng.Assert.*;

public class DefinitionStepsHomePage {
    private final Hook hook;
    private static String expectedResult;
    private static int siteLogoHeight;
    private static int catalogHeight;

    public DefinitionStepsHomePage(Hook hook) {
        this.hook = hook;
    }

    HomePage homePage;
    WaitUtils waitUtils;
    BaseOperations baseOperations;

    @Given("User clicks on the city button")
    public void clickChangeCity() {
        waitUtils = new WaitUtils(hook.driver);
        baseOperations = new BaseOperations(hook.driver);

        homePage = new HomePage(hook.driver);
        baseOperations.clickButton(homePage.changeCitiesButton);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.modalHeader);
    }

    @When("User selects random city")
    public void selectRandomCity() {
        Random rand = new Random();
        int randomCity = rand.nextInt(homePage.popularCityList.size());

        expectedResult = homePage.popularCityList.get(randomCity).getText();
        baseOperations.clickButton(homePage.popularCityList.get(randomCity));
    }

    @When("User clicks accept city choice button")
    public void acceptCityChoice() {
        assertFalse(homePage.acceptCityChoiceButton.isSelected());

        baseOperations.clickButton(homePage.acceptCityChoiceButton);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.changeCitiesButton);
    }

    @Then("User checks changed city location")
    public void checkCityLocationChanged() {
        String actualResult = homePage.changeCitiesButton.getText();
        assertEquals(actualResult, expectedResult,
                "Actual chosen city doesn't equals expected city. Actual city: " + actualResult
                        + ". Expected city: " + expectedResult);
    }

    @Given("User opens home page app")
            public void openHomePage() {
        homePage = new HomePage(hook.driver);
        siteLogoHeight = homePage.siteLogo.getLocation().y;
        catalogHeight = homePage.catalog.getLocation().y;
    }

    @When("User checks that tag name equals {string}")
            public void getTagName(final String sortingType) {
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
    @Then("User checks correct site logo and product catalog elements location")
            public void checkCorrectElementLocation(){
        assertTrue(homePage.siteLogo.getLocation().x
                + homePage.siteLogo.getSize().width < homePage.catalog.getLocation().x);
        assertEquals(siteLogoHeight, catalogHeight);
    }
}