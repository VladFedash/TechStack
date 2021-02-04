package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotebooksPage extends BasePage {

    @FindBy(xpath = ".//label[@for = 'Lenovo']")
    public WebElement lenovoFirmSelectButton;

    public NotebooksPage(WebDriver driver) {
        super(driver);
    }
}