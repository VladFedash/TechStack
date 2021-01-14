package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import pages.BasePage;
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

    BasePage basePage = new BasePage(driver);
    HomePage homePage = new HomePage(driver);
    SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
    NotebooksPage notebooksPage = new NotebooksPage(driver);

    @Test
    public void checkCorrectSortingProductAscending() {
        basePage.clickButton(homePage.catalog);
        basePage.clickButton(homePage.notebooksPageOpenButton);
        basePage.waitForElementVisibility(5, notebooksPage.sortedList);

        Select objSelect = new Select(notebooksPage.sortedList);
        objSelect.selectByVisibleText(SORTING_BY_ASCENDING_KEYWORD);

        List<Integer> actualProductPriceList = new ArrayList<>();

        for (int i = 0; i < notebooksPage.productPriceList.size(); i++) {
            basePage.waitForElementVisibility(5, notebooksPage.productPriceList.get(i));
            actualProductPriceList.add(Integer.parseInt(notebooksPage.productPriceList.get(i)
                    .getText().replaceAll("[^0-9]", "")));
        }

        List<Integer> expectedProductPriceList = new ArrayList<>(actualProductPriceList);
        Collections.sort(expectedProductPriceList);

        for (int i = 0; i < actualProductPriceList.size(); i++) {
            assertEquals(actualProductPriceList.get(i), expectedProductPriceList.get(i));
        }
    }

    @Test
    public void checkCorrectSortingProductDescending() {
        basePage.clickButton(homePage.catalog);
        basePage.clickButton(homePage.notebooksPageOpenButton);
        basePage.waitForElementVisibility(5, notebooksPage.sortedList);

        Select objSelect = new Select(notebooksPage.sortedList);
        objSelect.selectByVisibleText(SORTING_BY_DESCENDING_KEYWORD);
        driver.navigate().refresh();

        List<Integer> actualProductPriceList = new ArrayList<>();

        for (int i = 0; i < notebooksPage.productPriceList.size(); i++) {
            basePage.waitForElementVisibility(10, notebooksPage.productPriceList.get(i));
            actualProductPriceList.add(Integer.parseInt(notebooksPage.productPriceList.get(i)
                    .getText().replaceAll("[^0-9]", "")));
        }

        List<Integer> expectedProductPriceList = new ArrayList<>(actualProductPriceList);
        Collections.sort(expectedProductPriceList, Collections.reverseOrder());

        for (int i = 0; i < actualProductPriceList.size(); i++) {
            assertEquals(actualProductPriceList.get(i), expectedProductPriceList.get(i));
        }
    }

    @Test
    public void checkCorrectSortingProductByFirmName() {
        basePage.clickButton(homePage.catalog);
        basePage.clickButton(homePage.notebooksPageOpenButton);
        basePage.waitForElementVisibility(5, notebooksPage.acerFirmSelectButton);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");

        basePage.waitForElementVisibility(5, notebooksPage.sortedList);
        basePage.clickButton(notebooksPage.acerFirmSelectButton);
        driver.navigate().refresh();

        for (WebElement element : searchResultsPage.titleProductList) {
            assertTrue(element.getText().contains(CHOSEN_NOTEBOOK_FIRM));
        }
    }
}