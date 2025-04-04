package testCases;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DeleteMajorPage;
import utils.Dialog;
import utils.Notification;

import java.util.List;

public class DeleteMajorTest extends BaseTest {
    DeleteMajorPage deleteMajorPage;
    private Notification notifiCheck;
    Dialog dialogUtils;
    @BeforeClass
    public void setupClass() {
        deleteMajorPage = new DeleteMajorPage(driver, wait);
        notifiCheck = new Notification(wait);
        dialogUtils = new Dialog(driver,wait);
    }

    @Test(priority = 0,testName = "DeleteSuccess")
    public void TC_DM_01(){
        deleteMajorPage.clickTermAndMajorTab();
        deleteMajorPage.clickMajorTab();
        deleteMajorPage.searchMajor("CNTT2023Success");
        deleteMajorPage.clickDeleteButton();
        Assert.assertTrue(dialogUtils.checkDialogConfirmDisplayed(), "Dialog not displayed");
        System.out.println("=========================");
        tools.checkEqualMessage(
                dialogUtils.getTitleDialog(),
                "Thông báo"
        );

        tools.checkEqualMessage(
                dialogUtils.getContentDialog(),
                "Bạn có chắc muốn xoá ngành này?"
        );

        List<WebElement> buttons = dialogUtils.getElementsDialog();

        System.out.println("Các button được tìm thấy: " + buttons.size());
        for (WebElement button : buttons) {
            System.out.println(" - " + button.getText().trim());
        }

        tools.checkContainsMessageListElement(buttons,"Xoá");
        tools.checkContainsMessageListElement(buttons,"Huỷ");


        deleteMajorPage.clickDeletedDialog();
        notifiCheck.testDeleteNotification();
    }

    @Test(priority = 1, testName = "DeleteFail")
    public void TC_DM_02() {
        deleteMajorPage.clickTermAndMajorTab();
        deleteMajorPage.clickMajorTab();
        deleteMajorPage.searchMajor("012345");
        deleteMajorPage.clickDeleteButton();
        Assert.assertTrue(deleteMajorPage.checkDialogConfirmDisplayed(), "Dialog not displayed");

        System.out.println("=========================");
        tools.checkEqualMessage(
                dialogUtils.getTitleDialog(),
                "Thông báo"
        );

        tools.checkEqualMessage(
                dialogUtils.getContentDialog(),
                "Bạn có chắc muốn xoá ngành này?"
        );


        deleteMajorPage.clickDeletedDialog();
        sleep(10);

        tools.checkEqualMessage(
                dialogUtils.getTitleDialog(),
                "Thông báo"
        );

        tools.checkEqualMessage(
                dialogUtils.getContentDialog(),
                "Không thể xoá do ngành này đã có dữ liệu!"
        );


        List<WebElement> buttons = dialogUtils.getElementsDialog();

        System.out.println("Các button được tìm thấy: " + buttons.size());
        for (WebElement button : buttons) {
            System.out.println(" - " + button.getText().trim());
        }

//        tools.checkContainsMessageListElement(buttons,"Xoá");
        tools.checkContainsMessageListElement(buttons, "OK");
        deleteMajorPage.clickFailClose();
    }



}
