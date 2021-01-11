package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//span[@class = 'goods-tile__title']")
    private List<WebElement> titleProductList;

    @FindBy(xpath = "//p[@class = 'catalog-selection__label']")
    private WebElement productAmountOnPage;

    @FindBy(xpath = "//div[@class = 'search-nothing__wrap']")
    private WebElement massageAboutNoMatches;

    @FindBy(xpath = "(//button[contains(@class, \"buy-button\")])[1]")
    private WebElement addFistProductInCartButton;

    @FindBy(xpath = "//button[contains(@class, \"buy-button\")]")
    private List<WebElement> addProductInCartButton;

    @FindBy(xpath = "//span[@class = 'exponea-close-cross']")
    private WebElement closeAdButton;

    @FindBy(id = "rz-banner")
    private WebElement banner;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getTitleProductList() {
        return titleProductList;
    }

    public WebElement getProductAmountOnPage() {
        return productAmountOnPage;
    }

    public WebElement getMassageAboutNoMatches() {
        return massageAboutNoMatches;
    }

    public void clickAddFistProductInCartButton() {
        addFistProductInCartButton.click();
    }

    public void clickAddProductInCartButton() {
        for (WebElement element : addProductInCartButton) {
            element.click();
        }
    }


    public WebElement getBanner() {
        return banner;
    }

    public void clickCloseAd() {
        closeAdButton.click();
    }

    public WebElement getCloseAdButton() {
        return closeAdButton;
    }
}
