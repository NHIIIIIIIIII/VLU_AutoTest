package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.AuthenticationMicrosoftBypass;

import static config.DriverConfig.getDriver;
import static config.DriverConfig.sleep;

public class test {
    AuthenticationMicrosoftBypass auth;
    WebDriver driver;

//    @BeforeClass
//    public void setupClass() {
//        driver = getDriver();
//        auth = new AuthenticationMicrosoftBypass(driver);
//
//        // Kiểm tra xem có cookies trong file không
//        Set<Cookie> cookies = auth.getCookiesFromPropertiesFile();
//
//        if (cookies != null && !cookies.isEmpty()) {
//            System.out.println("Tìm thấy cookies trong file, thử đăng nhập...");
//            driver = auth.loginToWebsite(cookies);
//        } else {
//            System.out.println("Không tìm thấy cookies trong file, cần đăng nhập thủ công...");
//            // Đăng nhập thủ công và lưu cookies (đợi 30 giây cho người dùng đăng nhập)
//            String cookiesString = auth.getCookiesFromFirstLogin(30000);
//            if (cookiesString != null) {
//                System.out.println("Đăng nhập thành công và đã lưu cookies.");
//            } else {
//                System.out.println("Đăng nhập thất bại hoặc không lưu được cookies.");
//            }
//        }
//    }

    @BeforeTest
    public void setupTest(){
        // Check if driver is null and initialize it if needed
        if (driver == null) {
            driver = getDriver();
            auth = new AuthenticationMicrosoftBypass(driver);
        }

        // Try to login with cookies from properties file
        try {
            // Thử đăng nhập bằng cookies từ config.properties
            auth.setBrowserCookiesFromProperties();
            sleep(2000);
            
            // Check if we're logged in
            if (driver.getCurrentUrl().contains("Login")) {
                System.out.println("Cookies từ file không hoạt động, thử đăng nhập lại...");
                auth.loginToWebsite(null);
            } else {
                System.out.println("Đăng nhập thành công bằng cookies từ file properties!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi thiết lập cookies: " + e.getMessage());
            // Ensure we're on a page before continuing
            driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
            auth.loginToWebsite(null);
        }
    }
    
    @BeforeMethod
    public void setupMethod(){
        // Đảm bảo rằng đã đăng nhập trước mỗi test method
//        if (driver.getCurrentUrl().contains("Login")) {
//            System.out.println("Chưa đăng nhập thành công, thử lại...");
//            auth.loginToWebsite(null);
//        }
    }

    @Test
    void f(){
//        auth.setBrowserCookiesFromProperties();
        driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
        String[] account = auth.getUsernameAndPasswordFromPropertiesFile();
        System.out.println(account[0]);
        System.out.println(account[1]);

    }
//    @Test(priority = 1)
//    public void testGetCookiesFromFirstLogin(){
//        // Kiểm tra xem đã đăng nhập thành công chưa
//        driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
//        sleep(2000);
//        System.out.println("URL hiện tại: " + driver.getCurrentUrl());
//
//        // Nếu URL không chứa "Login", tức là đã đăng nhập thành công
//        if (!driver.getCurrentUrl().contains("Login")) {
//            System.out.println("Đã đăng nhập thành công!");
//        } else {
//            System.out.println("Chưa đăng nhập thành công, thử lại...");
//            auth.loginToWebsite(null);
//        }
//    }
//
//    @Test(priority = 2)
//    public void testLoginWithCookies() {
//        // Kiểm tra xem cookies có còn hiệu lực không
//        boolean cookiesValid = auth.checkCookiesValid();
//        System.out.println("Cookies còn hiệu lực: " + cookiesValid);
//
//        // Nếu cookies không còn hiệu lực, thực hiện đăng nhập thủ công và lưu cookies mới
//        if (!cookiesValid) {
//            System.out.println("Cookies không còn hiệu lực, thực hiện đăng nhập thủ công...");
//            String newCookies = auth.getCookiesFromFirstLogin(30000);
//            if (newCookies != null) {
//                System.out.println("Đã lưu cookies mới thành công!");
//            }
//        }
//    }
//
//    @AfterTest
//    public void tearDown() {
//        // Không quit driver ở đây để giữ phiên đăng nhập
//        // Chỉ đóng driver khi kết thúc tất cả các test
//    }
//
//    @AfterClass
//    public void cleanupClass() {
//        // Lưu cookies trước khi đóng driver
//        if (driver != null) {
//            Set<Cookie> finalCookies = driver.manage().getCookies();
//            auth.setCookiesToPropertiesFile(finalCookies);
//            System.out.println("Đã lưu cookies cuối cùng trước khi đóng driver");
//
//            // Đóng driver
////            driver.quit();
//        }
//    }


}
