package browser_factory;

import helpers.ReadProperty;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    protected WebDriver driver;
    protected String browser;

    public WebDriver getBrowser() {
        browser = new ReadProperty().readProperty("BROWSER");
        switch (browser.toUpperCase()) {
            case "CHROME" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();

            }
            case "FIREFOX" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
            default -> throw new RuntimeException("Unexpected value: " + browser.toUpperCase());
        }
        return driver;
    }
}