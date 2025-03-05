package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverConfig {
    public static String baseURL = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login";
    private static WebDriver driver;



    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.addArguments("--headless=new");
            chromeOptions.setAcceptInsecureCerts(true); // Chấp nhận chứng chỉ không an toàn
            chromeOptions.addArguments("--ignore-certificate-errors"); // Bỏ qua lỗi chứng chỉ
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void sleep(long second) {
        try {
            Thread.sleep(second + 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
