package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchResultTests extends BaseTest {

    private static final String SEARCH_WORD = "iphone";
    private static final String SEARCH_NON_EXISTENT_KEYWORD = "non existent request";
    private static final String EXPECTED_NO_MATCHES_MESSAGE = "По запросу\n" + "«" + SEARCH_NON_EXISTENT_KEYWORD + "»" + "\n"
            + "ничего не найдено, попробуйте изменить запрос";

    BasePage basePage = new BasePage(driver);
    HomePage homePage = new HomePage(driver);
    SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

    @Test
    public void checkSearchResultsContainsWordIphone() {
        homePage.inputToSearchField(SEARCH_WORD);
        for (WebElement element : searchResultsPage.titleProductList) {
            assertTrue(element.getText().toLowerCase().contains(SEARCH_WORD));
        }
    }

    @Test
    public void checkCorrectElementsAmountOnSearchPage() {
        homePage.inputToSearchField(SEARCH_WORD);
        basePage.waitForElementVisibility(5, searchResultsPage.productAmountOnPage);
        int expectedResult = Integer.parseInt(searchResultsPage.productAmountOnPage
                .getText().replaceAll("[^0-9]", ""));
        assertEquals(searchResultsPage.titleProductList.size(), expectedResult);
    }

    @Test
    public void checkSearchForNoMatches() {
        homePage.inputToSearchField(SEARCH_NON_EXISTENT_KEYWORD);
        basePage.waitForElementVisibility(5, searchResultsPage.massageAboutNoMatches);
        String actualResult = searchResultsPage.massageAboutNoMatches.getText();
        assertEquals(actualResult, EXPECTED_NO_MATCHES_MESSAGE);
    }
}