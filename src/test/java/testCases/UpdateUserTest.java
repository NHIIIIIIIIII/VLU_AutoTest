package testCases;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.UserManagementPage;

public class UpdateUserTest extends BaseTest {
    private UserManagementPage UserManagement;

    @BeforeClass
    public void setupClass() {
        UserManagement = new UserManagementPage(driver, wait);
    }

    @BeforeMethod
    public void prepareTest() {
        // Bấm vào tab Người dùng
        UserManagement.clickUserTab();

        // Tìm kiếm user với từ khóa "Buiquangphuong@vanlanguni.vn"
        UserManagement.enterSearchQuery("Buiquangtuong@vanlanguni.vn");

        // Bấm vào nút chỉnh sửa (UpdateButton)
        UserManagement.clickUpdateButton();
    }

    @Test
    public void testUpdateUserIdWithInvalidCharacters() {

        // Lấy giá trị mã giảng viên hiện tại và thêm "a b c"
        String currentUserId = UserManagement.getUserIdValue();
        String invalidUserId = currentUserId + "a b c";
        UserManagement.enterUserId(invalidUserId);

        // Bấm nút Lưu (saveButtonup)
        UserManagement.clickSaveButtonup();

        // Kiểm tra lỗi UserIdError1 xuất hiện
        UserManagement.checkUserIdError("Chỉ được nhập số-chữ không dấu và không có khoảng trắng!");
    }

    @Test
    public void testUpdateEmailWithInvalidDomain() {

        // Sửa email thành @gmail.com
        String currentEmail = UserManagement.getEmailValue();
        String invalidEmail = currentEmail.replace("@vanlanguni.vn", "@gmail.com");
        UserManagement.enterEmail(invalidEmail);

        // Bấm nút Lưu (saveButtonup)
        UserManagement.clickSaveButtonup();

        // Kiểm tra lỗi emailError xuất hiện
        UserManagement.checkEmailError("Vui lòng nhập email Văn Lang hợp lệ!");
    }

    @Test
    public void testUpdateUserIdWithValidChange() {

        // Lấy giá trị mã giảng viên hiện tại và thêm số 1 vào cuối
        String currentUserId = UserManagement.getUserIdValue();
        String newUserId = currentUserId + "1";
        UserManagement.enterUserId(newUserId);

        // Bấm nút Lưu (saveButtonup)
        UserManagement.clickSaveButtonup();

        // Kiểm tra thông báo thành công
        UserManagement.checkSuccessNotification();
    }

    @AfterMethod
    public void cleanupAfterTest() {
        // Bấm nút Exit (closeButton) để đóng form sau mỗi test case
        try {
            UserManagement.clickCloseButton();
        } catch (Exception e) {
            System.out.println("Không thể bấm nút Exit sau test case. Chi tiết lỗi: " + e.getMessage());
        }
    }
}