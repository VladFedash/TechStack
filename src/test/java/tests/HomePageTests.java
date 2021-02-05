package tests;

import helpers.BaseOperations;
import helpers.WaitUtils;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Random;

import static org.testng.Assert.*;

public class HomePageTests extends BaseTest {

    @Test
    public void checkAbilityChangeCityLocation() {
        WaitUtils waitUtils = new WaitUtils(driver);
        BaseOperations baseOperations = new BaseOperations(driver);

        HomePage homePage = new HomePage(driver);
        baseOperations.clickButton(homePage.changeCitiesButton);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.modalHeader);

        Random rand = new Random();
        int randomCity = rand.nextInt(homePage.popularCityList.size());

        String expectedResult = homePage.popularCityList.get(randomCity).getText();
        baseOperations.clickButton(homePage.popularCityList.get(randomCity));
        assertFalse(homePage.acceptCityChoiceButton.isSelected());

        baseOperations.clickButton(homePage.acceptCityChoiceButton);
        waitUtils.waitForElementVisibilityAfterShortWait(homePage.changeCitiesButton);
        String actualResult = homePage.changeCitiesButton.getText();
        assertEquals(actualResult, expectedResult,
                "Actual chosen city doesn't equals expected city. Actual city: " + actualResult
                        + ". Expected city: " + expectedResult);
    }

    // Test using for check different methods (Web_Elements Lecture)
    @Test
    public void checkDifferentMethodsForWorkCapacity() {
        HomePage homePage = new HomePage(driver);
        int siteLogoHeight = homePage.siteLogo.getLocation().y;
        int catalogHeight = homePage.catalog.getLocation().y;

        assertEquals(homePage.catalog.getTagName(), "button");
        assertEquals(homePage.catalog.getAttribute("aria-label"), "Каталог товаров");
        assertEquals(homePage.catalog.getCssValue("font-size"), "16px");
        assertTrue(homePage.siteLogo.getLocation().x
                + homePage.siteLogo.getSize().width < homePage.catalog.getLocation().x);
        assertEquals(siteLogoHeight, catalogHeight);
    }
}