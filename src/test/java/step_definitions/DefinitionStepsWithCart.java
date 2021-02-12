package step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.CartActionsSteps;

public class DefinitionStepsWithCart {
    private final BaseStepDefinition baseStepDefinition;

    public DefinitionStepsWithCart(BaseStepDefinition baseStepDefinition) {
        this.baseStepDefinition = baseStepDefinition;
    }

    @Steps
    CartActionsSteps cartActionsSteps;

    @When("User input {string} into search field with action functionality")
    public void shouldInputKeywordByActionToSearchField(String searchKeyword) {
        cartActionsSteps = new CartActionsSteps(baseStepDefinition.driver);
        cartActionsSteps.inputKeywordByActionToSearchField(searchKeyword);
    }

    @When("User adds into the cart product with {string} title")
    public void shouldAddProductIntoCart(String title) {
        cartActionsSteps.addProductIntoCart(title);
    }

    @Then("Users checks that cart count is {string}")
    public void shouldCheckAddIntoCart(final String productAmountInCart) {
        cartActionsSteps.checkAddIntoCart(productAmountInCart);
    }

    @When("User opens cart")
    public void shouldOpenCart() {
        cartActionsSteps.openCart();
    }

    @When("User deletes product from cart")
    public void shouldDeleteProduct() {
        cartActionsSteps.deleteProduct();
    }

    @Then("User checks that cart is {string}")
    public void shouldCheckCartEmpty(String emptyMessage) {
        cartActionsSteps.checkCartEmpty(emptyMessage);
    }

    @When("User closes ad popup if it's visible")
    public void shouldClosePopup() {
        cartActionsSteps.closePopup();
    }

    @Then("User checks that products sum price equals subtotal price")
    public void shouldCheckSubtotalPrice() {
        cartActionsSteps.checkSubtotalPrice();
    }
}