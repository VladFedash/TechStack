package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[contains(@class, 'search-form')]")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class = 'menu-toggler']")
    private WebElement catalog;

    @FindBy(xpath = "//a[@class ='menu__hidden-title'][contains(text(), 'Ноутбуки')]")
    private WebElement notebooksPageOpenButton;

    @FindBy(xpath = "//span[contains(@class, \"button-counter\")]")
    private WebElement productCountInCart;

    @FindBy(id = "cartProductActions0")
    private WebElement contextMenuButton;

    @FindBy(xpath = "//button[contains(@class, 'context-menu-actions__button')]")
    private WebElement deleteProductFromCartButton;

    @FindBy(xpath = "//h4[@class = 'cart-dummy__heading']")
    private WebElement emptyCartMessage;

    @FindBy(xpath = "//div[@class = 'js-rz-cart']")
    private WebElement openCartButton;

    @FindBy(xpath = "//p[@class = 'cart-product__price'] ")
    private List<WebElement> productPriceListInCart;

    @FindBy(xpath = "//div[contains(@class, 'sum-price')]")
    private WebElement totalProductPriceInCart;

    @FindBy(xpath = "//button[contains(@class, 'cities__label')]")
    private WebElement changeCitiesButton;

    @FindBy(xpath = "//div[@class = 'modal__header']")
    private WebElement modalHeader;

    @FindBy(xpath = "//a[@class = 'header-location__popular-link']")
    private List<WebElement> popularCityList;

    @FindBy(xpath = "//button[contains(@class, 'medium button')]")
    private WebElement acceptCityChoiceButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public void inputToSearchField(String keyword) {
        searchField.sendKeys(keyword, Keys.ENTER);
    }

    public void clickCatalogButton() {
        catalog.click();
    }

    public void clickNotebooksPageOpenButton() {
        notebooksPageOpenButton.click();
    }

    public WebElement getProductCountInCart() {
        return productCountInCart;
    }

    public WebElement getContextMenuButton() {
        return contextMenuButton;
    }

    public void clickContextMenuButton() {
        contextMenuButton.click();
    }

    public WebElement getDeleteProductFromCartButton() {
        return deleteProductFromCartButton;
    }

    public void clickDeleteProductFromCartButton() {
        deleteProductFromCartButton.click();
    }

    public WebElement getEmptyCartMessage() {
        return emptyCartMessage;
    }

    public void ClickOpenCartButton() {
        openCartButton.click();
    }

    public List<WebElement> getProductPriceListInCart() {
        return productPriceListInCart;
    }

    public int getTotalProductPriceInCart() {
        return Integer.parseInt(totalProductPriceInCart.getText().replaceAll("[^0-9]", ""));
    }

    public void clickChangeCitiesButton(){
        changeCitiesButton.click();
    }

    public WebElement getCityTitle(){
        return changeCitiesButton;
    }

    public WebElement getModalHeader(){
        return modalHeader;
    }

    public List<WebElement> getPopularCityList(){
        return popularCityList;
    }

    public void clickAcceptCityChoiceButton(){
        acceptCityChoiceButton.click();
    }



}
