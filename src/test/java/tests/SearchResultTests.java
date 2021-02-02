package tests;

import helpers.BaseOperations;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;

public class SearchResultTests extends BaseTest {

    private static final String SEARCH_WORD_IPHONE = "iphone";
    private static final String SEARCH_WORD_PHONE = "phone";
    private static final String SEARCH_NON_EXISTENT_KEYWORD = "non existent request";
    private static final String EXPECTED_NO_MATCHES_MESSAGE = "По заданным параметрам не найдена ни одна модель";
    public static final String PRODUCT_AMOUNT_LOCATOR = ".//p[@class = 'catalog-selection__label']";

    @Test
    public void checkShowMoreFeature() {
        BaseOperations baseOperations = new BaseOperations(driver);
        WaitUtils waitUtils = new WaitUtils(driver);

        new HomePage(driver).inputToSearchFieldAndPressEnter(SEARCH_WORD_PHONE);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
        int beforeShowMore = searchResultsPage.titleProductList.size();

        baseOperations.clickButton(searchResultsPage.showMoreButton);
        int countOfProductsToBeDisplayed = baseOperations.getProductPrice(searchResultsPage.showMoreButton);
        int expectedResult = beforeShowMore + countOfProductsToBeDisplayed;
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.showMoreButton);

        assertEquals(searchResultsPage.titleProductList.size(), expectedResult,
                "Actual amount of elements doesn't equals expected amount. Actual amount: "
                        + searchResultsPage.titleProductList.size() + ". Expected result: " + expectedResult);
    }

    @Test
    public void checkCorrectElementsAmountOnSearchPage() {
        WaitUtils waitUtils = new WaitUtils(driver);

        HomePage homePage = new HomePage(driver);
        homePage.inputToSearchFieldAndPressEnter(SEARCH_WORD_PHONE);

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        waitUtils.waitForVisibilityOfAllElements(searchResultsPage.titleProductList);
        homePage.searchField.clear();
        homePage.inputToSearchFieldAndPressEnter(SEARCH_WORD_IPHONE);
        waitUtils.waitForElementPresenceAfterShortWait(By.xpath(PRODUCT_AMOUNT_LOCATOR));

        int expectedResult = new BaseOperations(driver).getProductPrice(searchResultsPage.productAmountOnPage);
        assertEquals(searchResultsPage.titleProductList.size(), expectedResult,
                "Actual amount of elements doesn't equals expected amount. Actual amount: "
                        + searchResultsPage.titleProductList.size() + ". Expected result: " + expectedResult);
    }

    @Test
    public void checkSearchForNoMatches() {
        new HomePage(driver).inputToSearchFieldAndPressEnter(SEARCH_NON_EXISTENT_KEYWORD);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        new WaitUtils(driver).waitForElementVisibilityAfterMiddleWait(searchResultsPage.massageAboutNoMatches);

        String actualResult = searchResultsPage.massageAboutNoMatches.getText();
        assertEquals(actualResult, EXPECTED_NO_MATCHES_MESSAGE,
                "Actual message doesn't equals expected message about no matches. Actual message: "
                        + actualResult + ". Expected message: " + EXPECTED_NO_MATCHES_MESSAGE);
    }
}