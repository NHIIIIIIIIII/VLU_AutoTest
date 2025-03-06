package testCases;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.UserManagementPage;

public class UserManagementTest extends BaseTest {
    private UserManagementPage userManagement;

    @BeforeClass
    public void setupClass() {
        userManagement = new UserManagementPage(driver, wait);
        // Precondition: Ensure stable internet connection and account with permission to create users
        System.out.println("Precondition: Kết nối Internet ổn định, tài khoản có quyền tạo tài khoản.");
    }

    @DataProvider(name = "UserDataForCreation")
    public Object[][] userDataForCreation() {
        return new Object[][]{
                // TC_CU_01: Nhập thông tin hợp lệ (email có đuôi @vanlanguni.vn)
                {"227480201091180", "Nguyen Van An", "nguyenvanan@vanlanguni.vn", "Cơ hữu", "Giảng viên"},
                // TC_CU_02: Nhập đầy đủ (bao gồm chọn role) nhưng email không hợp lệ
                {"227480280109181", "Tran Thi Be", "tranthibe@gmail.com", "Thỉnh giảng", "Giảng viên"},
                // TC_CU_03: Không nhập gì cả
                {"", "", "", "", ""}
        };
    }

    @Test(dataProvider = "UserDataForCreation")
    public void testUserCreationScenarios(String id, String name, String email, String contract, String role) throws InterruptedException {
        // TC_CU_01: Kiểm tra hệ thống cho phép tạo người dùng mới với thông tin hợp lệ
        if (email.endsWith("@vanlanguni.vn") && !id.isEmpty() && !name.isEmpty() && !contract.isEmpty() && !role.isEmpty()) {
            System.out.println("🔹 TC_CU_01: Kiểm tra hệ thống cho phép tạo người dùng mới với thông tin hợp lệ...");
            userManagement.addUserWithMultiCase(id, name, email, contract, role);
            System.out.println("TC_CU_01: Test thành công - Hệ thống cho phép tạo người dùng mới và hiển thị thông báo thành công tại XPath /html[1]/body[1]/span[1].");
        }
        // TC_CU_02: Kiểm tra hệ thống báo lỗi khi nhập email không hợp lệ
        else if (email.contains("@") && !email.endsWith("@vanlanguni.vn") && !id.isEmpty() && !name.isEmpty() && !contract.isEmpty() && !role.isEmpty()) {
            System.out.println("🔹 TC_CU_02: Kiểm tra hệ thống báo lỗi khi nhập email không hợp lệ...");
            userManagement.addNewUserDetails(id, name, email, contract, role);
            userManagement.checkEmailError("Vui lòng nhập email Văn Lang hợp lệ!");
            userManagement.clickCloseButton();
            System.out.println("TC_CU_02: Test thành công - Hệ thống báo lỗi 'Vui lòng nhập email Văn Lang hợp lệ!'.");
        }
        // TC_CU_03: Kiểm tra hệ thống báo lỗi khi bỏ trống trường bắt buộc
        else if (id.isEmpty() && name.isEmpty() && email.isEmpty() && contract.isEmpty() && role.isEmpty()) {
            System.out.println("🔹 TC_CU_03: Kiểm tra hệ thống báo lỗi khi bỏ trống trường bắt buộc...");
            userManagement.addNewUserDetails(id, name, email, contract, role);
            userManagement.checkUserIdError("Bạn chưa nhập mã giảng viên");
            userManagement.checkUserNameError("Bạn chưa nhập tên giảng viên");
            userManagement.checkContractTypeError("Bạn chưa chọn loại giảng viên");
            userManagement.checkRoleError("Bạn chưa chọn role");
            userManagement.clickCloseButton();
            System.out.println("TC_CU_03: Test thành công - Hệ thống hiển thị 4 thông báo lỗi: Bạn chưa nhập mã giảng viên, Bạn chưa nhập tên giảng viên, Bạn chưa chọn loại giảng viên, Bạn chưa chọn role.");
        }
        Thread.sleep(1000); // Đợi để đảm bảo hành động được thực hiện
    }
}