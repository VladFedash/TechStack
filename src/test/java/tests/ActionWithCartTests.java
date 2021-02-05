package tests;

import helpers.Action;
import helpers.BaseOperations;
import helpers.WaitUtils;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;

public class ActionWithCartTests extends BaseTest {
    private static final int EXPECTED_PRODUCT_AMOUNT_IN_CART_AFTER_ADD = 1;
    private static final String EXPECTED_CART_EMPTY_MESSAGE = "Корзина пуста";
    private static final String XIAOMI_SEARCH_WORD = "xiaomi";
    private static final String FIRST_PRODUCT_TITLE_XIAOMI = "Видеорегистратор Xiaomi Yi Smart Dash WiFi Gray International Edition";
    private static final String SECOND_PRODUCT_TITLE_XIAOMI = "IP-камера Xiaomi YI 1080p Home White YYS.2016";
    private static final String THIRD_PRODUCT_TITLE_XIAOMI = "IP-камера Xiaomi YI Dome X 360° 1080P White YYS.3017";

    @Test
    public void checkAddProductToCart() {
        WaitUtils waitUtils = new WaitUtils(driver);

        HomePage homePage = new HomePage(driver);
        new Action(driver).inputToSearchField(homePage.searchField, XIAOMI_SEARCH_WORD);

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
        searchResultsPage.addProductInCart(FIRST_PRODUCT_TITLE_XIAOMI);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.productCountInCart);

        int actualResult = Integer.parseInt(homePage.productCountInCart.getText().trim());
        assertEquals(actualResult, EXPECTED_PRODUCT_AMOUNT_IN_CART_AFTER_ADD,
                "Actual count in cart doesn't equals expected count. Actual count is: " + actualResult
                        + ". Expected amount is: " + EXPECTED_PRODUCT_AMOUNT_IN_CART_AFTER_ADD);
    }

    @Test
    public void checkRemoveProductFromCart() {
        WaitUtils waitUtils = new WaitUtils(driver);
        BaseOperations baseOperations = new BaseOperations(driver);

        HomePage homePage = new HomePage(driver);
        new Action(driver).inputToSearchField(homePage.searchField, XIAOMI_SEARCH_WORD);

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
        searchResultsPage.addProductInCart(FIRST_PRODUCT_TITLE_XIAOMI);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.productCountInCart);

        baseOperations.clickButton(homePage.openCartButton);
        baseOperations.clickButton(homePage.contextMenuButton);
        baseOperations.clickButton(homePage.deleteProductFromCartButton);

        waitUtils.waitForElementVisibilityAfterShortWait(homePage.emptyCartMessage);
        String actualResult = homePage.emptyCartMessage.getText();
        assertEquals(actualResult, EXPECTED_CART_EMPTY_MESSAGE,
                "Actual message about empty cart doesn't equals expected message. Actual message is: " + actualResult
                        + ". Expected message is: " + EXPECTED_CART_EMPTY_MESSAGE);
    }

    @Test
    public void checkSubtotalElementsInCart() {
        WaitUtils waitUtils = new WaitUtils(driver);
        BaseOperations baseOperations = new BaseOperations(driver);

        HomePage homePage = new HomePage(driver);
        homePage.inputToSearchFieldAndPressEnter(XIAOMI_SEARCH_WORD);
        new BasePage(driver).closeAdPopup();

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.addProductInCart(FIRST_PRODUCT_TITLE_XIAOMI);
        searchResultsPage.addProductInCart(SECOND_PRODUCT_TITLE_XIAOMI);
        searchResultsPage.addProductInCart(THIRD_PRODUCT_TITLE_XIAOMI);

        waitUtils.waitForElementVisibilityAfterLongWait(homePage.productCountInCart);

        baseOperations.clickButton(homePage.openCartButton);
        waitUtils.waitForElementVisibilityAfterLongWait(homePage.totalProductPriceInCart);

        int actualResult = baseOperations.getProductPrice(homePage.totalProductPriceInCart);
        assertEquals(actualResult, searchResultsPage.getProductDTOSubtotal(),
                "Actual product subtotal doesn't equals expected result. Actual result: " + actualResult
                        + "Expected subtotal: " + searchResultsPage.getProductDTOSubtotal());
    }
}