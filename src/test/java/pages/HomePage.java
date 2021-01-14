package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(name = "search")
    public WebElement searchField;

    @FindBys( {
            @FindBy(xpath = "//button[@class = 'menu-toggler']"),
            @FindBy(xpath = "//button[@aria-label = 'Каталог товаров']")
    })
    public WebElement catalog;

    @FindBy(xpath = "//a[@class = 'header__logo']")
    public WebElement siteLogo;

    @FindBy(linkText = "Ноутбуки")
    public WebElement notebooksPageOpenButton;

    @FindBy(xpath = "//div[@class = 'js-rz-cart']//span[contains(@class, 'button-counter')]")
    public WebElement productCountInCart;

    @FindBy(id = "cartProductActions0")
    public WebElement contextMenuButton;

    @FindBy(xpath = "//button[contains(@class, 'context-menu-actions__button')]")
    public WebElement deleteProductFromCartButton;

    @FindBy(xpath = "//h4[@class = 'cart-dummy__heading']")
    public WebElement emptyCartMessage;

    @FindBy(css = ".js-rz-cart")
    public WebElement openCartButton;

    @FindBy(xpath = "//div[@class = 'cart-product__coast']")
    public List<WebElement> productPriceListInCart;

    @FindBy(xpath = "//div[contains(@class, 'sum-price')]")
    public WebElement totalProductPriceInCart;

    @FindBy(xpath = "//button[contains(@class, 'cities__label')]")
    public WebElement changeCitiesButton;

    @FindBy(xpath = "//div[@class = 'modal__header']")
    public WebElement modalHeader;

    @FindBy(className = "header-location__popular-link")
    public List<WebElement> popularCityList;

    @FindBy(xpath = "//button[contains(@class, 'medium button')]")
    public WebElement acceptCityChoiceButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void inputToSearchFieldAndPressEnter(String keyword) {
        searchField.sendKeys(keyword, Keys.ENTER);
    }
}