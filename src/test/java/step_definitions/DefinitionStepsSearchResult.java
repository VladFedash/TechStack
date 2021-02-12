package step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.SearchSteps;

public class DefinitionStepsSearchResult {
    private final BaseStepDefinition baseStepDefinition;

    public DefinitionStepsSearchResult(BaseStepDefinition baseStepDefinition) {
        this.baseStepDefinition = baseStepDefinition;
    }

    @Steps
    SearchSteps searchSteps;

    @When("User inputs {string} to search field and press enter")
    public void shouldInputKeywordToSearchField(String searchKeyword) {
        searchSteps = new SearchSteps(baseStepDefinition.driver);
        searchSteps.inputKeywordToSearchField(searchKeyword);
    }

    @When("User scrolls down and click show more products button")
    public void shouldClickShowMore() {
        searchSteps.clickShowMore();
    }

    @Then("User checks that amount of elements increased by the specified amount")
    public void shouldCheckElementIncreasing() {
        searchSteps.checkElementIncreasing();
    }

    @When("User clears search field and input another {string}")
    public void shouldClearSearchFieldAndInputKeyword(String searchKeyword) {
        searchSteps.clearSearchFieldAndInputKeyword(searchKeyword);
    }

    @Then("User checks amount of products equals specified quantity")
    public void shouldCheckAmountOfProducts() {
        searchSteps.checkAmountOfProducts();
    }

    @Then("User checks actual massage equals {string}")
    public void shouldChecksMessageEquals(String message) {
        searchSteps.checksMessageEquals(message);
    }
}