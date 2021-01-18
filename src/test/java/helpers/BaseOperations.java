package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseOperations extends WaitUtils {

    public BaseOperations(WebDriver driver) {
        super(driver);
    }

    public void clickButton(WebElement element) {
        waitForElementVisibility(5, element);
        element.click();
    }
}