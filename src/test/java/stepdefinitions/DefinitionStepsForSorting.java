package stepdefinitions;

import helpers.ActionsByJavaScript;
import helpers.BaseOperations;
import helpers.WaitUtils;
import io.cucumber.java.en.Given;
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

    private final Hook hook;

    public DefinitionStepsForSorting(Hook hook) {
        this.hook = hook;
    }

    private static final String SORTING_BY_ASCENDING_KEYWORD = "От дешевых к дорогим";
    private static final String SORTING_BY_DESCENDING_KEYWORD = "От дорогих к дешевым";
    private static final String CHOSEN_NOTEBOOK_FIRM = "lenovo";
    private static List<Integer> actualProductPriceList;
    private static List<Integer> expectedProductPriceList;

    HomePage homePage;
    WaitUtils waitUtils;
    ActionsByJavaScript js;
    NotebooksPage notebooksPage;
    BaseOperations baseOperations;
    SearchResultsPage searchResultsPage;

    @Given("User opens notebook page")
    public void openPageByProductCatalog() {
        baseOperations = new BaseOperations(hook.driver);
        waitUtils = new WaitUtils(hook.driver);
        homePage = new HomePage(hook.driver);
        baseOperations.clickButton(homePage.catalog);
        baseOperations.clickButton(homePage.notebooksPageOpenButton);

        searchResultsPage = new SearchResultsPage(hook.driver);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sortedList);
    }

    @When("User selects in dropdown sorting type - {string}")
    public void selectSortingType(final String sortingType) {
        baseOperations.selectByDropdownText(searchResultsPage.sortedList, sortingType);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sidebar);
    }

    @When("User waits when products are sorting by {string}")
    public void sortingByAscending(final String sortingType) {
        searchResultsPage.productPriceList.forEach(productPrice -> {
            waitUtils.waitForElementVisibilityAfterMiddleWait(productPrice);
            actualProductPriceList = new ArrayList<>();
            actualProductPriceList.add(baseOperations.getProductPrice(productPrice));
            expectedProductPriceList = new ArrayList<>(actualProductPriceList);
        });
        switch (sortingType) {
            case SORTING_BY_ASCENDING_KEYWORD -> Collections.sort(expectedProductPriceList);
            case SORTING_BY_DESCENDING_KEYWORD -> expectedProductPriceList.sort(Collections.reverseOrder());
        }
    }

    @Then("User checks that products sorted by selected sorting")
    public void checkSortingBeAscending() {

        assertEquals(actualProductPriceList, expectedProductPriceList,
                "Actual list by ascending price doesn't equals expected list. Actual product price list: "
                        + actualProductPriceList + ". Expected product price list: " + expectedProductPriceList);
    }

    @When("User selects product firm")
            public void selectProductFirm() {
        notebooksPage = new NotebooksPage(hook.driver);
        waitUtils.waitForElementVisibilityAfterMiddleWait(notebooksPage.lenovoFirmSelectButton);

        baseOperations.clickButton(notebooksPage.lenovoFirmSelectButton);
        waitUtils.waitForElementVisibilityAfterMiddleWait(searchResultsPage.sidebar);
    }

    @Then("User checks products contains in title selected firm")
            public void checkProductFirm(){
        js = new ActionsByJavaScript(hook.driver);
        js.scrollByWebElement(notebooksPage.lenovoFirmSelectButton);
        for (WebElement element : searchResultsPage.titleProductList) {
            waitUtils.waitForElementVisibilityAfterMiddleWait(element);
            assertTrue(element.getText().toLowerCase().contains(CHOSEN_NOTEBOOK_FIRM),
                    "Element doesn't contains chosen firm: " + CHOSEN_NOTEBOOK_FIRM);
        }
    }
}