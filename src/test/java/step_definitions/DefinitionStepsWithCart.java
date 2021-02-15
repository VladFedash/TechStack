package step_definitions;

import helpers.Action;
import helpers.BaseOperations;
import helpers.WaitUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;

public class DefinitionStepsWithCart {

    Action action;
    HomePage homePage;
    BasePage basePage;
    WaitUtils waitUtils;
    BaseOperations baseOperations;
    SearchResultsPage searchResultsPage;

    public DefinitionStepsWithCart(BaseStepDefinition baseStepDefinition) {
        action = new Action(baseStepDefinition.driver);
        homePage = new HomePage(baseStepDefinition.driver);
        basePage = new BasePage(baseStepDefinition.driver);
        waitUtils = new WaitUtils(baseStepDefinition.driver);
        baseOperations = new BaseOperations(baseStepDefinition.driver);
        searchResultsPage = new SearchResultsPage(baseStepDefinition.driver);
    }

    @When("User inputs {string} into search field with action functionality")
    public void inputKeywordByActionToSearchField(String searchKeyword) {
        waitUtils.waitForProblematicElements(homePage.searchField);
        action.inputToSearchField(homePage.searchField, searchKeyword);
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
    }

    @When("User adds into the cart product with {string} title")
    public void addProductIntoCart(String title) {
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
        searchResultsPage.addProductInCart(title);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.productCountInCart);
    }

    @Then("Users checks that cart count is {string}")
    public void checkAddIntoCart(String productAmountInCart) {
        int actualResult = Integer.parseInt(homePage.productCountInCart.getText().trim());
        assertEquals(actualResult, Integer.parseInt(productAmountInCart),
                "Actual count in cart doesn't equals expected count. Actual count is: " + actualResult
                        + ". Expected amount is: " + Integer.parseInt(productAmountInCart));
    }

    @When("User opens cart")
    public void openCart() {
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
        baseOperations.clickButton(homePage.openCartButton);
    }

    @When("User deletes product from cart")
    public void deleteProduct() {
        waitUtils.waitForProblematicElements(homePage.contextMenuButton);
        baseOperations.clickButton(homePage.contextMenuButton);
        baseOperations.clickButton(homePage.deleteProductFromCartButton);
    }

    @Then("User checks that cart is {string}")
    public void checkCartEmpty(String emptyMessage) {
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.emptyCartMessage);
        String actualResult = homePage.emptyCartMessage.getText();
        assertEquals(actualResult, emptyMessage,
                "Actual message about empty cart doesn't equals expected message. Actual message is: " + actualResult
                        + ". Expected message is: " + emptyMessage);
    }

    @When("User closes ad popup if it's visible")
    public void closePopup() {
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