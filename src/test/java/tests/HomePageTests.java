package tests;

import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertEquals;

public class HomePageTests extends BaseTest {

    @Test
    public void checkAbilityChangeCityLocation() {
        getHomePage().clickChangeCitiesButton();
        getBasePage().waitForElementVisibility(5, getHomePage().getModalHeader());

        Random rand = new Random();
        int randomCity = rand.nextInt(getHomePage().getPopularCityList().size());

        String actualResult = getHomePage().getPopularCityList().get(randomCity).getText();
        getHomePage().getPopularCityList().get(randomCity).click();
        getHomePage().clickAcceptCityChoiceButton();
        getBasePage().waitForElementVisibility(5, getHomePage().getCityTitle());
        assertEquals(actualResult, getHomePage().getCityTitle().getText());
    }
}
