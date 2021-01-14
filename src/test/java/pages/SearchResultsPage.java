package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(partialLinkText = "Мобильный")
    public List<WebElement> titleProductList;

    @FindBy(xpath = "//p[@class = 'catalog-selection__label']")
    public WebElement productAmountOnPage;

    @FindBy(xpath = "//div[@class = 'search-nothing__wrap']")
    public WebElement massageAboutNoMatches;

    @FindBy(xpath = "//button[contains(@class, 'buy-button')]")
    public List<WebElement> addProductInCartButton;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickAddVisibleProductInCartButton() {
        for (WebElement element : addProductInCartButton) {
            element.click();
        }
    }
}