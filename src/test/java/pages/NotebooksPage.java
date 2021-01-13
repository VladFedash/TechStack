package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NotebooksPage extends BasePage {

    @FindBy(xpath = "//select[contains(@class, 'select')]")
    public WebElement sortedList;

    @FindBy(xpath = "//span[contains(@class, 'price-value')]")
    public List<WebElement> productPriceList;

    @FindBy(xpath = "//a[contains(@class, 'filter__link')]/label[@for = 'Acer']")
    public WebElement acerFirmSelectButton;

    public NotebooksPage(WebDriver driver) {
        super(driver);
    }
}