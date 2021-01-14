package tests;

import helpers.BaseOperations;
import helpers.Expectations;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Random;

import static org.testng.Assert.*;

public class HomePageTests extends BaseTest {
    BaseOperations baseOperations = new BaseOperations(driver);
    Expectations expectations = new Expectations(driver);
    HomePage homePage = new HomePage(driver);

    @Test
    public void checkAbilityChangeCityLocation() {
        baseOperations.clickButton(homePage.changeCitiesButton);
        expectations.waitForElementVisibility(5, homePage.modalHeader);

        Random rand = new Random();
        int randomCity = rand.nextInt(homePage.popularCityList.size());

        String actualResult = homePage.popularCityList.get(randomCity).getText();
        homePage.popularCityList.get(randomCity).click();
        assertFalse(homePage.acceptCityChoiceButton.isSelected());
        baseOperations.clickButton(homePage.acceptCityChoiceButton);
        expectations.waitForElementVisibility(5, homePage.changeCitiesButton);
        assertEquals(actualResult, homePage.changeCitiesButton.getText());
    }

    @Test
    public void checkDifferentMethodsForWorkCapacity() {
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