    package testCases;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DeleteADMPage;
import utils.Dialog;
import utils.Notification;

import java.util.List;

    public class DeleteADMTest extends BaseTest {
    DeleteADMPage deleteADMPage;
    private Notification notifiCheck;
    Dialog dialogUtils;

    @BeforeClass
    public void setupClass() {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/AcademicDegree");
        deleteADMPage = new DeleteADMPage(driver, wait);
        dialogUtils = new Dialog(driver, wait);
        notifiCheck = new Notification(wait);
    }

    @Test(priority = 0, testName = "TC_DeleteADM_01")
    public void testDeleteSuccess() {
        deleteADMPage.searchADM("Tiến sĩ Công nghệ thông tin");
        deleteADMPage.clickDeleteButton();
        tools.checkEqualBoolean("Kiểm tra dialog hiển thị",dialogUtils.checkDialogConfirmDisplayed(),true);
        deleteADMPage.checkDialog();
        sleep(3);
        deleteADMPage.clickDeleteDialog();
        sleep(3);
        notifiCheck.testDeleteNotification();
    }

    @Test(priority = 1, testName = "TC_DeleteADM_02")
    public void testDeleteFailWithDataAvailable() {
        deleteADMPage.searchADM("Tiến sĩ");
        deleteADMPage.clickDeleteButton();
        tools.checkEqualBoolean("Kiểm tra dialog hiển thị",dialogUtils.checkDialogConfirmDisplayed(),true);
        deleteADMPage.checkDialog();
        sleep(5);
        System.out.println("======= Fail Notification ======");
        tools.checkEqualMessage(
                dialogUtils.getTitleDialog(),
                "Thông báo"
        );

        tools.checkEqualMessage(
                dialogUtils.getContentDialog(),
                "Bạn có chắc muốn xoá học hàm, học vị này?"
        );
        sleep(2);
        deleteADMPage.clickDeleteDialog();
        sleep(2);
        tools.checkEqualMessage(
                dialogUtils.getTitleDialog(),
                "Thông báo"
        );

        tools.checkEqualMessage(
                dialogUtils.getContentDialog(),
                "Không thể xoá do học hàm, học vị này đã có dữ liệu!"
        );

        sleep(20);

        List<WebElement> buttons = dialogUtils.getElementsDialog();

        System.out.println("Các button được tìm thấy: " + buttons.size());
        Assert.assertEquals(buttons.size(), 1,"Buttons have more than expect");
        for (WebElement button : buttons) {
            System.out.println(" - " + button.getText().trim());
        }

        tools.checkContainsMessageListElement(buttons, "OK");
        deleteADMPage.clickFailClose();


    }

    @AfterClass
    public void cleanup() {
//        cleanupTest();
    }

}