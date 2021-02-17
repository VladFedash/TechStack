package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotebooksPage extends BasePage {
    private static final String PRODUCT_FIRM= ".//label[@for = '%s']";

    @FindBy(xpath = PRODUCT_FIRM)
    public WebElement productFirm;

    public NotebooksPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getProductFirm(String firm){
        productFirm = driver.findElement(By.xpath(String.format(PRODUCT_FIRM, firm)));
        waitUtils.waitForElementVisibilityAfterMiddleWait(productFirm);
        return productFirm;
    }
}