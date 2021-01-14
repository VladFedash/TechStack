package tests;

import helpers.Expectations;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchResultTests extends BaseTest {

    private static final String SEARCH_WORD_IPHONE = "iphone";
    private static final String SEARCH_WORD_PHONE = "phone";
    private static final String SEARCH_NON_EXISTENT_KEYWORD = "non existent request";
    private static final String EXPECTED_NO_MATCHES_MESSAGE = "По запросу\n" + "«" + SEARCH_NON_EXISTENT_KEYWORD + "»" + "\n"
            + "ничего не найдено, попробуйте изменить запрос";

    Expectations expectations = new Expectations(driver);
    HomePage homePage = new HomePage(driver);
    SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

    @Test
    public void checkSearchResultsContainsWordIphone() {
        homePage.inputToSearchFieldAndPressEnter(SEARCH_WORD_PHONE);
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
        expectations.waitForElementVisibility(5, searchResultsPage.productAmountOnPage);
        int expectedResult = Integer.parseInt(searchResultsPage.productAmountOnPage
                .getText().replaceAll("[^0-9]", ""));
        assertEquals(searchResultsPage.titleProductList.size(), expectedResult);
    }

    @Test
    public void checkSearchForNoMatches() {
        homePage.inputToSearchFieldAndPressEnter(SEARCH_NON_EXISTENT_KEYWORD);
        expectations.waitForElementVisibility(5, searchResultsPage.massageAboutNoMatches);
        String actualResult = searchResultsPage.massageAboutNoMatches.getText();
        assertEquals(actualResult, EXPECTED_NO_MATCHES_MESSAGE);
    }
}