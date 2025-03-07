package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.AuthenticationMicrosoftBypass;
import utils.JsonReader;
import utils.Tools;

import java.time.Duration;

public class BaseTest extends DriverConfig {
    protected static Tools tools;
    protected static JavascriptExecutor js;
    protected static WebDriver driver;
    protected static AuthenticationMicrosoftBypass auth;
    protected static WebDriverWait wait;
    protected static JsonReader jsonReader;

    @BeforeSuite
    protected void setupSuite() {
        driver = getDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        tools = new Tools(driver);
        js = (JavascriptExecutor) driver;
        auth = new AuthenticationMicrosoftBypass(driver);
        auth.setBrowserCookiesFromProperties();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        jsonReader = new JsonReader();


    }

    @AfterSuite
    protected void cleanupTest() {
        quitDriver();
    }
}
