package testCases;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MajorManagementPage;

public class MajorTest extends BaseTest {
    MajorManagementPage majorManagement;

    @BeforeClass
    public void setupClass() {
        majorManagement = new MajorManagementPage(driver, wait);
    }

    @BeforeTest
    public void setupTest() {
    }


    /**
     * @author Trần Văn Hiếu - 2274802010262
     */
    @DataProvider(name = "Major")
    public Object[][] MajorData() {
        return new Object[][]{
                // id,  name,  abbreviation,  programName
                    {"CNTT2023", "Công nghệ thông tin 2023", "CNTT", "Đặc biệt"}, // Thêm ngành học thành công
                {"CNTT2023", "Công nghệ thông tin 2023", "CNTT", "Đặc biệt"}, //Thêm ngành học bị trùng lặp
                {"CNTT202 3", "Công nghệ thông tin 223", "CNTT233","Tiêu chuẩn"}, // Thêm ngành học với Id sai quy tắc (chứa khoảng trắng)
                {"CNTT2023#@$%^", "Công nghệ thông tin 223", "CNTT233", "Tiêu chuẩn"}, // Thêm ngành học với Id sai quy tắc (chứa kí tự đặc biệt)
                {"", "", "", ""}, // Trống
                {tools.generateRandomString(258), tools.generateRandomString(258), tools.generateRandomString(52), "Đặc biệt"}, // Nhập các giá trị trên cận biên trên
        };
    }

    /**
     * @author Trần Văn Hiếu - 2274802010262
     * @Title: Kiểm thử chức năng thêm ngành học
     * @since 06/03/2025
     */
    @Test(dataProvider = "Major")
    public void addNewMajor(String id, String name, String abbreviation, String programName) {
        sleep(10);
        majorManagement.addMajorWithMultiCase(id, name, abbreviation, programName);
        sleep(10);
    }


}
