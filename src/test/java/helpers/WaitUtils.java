package helpers;

import enums.WaitTimes;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitUtils {
    WebDriver driver;
    private static final int FREQUENCY = 3;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementVisibility(long timeout, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementVisibilityAfterShortWait(WebElement element) {
        waitForElementVisibility(WaitTimes.SHORT.getValue(), element);
    }

    public void waitForPageLoading() {
        driver.manage().timeouts().pageLoadTimeout(WaitTimes.LONG.getValue(), TimeUnit.SECONDS);
    }

    public void waitForElementVisibilityAfterMiddleWait(WebElement element) {
        waitForElementVisibility(WaitTimes.MIDDLE.getValue(), element);
    }

    public void waitForElementVisibilityAfterLongWait(WebElement element) {
        waitForElementVisibility(WaitTimes.LONG.getValue(), element);
    }

    public void waitForElementToBeClickable(long timeout, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeClickableAfterMiddleWait(WebElement element) {
        waitForElementToBeClickable(WaitTimes.MIDDLE.getValue(), element);
    }

    public void waitForElementInvisibility(long timeout, By by) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitForElementInvisibilityAfterShortWait(By by) {
        waitForElementInvisibility(WaitTimes.SHORT.getValue(), by);
    }

    public void waitForElementInvisibilityAfterMiddleWait(By by) {
        waitForElementInvisibility(WaitTimes.MIDDLE.getValue(), by);
    }

    public void waitForDataLoading(By by) {
        try {
            driver.findElement(by).isDisplayed();
            waitForElementInvisibilityAfterMiddleWait(by);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void waitForStaleElements(WebElement element) {
        try {
            element.isDisplayed();
            waitForElementVisibilityAfterShortWait(element);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }

    public void waitForElementPresence(long timeout, By by) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForElementPresenceAfterShortWait(By by) {
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
            waitForElementVisibilityAfterMiddleWait(element);
            return true;
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
    }
}