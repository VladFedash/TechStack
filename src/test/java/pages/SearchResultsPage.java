package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//span[@class = 'goods-tile__title']")
    public List<WebElement> titleProductList;

    @FindBy(xpath = "//p[@class = 'catalog-selection__label']")
    public WebElement productAmountOnPage;

    @FindBy(xpath = "//div[@class = 'search-nothing__wrap']")
    public WebElement massageAboutNoMatches;

    @FindBy(xpath = "//button[contains(@class, 'buy-button')]")
    public List<WebElement> addProductInCartButton;

    @FindBy(xpath = "//span[@class = 'exponea-close-cross']")
    public WebElement closeAdButton;

    @FindBy(id = "rz-banner")
    public WebElement banner;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddVisibleProductInCartButton() {
        for (WebElement element : addProductInCartButton) {
            element.click();
        }
    }
}
