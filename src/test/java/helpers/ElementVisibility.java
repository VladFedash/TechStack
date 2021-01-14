package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementVisibility extends Expectations {
    public ElementVisibility(WebDriver driver) {
        super(driver);
    }

    public boolean elementIsVisible(long timeout, WebElement element) {
        try {
            waitForElementVisibility(timeout, element);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}