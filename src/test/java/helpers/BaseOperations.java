package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BaseOperations extends WaitUtils {
    public BaseOperations(WebDriver driver) {
        super(driver);
    }

    public void clickButton(WebElement element) {
        waitForElementToBeClickableAfterMiddleWait(element);
        element.click();
    }

    public void clickButton(By by) {
        waitForElementVisibilityAfterShortWait(driver.findElement(by));
        driver.findElement(by).click();
    }

    public int getProductPrice(WebElement element){
        return Integer.parseInt(element.getText().replaceAll("[^0-9]", ""));
    }

    public void selectByDropdownText(WebElement element, String sortingBy){
        Select objSelect = new Select(element);
        objSelect.selectByVisibleText(sortingBy);
    }
}