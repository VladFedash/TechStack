package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(xpath = ".//input[contains(@class, 'search-form')]")
    public WebElement searchField;

    @FindBy(xpath = ".//button[@class = 'menu-toggler']")
    public WebElement catalog;

    @FindBy(xpath = ".//a[@class = 'header__logo']")
    public WebElement siteLogo;

    @FindBy(xpath = ".//a[@class ='menu__hidden-title'][not(contains(@href, 'preset=game')) and contains(@href, '/notebooks/')]")
    public WebElement notebooksPageOpenButton;

    @FindBy(xpath = ".//div[@class = 'js-rz-cart']//span[contains(@class, 'button-counter')]")
    public WebElement productCountInCart;

    @FindBy(xpath = ".//button[contains(@class, 'context-menu__toggle')]")
    public WebElement contextMenuButton;

    @FindBy(xpath = ".//button[contains(@class, 'context-menu-actions')]")
    public WebElement deleteProductFromCartButton;

    @FindBy(xpath = ".//h4[@class = 'cart-dummy__heading']")
    public WebElement emptyCartMessage;

    @FindBy(xpath = ".//div[@class = 'js-rz-cart']")
    public WebElement openCartButton;

    @FindBy(xpath = ".//div[@class = 'cart-product__coast']")
    public List<WebElement> productPriceListInCart;

    @FindBy(xpath = ".//div[contains(@class, 'sum-price')]")
    public WebElement totalProductPriceInCart;

    @FindBy(xpath = ".//button[contains(@class, 'cities__label')]")
    public WebElement changeCitiesButton;

    @FindBy(xpath = ".//div[@class = 'modal__header']")
    public WebElement modalHeader;

    @FindBy(xpath = ".//a[@class = 'header-location__popular-link']")
    public List<WebElement> popularCityList;

    @FindBy(xpath = ".//button[contains(@class, 'medium button')]")
    public WebElement acceptCityChoiceButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void inputToSearchFieldAndPressEnter(String keyword) {
        searchField.sendKeys(keyword, Keys.ENTER);
    }
}