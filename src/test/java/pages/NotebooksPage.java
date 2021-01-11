package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NotebooksPage extends BasePage {

    @FindBy(xpath = "//select[contains(@class, 'select')]")
    private WebElement sortedList;

    @FindBy(xpath = "//span[contains(@class, 'price-value')]")
    private List<WebElement> productPriceList;

    @FindBy(xpath = "//a[contains(@class, 'filter__link')]/label[@for = 'HP']")
    private WebElement hpFirmSelectButton;

    public NotebooksPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSortedList() {
        return sortedList;
    }

    public List<WebElement> getProductPriceList() {
        return productPriceList;
    }

    public WebElement getHpFirmSelectButton() {
        return hpFirmSelectButton;
    }

    public void clickHpFirmSelectButton() {
        hpFirmSelectButton.click();
    }
}
