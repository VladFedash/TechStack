package steps;

import helpers.Action;
import helpers.BaseOperations;
import helpers.WaitUtils;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;

public class CartActionsSteps {
    WebDriver driver;

    public CartActionsSteps(WebDriver driver) {
        this.driver = driver;
    }

    Action action;
    HomePage homePage;
    BasePage basePage;
    WaitUtils waitUtils;
    BaseOperations baseOperations;
    SearchResultsPage searchResultsPage;

    @Step
    public void inputKeywordByActionToSearchField(String searchKeyword) {
        waitUtils = new WaitUtils(driver);
        homePage = new HomePage(driver);
        action = new Action(driver);
        action.inputToSearchField(homePage.searchField, searchKeyword);

        searchResultsPage = new SearchResultsPage(driver);
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
    }


    @Step
    public void addProductIntoCart(String title) {
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
        searchResultsPage.addProductInCart(title);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.productCountInCart);
    }

    @Step
    public void checkAddIntoCart(String productAmountInCart) {
        int actualResult = Integer.parseInt(homePage.productCountInCart.getText().trim());
        assertEquals(actualResult, Integer.parseInt(productAmountInCart),
                "Actual count in cart doesn't equals expected count. Actual count is: " + actualResult
                        + ". Expected amount is: " + Integer.parseInt(productAmountInCart));
    }

    @Step
    public void openCart() {
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
        baseOperations = new BaseOperations(driver);
        baseOperations.clickButton(homePage.openCartButton);
    }

    @Step
    public void deleteProduct() {
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.contextMenuButton);
        baseOperations.clickButton(homePage.contextMenuButton);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.deleteProductFromCartButton);
        baseOperations.clickButton(homePage.deleteProductFromCartButton);
    }

    @Step
    public void checkCartEmpty(String emptyMessage) {
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.emptyCartMessage);
        String actualResult = homePage.emptyCartMessage.getText();
        assertEquals(actualResult, emptyMessage,
                "Actual message about empty cart doesn't equals expected message. Actual message is: " + actualResult
                        + ". Expected message is: " + emptyMessage);
    }

    @Step
    public void closePopup() {
        basePage = new BasePage(driver);
        basePage.closeAdPopup();
    }

    @Step
    public void checkSubtotalPrice() {
        waitUtils.waitForElementVisibilityAfterLongWait(homePage.totalProductPriceInCart);

        int actualResult = baseOperations.getProductPrice(homePage.totalProductPriceInCart);
        assertEquals(actualResult, searchResultsPage.getProductDTOSubtotal(),
                "Actual product subtotal doesn't equals expected result. Actual result: " + actualResult
                        + "Expected subtotal: " + searchResultsPage.getProductDTOSubtotal());
    }
}