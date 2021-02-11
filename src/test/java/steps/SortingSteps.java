package steps;

import enums.Languages;
import helpers.ActionsByJavaScript;
import helpers.BaseOperations;
import helpers.WaitUtils;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.HomePage;
import pages.NotebooksPage;
import pages.SearchResultsPage;

public class SortingSteps extends ScenarioSteps {
    private static final String ROZETKA_URL = "https://rozetka.com.ua/";

    WebDriver driver =  getDriver();;
    HomePage homePage;
    WaitUtils waitUtils;
    ActionsByJavaScript js;
    NotebooksPage notebooksPage;
    BaseOperations baseOperations;
    SearchResultsPage searchResultsPage;


    @Step()
    public void openHomePage() {
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
        new BasePage(driver).setAppLanguage(Languages.RU);
    }

    @Step()
    public void openPageByProductCatalog(){
        System.out.println(driver);
        baseOperations = new BaseOperations(driver);
        waitUtils = new WaitUtils(driver);
        homePage = new HomePage(driver);
        baseOperations.clickButton(homePage.catalog);
        baseOperations.clickButton(homePage.notebooksPageOpenButton);

        searchResultsPage = new SearchResultsPage(driver);
        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sortedList);
    }


//    @When("User selects in dropdown sorting type - {string}")
//    public void selectSortingType(final String sortingType) {
//        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sortedList);
//        baseOperations.selectByDropdownText(searchResultsPage.sortedList, sortingType);
//        waitUtils.waitForElementVisibilityAfterShortWait(searchResultsPage.sidebar);
//    }
//
//    @Then("User checks that products sorted by ascending")
//    public void checkSortingByAscending() {
//        actualProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
//        expectedProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
//        Collections.sort(expectedProductPriceList);
//
//        assertEquals(actualProductPriceList, expectedProductPriceList,
//                "Actual list by ascending price doesn't equals expected list. Actual product price list: "
//                        + actualProductPriceList + ". Expected product price list: " + expectedProductPriceList);
//    }
//
//    @Then("User checks that products sorted by descending")
//    public void checkSortingByDescending() {
//        actualProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
//        expectedProductPriceList = new ArrayList<>(searchResultsPage.getProductPriceList());
//        expectedProductPriceList.sort(Collections.reverseOrder());
//
//        assertEquals(actualProductPriceList, expectedProductPriceList,
//                "Actual list by descending price doesn't equals expected list. Actual product price list: "
//                        + actualProductPriceList + ". Expected product price list: " + expectedProductPriceList);
//    }
//
//    @When("User selects product firm")
//    public void selectProductFirm() {
//        notebooksPage = new NotebooksPage(baseStepDefinition.driver);
//        waitUtils.waitForElementVisibilityAfterMiddleWait(notebooksPage.lenovoFirmSelectButton);
//
//        baseOperations.clickButton(notebooksPage.lenovoFirmSelectButton);
//        waitUtils.waitForElementVisibilityAfterMiddleWait(searchResultsPage.sidebar);
//    }
//
//    @Then("User checks products contains in title selected firm")
//    public void checkProductFirm() {
//        js = new ActionsByJavaScript(baseStepDefinition.driver);
//        js.scrollByWebElement(notebooksPage.lenovoFirmSelectButton);
//        for (WebElement element : searchResultsPage.titleProductList) {
//            waitUtils.waitForElementVisibilityAfterMiddleWait(element);
//            assertTrue(element.getText().toLowerCase().contains(CHOSEN_NOTEBOOK_FIRM),
//                    "Element doesn't contains chosen firm: " + CHOSEN_NOTEBOOK_FIRM);
//        }
}
