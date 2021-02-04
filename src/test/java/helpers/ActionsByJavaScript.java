package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionsByJavaScript extends WaitUtils {
    public ActionsByJavaScript(WebDriver driver) {
        super(driver);
    }

    JavascriptExecutor js = (JavascriptExecutor) driver;

    public void scrollByWebElement(WebElement element) {
        js.executeScript("arguments[0].scrollTop = arguments[1]", element);
    }
}