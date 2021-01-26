package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseOperations extends WaitUtils {

    public BaseOperations(WebDriver driver) {
        super(driver);
    }

    public void clickButton(WebElement element) {
        waitForElementToBeClickableAfterShortWait(element);
        element.click();
    }

    public void clickButton(By by) {
        waitForElementVisibilityAfterShortWait(driver.findElement(by));
        driver.findElement(by).click();
    }
}