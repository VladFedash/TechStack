package tests;

import helpers.BaseOperations;
import helpers.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
    private static final String CHOSEN_NOTEBOOK_FIRM = "Acer";

    BaseOperations baseOperations = new BaseOperations(driver);
    WaitUtils waitUtils = new WaitUtils(driver);
    HomePage homePage = new HomePage(driver);
    SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
    NotebooksPage notebooksPage = new NotebooksPage(driver);

    @Test
    public void checkCorrectSortingProductAscending() {
        baseOperations.clickButton(homePage.catalog);
        baseOperations.clickButton(homePage.notebooksPageOpenButton);
        waitUtils.waitForElementVisibilityAfterShortWait(notebooksPage.sortedList);

        Select objSelect = new Select(notebooksPage.sortedList);
        objSelect.selectByVisibleText(SORTING_BY_ASCENDING_KEYWORD);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sidebar);
        List<Integer> actualProductPriceList = new ArrayList<>();

        searchResultsPage.productPriceList.forEach(productPrice -> {
            waitUtils.waitForElementVisibilityAfterShortWait(productPrice);
            actualProductPriceList.add(baseOperations.getProductPriceWithNumericalSymbols(productPrice));
        });

        List<Integer> expectedProductPriceList = new ArrayList<>(actualProductPriceList);
        Collections.sort(expectedProductPriceList);

        for (int i = 0; i < actualProductPriceList.size(); i++) {
            assertEquals(actualProductPriceList.get(i), expectedProductPriceList.get(i));
        }
    }

    @Test
    public void checkCorrectSortingProductDescending() {
        baseOperations.clickButton(homePage.catalog);
        baseOperations.clickButton(homePage.notebooksPageOpenButton);
        waitUtils.waitForElementVisibilityAfterShortWait(notebooksPage.sortedList);

        Select objSelect = new Select(notebooksPage.sortedList);
        objSelect.selectByVisibleText(SORTING_BY_DESCENDING_KEYWORD);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sidebar);
        List<Integer> actualProductPriceList = new ArrayList<>();

        searchResultsPage.productPriceList.forEach(productPrice -> {
            waitUtils.waitForElementVisibilityAfterShortWait(productPrice);
            actualProductPriceList.add(baseOperations.getProductPriceWithNumericalSymbols(productPrice));
        });

        List<Integer> expectedProductPriceList = new ArrayList<>(actualProductPriceList);
        expectedProductPriceList.sort(Collections.reverseOrder());

        for (int i = 0; i < actualProductPriceList.size(); i++) {
            assertEquals(actualProductPriceList.get(i), expectedProductPriceList.get(i));
        }
    }

    @Test
    public void checkCorrectSortingProductByFirmName() {
        baseOperations.clickButton(homePage.catalog);
        baseOperations.clickButton(homePage.notebooksPageOpenButton);
        waitUtils.waitForElementVisibilityAfterShortWait(notebooksPage.acerFirmSelectButton);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");

        waitUtils.waitForElementVisibilityAfterShortWait(notebooksPage.sortedList);
        baseOperations.clickButton(notebooksPage.acerFirmSelectButton);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sidebar);

        for (WebElement element : searchResultsPage.titleProductList) {
            assertTrue(element.getText().contains(CHOSEN_NOTEBOOK_FIRM));
        }
    }
}