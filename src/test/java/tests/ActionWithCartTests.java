package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ActionWithCartTests extends BaseTest {
    private static final int EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART_AFTER_ADD = 1;
    private static final String EXPECTED_CART_EMPTY_MESSAGE = "Корзина пуста";
    private static final String MACBOOK_SEARCH_WORD = "macbook";
    private static final String XIOMI_SEARCH_WORD = "xiomi";

    @Test
    public void checkAddProductToCart() {
        Actions action = new Actions(getBasePage().driver);
        action.moveToElement(getHomePage().getSearchField())
                .click()
                .keyDown(getHomePage().getSearchField(), Keys.SHIFT)
                .sendKeys(getHomePage().getSearchField(), MACBOOK_SEARCH_WORD, Keys.ENTER)
                .perform();
        getSearchPage().clickAddFistProductInCartButton();
        getBasePage().waitForElementVisibility(5, getHomePage().getProductCountInCart());
        int actualResult = Integer.parseInt(getHomePage().getProductCountInCart().getText().trim());
        assertEquals(actualResult, EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART_AFTER_ADD);

    }

    @Test
    public void checkRemoveProductFromCart() {
        Actions action = new Actions(getBasePage().driver);
        action.moveToElement(getHomePage().getSearchField())
                .click()
                .keyDown(getHomePage().getSearchField(), Keys.SHIFT)
                .sendKeys(getHomePage().getSearchField(), MACBOOK_SEARCH_WORD, Keys.ENTER)
                .perform();
        getSearchPage().clickAddFistProductInCartButton();
        getBasePage().waitForElementVisibility(5, getHomePage().getProductCountInCart());

        getSearchPage().clickAddFistProductInCartButton();
        getBasePage().waitForElementVisibility(10, getHomePage().getContextMenuButton());
        getHomePage().clickContextMenuButton();
        getBasePage().waitForElementVisibility(5, getHomePage().getDeleteProductFromCartButton());
        getHomePage().clickDeleteProductFromCartButton();

        getBasePage().waitForElementVisibility(5, getHomePage().getEmptyCartMessage());
        String actualResult = getHomePage().getEmptyCartMessage().getText();
        assertEquals(actualResult, EXPECTED_CART_EMPTY_MESSAGE);
    }

    @Test
    public void checkSubtotalElementsInCart() {
        getHomePage().inputToSearchField(XIOMI_SEARCH_WORD);
        getBasePage().waitForElementVisibility(10, getSearchPage().getBanner());
        getBasePage().waitForElementVisibility(10, getSearchPage().getCloseAdButton());
        getSearchPage().clickCloseAd();

        getSearchPage().clickAddProductInCartButton();
        getBasePage().waitForElementVisibility(30, getHomePage().getProductCountInCart());
        getHomePage().ClickOpenCartButton();
        int expectedResult = 0;

        getBasePage().waitForElementVisibility(10, getHomePage().getContextMenuButton());
        for (WebElement element : getHomePage().getProductPriceListInCart()) {
            expectedResult += Integer.parseInt(element.getText().replaceAll("[^0-9]", ""));
        }
        assertEquals(getHomePage().getTotalProductPriceInCart(), expectedResult);
    }
}
