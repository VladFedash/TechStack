package enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum Browsers {
    CHROME {
        public WebDriver create(){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            return new ChromeDriver();
        }
    },
    FIREFOX {
        public WebDriver create() {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            return new FirefoxDriver();
        }
    };

    public WebDriver create(){
        return null;
    }
}