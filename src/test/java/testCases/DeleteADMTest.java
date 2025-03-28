package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
        deleteADMPage.searchADM("Tiến sĩ Công nghệ thông tin");
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
        sleep(5);
        System.out.println("======= Fail Notification ======");
        System.out.println("Actual: " + deleteADMPage.getTextFailNotification());
        System.out.println("Expect: " + deleteADMPage.getErrorDataAvailable());
        Assert.assertEquals(deleteADMPage.getTextFailNotification(),
                deleteADMPage.getErrorDataAvailable(),
                "Fail Notification not match with expect");

        deleteADMPage.clickFailClose();
    }

    @AfterClass
    public void cleanup() {
//        cleanupTest();
    }

}