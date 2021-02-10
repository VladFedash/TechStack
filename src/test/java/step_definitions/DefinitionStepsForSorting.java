package step_definitions;

import helpers.ActionsByJavaScript;
import helpers.BaseOperations;
import helpers.WaitUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.NotebooksPage;
import pages.SearchResultsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DefinitionStepsForSorting {

    private final BaseStepDefinition baseStepDefinition;
    private static final String CHOSEN_NOTEBOOK_FIRM = "lenovo";
    private static List<Integer> actualProductPriceList;
    private static List<Integer> expectedProductPriceList;

    public DefinitionStepsForSorting(BaseStepDefinition baseStepDefinition) {
        this.baseStepDefinition = baseStepDefinition;
    }

    HomePage homePage;
    WaitUtils waitUtils;
    ActionsByJavaScript js;
    NotebooksPage notebooksPage;
    BaseOperations baseOperations;
    SearchResultsPage searchResultsPage;

    @When("User opens notebook page")
    public void openPageByProductCatalog() {
        baseOperations = new BaseOperations(baseStepDefinition.driver);
        waitUtils = new WaitUtils(baseStepDefinition.driver);
        homePage = new HomePage(baseStepDefinition.driver);
        baseOperations.clickButton(homePage.catalog);
        baseOperations.clickButton(homePage.notebooksPageOpenButton);

        searchResultsPage = new SearchResultsPage(baseStepDefinition.driver);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sortedList);
    }

    @When("User selects in dropdown sorting type - {string}")
    public void selectSortingType(final String sortingType) {
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sortedList);
        baseOperations.selectByDropdownText(searchResultsPage.sortedList, sortingType);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sidebar);
    }

    @Then("User checks that products sorted by ascending")
    public void checkSortingByAscending() {
        actualProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
        expectedProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
        Collections.sort(expectedProductPriceList);

        assertEquals(actualProductPriceList, expectedProductPriceList,
                "Actual list by ascending price doesn't equals expected list. Actual product price list: "
                        + actualProductPriceList + ". Expected product price list: " + expectedProductPriceList);
    }

    @Then("User checks that products sorted by descending")
    public void checkSortingByDescending() {
        actualProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
        expectedProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
        expectedProductPriceList.sort(Collections.reverseOrder());

        assertEquals(actualProductPriceList, expectedProductPriceList,
                "Actual list by descending price doesn't equals expected list. Actual product price list: "
                        + actualProductPriceList + ". Expected product price list: " + expectedProductPriceList);
    }

    @When("User selects product firm")
    public void selectProductFirm() {
        notebooksPage = new NotebooksPage(baseStepDefinition.driver);
        waitUtils.waitForElementVisibilityAfterMiddleWait(notebooksPage.lenovoFirmSelectButton);

        baseOperations.clickButton(notebooksPage.lenovoFirmSelectButton);
        waitUtils.waitForElementVisibilityAfterMiddleWait(searchResultsPage.sidebar);
    }

    @Then("User checks products contains in title selected firm")
    public void checkProductFirm() {
        js = new ActionsByJavaScript(baseStepDefinition.driver);
        js.scrollByWebElement(notebooksPage.lenovoFirmSelectButton);
        for (WebElement element : searchResultsPage.titleProductList) {
            waitUtils.waitForElementVisibilityAfterMiddleWait(element);
            assertTrue(element.getText().toLowerCase().contains(CHOSEN_NOTEBOOK_FIRM),
                    "Element doesn't contains chosen firm: " + CHOSEN_NOTEBOOK_FIRM);
        }
    }
}