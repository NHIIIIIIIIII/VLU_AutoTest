package testCases;

import base.BaseTest;
import base.Notification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AcademicDegreeManagementPage;
import utils.AuthenticationMicrosoftBypass;

import java.time.Duration;

public class AcademicDegreeTest extends BaseTest {
    private AuthenticationMicrosoftBypass auth;
    private AcademicDegreeManagementPage academicDegreeManagementPage;
    private Notification notification;
    private WebDriverWait wait;

    @BeforeClass
    public void setupTest() {
        // Sử dụng driver từ BaseTest, không khai báo lại
        driver = getDriver(); // Giả sử BaseTest có phương thức getDriver()
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized in BaseTest!");
        }

        // Khởi tạo WebDriverWait với timeout 10 giây
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Khởi tạo đối tượng auth để xử lý đăng nhập
        auth = new AuthenticationMicrosoftBypass(driver);

        // Thử đăng nhập bằng cookies từ file properties
        try {
            auth.setBrowserCookiesFromProperties();
            sleep(2000); // Chờ 2 giây để cookies được áp dụng

            // Kiểm tra xem đã đăng nhập thành công hay chưa
            if (driver.getCurrentUrl().contains("Login")) {
                System.out.println("Cookies từ file không hoạt động, thử đăng nhập lại...");
                auth.loginToWebsite(null);
            } else {
                System.out.println("Đăng nhập thành công bằng cookies từ file properties!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi thiết lập cookies: " + e.getMessage());
            // Điều hướng đến trang đăng nhập nếu có lỗi
            driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
            auth.loginToWebsite(null);
        }

        // Khởi tạo AcademicDegreeManagementPage và điều hướng đến trang cần thiết
        academicDegreeManagementPage = new AcademicDegreeManagementPage(driver);
        academicDegreeManagementPage.navigateToAcademicDegreePage();

        // Khởi tạo Notification với WebDriverWait
        notification = new Notification(wait);
    }

    @Test
    public void testAddADSucces() {
        String code = "AMCD01";
        String name = "aaaaaaAAAHHHHdddddđe";
        int order = 5;

        // Kiểm tra null để đảm bảo an toàn
        if (academicDegreeManagementPage == null) {
            throw new IllegalStateException("academicDegreeManagementPage is not initialized!");
        }
        if (notification == null) {
            throw new IllegalStateException("notification is not initialized!");
        }

        academicDegreeManagementPage.addNewADDetails(code, name, order);
        notification.checkAddNotification(); // Kiểm tra thông báo "Lưu thành công!"

        // Kiểm tra xem tên học vị có xuất hiện trên trang hay không
        Assert.assertTrue(driver.getPageSource().contains(name), "Academic Degree was not added successfully");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Đóng trình duyệt sau khi hoàn tất
        }
    }

    // Phương thức sleep để tái sử dụng
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}