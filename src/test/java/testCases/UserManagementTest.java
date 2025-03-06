package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.UserManagementPage;

public class UserManagementTest extends BaseTest {
    private UserManagementPage userManagement;

    @BeforeClass
    public void setupClass() {
        userManagement = new UserManagementPage(driver, wait);
    }

    @BeforeTest
    public void setupTest() {
    }


    @Test
    public void addNewUser() {
       userManagement.addNewUserDetails("2274802010979", "Bui Ke Ton Hau", "BuiKeTonHau@vanlanguni.vn", "Thỉnh giảng", "Giảng viên");
    }}
//    @Test
//    public void InvalidEmail() {
//        userManagement.InvalidEmailFormat("22748020109799", "Bui Ke Ton Hau", "BuiKeTonHau@gmail.com", "Thỉnh giảng", "Giảng viên");
//        String errorMessage = userManagement.getErrorMessage();
//        Assert.assertTrue(errorMessage.contains("Vui lòng nhập email Văn Lang hợp lệ!"), "Lỗi không hiển thị hoặc sai nội dung!");
//    }
//
//}