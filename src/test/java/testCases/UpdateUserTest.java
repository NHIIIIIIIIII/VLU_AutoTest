package testCases;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.UpdateUserPage;

public class UpdateUserTest extends BaseTest {
    private UpdateUserPage updateUser;

    @BeforeClass
    public void setupClass() {
        updateUser = new UpdateUserPage(driver, wait);
    }

    @BeforeMethod
    public void prepareTest() {
        updateUser.clickUserTab();
        updateUser.enterSearchQuery("Buiquangtuong@vanlanguni.vn");
        updateUser.clickUpdateButton();
    }

    @Test
    public void testUpdateUserIdWithInvalidCharacters() {
        String currentUserId = updateUser.getUserIdValue();
        String invalidUserId = currentUserId + "a b c";
        updateUser.enterUserId(invalidUserId);
        updateUser.clickSaveButton();
        updateUser.checkUserIdError("Chỉ được nhập số-chữ không dấu và không có khoảng trắng!");
    }

    @Test
    public void testUpdateEmailWithInvalidDomain() {
        String currentEmail = updateUser.getEmailValue();
        String invalidEmail = currentEmail.replace("@vanlanguni.vn", "@gmail.com");
        updateUser.enterEmail(invalidEmail);
        updateUser.clickSaveButton();
        updateUser.checkEmailError("Vui lòng nhập email Văn Lang hợp lệ!");
    }

    @Test
    public void testUpdateUserIdWithValidChange() {
        String currentUserId = updateUser.getUserIdValue();
        String newUserId = currentUserId + "1";
        updateUser.enterUserId(newUserId);
        updateUser.clickSaveButton();
        updateUser.checkSuccessNotification();
    }

    @AfterMethod
    public void cleanupAfterTest() {
        try {
            updateUser.clickCloseButton();
        } catch (Exception e) {
            System.out.println("Không thể bấm nút Exit sau test case. Chi tiết lỗi: " + e.getMessage());
        }
    }
}