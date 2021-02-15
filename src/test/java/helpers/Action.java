package helpers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Action extends WaitUtils {

    public Action(WebDriver driver) {
        super(driver);
    }

    Actions action = new Actions(driver);

    public void inputToSearchField(WebElement element, String str) {
        action.moveToElement(element).click();
        action.keyDown(element, Keys.SHIFT);
        action.sendKeys(element, str, Keys.ENTER).perform();
    }
}