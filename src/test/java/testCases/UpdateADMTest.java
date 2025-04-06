package testCases;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.UpdateADMPage;
import utils.Dialog;
import utils.JsonReader;
import utils.Notification;

import java.util.List;

/**
 * @author Nguyễn Liên Nhi - 2274802010612
 * @Title: Kiểm thử chức năng sửa học hàm, học vị
 * @since 24/03/2025
 */

//@Listeners(utils.ExcelTestListener.class)

public class UpdateADMTest extends BaseTest {
    private UpdateADMPage updateADMPage;
    private Notification notifiCheck;
    Dialog dialogUtils;

    @BeforeClass
    public void setupClass() {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/AcademicDegree");
        updateADMPage = new UpdateADMPage(driver);
        notifiCheck = new Notification(wait);
        dialogUtils = new Dialog(driver, wait);
    }

    @Test(priority = 0, testName = "ValidUpdateNormalCase")
    public void TC_UpdateADM_01() {
        updateADMPage.searchAD("Test0135");
        updateADMPage.clickEditADButton();
        sleep(5);

        tools.checkEqualBoolean("Kiểm tra dialog Quản lý học hàm, học vị hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý học hàm, học vị"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 3,"Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        sleep(5);
        tools.checkContainsMessageListElement(labels, "Mã học hàm, học vị:");
        tools.checkContainsMessageListElement(labels, "Tên học hàm, học vị:");
        tools.checkContainsMessageListElement(labels, "Thứ tự:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(5);

        sleep(20);
        List<WebElement> buttons = dialogUtils.getElementsButtonManagementDialog();
        System.out.println("Các button được tìm thấy: " + buttons.size());
        Assert.assertEquals(buttons.size(), 2,"Buttons have more than expect");
        for (WebElement button : buttons) {
            System.out.println(" - " + button.getText().trim());
        }
        sleep(5);
        tools.checkContainsMessageListElement(buttons, "Huỷ");
        tools.checkContainsMessageListElement(buttons, "Lưu");


        updateADMPage.enterNameAD("Professor Updated");
        sleep(5);

        updateADMPage.enterOrderAD("12");
        sleep(5);

        updateADMPage.clickSaveADButton();
        notifiCheck.testUpdateNotification();
    }

    @Test(priority = 1, testName = "InvalidUpdateEmptyName")
    public void TC_UpdateADM_02() {
        updateADMPage.searchAD("Test0135");
        updateADMPage.clickEditADButton();
        sleep(5);

        tools.checkEqualBoolean("Kiểm tra dialog Quản lý học hàm, học vị hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý học hàm, học vị"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 3,"Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        sleep(5);
        tools.checkContainsMessageListElement(labels, "Mã học hàm, học vị:");
        tools.checkContainsMessageListElement(labels, "Tên học hàm, học vị:");
        tools.checkContainsMessageListElement(labels, "Thứ tự:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(5);

        sleep(20);
        List<WebElement> buttons = dialogUtils.getElementsButtonManagementDialog();
        System.out.println("Các button được tìm thấy: " + buttons.size());
        Assert.assertEquals(buttons.size(), 2,"Buttons have more than expect");
        for (WebElement button : buttons) {
            System.out.println(" - " + button.getText().trim());
        }
        sleep(5);
        tools.checkContainsMessageListElement(buttons, "Huỷ");
        tools.checkContainsMessageListElement(buttons, "Lưu");


        updateADMPage.enterNameAD("");
        sleep(5);

        updateADMPage.enterOrderAD("13");
        sleep(5);

        updateADMPage.clickSaveADButton();
        sleep(2);

        System.out.println("==========================================");
        System.out.println("Check Error: ");
        System.out.println("Actual: " + updateADMPage.getTextADMNameError());
        System.out.println("Expect: " + updateADMPage.getNameADEmptyEM());
        Assert.assertEquals(updateADMPage.getTextADMNameError(), updateADMPage.getNameADEmptyEM(), "Error Message not equal");
        System.out.println("==========================================");

        updateADMPage.clickCloseADButton();
    }

    @Test(priority = 2, testName = "InvalidUpdateNameExceeds100Chars")
    public void TC_UpdateADM_03() {
        updateADMPage.searchAD("Test0135");
        updateADMPage.clickEditADButton();
        sleep(5);

        tools.checkEqualBoolean("Kiểm tra dialog Quản lý học hàm, học vị hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý học hàm, học vị"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 3,"Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        sleep(5);
        tools.checkContainsMessageListElement(labels, "Mã học hàm, học vị:");
        tools.checkContainsMessageListElement(labels, "Tên học hàm, học vị:");
        tools.checkContainsMessageListElement(labels, "Thứ tự:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(5);

        sleep(20);
        List<WebElement> buttons = dialogUtils.getElementsButtonManagementDialog();
        System.out.println("Các button được tìm thấy: " + buttons.size());
        Assert.assertEquals(buttons.size(), 2,"Buttons have more than expect");
        for (WebElement button : buttons) {
            System.out.println(" - " + button.getText().trim());
        }
        sleep(5);
        tools.checkContainsMessageListElement(buttons, "Huỷ");
        tools.checkContainsMessageListElement(buttons, "Lưu");

        updateADMPage.enterNameAD("Tiến sĩ Công nghệ thông tin và Truyền thông, chuyên về trí tuệ nhân tạo và các ứng dụng trong đời sống hàng ngày và Tiến sĩ Công nghệ thông tin và Truyền thông, chuyên về trí tuệ nhân tạo và các ứng dụng trong đời sống hàng ngày");
        sleep(5);

        updateADMPage.enterOrderAD("3");
        sleep(5);

        updateADMPage.clickSaveADButton();
        sleep(2);

        System.out.println("==========================================");
        System.out.println("Check Error: ");
        System.out.println("Actual: " + updateADMPage.getTextADMNameError());
        System.out.println("Expect: " + updateADMPage.getNameADMaxLengthEM());
        Assert.assertEquals(updateADMPage.getTextADMNameError(), updateADMPage.getNameADMaxLengthEM(), "Error Message not equal");
        System.out.println("==========================================");

        updateADMPage.clickCloseADButton();
    }

    @Test(priority = 3, testName = "InvalidUpdateOrderEmpty")
    public void TC_UpdateADM_04() {
        updateADMPage.searchAD("Test0135");
        updateADMPage.clickEditADButton();
        sleep(5);

        tools.checkEqualBoolean("Kiểm tra dialog Quản lý học hàm, học vị hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý học hàm, học vị"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 3,"Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        sleep(5);
        tools.checkContainsMessageListElement(labels, "Mã học hàm, học vị:");
        tools.checkContainsMessageListElement(labels, "Tên học hàm, học vị:");
        tools.checkContainsMessageListElement(labels, "Thứ tự:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(5);

        sleep(20);
        List<WebElement> buttons = dialogUtils.getElementsButtonManagementDialog();
        System.out.println("Các button được tìm thấy: " + buttons.size());
        Assert.assertEquals(buttons.size(), 2,"Buttons have more than expect");
        for (WebElement button : buttons) {
            System.out.println(" - " + button.getText().trim());
        }
        sleep(5);
        tools.checkContainsMessageListElement(buttons, "Huỷ");
        tools.checkContainsMessageListElement(buttons, "Lưu");


        updateADMPage.enterNameAD("Valid Name");
        sleep(5);

        updateADMPage.enterOrderAD("");
        sleep(5);

        updateADMPage.clickSaveADButton();
        sleep(2);

        System.out.println("==========================================");
        System.out.println("Check Error: ");
        System.out.println("Actual: " + updateADMPage.getTextADMOderError());
        System.out.println("Expect: " + updateADMPage.getOrderADEmptyEM());
        Assert.assertEquals(updateADMPage.getTextADMOderError(), updateADMPage.getOrderADEmptyEM(), "Error Message not equal");
        System.out.println("==========================================");

        updateADMPage.clickCloseADButton();
    }

    @Test(priority = 4, testName = "InvalidUpdateOrderExceeds100")
    public void TC_UpdateADM_05() {
        updateADMPage.searchAD("Test0135");
        updateADMPage.clickEditADButton();
        sleep(5);

        tools.checkEqualBoolean("Kiểm tra dialog Quản lý học hàm, học vị hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý học hàm, học vị"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 3,"Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        sleep(5);
        tools.checkContainsMessageListElement(labels, "Mã học hàm, học vị:");
        tools.checkContainsMessageListElement(labels, "Tên học hàm, học vị:");
        tools.checkContainsMessageListElement(labels, "Thứ tự:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(5);

        sleep(20);
        List<WebElement> buttons = dialogUtils.getElementsButtonManagementDialog();
        System.out.println("Các button được tìm thấy: " + buttons.size());
        Assert.assertEquals(buttons.size(), 2,"Buttons have more than expect");
        for (WebElement button : buttons) {
            System.out.println(" - " + button.getText().trim());
        }
        sleep(5);
        tools.checkContainsMessageListElement(buttons, "Huỷ");
        tools.checkContainsMessageListElement(buttons, "Lưu");

        updateADMPage.enterNameAD("Valid Name");
        sleep(5);

        updateADMPage.enterOrderAD("111");
        sleep(5);

        updateADMPage.clickSaveADButton();
        sleep(2);

        System.out.println("==========================================");
        System.out.println("Check Error: ");
        System.out.println("Actual: " + updateADMPage.getTextADMOderError());
        System.out.println("Expect: " + updateADMPage.getOrderADMaxLengthEM());
        Assert.assertEquals(updateADMPage.getTextADMOderError(), updateADMPage.getOrderADMaxLengthEM(), "Error Message not equal");
        System.out.println("==========================================");

        updateADMPage.clickCloseADButton();
    }

    @Test(priority = 5, testName = "InvalidUpdateOrderEqualsZero")
    public void TC_UpdateADM_06() {
        updateADMPage.searchAD("Test0135");
        updateADMPage.clickEditADButton();
        sleep(5);

        tools.checkEqualBoolean("Kiểm tra dialog Quản lý học hàm, học vị hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý học hàm, học vị"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 3,"Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        sleep(5);
        tools.checkContainsMessageListElement(labels, "Mã học hàm, học vị:");
        tools.checkContainsMessageListElement(labels, "Tên học hàm, học vị:");
        tools.checkContainsMessageListElement(labels, "Thứ tự:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(5);

        sleep(20);
        List<WebElement> buttons = dialogUtils.getElementsButtonManagementDialog();
        System.out.println("Các button được tìm thấy: " + buttons.size());
        Assert.assertEquals(buttons.size(), 2,"Buttons have more than expect");
        for (WebElement button : buttons) {
            System.out.println(" - " + button.getText().trim());
        }
        sleep(5);
        tools.checkContainsMessageListElement(buttons, "Huỷ");
        tools.checkContainsMessageListElement(buttons, "Lưu");

        updateADMPage.enterNameAD("Valid Name");
        sleep(5);

        updateADMPage.enterOrderAD("0");
        sleep(5);

        updateADMPage.clickSaveADButton();
        sleep(2);

        System.out.println("==========================================");
        System.out.println("Check Error: ");
        System.out.println("Actual: " + updateADMPage.getTextADMOderError());
        System.out.println("Expect: " + updateADMPage.getOrderADMinLengthEM());
        Assert.assertEquals(updateADMPage.getOrderADMinLengthEM(),updateADMPage.getTextADMOderError(), "Error Message not equal");
        System.out.println("==========================================");
    }

    @AfterClass
    public void cleanup() {
//        cleanupTest();
    }

}