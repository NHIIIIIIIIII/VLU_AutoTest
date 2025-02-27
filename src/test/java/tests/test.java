package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.AuthenticationMicrosoftBypass;

public class test {
    AuthenticationMicrosoftBypass auth;
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        auth = new AuthenticationMicrosoftBypass();
    }


    @Test(priority = 1)
    public void testGetCookiesFromFirstLogin() throws InterruptedException {
        long timeToAuthentication = 10000; // 10 giây để hoàn tất xác thực (tùy chỉnh)
        String cookies = auth.getCookiesFromFirstLogin(timeToAuthentication);
        System.out.println("Cookies từ lần đăng nhập đầu tiên: " + cookies);
    }

    @Test(priority = 2)
    public void testLoginWithCookies() throws InterruptedException {
        String cookies = auth.getCookiesFromPropertiesFile();
        if (cookies != null) {
            driver = auth.loginToWebsite(cookies);
            System.out.println("URL sau khi đăng nhập bằng cookies: " + driver.getCurrentUrl());
        } else {
            System.out.println("Không tìm thấy cookies trong file properties.");
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
