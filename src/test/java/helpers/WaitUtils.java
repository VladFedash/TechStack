package helpers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitUtils {
    WebDriver driver;
    private static final int SHORT_WAITING_TIME = 5;
    private static final int MIDDLE_WAITING_TIME = 10;
    private static final int LONG_WAITING_TIME = 15;
    private static final int FREQUENCY = 5;


    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementVisibility(long timeout, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementVisibilityShort(WebElement element) {
        waitForElementVisibility(SHORT_WAITING_TIME, element);
    }

    public void waitForElementVisibilityMiddle(WebElement element) {
        waitForElementVisibility(MIDDLE_WAITING_TIME, element);
    }

    public void waitForElementVisibilityLong(WebElement element) {
        waitForElementVisibility(LONG_WAITING_TIME, element);
    }

    public void waitForElementToBeClickable(long timeout, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeClickableShort(WebElement element) {
        waitForElementToBeClickable(SHORT_WAITING_TIME, element);
    }

    public void waitForUrl(long timeout, String str) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.urlContains(str));
    }

    public void waitForUrlLong(String str) {
        waitForUrl(LONG_WAITING_TIME, str);
    }

    public void waitForWindowNumber(int numberOfWindows) {
        WebDriverWait wait = new WebDriverWait(driver, SHORT_WAITING_TIME);
        wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));
    }

    public void waitForVisibilityOfAllElements(List<WebElement> elements) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(LONG_WAITING_TIME))
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