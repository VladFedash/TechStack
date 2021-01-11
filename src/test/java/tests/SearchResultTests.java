package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchResultTests extends BaseTest {

    private static final String SEARCH_WORD = "iphone";
    private static final String SEARCH_NON_EXISTENT_KEYWORD = "non existent request";
    private static final String EXPECTED_NO_MATCHES_MESSAGE = "По запросу\n" + "«" + SEARCH_NON_EXISTENT_KEYWORD + "»" + "\n"
            + "ничего не найдено, попробуйте изменить запрос";


    @Test
    public void checkSearchResultsContainsWordIphone() {
        getHomePage().inputToSearchField(SEARCH_WORD);
        for (WebElement element : getSearchPage().getTitleProductList()) {
            assertTrue(element.getText().toLowerCase().contains(SEARCH_WORD));
        }
    }

    @Test
    public void checkCorrectElementsAmountOnSearchPage() {
        getHomePage().inputToSearchField(SEARCH_WORD);
        getBasePage().waitForElementVisibility(5, getSearchPage().getProductAmountOnPage());
        int expectedResult = Integer.parseInt(getSearchPage().getProductAmountOnPage()
                .getText().replaceAll("[^0-9]", ""));
        assertEquals(getSearchPage().getTitleProductList().size(), expectedResult);
    }

    @Test
    public void checkSearchForNoMatches() {
        getHomePage().inputToSearchField(SEARCH_NON_EXISTENT_KEYWORD);
        getBasePage().waitForElementVisibility(5, getSearchPage().getMassageAboutNoMatches());
        String actualResult = getSearchPage().getMassageAboutNoMatches().getText();
        assertEquals(actualResult, EXPECTED_NO_MATCHES_MESSAGE);

    }

}
