package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.HomePage;

public class BaseOperations extends WaitUtils {
    protected HomePage homePage;

    public BaseOperations(WebDriver driver) {
        super(driver);
    }

    public void clickButton(WebElement element) {
        waitForElementToBeClickableAfterMiddleWait(element);
        element.click();
    }

    public void clickButton(By by) {
        waitForElementVisibilityAfterMiddleWait(driver.findElement(by));
        driver.findElement(by).click();
    }

    public void clickButtonForStaleElements(WebElement element){
        waitForStaleElements(element);
        element.click();
    }

    public int getProductPrice(WebElement element) {
        waitForElementVisibilityAfterLongWait(element);
        return Integer.parseInt(element.getText().replaceAll("[^0-9]", ""));
    }

    public void selectByDropdownText(WebElement element, String sortingBy) {
        waitForElementVisibilityAfterMiddleWait(element);
        Select objSelect = new Select(element);
        objSelect.selectByVisibleText(sortingBy);
    }

    public void inputToSearchFieldAndPressEnter(String keyword) {
        homePage = new HomePage(driver);
        waitForStaleElements(homePage.searchField);
        homePage.searchField.sendKeys(keyword, Keys.ENTER);
    }
}