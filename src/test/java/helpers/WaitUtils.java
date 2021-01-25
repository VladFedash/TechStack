package helpers;

import enums.WaitTimes;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class WaitUtils {
    WebDriver driver;
    private static final int FREQUENCY = 5;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementVisibility(long timeout, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementVisibilityShort(WebElement element) {
        waitForElementVisibility(WaitTimes.SHORT.getValue(), element);
    }

    public void waitForElementVisibilityMiddle(WebElement element) {
        waitForElementVisibility(WaitTimes.MIDDLE.getValue(), element);
    }

    public void waitForElementVisibilityLong(WebElement element) {
        waitForElementVisibility(WaitTimes.LONG.getValue(), element);
    }

    public void waitForElementToBeClickable(long timeout, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeClickableShort(WebElement element) {
        waitForElementToBeClickable(WaitTimes.SHORT.getValue(), element);
    }

    public void waitForElementInvisibility(long timeout, By by) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitForElementInvisibilityShort(By by){
        waitForElementInvisibility(WaitTimes.SHORT.getValue(), by);
    }

    public void waitForElementPresence(long timeout, By by){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForElementPresenceShort(By by){
        waitForElementPresence(WaitTimes.SHORT.getValue(), by);
    }

    public void waitForVisibilityOfAllElements(List<WebElement> elements) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(WaitTimes.LONG.getValue()))
                .pollingEvery(Duration.ofSeconds(FREQUENCY))
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public boolean elementIsVisible(WebElement element) {
        try {
            waitForElementVisibilityMiddle(element);
            return true;
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
    }
}