package pages;

import enums.Languages;
import helpers.BaseOperations;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;
    public static final String LOCATOR_FOR_BANNER = "//a[contains(@class ,'exponea-animate')]";
    protected BaseOperations baseOperations;
    protected WaitUtils waitUtils;

    @FindBy(xpath = "//span[@class = 'exponea-close-cross']")
    public WebElement closeAdButton;

    @FindAll({
            @FindBy(id = "rz-banner"),
            @FindBy(xpath = LOCATOR_FOR_BANNER)
    })
    public WebElement banner;

    @FindBy(xpath = ".//rz-mobile-user-menu/button[@class = 'header__button']")
    public WebElement menuButton;

    protected String languageLocator = ".//a[text() = ' %s ']";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        baseOperations = new BaseOperations(driver);
        waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void closeAdPopup() {
        if (waitUtils.elementIsVisible(banner)) {
            baseOperations.clickButton(closeAdButton);
            waitUtils.waitForElementInvisibilityAfterShortWait(By.xpath(LOCATOR_FOR_BANNER));
        }
    }

    public void setAppLanguage(Languages language) {
        baseOperations.clickButton(menuButton);
        switch (language) {
            case RU -> baseOperations.clickButton(By.xpath(String.format(languageLocator, Languages.RU)));
            case UA -> baseOperations.clickButton(By.xpath(String.format(languageLocator, Languages.UA)));
        }
    }
}