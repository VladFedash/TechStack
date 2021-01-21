package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PageWithLocators extends BasePage {

    //Create separate class in solution and add at least 2 web elements located by xpath using following functions/operators

    //contains
    @FindBy(xpath = ".//button[contains(@class, 'cities')]")
    private WebElement cityButton;

    @FindBy(xpath = ".//input[contains(@class, 'search-form')]")
    public WebElement searchField;

    //starts-with
    @FindBy(xpath = ".//span[starts-with(@class, 'm') and contains(@class, 'toggler')]")
    public WebElement catalog;

    @FindBy(xpath = ".//a[starts-with(text(), 'U')]")
    private WebElement uaLanguageButton;

    //last
    @FindBy(xpath = ".//li[contains(@class, 'grid__cell')][last()]")
    private WebElement lastElementOnProductList;

    @FindBy(xpath = ".//li[contains(@class, 'cart-list')][last()]")
    private WebElement LastElementOnCartList;

    //not
    @FindBy(xpath = ".//li[contains(@class, 'header-topline')][not(a)]")
    private WebElement unselectedLanguageButton;

    @FindBy(xpath = ".//div[contains(@class, 'available')][not(contains(@class, 'unavailable'))]")
    private List<WebElement> availableProductList;

    //or
    @FindBy(xpath = ".//div[contains(@class, 'actions__button') or contains(@class, 'actions__button')]")
    private List<WebElement> headerActionsButtonList;

    @FindBy(xpath = ".//li[contains(@class, 'links-item') or contains(@class, 'language-item')]")
    private List<WebElement> headerTopLineButtonList;

    //and
    @FindBy(xpath = ".//app-rz-header[contains(@class, 'header-component') and (@style = 'display: block;')]")
    private WebElement componentHeader;

    @FindBy(xpath = ".//button[@type = 'button' and contains(@class, 'microphone')]")
    private WebElement microphoneButton;

    //ancestor
    @FindBy(xpath = ".//ancestor::li[contains(@class, 'links-item')]/a[contains(@href, 'contacts')]")
    private WebElement contacts;

    @FindBy(xpath = ".//ancestor::li[contains(@class, 'links-item')]/a[contains(@href, 'help')]")
    private WebElement help;

    //parent
    @FindBy(xpath = ".//a[@class = 'main-stores__button']/parent::div")
    private WebElement mainStores;

    @FindBy(xpath = ".//div[contains(@class, 'support')]/parent::div")
    private WebElement mainSupport;

    //preceding (iphone page - https://rozetka.com.ua/ua/mobile-phones/c80003/producer=apple/#search_text=iphone)
    @FindBy(xpath = ".//div[@class = 'layout']/preceding::h1")
    private WebElement productTitle;

    @FindBy(xpath = ".//div[@class = 'layout']/preceding::section[@class = 'js-bottom-text']")
    private WebElement bottomText;

    //preceding-sibling (computers-notebooks page - https://rozetka.com.ua/ua/computers-notebooks/c80253/)
    @FindBy(xpath = ".//li[contains(text(), 'HP')]/preceding-sibling::li")
    private List<WebElement> firmListPrecedingHpElement;

    @FindBy(xpath = ".//li[contains(text(), 'Panasonic')]/preceding-sibling::li")
    private WebElement firmListPrecedingPanasonicElement;

    //descendant
    @FindBy(xpath = ".//div[contains(@class, 'main-stores')]//descendant::a/img[@alt = 'Google Play']")
    private WebElement googlePlayStore;

    @FindBy(xpath = ".//div[contains(@class, 'main-stores')]//descendant::a/img[@alt = 'AppStore']")
    private WebElement appStore;

    //following-sibling (computers-notebooks page - https://rozetka.com.ua/ua/computers-notebooks/c80253/)
    @FindBy(xpath = ".//li[contains(text(), 'HP')]/following-sibling::li")
    private List<WebElement> firmListFollowingHpElement;

    @FindBy(xpath = ".//li[contains(text(), 'Acer')]/following-sibling::li")
    private WebElement firmListFollowingPanasonicElement;

    //text
    @FindBy(xpath = ".//label[contains(text(), 'Rozetka')]")
    private WebElement rosetkaSeller;

    @FindBy(xpath = ".//li[contains(@class, 'links-item')]/a[contains(text(), 'COVID')]")
    private WebElement responseToCovid;

    //count (iphone page - https://rozetka.com.ua/ua/mobile-phones/c80003/producer=apple/#search_text=iphone)
    @FindBy(xpath = ".//label[count(@for) = 1 and @for = 'Huawei']")
    private WebElement huaweiFirmSelectButton;

    @FindBy(xpath = ".//button[count(@type) = 0 and @class ='catalog-view__button']")
    private WebElement largeTileForViewProductListButton;

    //  |
    @FindBy(xpath = ".//input | .//class")
    private WebElement searchFieldFindByOrMethod;

    @FindBy(xpath = ".//a[@class = 'header-bottomline']| .//span[@class = 'menu-toggler__text']")
    private WebElement catalogButton;

    // >= (iphone page - https://rozetka.com.ua/ua/mobile-phones/c80003/producer=apple/#search_text=iphone)
    @FindBy(xpath = ".//div[contains(@class, 'tile__colors')]/ul[count(li)>=6]")
    private List<WebElement> productListWithSixColorsCountAndMore;

    //home page
    @FindBy(xpath = ".//div[contains(@class, 'price_color')]/span[@class='tile__price-value'][number()>= 800]")
    private List<WebElement> productListWithPriceMoreThanIndicated;

    // <= (iphone page - https://rozetka.com.ua/ua/mobile-phones/c80003/producer=apple/#search_text=iphone)
    @FindBy(xpath = ".//div[contains(@class, 'tile__colors')]/ul[count(li)<=2]  | .//div[contains(@class, 'tile__colors')][not(ul)]")
    private List<WebElement> productListWithTwoColorsCountOrLess;

    //home page
    @FindBy(xpath = ".//div[contains(@class, 'price_color')]/span[@class='tile__price-value'][number()<= 100]")
    private List<WebElement> productListWithPriceLessThanIndicated;

    // mod (iphone page - https://rozetka.com.ua/ua/mobile-phones/c80003/producer=apple/#search_text=iphone)
    @FindBy(xpath = ".//div[contains(@class, 'tile__colors')]/ul[ count(li) mod 3 = 0]")
    private List<WebElement> productListWithDivisibleThreeColorsCount;

    @FindBy(xpath = ".//div[contains(@class, 'tile__colors')]/ul[ count(li) mod 3 = 2]")
    private List<WebElement> productListDivisibleThreeWithRemainderTwo;

    public PageWithLocators(WebDriver driver) {
        super(driver);
    }
}