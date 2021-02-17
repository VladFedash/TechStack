package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    private static final String PRODUCT_NAME = ".//a[@class ='menu__hidden-title'][contains(text(), '%s')]";
    @FindBy(xpath = ".//input[@name = 'search']")
    public WebElement searchField;

    @FindBy(xpath = ".//button[@id= 'fat-menu']")
    public WebElement catalog;

    @FindBy(xpath = ".//a[@class = 'header__logo']")
    public WebElement siteLogo;

    @FindBy(xpath = PRODUCT_NAME)
    public WebElement productName;

    @FindBy(xpath = ".//span[contains(@class, 'counter')]")
    public WebElement productCountInCart;

    @FindBy(xpath = ".//button[contains(@class, 'context-menu__toggle')]")
    public WebElement contextMenuButton;

    @FindBy(xpath = ".//button[contains(@class, 'context-menu-actions')]")
    public WebElement deleteProductFromCartButton;

    @FindBy(xpath = ".//h4[@class = 'cart-dummy__heading']")
    public WebElement emptyCartMessage;

    @FindBy(xpath = ".//rz-cart[contains(@class, 'header')]")
    public WebElement openCartButton;

    @FindBy(xpath = ".//div[contains(@class, 'sum-price')]")
    public WebElement totalProductPriceInCart;

    @FindBy(xpath = ".//button[contains(@class, 'city')]")
    public WebElement changeCitiesButton;

    @FindBy(xpath = ".//div[@class = 'modal__header']")
    public WebElement modalHeader;

    @FindBy(xpath = ".//a[@class = 'header-location__popular-link']")
    public List<WebElement> popularCityList;

    @FindBy(xpath = ".//div[@class = 'header-location__footer']//button[contains(@class, 'color_green')]")
    public WebElement acceptCityChoiceButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getProductByName(String product){
        productName = driver.findElement(By.xpath(String.format(PRODUCT_NAME, product)));
        waitUtils.waitForElementVisibilityAfterMiddleWait(productName);
        return productName;
    }
}