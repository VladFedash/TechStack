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
    private static int expectedResult;

    HomePage homePage;
    WaitUtils waitUtils;
    BaseOperations baseOperations;
    SearchResultsPage searchResultsPage;

    public DefinitionStepsSearchResult(BaseStepDefinition baseStepDefinition) {
        homePage = new HomePage(baseStepDefinition.driver);
        waitUtils = new WaitUtils(baseStepDefinition.driver);
        baseOperations = new BaseOperations(baseStepDefinition.driver);
        searchResultsPage = new SearchResultsPage(baseStepDefinition.driver);
    }

    @When("User inputs {string} to search field and press enter")
    public void inputKeywordToSearchField(String searchKeyword) {
        homePage.inputToSearchFieldAndPressEnter(searchKeyword);
    }

    @When("User scrolls down and click show more products button")
    public void clickShowMore() {
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
        int beforeShowMore = searchResultsPage.titleProductList.size();

        baseOperations.clickButton(searchResultsPage.showMoreButton);
        waitUtils.waitForDataLoading(By.xpath(searchResultsPage.SPINNER));

        int countOfProductsToBeDisplayed = baseOperations.getProductPrice(searchResultsPage.showMoreButton);
        expectedResult = beforeShowMore + countOfProductsToBeDisplayed;
    }

    @Then("User checks that amount of elements increased by the specified amount")
    public void checkElementIncreasing() {
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
        assertEquals(searchResultsPage.titleProductList.size(), expectedResult,
                "Actual amount of elements doesn't equals expected amount. Actual amount: "
                        + searchResultsPage.titleProductList.size() + ". Expected result: " + expectedResult);
    }

    @When("User clears search field and input another {string}")
    public void clearSearchFieldAndInputKeyword(String searchKeyword) {
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.searchField);
        homePage.searchField.clear();
        homePage.inputToSearchFieldAndPressEnter(searchKeyword);
    }

    @Then("User checks amount of products equals specified quantity")
    public void checkAmountOfProducts() {
        waitUtils.waitForElementPresenceAfterShortWait(By.xpath(searchResultsPage.PRODUCT_AMOUNT_LOCATOR));
        int expectedResult = baseOperations.getProductPrice(searchResultsPage.productAmountOnPage);
        assertEquals(searchResultsPage.titleProductList.size(), expectedResult,
                "Actual amount of elements doesn't equals expected amount. Actual amount: "
                        + searchResultsPage.titleProductList.size() + ". Expected result: " + expectedResult);
    }

    @Then("User checks actual massage equals {string}")
    public void checksMessageEquals(String massage) {
        waitUtils.waitForElementToBeClickableAfterMiddleWait(searchResultsPage.messageAboutNoMatches);
        String actualResult = searchResultsPage.messageAboutNoMatches.getText();
        assertEquals(actualResult, massage,
                "Actual message doesn't equals expected message about no matches. Actual message: "
                        + actualResult + ". Expected message: " + massage);
    }
}