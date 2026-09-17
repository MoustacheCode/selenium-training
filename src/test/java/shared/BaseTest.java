package shared;

import config.ConfigReader;
import config.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver driver;


    @BeforeEach
    public void setup() {

        driver = WebDriverFactory.createDriver(ConfigReader.browser());
        driver.get(ConfigReader.baseUrl());

    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
