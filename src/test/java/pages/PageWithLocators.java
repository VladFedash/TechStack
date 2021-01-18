package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PageWithLocators extends  BasePage{

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

    @FindBy(xpath = ".//div[contains(@class, 'available')][not(contains(text(), 'Нет в наличии'))]")
    private List<WebElement> availableProductList;

    //or
    @FindBy(xpath = ".//div[contains(@class, 'actions__button') or contains(@class, 'actions__button')]")
    private List<WebElement> headerActionsButtonList;

    @FindBy(xpath = ".//li[contains(@class, 'links-item') or contains(@class, 'language-item')]")
    private List<WebElement> headerTopLineButtonList;

    //and
    @FindBy(xpath = ".//span[contains(@class, header) and contains(text(), 'Здравствуйте')]")
    private WebElement greetingsForUser;

    @FindBy(xpath = ".//button[@type = 'button' and contains(@class, 'microphone')]")
    private WebElement microphoneButton;

    //ancestor
    @FindBy(xpath = ".//ancestor::li[contains(@class, 'links-item')]/a[contains(text(), 'Контакты')]")
    private WebElement contacts;

    @FindBy(xpath = ".//ancestor::li[contains(@class, 'links-item')]/a[contains(text(), 'Помощь')]")
    private WebElement help;

    //parent
    @FindBy(xpath = ".//a[@class = 'main-stores__button']/parent::div")
    private WebElement mainStores;

    @FindBy(xpath = ".//div[contains(@class, 'support')]/parent::div")
    private WebElement mainSupport;

    //preceding
    @FindBy(xpath = ".//div[@class = 'layout']/preceding::h1")
    private WebElement productTitle;

    @FindBy(xpath = ".//div[@class = 'layout']/preceding::section[@class = 'js-bottom-text']")
    private WebElement bottomText;

    //preceding-sibling (computers-notebooks page)
    @FindBy(xpath = "(.//li[@class = 'portal-grid__cell'])[4]/preceding-sibling::li")
    private List<WebElement> productListPrecedingFourthElement;

    @FindBy(xpath = "(.//li[@class = 'portal-grid__cell'])[2]/preceding-sibling::li")
    private WebElement firstProduct;

    //descendant
    @FindBy(xpath = ".//div[contains(@class, 'main-stores')]//descendant::a/img[@alt = 'Google Play']")
    private WebElement googlePlayStore;

    @FindBy(xpath = ".//div[contains(@class, 'main-stores')]//descendant::a/img[@alt = 'AppStore']")
    private WebElement appStore;

    //following-sibling
    @FindBy(xpath = "(.//li[@class = 'portal-grid__cell'])[1]/following-sibling::li")
    private List<WebElement> productListFollowingFirstElement;

    @FindBy(xpath = "(.//li[@class = 'portal-grid__cell'])[5]/following-sibling::li")
    private WebElement lastProduct;

    //text
    @FindBy(xpath = ".//button[contains(text(), 'Найти')]")
    private WebElement searchButton;

    @FindBy(xpath = ".//li[contains(@class, 'links-item')]/a[contains(text(), 'ответ')]")
    private WebElement responseToCovid;

    //count
    @FindBy(xpath = "//label[count(@for) = 1 and @for = 'Huawei']")
    private WebElement huaweiFirmSelectButton;

    @FindBy(xpath = "//button[count(@type) = 0 and contains(@arial-label, 'Крупная')]")
    private WebElement largeTileForViewProductListButton;

    //  |
    @FindBy(xpath = ".//input | .//class")
    private WebElement searchFieldFindByOr;

    @FindBy(xpath = ".//a[@class = 'header-bottomline']| .//span[@class = 'menu-toggler__text']")
    private WebElement catalogButton;

    // >=

    // <=

    // mod

    public PageWithLocators(WebDriver driver) {
        super(driver);
    }
}