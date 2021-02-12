package steps;

import helpers.ActionsByJavaScript;
import helpers.BaseOperations;
import helpers.WaitUtils;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.NotebooksPage;
import pages.SearchResultsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SortingSteps {
    WebDriver driver;

    private static final String CHOSEN_NOTEBOOK_FIRM = "lenovo";
    private static List<Integer> actualProductPriceList;
    private static List<Integer> expectedProductPriceList;

    public SortingSteps(WebDriver driver) {
        this.driver = driver;
    }

    HomePage homePage;
    WaitUtils waitUtils;
    ActionsByJavaScript js;
    NotebooksPage notebooksPage;
    BaseOperations baseOperations;
    SearchResultsPage searchResultsPage;

    @Step
    public void openPageByProductCatalog() {
        baseOperations = new BaseOperations(driver);
        waitUtils = new WaitUtils(driver);
        homePage = new HomePage(driver);
        baseOperations.clickButton(homePage.catalog);
        baseOperations.clickButton(homePage.notebooksPageOpenButton);

        searchResultsPage = new SearchResultsPage(driver);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sortedList);
    }


    @Step
    public void selectSortingType(String sortingType) {
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sortedList);
        baseOperations.selectByDropdownText(searchResultsPage.sortedList, sortingType);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sidebar);
    }

    @Step
    public void checkSortingByAscending() {
        actualProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
        expectedProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
        Collections.sort(expectedProductPriceList);

        assertEquals(actualProductPriceList, expectedProductPriceList,
                "Actual list by ascending price doesn't equals expected list. Actual product price list: "
                        + actualProductPriceList + ". Expected product price list: " + expectedProductPriceList);
    }

    @Step
    public void checkSortingByDescending() {
        actualProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
        expectedProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
        expectedProductPriceList.sort(Collections.reverseOrder());

        assertEquals(actualProductPriceList, expectedProductPriceList,
                "Actual list by descending price doesn't equals expected list. Actual product price list: "
                        + actualProductPriceList + ". Expected product price list: " + expectedProductPriceList);
    }

    @Step
    public void selectProductFirm() {
        notebooksPage = new NotebooksPage(driver);
        waitUtils.waitForElementVisibilityAfterMiddleWait(notebooksPage.lenovoFirmSelectButton);

        baseOperations.clickButton(notebooksPage.lenovoFirmSelectButton);
        waitUtils.waitForElementVisibilityAfterMiddleWait(searchResultsPage.sidebar);
    }

    @Step
    public void checkProductFirm() {
        js = new ActionsByJavaScript(driver);
        js.scrollByWebElement(notebooksPage.lenovoFirmSelectButton);
        for (WebElement element : searchResultsPage.titleProductList) {
            waitUtils.waitForElementVisibilityAfterMiddleWait(element);
            assertTrue(element.getText().toLowerCase().contains(CHOSEN_NOTEBOOK_FIRM),
                    "Element doesn't contains chosen firm: " + CHOSEN_NOTEBOOK_FIRM);
        }
    }
}