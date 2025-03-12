package utils;

import base.BaseTest;
import org.testng.annotations.DataProvider;


public class JsonReader extends BaseTest {

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
     * @author Nguyễn Liên Nhi - 2274802010612
     */

    // DataProvider with corrected data types
    @DataProvider(name = "academicDegreeData")
    public Object[][] AcademicDegreeData() {
        return new Object[][]{
                // Dữ liệu hợp lệ
                {"MDA001", "Professora", 12},
                {tools.generateRandomString(50), tools.generateRandomString(50), 20},  // Nhập các giá trị ngẫu nhiên
                // Dữ liệu không hợp lệ
                {tools.generateRandomString(100), tools.generateRandomString(150), 110},


                // Các trường hợp kiểm thử lỗi với mã học hàm (ID)
                {"", "Parofessorc", 13},  // Thất bại do trống cột - id
                {"MDA001", "Another Valid Name", 2},  // Thất bại do mã học hàm trùng lặp (ID duplicate)
                {"AABavAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "Valid Name", 1},  // Thất bại do mã học hàm dài hơn 50 ký tự
                {"MCB_002", "Valid Name", 1},  // Thất bại do mã học hàm có dấu gạch dưới (ID không hợp lệ)

                // Các trường hợp kiểm thử lỗi với tên học hàm (Name)
                {"MCB004", "", 2},  // Thất bại do trống cột - tên học hàm (Name empty)
                {"MDB005", "A very long name that exceeds the allowed limit of 100 characters, making this a test case that should fail!", 3},  // Thất bại do tên học hàm dài quá 100 ký tự

                // Các trường hợp kiểm thử lỗi với thứ tự (Order)
                {"MEB006", "Valid Name", 0},  // Thất bại do thứ tự = 0 (Order = 0)
                {"MEB007", "Valid Name", 111},  // Thất bại do thứ tự > 100 (Order > 100)
                {"MEB008", "Valid Name", 15},  // Dữ liệu hợp lệ với thứ tự trong phạm vi

        };
    }

}
