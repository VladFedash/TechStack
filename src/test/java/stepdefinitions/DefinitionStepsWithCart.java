package stepdefinitions;

import helpers.Action;
import helpers.BaseOperations;
import helpers.WaitUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;

public class DefinitionStepsWithCart {
    private final Hook hook;

    public DefinitionStepsWithCart(Hook hook) {
        this.hook = hook;
    }

    Action action;
    HomePage homePage;
    BasePage basePage;
    WaitUtils waitUtils;
    BaseOperations baseOperations;
    SearchResultsPage searchResultsPage;

    @Given("User input {string} into search field with action functionality")
    public void inputKeywordByActionToSearchField(final String searchKeyword) {
        waitUtils = new WaitUtils(hook.driver);
        homePage = new HomePage(hook.driver);
        action = new Action(hook.driver);
        action.inputToSearchField(homePage.searchField, searchKeyword);

        searchResultsPage = new SearchResultsPage(hook.driver);
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
    }


    @When("User adds into the cart product with {string} title")
    public void addProductIntoCart(final String title) {
        searchResultsPage.addProductInCart(title);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.productCountInCart);
    }

    @Then("Users checks that cart count is {string}")
    public void checkAddIntoCart(final String productAmountInCart) {
        int actualResult = Integer.parseInt(homePage.productCountInCart.getText().trim());
        assertEquals(actualResult, Integer.parseInt(productAmountInCart),
                "Actual count in cart doesn't equals expected count. Actual count is: " + actualResult
                        + ". Expected amount is: " + Integer.parseInt(productAmountInCart));
    }

    @When("User opens cart")
    public void openCart() {
        baseOperations = new BaseOperations(hook.driver);
        baseOperations.clickButton(homePage.openCartButton);
    }

    @When("User deletes product from cart")
    public void deleteProduct() {
        baseOperations.clickButton(homePage.contextMenuButton);
        baseOperations.clickButton(homePage.deleteProductFromCartButton);
    }

    @Then("User checks that cart is {string}")
    public void checkCartEmpty(final String emptyMessage) {
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.emptyCartMessage);
        String actualResult = homePage.emptyCartMessage.getText();
        assertEquals(actualResult, emptyMessage,
                "Actual message about empty cart doesn't equals expected message. Actual message is: " + actualResult
                        + ". Expected message is: " + emptyMessage);
    }

    @When("User closes ad popup if it's visible")
    public void closePopup() {
        basePage = new BasePage(hook.driver);
        basePage.closeAdPopup();
    }

    @Then("User checks that products sum price equals subtotal price")
    public void checkSubtotalPrice() {
        waitUtils.waitForElementVisibilityAfterLongWait(homePage.totalProductPriceInCart);

        int actualResult = baseOperations.getProductPrice(homePage.totalProductPriceInCart);
        assertEquals(actualResult, searchResultsPage.getProductDTOSubtotal(),
                "Actual product subtotal doesn't equals expected result. Actual result: " + actualResult
                        + "Expected subtotal: " + searchResultsPage.getProductDTOSubtotal());
    }
}