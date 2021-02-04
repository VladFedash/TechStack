package browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class BrowserFactory {
    String browsers;
    WebDriver driver;
    Properties prop = new Properties();

    public WebDriver getBrowser() {
        try {
            prop.load(new FileInputStream("src/main/resources/config.properties"));
            browsers = prop.getProperty("BROWSER");
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (browsers.toUpperCase()) {
            case "CHROME" -> {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
            }
            case "FIREFOX" -> {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
            }
            default -> throw new IllegalStateException("Unexpected value: " + browsers.toUpperCase());
        }
        return driver;
    }
}
