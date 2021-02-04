package tests;

import helpers.ActionsByJavaScript;
import helpers.BaseOperations;
import helpers.WaitUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NotebooksPage;
import pages.SearchResultsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SortingTests extends BaseTest {
    private static final String SORTING_BY_ASCENDING_KEYWORD = "От дешевых к дорогим";
    private static final String SORTING_BY_DESCENDING_KEYWORD = "От дорогих к дешевым";
    private static final String CHOSEN_NOTEBOOK_FIRM = "lenovo";

    @Test
    public void checkCorrectSortingProductAscending() {
        BaseOperations baseOperations = new BaseOperations(driver);
        WaitUtils waitUtils = new WaitUtils(driver);

        HomePage homePage = new HomePage(driver);
        baseOperations.clickButton(homePage.catalog);
        baseOperations.clickButton(homePage.notebooksPageOpenButton);

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sortedList);

        baseOperations.selectByDropdownText(searchResultsPage.sortedList, SORTING_BY_ASCENDING_KEYWORD);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sidebar);
        List<Integer> actualProductPriceList = new ArrayList<>();

        searchResultsPage.productPriceList.forEach(productPrice -> {
            waitUtils.waitForElementVisibilityAfterMiddleWait(productPrice);
            actualProductPriceList.add(baseOperations.getProductPrice(productPrice));
        });

        List<Integer> expectedProductPriceList = new ArrayList<>(actualProductPriceList);
        Collections.sort(expectedProductPriceList);

        assertEquals(actualProductPriceList, expectedProductPriceList,
                "Actual list by ascending price doesn't equals expected list. Actual product price list: "
                        + actualProductPriceList + ". Expected product price list: " + expectedProductPriceList);
    }

    @Test
    public void checkCorrectSortingProductDescending() {
        BaseOperations baseOperations = new BaseOperations(driver);
        WaitUtils waitUtils = new WaitUtils(driver);

        HomePage homePage = new HomePage(driver);
        baseOperations.clickButton(homePage.catalog);
        baseOperations.clickButton(homePage.notebooksPageOpenButton);

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sortedList);

        baseOperations.selectByDropdownText(searchResultsPage.sortedList, SORTING_BY_DESCENDING_KEYWORD);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sidebar);
        List<Integer> actualProductPriceList = new ArrayList<>();

        searchResultsPage.productPriceList.forEach(productPrice -> {
            waitUtils.waitForElementVisibilityAfterMiddleWait(productPrice);
            actualProductPriceList.add(baseOperations.getProductPrice(productPrice));
        });

        List<Integer> expectedProductPriceList = new ArrayList<>(actualProductPriceList);
        expectedProductPriceList.sort(Collections.reverseOrder());

        assertEquals(actualProductPriceList, expectedProductPriceList,
                "Actual list by descending price doesn't equals expected list. Actual product price list: "
                        + actualProductPriceList + "Expected product price list: " + expectedProductPriceList);
    }

    @Test
    public void checkCorrectSortingProductByFirmName() {
        BaseOperations baseOperations = new BaseOperations(driver);
        WaitUtils waitUtils = new WaitUtils(driver);

        HomePage homePage = new HomePage(driver);
        baseOperations.clickButton(homePage.catalog);
        baseOperations.clickButton(homePage.notebooksPageOpenButton);

        NotebooksPage notebooksPage = new NotebooksPage(driver);
        waitUtils.waitForElementVisibilityAfterMiddleWait(notebooksPage.lenovoFirmSelectButton);

        new ActionsByJavaScript(driver).scrollByWebElement(notebooksPage.lenovoFirmSelectButton);

        baseOperations.clickButton(notebooksPage.lenovoFirmSelectButton);

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        waitUtils.waitForElementVisibilityAfterMiddleWait(searchResultsPage.sidebar);

        for (WebElement element : searchResultsPage.titleProductList) {
            waitUtils.waitForElementVisibilityAfterMiddleWait(element);
            assertTrue(element.getText().toLowerCase().contains(CHOSEN_NOTEBOOK_FIRM),
                    "Element doesn't contains chosen firm: " + CHOSEN_NOTEBOOK_FIRM);
        }
    }
}