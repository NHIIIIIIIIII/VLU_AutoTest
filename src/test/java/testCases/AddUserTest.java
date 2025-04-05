package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddUserPage;

public class AddUserTest extends BaseTest {
    @BeforeClass
    public void setupClass() {
        new AddUserPage(driver, wait);
        System.out.println("Precondition: Kết nối Internet ổn định, tài khoản có quyền tạo tài khoản.");
    }

    @Test
    public void TC_CU_01() throws InterruptedException {
        System.out.println("🔹 TC_CU_01: Kiểm tra hệ thống cho phép tạo người dùng mới với thông tin hợp lệ...");
        AddUserPage.addNewUserDetails(
            "227480201091840",
            "Nguyen Van An",
            "nguyenvanan@vanlanguni.vn",
            "Cơ hữu",
            "Giảng viên"
        );
        AddUserPage.checkSuccessNotification();
        System.out.println("TC_CU_01: Test thành công - Hệ thống cho phép tạo người dùng mới và hiển thị thông báo t    hành công.");
        Thread.sleep(1000);
    }

    @Test
    public void TC_CU_02() throws InterruptedException {
        System.out.println("🔹 TC_CU_02: Kiểm tra hệ thống báo lỗi khi nhập email không hợp lệ...");
        System.out.println("==========================================");
        System.out.println("Test Data:");
        System.out.println("User ID: 227480201091851");
        System.out.println("Name: Tran Thi Be");
        System.out.println("Email: tranthibe@gmail.com");
        
        AddUserPage.addNewUserDetails1(
                "227480201091851",
                "Tran Thi Be",
                "tranthibe@gmail.com"
        );

        String expectedError = "Vui lòng nhập email Văn Lang hợp lệ!";
        String actualError = AddUserPage.getEmailErrorMessage();
        
        System.out.println("==========================================");
        System.out.println("Actual: " + actualError);
        System.out.println("Expect: " + expectedError);
        Assert.assertEquals(actualError, expectedError, "Email error message does not match expected");
        
        AddUserPage.clickCloseButton();
        Thread.sleep(1000);
    }

    @Test
    public void TC_CU_03() throws InterruptedException {
        System.out.println("🔹 TC_CU_03: Kiểm tra hệ thống báo lỗi khi bỏ trống trường bắt buộc...");
        System.out.println("==========================================");
        System.out.println("Test Data: All fields empty");
        
        AddUserPage.clickUserTab();
        AddUserPage.clickAddUserButton();
        AddUserPage.clickSaveButton();

        String expectedUserIdError = "Bạn chưa nhập mã giảng viên";
        String expectedNameError = "Bạn chưa nhập tên giảng viên";
        String expectedContractError = "Bạn chưa chọn loại giảng viên";
        String expectedRoleError = "Bạn chưa chọn role";
        
        String actualUserIdError = AddUserPage.getUserIdErrorMessage();
        String actualNameError = AddUserPage.getUserNameErrorMessage();
        String actualContractError = AddUserPage.getContractTypeErrorMessage();
        String actualRoleError = AddUserPage.getRoleErrorMessage();
        
        System.out.println("==========================================");
        System.out.println("User ID Error:");
        System.out.println("Actual: " + actualUserIdError);
        System.out.println("Expect: " + expectedUserIdError);
        Assert.assertEquals(actualUserIdError, expectedUserIdError, "User ID error message does not match");
        
        System.out.println("Name Error:");
        System.out.println("Actual: " + actualNameError); 
        System.out.println("Expect: " + expectedNameError);
        Assert.assertEquals(actualNameError, expectedNameError, "Name error message does not match");
        
        System.out.println("Contract Type Error:");
        System.out.println("Actual: " + actualContractError);
        System.out.println("Expect: " + expectedContractError);
        Assert.assertEquals(actualContractError, expectedContractError, "Contract error message does not match");
        
        System.out.println("Role Error:");
        System.out.println("Actual: " + actualRoleError);
        System.out.println("Expect: " + expectedRoleError);
        Assert.assertEquals(actualRoleError, expectedRoleError, "Role error message does not match");
        
        AddUserPage.clickCloseButton();
        Thread.sleep(1000);
    }
}