package step_definitions;

import helpers.ActionsByJavaScript;
import helpers.BaseOperations;
import helpers.WaitUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
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

    private static final String CHOSEN_NOTEBOOK_FIRM = "lenovo";
    private static List<Integer> actualProductPriceList;
    private static List<Integer> expectedProductPriceList;

    HomePage homePage;
    WaitUtils waitUtils;
    ActionsByJavaScript js;
    NotebooksPage notebooksPage;
    BaseOperations baseOperations;
    SearchResultsPage searchResultsPage;

    public DefinitionStepsForSorting(BaseStepDefinition baseStepDefinition) {
        homePage = new HomePage(baseStepDefinition.driver);
        waitUtils = new WaitUtils(baseStepDefinition.driver);
        js = new ActionsByJavaScript(baseStepDefinition.driver);
        notebooksPage = new NotebooksPage(baseStepDefinition.driver);
        baseOperations = new BaseOperations(baseStepDefinition.driver);
        searchResultsPage = new SearchResultsPage(baseStepDefinition.driver);
    }

    @When("User opens notebook page")
    public void openPageByProductCatalog() {
        waitUtils.waitForStaleElements(homePage.catalog);
        baseOperations.clickButton(homePage.catalog);
        waitUtils.waitForStaleElements(homePage.notebooksPageOpenButton);
        baseOperations.clickButton(homePage.notebooksPageOpenButton);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sortedList);
    }

    @When("User selects in dropdown sorting type - {string}")
    public void selectSortingType(String sortingType) {
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sortedList);
        baseOperations.selectByDropdownText(searchResultsPage.sortedList, sortingType);
        waitUtils.waitForDataLoading(By.xpath(searchResultsPage.SPINNER));
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
        waitUtils.waitForElementVisibilityAfterMiddleWait(notebooksPage.lenovoFirmSelectButton);
        baseOperations.clickButton(notebooksPage.lenovoFirmSelectButton);
        waitUtils.waitForDataLoading(By.xpath(searchResultsPage.SPINNER));
    }

    @Then("User checks products contains in title selected firm")
    public void checkProductFirm() {
        js.scrollByWebElement(notebooksPage.lenovoFirmSelectButton);
        for (WebElement element : searchResultsPage.titleProductList) {
            waitUtils.waitForElementVisibilityAfterMiddleWait(element);
            assertTrue(element.getText().toLowerCase().contains(CHOSEN_NOTEBOOK_FIRM),
                    "Element doesn't contains chosen firm: " + CHOSEN_NOTEBOOK_FIRM);
        }
    }
}