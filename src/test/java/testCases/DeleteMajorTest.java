package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DeleteMajorPage;
import utils.Notification;

public class DeleteMajorTest extends BaseTest {
    DeleteMajorPage deleteMajorPage;
    private Notification notifiCheck;

    @BeforeClass
    public void setupClass() {
        deleteMajorPage = new DeleteMajorPage(driver, wait);
        notifiCheck = new Notification(wait);
    }

    @Test(priority = 0,testName = "TC_DM_01")
    public void testDeleteSuccess(){
        deleteMajorPage.clickTermAndMajorTab();
        deleteMajorPage.clickMajorTab();
        deleteMajorPage.searchMajor("CNTT2023Success");
        deleteMajorPage.clickDeleteButton();
        Assert.assertTrue(deleteMajorPage.checkDialogConfirmDisplayed(),"Dialog not displayed");
        deleteMajorPage.clickDeletedDialog();
        notifiCheck.testDeleteNotification();
    }

    @Test(priority = 1, testName = "TC_DM_02")
    public void testDeleteFail() {
        deleteMajorPage.clickTermAndMajorTab();
        deleteMajorPage.clickMajorTab();
        deleteMajorPage.searchMajor("012345");
        deleteMajorPage.clickDeleteButton();
        Assert.assertTrue(deleteMajorPage.checkDialogConfirmDisplayed(), "Dialog not displayed");
        deleteMajorPage.clickDeletedDialog();
        sleep(50);
        String messageFail = "Không thể xoá do ngành này đã có dữ liệu!";
        System.out.println("======= Fail Notification ======");
        System.out.println("Actual :" + deleteMajorPage.getTextFailNotification());
        System.out.println("Expect : "+messageFail);
        Assert.assertEquals(deleteMajorPage.getTextFailNotification(),
                            messageFail,
                            "Fail Notification not match with expect");

        deleteMajorPage.clickFailClose();

    }
}
