package tests;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;

import java.util.Random;

import static org.testng.Assert.assertEquals;

public class HomePageTests extends BaseTest {

    BasePage basePage = new BasePage(driver);
    HomePage homePage = new HomePage(driver);

    @Test
    public void checkAbilityChangeCityLocation() {
        basePage.clickButton(homePage.changeCitiesButton);
        basePage.waitForElementVisibility(5, homePage.modalHeader);

        Random rand = new Random();
        int randomCity = rand.nextInt(homePage.popularCityList.size());

        String actualResult = homePage.popularCityList.get(randomCity).getText();
        homePage.popularCityList.get(randomCity).click();
        basePage.clickButton(homePage.acceptCityChoiceButton);
        basePage.waitForElementVisibility(5, homePage.changeCitiesButton);
        assertEquals(actualResult, homePage.changeCitiesButton.getText());
    }
}