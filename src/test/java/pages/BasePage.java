package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementVisibility(long timeout, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickButton(WebElement element) {
        waitForElementVisibility(5, element);
        element.click();
    }

    public boolean elementIsVisible(long timeout, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public void closePopUpOrMoveOn(WebElement banner, WebElement closeAdButton){
        if (elementIsVisible(10, banner))
            clickButton(closeAdButton);
    }
}