package testCases;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MajorManagementPage;

public class MajorTest extends BaseTest {
    MajorManagementPage majorManagement;
    @BeforeClass
    public void setupClass(){
        majorManagement= new MajorManagementPage(driver,wait);
    }
    @BeforeTest
    public void setupTest(){
    }

    @Test
    public void addNewMajor(){
        majorManagement.addNewMajorDetails("adkin","shinki","SSTK","Đặc biệt");
        sleep(10);
    }
}
