package testCases;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MajorManagementPage;
import utils.JsonReader;


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
     * @Title: Kiểm thử chức năng thêm ngành học
     * @since 06/03/2025
     */
    @Test(dataProvider = "Major",  dataProviderClass = JsonReader.class)
    public void addNewMajor(String id, String name, String abbreviation, String programName) {
        sleep(10);
        majorManagement.addMajorWithMultiCase(id, name, abbreviation, programName);
        sleep(10);
    }


}
