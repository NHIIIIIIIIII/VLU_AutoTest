package testCases;

import base.BaseTest;
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
    public void testValidUserCreation() throws InterruptedException {
        System.out.println("🔹 TC_CU_01: Kiểm tra hệ thống cho phép tạo người dùng mới với thông tin hợp lệ...");
        AddUserPage.addNewUserDetails(
                "227480201091840",
                "Nguyen Van An",
                "nguyenvanan@vanlanguni.vn",
                "Cơ hữu",
                "Giảng viên"
        );
        AddUserPage.checkSuccessNotification();
        System.out.println("TC_CU_01: Test thành công - Hệ thống cho phép tạo người dùng mới và hiển thị thông báo thành công.");
        Thread.sleep(1000);
    }

    @Test
    public void testInvalidEmailCreation() throws InterruptedException {
        System.out.println("🔹 TC_CU_02: Kiểm tra hệ thống báo lỗi khi nhập email không hợp lệ...");
        AddUserPage.addNewUserDetails1(
                "227480201091851",
                "Tran Thi Be",
                "tranthibe@gmail.com"
        );
        try {
            AddUserPage.checkEmailError("Vui lòng nhập email Văn Lang hợp lệ!");
            System.out.println("TC_CU_02: Test thành công - Hệ thống báo lỗi 'Vui lòng nhập email Văn Lang hợp lệ!'.");
        } catch (AssertionError e) {
            System.out.println("TC_CU_02: Test thất bại - Không hiển thị thông báo lỗi hoặc thông báo không đúng.");
            throw e;
        } finally {
            AddUserPage.clickCloseButton();
            Thread.sleep(1000);
        }
    }

    @Test
    public void testEmptyFieldsCreation() throws InterruptedException {
        System.out.println("🔹 TC_CU_03: Kiểm tra hệ thống báo lỗi khi bỏ trống trường bắt buộc...");
        AddUserPage.clickUserTab();
        AddUserPage.clickAddUserButton();
        AddUserPage.clickSaveButton();
        AddUserPage.checkUserIdError("Bạn chưa nhập mã giảng viên");
        AddUserPage.checkUserNameError("Bạn chưa nhập tên giảng viên");
        AddUserPage.checkContractTypeError("Bạn chưa chọn loại giảng viên");
        AddUserPage.checkRoleError("Bạn chưa chọn role");
        AddUserPage.clickCloseButton();
        System.out.println("TC_CU_03: Test thành công - Hệ thống hiển thị các thông báo lỗi cho các trường bắt buộc.");
        Thread.sleep(1000);
    }
}