package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DeleteADMPage;
import utils.Notification;

public class DeleteADMTest extends BaseTest {
    DeleteADMPage deleteADMPage;
    private Notification notifiCheck;

    @BeforeClass
    public void setupClass() {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/AcademicDegree");
        deleteADMPage = new DeleteADMPage(driver, wait);
        notifiCheck = new Notification(wait);
    }

    @Test(priority = 0, testName = "TC_DeleteADM_01")
    public void testDeleteSuccess() {
        deleteADMPage.searchADM("Another Valid Name");
        deleteADMPage.clickDeleteButton();
        Assert.assertTrue(deleteADMPage.checkDialogConfirmDisplayed(), "Dialog not displayed");
        deleteADMPage.clickDeleteDialog();
        notifiCheck.testDeleteNotification();
//        deleteADMPage.clickFailClose();
    }

    @Test(priority = 1, testName = "TC_DeleteADM_02")
    public void testDeleteFailWithDataAvailable() {
        deleteADMPage.searchADM("Tiến sĩ");
        deleteADMPage.clickDeleteButton();
        Assert.assertTrue(deleteADMPage.checkDialogConfirmDisplayed(), "Dialog not displayed");
        deleteADMPage.clickDeleteDialog();
        sleep(5); // Giảm thời gian sleep từ 50 xuống 5 để tối ưu, tùy chỉnh nếu cần
        System.out.println("======= Fail Notification ======");
        System.out.println("Actual: " + deleteADMPage.getTextFailNotification());
        System.out.println("Expect: " + deleteADMPage.getErrorDataAvailable());
        Assert.assertEquals(deleteADMPage.getTextFailNotification(),
                deleteADMPage.getErrorDataAvailable(),
                "Fail Notification not match with expect");

        deleteADMPage.clickFailClose();
    }

    @Test(priority = 2, testName = "TC_DeleteADM_03")
    public void testDeleteFailWithFindNotFound() {
        deleteADMPage.searchADM("Tiến sĩ Không Tồn Tại");
        sleep(5);
        System.out.println("======= Fail Notification ======");
        System.out.println("Actual: " + deleteADMPage.getTextFindNotFound());
        System.out.println("Expect: " + deleteADMPage.getErrorFindNotFound());
        Assert.assertEquals(deleteADMPage.getTextFindNotFound(),
                deleteADMPage.getErrorFindNotFound(),
                "No result message does not match expected");
    }
    @AfterClass
    public void cleanup() {
//        cleanupTest();
    }

}