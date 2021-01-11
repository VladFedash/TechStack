package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SortingTests extends BaseTest {
    private static final String SORTING_BY_ASCENDING_KEYWORD = "От дешевых к дорогим";
    private static final String SORTING_BY_DESCENDING_KEYWORD = "От дорогих к дешевым";
    private static final String CHOSEN_NOTEBOOK_FIRM = "HP";

    @Test
    public void checkCorrectSortingProductAscending() {
        getHomePage().clickCatalogButton();
        getHomePage().clickNotebooksPageOpenButton();
        getBasePage().waitForElementVisibility(5, getNotebooksPage().getSortedList());

        Select objSelect = new Select(getNotebooksPage().getSortedList());
        objSelect.selectByVisibleText(SORTING_BY_ASCENDING_KEYWORD);


        for (int i = 0; i < getNotebooksPage().getProductPriceList().size() - 1; i++) {
            assertTrue(Integer.parseInt(getNotebooksPage().getProductPriceList().get(i).getText().replace(" ", ""))
                    <= Integer.parseInt(getNotebooksPage().getProductPriceList().get(i + 1).getText().replace(" ", "")));
        }
    }

    @Test
    public void checkCorrectSortingProductDescending() {
        getHomePage().clickCatalogButton();
        getHomePage().clickNotebooksPageOpenButton();
        getBasePage().waitForElementVisibility(5, getNotebooksPage().getSortedList());

        Select objSelect = new Select(getNotebooksPage().getSortedList());
        objSelect.selectByVisibleText(SORTING_BY_DESCENDING_KEYWORD);

        for (int i = 0; i < getNotebooksPage().getProductPriceList().size() - 1; i++) {
            assertTrue(Integer.parseInt(getNotebooksPage().getProductPriceList().get(i).getText().replace(" ", ""))
                    >= Integer.parseInt(getNotebooksPage().getProductPriceList().get(i + 1).getText().replace(" ", "")));
        }
    }

    @Test
    public void checkCorrectSortingProductByFirmName() {
        getHomePage().clickCatalogButton();
        getHomePage().clickNotebooksPageOpenButton();
        getBasePage().waitForElementVisibility(5, getNotebooksPage().getHpFirmSelectButton());

        JavascriptExecutor js = (JavascriptExecutor) getBasePage().driver;
        js.executeScript("window.scrollBy(0,600)");
        getBasePage().waitForElementVisibility(5, getNotebooksPage().getSortedList());

        getNotebooksPage().clickHpFirmSelectButton();
        getBasePage().driver.navigate().refresh();

        for (WebElement element : getSearchPage().getTitleProductList()) {
            assertTrue(element.getText().replaceAll("Н", "H").replaceAll("Р", "P").contains(CHOSEN_NOTEBOOK_FIRM));
              System.out.println(element.getText());
        }
    }
}
