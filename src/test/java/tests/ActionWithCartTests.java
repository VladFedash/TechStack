package tests;

import helpers.BaseOperations;
import helpers.WaitUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.SearchResultsPage;
import values.Item;
import values.ProductDTO;

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
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.addProductInCartButton);
        baseOperations.clickButton(searchResultsPage.addProductInCartButton.get(0));
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.productCountInCart);

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
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.productCountInCart);

        baseOperations.clickButton(homePage.openCartButton);
        baseOperations.clickButton(homePage.contextMenuButton);
        baseOperations.clickButton(homePage.deleteProductFromCartButton);

        waitUtils.waitForElementVisibilityAfterShortWait(homePage.emptyCartMessage);
        String actualResult = homePage.emptyCartMessage.getText();
        assertEquals(actualResult, EXPECTED_CART_EMPTY_MESSAGE_RU);
    }

    @Test
    public void checkSubtotalElementsInCart() {
        ProductDTO productDTO = new ProductDTO();
        homePage.inputToSearchFieldAndPressEnter(XIOMI_SEARCH_WORD);
        basePage.closeAdPopup();

        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.addProductInCartButton);
        searchResultsPage.clickAddVisibleProductInCartButton();
        waitUtils.waitForElementVisibilityAfterLongWait(homePage.productCountInCart);
        for (WebElement element : searchResultsPage.productPriceList) {
            assertTrue(element.isEnabled());
            productDTO.addItem(new Item(baseOperations.getProductPriceWithNumericalSymbols(element)));
        }
        baseOperations.clickButton(homePage.openCartButton);
        waitUtils.waitForElementVisibilityAfterLongWait(homePage.totalProductPriceInCart);

        int actualResult = baseOperations.getProductPriceWithNumericalSymbols(homePage.totalProductPriceInCart);
        assertEquals(actualResult, productDTO.subtotal);
    }
}