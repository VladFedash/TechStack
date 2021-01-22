package tests;

import helpers.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PageWithLocators;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchResultTests extends BaseTest {

    private static final String SEARCH_WORD_IPHONE = "iphone";
    private static final String SEARCH_WORD_PHONE = "phone";
    private static final String SEARCH_NON_EXISTENT_KEYWORD = "non existent request";
    private static final String URL_FOR_PHONE_SEARCH = "text=phone";
    private static final int WINDOWS_COUNT = 2;
    private static final String EXPECTED_NO_MATCHES_MESSAGE = "По запросу\n" + "«" + SEARCH_NON_EXISTENT_KEYWORD + "»" + "\n"
            + "ничего не найдено, попробуйте изменить запрос";

    WaitUtils waitUtils = new WaitUtils(driver);
    HomePage homePage = new HomePage(driver);
    SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
    PageWithLocators pageWithLocators = new PageWithLocators(driver);

    @Test
    public void checkSearchResultsContainsWordIphone() {
        homePage.inputToSearchFieldAndPressEnter(SEARCH_WORD_PHONE);
        waitUtils.waitForUrlLong(URL_FOR_PHONE_SEARCH);
        homePage.searchField.clear();
        homePage.inputToSearchFieldAndPressEnter(SEARCH_WORD_IPHONE);

        for (WebElement element : searchResultsPage.titleProductList) {
            assertTrue(element.isDisplayed());
            assertTrue(element.getText().toLowerCase().contains(SEARCH_WORD_IPHONE));
        }
    }

    @Test
    public void checkCorrectElementsAmountOnSearchPage() {
        homePage.searchField.submit();
        homePage.inputToSearchFieldAndPressEnter(SEARCH_WORD_IPHONE);
        waitUtils.waitForElementVisibilityShort(searchResultsPage.productAmountOnPage);
        int expectedResult = Integer.parseInt(searchResultsPage.productAmountOnPage
                .getText().replaceAll("[^0-9]", ""));
        assertEquals(searchResultsPage.titleProductList.size(), expectedResult);

        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", "https://rozetka.com.ua/");
        waitUtils.waitForWindowNumber(WINDOWS_COUNT);
        homePage.inputToSearchFieldAndPressEnter(SEARCH_WORD_PHONE);
        assertEquals(pageWithLocators.productTitle.getText(), "«" + SEARCH_WORD_PHONE + "»");
    }

    @Test
    public void checkSearchForNoMatches() {
        homePage.inputToSearchFieldAndPressEnter(SEARCH_NON_EXISTENT_KEYWORD);
        waitUtils.waitForElementVisibilityShort(searchResultsPage.massageAboutNoMatches);
        String actualResult = searchResultsPage.massageAboutNoMatches.getText();
        assertEquals(actualResult, EXPECTED_NO_MATCHES_MESSAGE);
    }
}