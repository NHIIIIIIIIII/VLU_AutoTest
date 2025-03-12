package testCases;

import base.BaseTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AcademicDegreeManagementPage;
import utils.JsonReader;

public class AcademicDegreeTest extends BaseTest {
    private AcademicDegreeManagementPage academicDMPage;

    @BeforeClass
    public void setupTest() {
        academicDMPage = new AcademicDegreeManagementPage(driver);
    }

    /**
     * @author Nguyễn Liên Nhi - 2274802010612
     * @Title: Kiểm thử chức năng thêm học hàm học vị
     * @since 06/03/2025
     */
    @Test(dataProvider = "academicDegreeData", dataProviderClass = JsonReader.class)

    public void testAddAD(String id, String name,  int order) {
        // Delay để chờ trước khi thực hiện thao tác (tuỳ chỉnh nếu cần)
        sleep(10);

        // Thực hiện thêm học hàm học vị
        academicDMPage.addNewADDetails(id, name, order);

        // Bạn có thể thêm assertion để xác nhận kết quả nếu cần
        // Assert.assertTrue(driver.getPageSource().contains(name), "Academic Degree '" + name + "' was not added successfully");
    }

    @AfterClass
    public void tearDown() {
        cleanupTest();
    }
}
