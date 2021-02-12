package step_definitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.SortingSteps;

public class DefinitionStepsForSorting {

    private final BaseStepDefinition baseStepDefinition;

    public DefinitionStepsForSorting(BaseStepDefinition baseStepDefinition) {
        this.baseStepDefinition = baseStepDefinition;
    }

    @Steps
    SortingSteps sortingSteps;

    @When("User opens notebook page")
    public void whenOpenPageByProductCatalog() {
        sortingSteps = new SortingSteps(baseStepDefinition.driver);
        sortingSteps.openPageByProductCatalog();
    }

    @When("User selects in dropdown sorting type - {string}")
    public void shouldSelectSortingType(String sortingType) {
        sortingSteps.selectSortingType(sortingType);
    }

    @Then("User checks that products sorted by ascending")
    public void shouldCheckSortingByAscending() {
        sortingSteps.checkSortingByAscending();
    }

    @Then("User checks that products sorted by descending")
    public void shouldCheckSortingByDescending() {
        sortingSteps.checkSortingByDescending();
    }

    @When("User selects product firm")
    public void shouldSelectProductFirm() {
        sortingSteps.selectProductFirm();
    }

    @Then("User checks products contains in title selected firm")
    public void shouldCheckProductFirm() {
        sortingSteps.checkProductFirm();
    }
}