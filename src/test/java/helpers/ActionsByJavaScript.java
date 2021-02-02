package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ActionsByJavaScript extends WaitUtils {
    public ActionsByJavaScript(WebDriver driver) {
        super(driver);
    }

    JavascriptExecutor js = (JavascriptExecutor) driver;

    public void slowlyScrollToBottom(){
        js.executeScript("window.scrollTo(0, 20)");
    }
}