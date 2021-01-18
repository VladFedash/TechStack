package pages;

import helpers.BaseOperations;
import helpers.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    WebDriver driver;
    private final BaseOperations baseOperations;
    private final WaitUtils waitUtils;

    @FindBy(xpath = "//span[@class = 'exponea-close-cross']")
    public WebElement closeAdButton;

    @FindAll({
            @FindBy(id = "rz-banner"),
            @FindBy(xpath = "//a[contains(@class ,'exponea-animate')]")
    })
    public WebElement banner;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        baseOperations = new BaseOperations(driver);
        waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void closeAdPopup() {
        if (waitUtils.elementIsVisible(10, banner))
            baseOperations.clickButton(closeAdButton);
    }
}