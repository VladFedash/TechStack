package step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.HomeSteps;

public class DefinitionStepsHomePage {
    private final BaseStepDefinition baseStepDefinition;

    public DefinitionStepsHomePage(BaseStepDefinition baseStepDefinition) {
        this.baseStepDefinition = baseStepDefinition;
    }

    @Steps
    HomeSteps homeSteps;

    @Given("User opens home page")
    public void shouldOpenHomePage() {
        homeSteps = new HomeSteps(baseStepDefinition.driver);
        homeSteps.openHomePage();
    }

    @Then("Home page is displayed for user")
    public void shouldWaitForHomePageDisplayed() {
        homeSteps.waitForHomePageDisplayed();
    }

    @When("User clicks on the city button")
    public void shouldClickChangeCity() {
        homeSteps.clickChangeCity();
    }

    @When("User selects random city")
    public void shouldSelectRandomCity() {
        homeSteps.selectRandomCity();
    }

    @When("User clicks accept city choice button")
    public void shouldAcceptCityChoice() {
        homeSteps.acceptCityChoice();
    }

    @Then("User checks changed city location")
    public void shouldCheckCityLocationChanged() {
        homeSteps.checkCityLocationChanged();
    }

    @Then("User checks that tag name equals {string}")
    public void shouldGetTagName(String sortingType) {
        homeSteps.getTagName(sortingType);
    }

    @Then("User checks that attribute name 'arial-label' equals {string}")
    public void shouldGetAttributeName(String attributeName) {
        homeSteps.getAttributeName(attributeName);
    }

    @Then("User checks that css value 'font-size' equals {string}")
    public void shouldGetCssValue(String pixels) {
        homeSteps.getCssValue(pixels);
    }

    @Then("User checks correct site logo and product catalog elements location")
    public void shouldCheckCorrectElementLocation() {
        homeSteps.checkCorrectElementLocation();
    }
}