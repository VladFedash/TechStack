package steps;

import helpers.BaseOperations;
import helpers.WaitUtils;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;

public class SearchSteps {
    WebDriver driver;
    private static final String PRODUCT_AMOUNT_LOCATOR = ".//p[@class = 'catalog-selection__label']";
    private static int expectedResult;

    public SearchSteps(WebDriver driver) {
        this.driver = driver;
    }

    HomePage homePage;
    WaitUtils waitUtils;
    BaseOperations baseOperations;
    SearchResultsPage searchResultsPage;

    @Step
    public void inputKeywordToSearchField(String searchKeyword) {
        baseOperations = new BaseOperations(driver);
        waitUtils = new WaitUtils(driver);

        homePage = new HomePage(driver);
        homePage.inputToSearchFieldAndPressEnter(searchKeyword);
        searchResultsPage = new SearchResultsPage(driver);
    }

    @Step
    public void clickShowMore() {
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
        int beforeShowMore = searchResultsPage.titleProductList.size();

        baseOperations.clickButton(searchResultsPage.showMoreButton);
        int countOfProductsToBeDisplayed = baseOperations.getProductPrice(searchResultsPage.showMoreButton);
        expectedResult = beforeShowMore + countOfProductsToBeDisplayed;
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.showMoreButton);
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
    }

    @Step
    public void checkElementIncreasing() {
        assertEquals(searchResultsPage.titleProductList.size(), expectedResult,
                "Actual amount of elements doesn't equals expected amount. Actual amount: "
                        + searchResultsPage.titleProductList.size() + ". Expected result: " + expectedResult);
    }

    @Step
    public void clearSearchFieldAndInputKeyword(String searchKeyword) {
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.searchField);
        homePage.searchField.clear();
        homePage.inputToSearchFieldAndPressEnter(searchKeyword);
    }

    @Step
    public void checkAmountOfProducts() {
        waitUtils.waitForElementPresenceAfterShortWait(By.xpath(PRODUCT_AMOUNT_LOCATOR));
        int expectedResult = baseOperations.getProductPrice(searchResultsPage.productAmountOnPage);
        assertEquals(searchResultsPage.titleProductList.size(), expectedResult,
                "Actual amount of elements doesn't equals expected amount. Actual amount: "
                        + searchResultsPage.titleProductList.size() + ". Expected result: " + expectedResult);
    }

    @Step
    public void checksMessageEquals(String massage) {
        waitUtils.waitForElementVisibilityAfterMiddleWait(searchResultsPage.massageAboutNoMatches);
        String actualResult = searchResultsPage.massageAboutNoMatches.getText();
        assertEquals(actualResult, massage,
                "Actual message doesn't equals expected message about no matches. Actual message: "
                        + actualResult + ". Expected message: " + massage);
    }
}