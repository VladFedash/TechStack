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

    protected String chosenNotebookFirm;
    private static List<Integer> actualProductPriceList;
    private static List<Integer> expectedProductPriceList;

    protected HomePage homePage;
    protected WaitUtils waitUtils;
    protected ActionsByJavaScript js;
    protected NotebooksPage notebooksPage;
    protected BaseOperations baseOperations;
    protected SearchResultsPage searchResultsPage;

    public DefinitionStepsForSorting(BaseStepDefinition baseStepDefinition) {
        homePage = new HomePage(baseStepDefinition.driver);
        waitUtils = new WaitUtils(baseStepDefinition.driver);
        js = new ActionsByJavaScript(baseStepDefinition.driver);
        notebooksPage = new NotebooksPage(baseStepDefinition.driver);
        baseOperations = new BaseOperations(baseStepDefinition.driver);
        searchResultsPage = new SearchResultsPage(baseStepDefinition.driver);
    }

    @When("User opens chosen {string} page")
    public void openPageByProductCatalog(String productName) {
        baseOperations.clickButtonForStaleElements(homePage.catalog);
        baseOperations.clickButtonForStaleElements(homePage.getProductByName(productName));
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
                String.format("Actual list by ascending price doesn't equals expected list. Actual product price list: %s. Expected product price list: %s",
                        actualProductPriceList, expectedProductPriceList));
    }

    @Then("User checks that products sorted by descending")
    public void checkSortingByDescending() {
        actualProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
        expectedProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
        expectedProductPriceList.sort(Collections.reverseOrder());

        assertEquals(actualProductPriceList, expectedProductPriceList,
                String.format("Actual list by descending price doesn't equals expected list. Actual product price list: %s. Expected product price list: %s",
                        actualProductPriceList, expectedProductPriceList));
    }

    @When("User selects product firm - {string}")
    public void selectProductFirm(String productFirm) {
        chosenNotebookFirm = productFirm;
        baseOperations.clickButton(notebooksPage.getProductFirm(chosenNotebookFirm));
        waitUtils.waitForDataLoading(By.xpath(searchResultsPage.SPINNER));
    }

    @Then("User checks products contains in title selected firm")
    public void checkProductFirm() {
        js.scrollByWebElement(notebooksPage.getProductFirm(chosenNotebookFirm));
        for (WebElement element : searchResultsPage.titleProductList) {
            waitUtils.waitForElementVisibilityAfterMiddleWait(element);
            assertTrue(element.getText().toLowerCase().contains(chosenNotebookFirm.toLowerCase()),
                    String.format("Element doesn't contains chosen firm: %s", chosenNotebookFirm.toLowerCase()));
        }
    }
}