package tests;

import helpers.BaseOperations;
import helpers.WaitUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ActionWithCartTests extends BaseTest {
    private static final int EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART_AFTER_ADD = 1;
    private static final String EXPECTED_CART_EMPTY_MESSAGE_RU = "Корзина пуста";
    private static final String NOKIA_SEARCH_WORD = "nokia";
    private static final String XIOMI_SEARCH_WORD = "xiomi";

    BaseOperations baseOperations = new BaseOperations(driver);
    WaitUtils waitUtils = new WaitUtils(driver);
    BasePage basePage = new BasePage(driver);
    HomePage homePage = new HomePage(driver);
    SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

    @Test
    public void checkAddProductToCart() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.searchField)
                .click()
                .keyDown(homePage.searchField, Keys.SHIFT)
                .sendKeys(homePage.searchField, NOKIA_SEARCH_WORD, Keys.ENTER)
                .perform();
        baseOperations.clickButton(searchResultsPage.addProductInCartButton.get(0));
        waitUtils.waitForElementVisibilityShort(homePage.productCountInCart);
        int actualResult = Integer.parseInt(homePage.productCountInCart.getText().trim());
        assertEquals(actualResult, EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART_AFTER_ADD);
    }

    @Test
    public void checkRemoveProductFromCart() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.searchField)
                .click()
                .keyDown(homePage.searchField, Keys.SHIFT)
                .sendKeys(homePage.searchField, NOKIA_SEARCH_WORD, Keys.ENTER)
                .perform();
        baseOperations.clickButton(searchResultsPage.addProductInCartButton.get(0));
        waitUtils.waitForElementVisibilityShort(homePage.productCountInCart);

        baseOperations.clickButton(searchResultsPage.addProductInCartButton.get(0));
        baseOperations.clickButton(homePage.contextMenuButton);
        baseOperations.clickButton(homePage.deleteProductFromCartButton);

        waitUtils.waitForElementVisibilityShort(homePage.emptyCartMessage);
        String actualResult = homePage.emptyCartMessage.getText();
        assertEquals(actualResult, EXPECTED_CART_EMPTY_MESSAGE_RU);
    }

    @Test
    public void checkSubtotalElementsInCart() {
        int expectedResult = 0;
        homePage.inputToSearchFieldAndPressEnter(XIOMI_SEARCH_WORD);
        basePage.closeAdPopup();

        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.addProductInCartButton);
        searchResultsPage.clickAddVisibleProductInCartButton();
        waitUtils.waitForElementVisibilityLong(homePage.productCountInCart);
        baseOperations.clickButton(homePage.openCartButton);

        waitUtils.waitForElementVisibilityLong(homePage.totalProductPriceInCart);

        for (WebElement element : homePage.productPriceListInCart) {
            assertTrue(element.isEnabled());
            waitUtils.waitForElementVisibilityShort(element);
            expectedResult += Integer.parseInt(element.getText().replaceAll("[^0-9]", ""));
        }

        int actualResult = Integer.parseInt(homePage.totalProductPriceInCart.getText().replaceAll("[^0-9]", ""));
        assertEquals(actualResult, expectedResult);
    }
}