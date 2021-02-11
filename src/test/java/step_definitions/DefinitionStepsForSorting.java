package step_definitions;

import io.cucumber.java.en.When;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;
import steps.SortingSteps;

import java.util.List;

@RunWith(SerenityRunner.class)
public class DefinitionStepsForSorting {

    private final BaseStepDefinition baseStepDefinition;
    private static final String CHOSEN_NOTEBOOK_FIRM = "lenovo";
    private static List<Integer> actualProductPriceList;
    private static List<Integer> expectedProductPriceList;


    public DefinitionStepsForSorting(BaseStepDefinition baseStepDefinition) {
        this.baseStepDefinition = baseStepDefinition;
    }

    @Steps
    SortingSteps sortingSteps;


    @When("User opens notebook page")
    public void whenOpenPageByProductCatalog() {
        System.out.println(sortingSteps);
        sortingSteps.openPageByProductCatalog();
    }

//    @When("User selects in dropdown sorting type - {string}")
//    public void selectSortingType(final String sortingType) {
//        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sortedList);
//        baseOperations.selectByDropdownText(searchResultsPage.sortedList, sortingType);
//        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sidebar);
//    }
//
//    @Then("User checks that products sorted by ascending")
//    public void checkSortingByAscending() {
//        actualProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
//        expectedProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
//        Collections.sort(expectedProductPriceList);
//
//        assertEquals(actualProductPriceList, expectedProductPriceList,
//                "Actual list by ascending price doesn't equals expected list. Actual product price list: "
//                        + actualProductPriceList + ". Expected product price list: " + expectedProductPriceList);
//    }
//
//    @Then("User checks that products sorted by descending")
//    public void checkSortingByDescending() {
//        actualProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
//        expectedProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
//        expectedProductPriceList.sort(Collections.reverseOrder());
//
//        assertEquals(actualProductPriceList, expectedProductPriceList,
//                "Actual list by descending price doesn't equals expected list. Actual product price list: "
//                        + actualProductPriceList + ". Expected product price list: " + expectedProductPriceList);
//    }
//
//    @When("User selects product firm")
//    public void selectProductFirm() {
//        notebooksPage = new NotebooksPage(baseStepDefinition.driver);
//        waitUtils.waitForElementVisibilityAfterMiddleWait(notebooksPage.lenovoFirmSelectButton);
//
//        baseOperations.clickButton(notebooksPage.lenovoFirmSelectButton);
//        waitUtils.waitForElementVisibilityAfterMiddleWait(searchResultsPage.sidebar);
//    }
//
//    @Then("User checks products contains in title selected firm")
//    public void checkProductFirm() {
//        js = new ActionsByJavaScript(baseStepDefinition.driver);
//        js.scrollByWebElement(notebooksPage.lenovoFirmSelectButton);
//        for (WebElement element : searchResultsPage.titleProductList) {
//            waitUtils.waitForElementVisibilityAfterMiddleWait(element);
//            assertTrue(element.getText().toLowerCase().contains(CHOSEN_NOTEBOOK_FIRM),
//                    "Element doesn't contains chosen firm: " + CHOSEN_NOTEBOOK_FIRM);
//        }
//    }
}