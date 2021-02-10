package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import values.PricesDTO;
import values.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage {
    private static final String PRODUCT_BLOCK = ".//span[@class='goods-tile__title'][contains(text(), '%s')]/ancestor::div[@class = 'goods-tile']";
    private static List<Integer> actualProductPriceList;

    PricesDTO pricesDTO = new PricesDTO();

    @FindBy(xpath = ".//span[@class = 'goods-tile__title']")
    public List<WebElement> titleProductList;

    @FindBy(xpath = ".//p[@class = 'catalog-selection__label']")
    public WebElement productAmountOnPage;

    @FindBy(xpath = ".//div[@class = 'catalog-empty']")
    public WebElement massageAboutNoMatches;

    @FindBy(xpath = PRODUCT_BLOCK)
    public WebElement productBlock;

    @FindBy(xpath = ".//span[@class='goods-tile__price-value']")
    public List<WebElement> productPriceList;

    @FindBy(xpath = "//div[contains(@class, 'with_sidebar')]")
    public WebElement sidebar;

    @FindBy(xpath = ".//span[@class = 'show-more__text']")
    public WebElement showMoreButton;

    @FindBy(xpath = ".//select[contains(@class, 'select')]")
    public WebElement sortedList;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void addProductInCart(String str) {
        productBlock = driver.findElement(By.xpath(String.format(PRODUCT_BLOCK, str)));
        baseOperations.clickButton(productBlock.findElement(By.xpath(".//button[contains(@class, 'buy-button')]")));
        pricesDTO.addItem(new ProductDTO(baseOperations.getProductPrice(productBlock.findElement(By.xpath(".//span[@class='goods-tile__price-value']"))), str));
    }

    public List<Integer> getProductPriceList() {
        productPriceList.forEach(productPrice -> {
            waitUtils.waitForElementVisibilityAfterMiddleWait(productPrice);
            actualProductPriceList = new ArrayList<>();
            actualProductPriceList.add(baseOperations.getProductPrice(productPrice));
        });
        return actualProductPriceList;
    }

    public int getProductDTOSubtotal() {
        return pricesDTO.subtotal;
    }
}