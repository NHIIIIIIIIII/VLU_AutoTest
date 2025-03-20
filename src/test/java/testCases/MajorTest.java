package testCases;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MajorManagementPage;
import utils.JsonReader;


public class MajorTest extends BaseTest {
    MajorManagementPage majorManagement;

    /**
     * @author Trần Văn Hiếu - 2274802010262
     */
    @DataProvider(name = "updateMajor")
    public static Object[][] updateMajorData() {
        return new Object[][]{

                // pass case
//                {"TCC02", "Toan cao cap 02", "TCC", "Tiêu chuẩn"},
//                {"ITSE02", "Công Nghệ Thông Tin 2", "CNTT02", "Đặc biệt"},
                {"K26ITSE111", "IT-SoftwareEngineer", "ITSE", "Tiêu chuẩn"},
                {"OTSE", "CÔNG NGHỆ Ô TÔ", "CNOT", "Đặc biệt"},

                // error case
                {"K26ITSE111", "", "ITSE", "Tiêu chuẩn"},
                {"K26ITSE111", "IT-SoftwareEngineer", "", "Tiêu chuẩn"},
                {"K26ITSE111", "IT-SoftwareEngineer", "ITSE", ""},
                {"", "", "", ""},

                {"ITSE02", "IT-SoftwareEngineer", tools.generateRandomString(51), "Đặc biệt"}, // Nhập các giá trị trên cận biên trên
                {"ITSE02", tools.generateRandomString(256), "ITSE", "Đặc biệt"}, // Nhập các giá trị trên cận biên trên
                {"ITSE02", tools.generateRandomString(256), tools.generateRandomString(51), "Đặc biệt"}, // Nhập các giá trị trên cận biên trên
        };
    }

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
//    @DataProvider(name = "newMajor")
//    public Object[][] newMajorData() {
//        return new Object[][]{
//                // id,  name,  abbreviation,  programName
//                {"CNTT2023", "Công nghệ thông tin 2023", "CNTT", "Đặc biệt"}, // Thêm ngành học thành công
//                {"CNTT2023", "Công nghệ thông tin 2023", "CNTT", "Đặc biệt"}, //Thêm ngành học bị trùng lặp
//                {"CNTT202 3", "C        ông nghệ thông tin 223", "CNTT233", "Tiêu chuẩn"}, // Thêm ngành học với Id sai quy tắc (chứa khoảng trắng)
//                {"CNTT2023#@$%^", "Công nghệ thông tin 223", "CNTT233", "Tiêu chuẩn"}, // Thêm ngành học với Id sai quy tắc (chứa kí tự đặc biệt)
//                {"", "", "", ""}, // Trống
//                {tools.generateRandomString(256), tools.generateRandomString(256), tools.generateRandomString(51), "Đặc biệt"}, // Nhập các giá trị trên cận biên trên
//        };
//    }

    /**
     * @author Trần Văn Hiếu - 2274802010262
     * @Title: Kiểm thử chức năng thêm ngành học
     * @since 06/03/2025
     */
    @Test(dataProvider = "newMajor", priority = 0)
    public void addNewMajor(String id, String name, String abbreviation, String programName) {
        sleep(10);
        majorManagement.addMajorWithMultiCase(id, name, abbreviation, programName);
        sleep(10);
    }

    /**
     * @author Trần Văn Hiếu - 2274802010262
     * @Title: Kiểm thử chức năng sửa ngành học
     * @since 13/03/2025
     */
    @Test(dataProvider = "updateMajor", priority = 1)
    public void updateMajor(String id, String name, String abbreviation, String programName) {
        sleep(10);
        majorManagement.updateMajorWithMultiCase(id, name, abbreviation, programName);
        sleep(10);
    }



}
