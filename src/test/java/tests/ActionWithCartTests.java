package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;

public class ActionWithCartTests extends BaseTest {
    private static final int EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART_AFTER_ADD = 1;
    private static final String EXPECTED_CART_EMPTY_MESSAGE = "Корзина пуста";
    private static final String NOKIA_SEARCH_WORD = "nokia";
    private static final String XIOMI_SEARCH_WORD = "xiomi";

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
        basePage.clickButton(searchResultsPage.addProductInCartButton.get(0));
        basePage.waitForElementVisibility(5, homePage.productCountInCart);
        int actualResult = Integer.parseInt(homePage.productCountInCart.getText().trim());
        assertEquals(actualResult, EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART_AFTER_ADD);
//        basePage.clickButton(searchResultsPage.addProductInCartButton.get(0));

    }

    @Test
    public void checkRemoveProductFromCart() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.searchField)
                .click()
                .keyDown(homePage.searchField, Keys.SHIFT)
                .sendKeys(homePage.searchField, NOKIA_SEARCH_WORD, Keys.ENTER)
                .perform();
        basePage.clickButton(searchResultsPage.addProductInCartButton.get(0));
        basePage.waitForElementVisibility(5, homePage.productCountInCart);

        basePage.clickButton(searchResultsPage.addProductInCartButton.get(0));
        basePage.clickButton(homePage.contextMenuButton);
        basePage.clickButton(homePage.deleteProductFromCartButton);

        basePage.waitForElementVisibility(5, homePage.emptyCartMessage);
        String actualResult = homePage.emptyCartMessage.getText();
        assertEquals(actualResult, EXPECTED_CART_EMPTY_MESSAGE);
    }

    @Test
    public void checkSubtotalElementsInCart() {
        int expectedResult = 0;
        homePage.inputToSearchField(XIOMI_SEARCH_WORD);

        if (basePage.elementIsVisible(10, searchResultsPage.banner))
            basePage.clickButton(searchResultsPage.closeAdButton);

        searchResultsPage.clickAddVisibleProductInCartButton();
        basePage.waitForElementVisibility(30, homePage.productCountInCart);
        basePage.clickButton(homePage.openCartButton);
        basePage.waitForElementVisibility(15, homePage.totalProductPriceInCart);

        for (WebElement element : homePage.productPriceListInCart) {
            basePage.waitForElementVisibility(5, element);
            expectedResult += Integer.parseInt(element.getText().replaceAll("[^0-9]", ""));
        }

        int actualResult = Integer.parseInt(homePage.totalProductPriceInCart.getText().replaceAll("[^0-9]", ""));
        assertEquals(actualResult, expectedResult);
    }
}