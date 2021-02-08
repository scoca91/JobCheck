package POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class InitiateDriver {

        public static WebDriver driver;

        @BeforeTest
        public void StartDriver() {
            System.setProperty("webdriver.chrome.driver", "ChromeDriver2.41/chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get("https://www.emag.ro/search/frigider?ref=effective_search");
        }
    }
