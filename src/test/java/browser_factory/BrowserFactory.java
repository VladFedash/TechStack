package browser_factory;

import helpers.ReadProperty;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    WebDriver driver;
    String browser;

    public WebDriver getBrowser() {
        browser = new ReadProperty().readProperty("BROWSER");
        switch (browser.toUpperCase()) {
            case "CHROME" -> {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
            }
            case "FIREFOX" -> {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
            }
            default -> throw new RuntimeException("Unexpected value: " + browser.toUpperCase());
        }
        return driver;
    }
}