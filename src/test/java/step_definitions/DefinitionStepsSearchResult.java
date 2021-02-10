package step_definitions;

import helpers.BaseOperations;
import helpers.WaitUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;

public class DefinitionStepsSearchResult {
    private final BaseStepDefinition baseStepDefinition;
    private static final String PRODUCT_AMOUNT_LOCATOR = ".//p[@class = 'catalog-selection__label']";
    private static int expectedResult;

    public DefinitionStepsSearchResult(BaseStepDefinition baseStepDefinition) {
        this.baseStepDefinition = baseStepDefinition;
    }

    HomePage homePage;
    WaitUtils waitUtils;
    BaseOperations baseOperations;
    SearchResultsPage searchResultsPage;

    @When("User inputs {string} to search field and press enter")
    public void inputKeywordToSearchField(final String searchKeyword) {
        baseOperations = new BaseOperations(baseStepDefinition.driver);
        waitUtils = new WaitUtils(baseStepDefinition.driver);

        homePage = new HomePage(baseStepDefinition.driver);
        homePage.inputToSearchFieldAndPressEnter(searchKeyword);
        searchResultsPage = new SearchResultsPage(baseStepDefinition.driver);
    }

    @When("User scrolls down and click show more products button")
    public void clickShowMore() {
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
        int beforeShowMore = searchResultsPage.titleProductList.size();

        baseOperations.clickButton(searchResultsPage.showMoreButton);
        int countOfProductsToBeDisplayed = baseOperations.getProductPrice(searchResultsPage.showMoreButton);
        expectedResult = beforeShowMore + countOfProductsToBeDisplayed;
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.showMoreButton);
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
    }

    @Then("User checks that amount of elements increased by the specified amount")
    public void checkElementIncreasing() {
        assertEquals(searchResultsPage.titleProductList.size(), expectedResult,
                "Actual amount of elements doesn't equals expected amount. Actual amount: "
                        + searchResultsPage.titleProductList.size() + ". Expected result: " + expectedResult);
    }

    @When("User clears search field and input another {string}")
    public void clearSearchFieldAndInputKeyword(final String searchKeyword) {
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.searchField);
        homePage.searchField.clear();
        homePage.inputToSearchFieldAndPressEnter(searchKeyword);
    }

    @Then("User checks amount of products equals specified quantity")
    public void checkAmountOfProducts() {
        waitUtils.waitForElementPresenceAfterShortWait(By.xpath(PRODUCT_AMOUNT_LOCATOR));
        int expectedResult = baseOperations.getProductPrice(searchResultsPage.productAmountOnPage);
        assertEquals(searchResultsPage.titleProductList.size(), expectedResult,
                "Actual amount of elements doesn't equals expected amount. Actual amount: "
                        + searchResultsPage.titleProductList.size() + ". Expected result: " + expectedResult);
    }

    @Then("User checks actual massage equals {string}")
    public void checksMessageEquals(final String massage) {
        waitUtils.waitForElementVisibilityAfterMiddleWait(searchResultsPage.massageAboutNoMatches);
        String actualResult = searchResultsPage.massageAboutNoMatches.getText();
        assertEquals(actualResult, massage,
                "Actual message doesn't equals expected message about no matches. Actual message: "
                        + actualResult + ". Expected message: " + massage);
    }
}