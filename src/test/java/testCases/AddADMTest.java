package testCases;

import base.BaseTest;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddADMPage;
import utils.Dialog;
import utils.JsonReader;
import utils.Notification;

import java.time.Duration;
import java.util.List;

public class AddADMTest extends BaseTest {
    private AddADMPage addADMPage;
    private Notification notifiCheck;
    private JsonReader jsonReader;
    Dialog dialogUtils;

    /**
     * @author Nguyễn Liên Nhi - 2274802010612
     * @Title: Kiểm thử chức năng sửa học hàm học vị
     * @since 06/03/2025
     */

    @BeforeClass
    public void setupClass() {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/AcademicDegree");
        addADMPage = new AddADMPage(driver);
        notifiCheck = new Notification(wait);
        jsonReader = new JsonReader();
        dialogUtils = new Dialog(driver, wait);
    }

    @Test(priority = 1, testName = "AddADMSucces")
    public void TC_AddADM_01() {
        Object[] data = jsonReader.getTestCase(0); //
        addADMPage.clickAddADButton();
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
        Assert.assertEquals(inputs.size(), 3, "Inputs have more than expect");

        for (WebElement input : inputs) {
            String label = input.getDomAttribute("aria-label");
            System.out.println(" - " + (label != null ? label.trim() : "not found"));
        }
        sleep(5);
        tools.checkContainsAriaLabelMessage(inputs, "Nhập mã học hàm, học vị");
        tools.checkContainsAriaLabelMessage(inputs, "Nhập tên học hàm, học vị");
        tools.checkContainsAriaLabelMessage(inputs, "Nhập thứ tự học hàm, học vị");

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

        addADMPage.enterCodeAD((String) data[0]);
        sleep(5);

        addADMPage.enterNameAD((String) data[1]);
        sleep(5);

        addADMPage.enterOrderAD(String.valueOf((int) data[2]));
        sleep(5);

        addADMPage.clickSaveADButton();
        notifiCheck.testAddNotification();
        sleep(5);
    }

    // data duplicate id - trùng lặp id
    @Test(priority = 2, testName = "AddADMErrorWithDuplicateID")
    public void TC_AddADM_02() {
        Object[] data = jsonReader.getTestCase(1);
        addADMPage.clickAddADButton();
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
        Assert.assertEquals(inputs.size(), 3, "Inputs have more than expect");

        for (WebElement input : inputs) {
            String label = input.getDomAttribute("aria-label");
            System.out.println(" - " + (label != null ? label.trim() : "not found"));
        }
        sleep(5);
        tools.checkContainsAriaLabelMessage(inputs, "Nhập mã học hàm, học vị");
        tools.checkContainsAriaLabelMessage(inputs, "Nhập tên học hàm, học vị");
        tools.checkContainsAriaLabelMessage(inputs, "Nhập thứ tự học hàm, học vị");

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

        addADMPage.enterCodeAD((String) data[0]);
        sleep(5);

        addADMPage.enterNameAD((String) data[1]);
        sleep(5);

        addADMPage.enterOrderAD(String.valueOf((int) data[2]));
        sleep(5);

        addADMPage.clickSaveADButton();
        sleep(2);
        addADMPage.checkDialog();
        System.out.println("======= Fail Notification ======");
        tools.checkEqualBoolean("Kiểm tra dialog hiển thị",dialogUtils.checkDialogConfirmDisplayed(),true);

        tools.checkEqualMessage(
                dialogUtils.getTitleDialog(),
                "Thông báo"
        );

        tools.checkEqualMessage(
                dialogUtils.getContentDialog(),
                "Mã học hàm, học vị này đã tồn tại!"
        );

        sleep(20);

        List<WebElement> buttonOKs = dialogUtils.getElementsButtonDialog();

        System.out.println("Các button được tìm thấy: " + buttonOKs.size());
        Assert.assertEquals(buttonOKs.size(), 1,"Buttons have more than expect");
        for (WebElement buttonOK : buttonOKs) {
            System.out.println(" - " + buttonOK.getText().trim());
        }

        tools.checkContainsMessageListElement(buttonOKs, "OK");
        sleep(2);
        addADMPage.clickOkADButtonDialog();
        sleep(2);
        addADMPage.clickCloseADButtonDialog();

//        // Replace sleep with explicit wait for the error dialog
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.visibilityOf(addADMPage.checkErrorDialogDisplayed()));
//            System.out.println("==========================================");
//            System.out.println("Check Error: ");
//            System.out.println("Actual: " + addADMPage.checkErrorDialogDisplayed().getText());
//            System.out.println("Expect: " + addADMPage.getIdADDuplicateEM());
//            Assert.assertEquals(addADMPage.checkErrorDialogDisplayed().getText(), addADMPage.getIdADDuplicateEM(), "Error Id Duplicated");
//            System.out.println("==========================================");
//
//            addADMPage.clickOkADButton();
//        } catch (TimeoutException e) {
//            Assert.fail("Error dialog did not appear within 10 seconds", e);
//        }
    }

    //   data > maxLength
    @Test(priority = 3, testName = "AddADMErrorWithDataMaxLength")
    public void TC_AddADM_03() {
        Object[] data = jsonReader.getTestCase(2);
        addADMPage.clickAddADButton();
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
        Assert.assertEquals(inputs.size(), 3, "Inputs have more than expect");

        for (WebElement input : inputs) {
            String label = input.getDomAttribute("aria-label");
            System.out.println(" - " + (label != null ? label.trim() : "not found"));
        }
        sleep(5);
        tools.checkContainsAriaLabelMessage(inputs, "Nhập mã học hàm, học vị");
        tools.checkContainsAriaLabelMessage(inputs, "Nhập tên học hàm, học vị");
        tools.checkContainsAriaLabelMessage(inputs, "Nhập thứ tự học hàm, học vị");

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

        addADMPage.enterCodeAD((String) data[0]);
        sleep(5);

        addADMPage.enterNameAD((String) data[1]);
        sleep(5);

        addADMPage.enterOrderAD(String.valueOf((int) data[2]));
        sleep(5);

        addADMPage.clickSaveADButton();
        System.out.println("==========================================");
        System.out.println("Check Error ID: ");
        System.out.println("Actual: " + addADMPage.getTextADIdError());
        System.out.println("Expect: " + addADMPage.getIdADMaxLengthEM());
        Assert.assertEquals(addADMPage.getIdADMaxLengthEM(),addADMPage.getTextADIdError(), "ID: Error Message not equal");
        System.out.println("Check Error Name: ");
        System.out.println("Actual: " + addADMPage.getTextADNameError());
        System.out.println("Expect: " + addADMPage.getNameADMaxLengthEM());
        Assert.assertEquals(addADMPage.getNameADMaxLengthEM(),addADMPage.getTextADNameError(), "Name: Error Message not equal");
        System.out.println("Check Error Order: ");
        System.out.println("Actual: " + addADMPage.getTextADOrderError());
        System.out.println("Expect: " + addADMPage.getOrderADMaxLengthEM());
        Assert.assertEquals(addADMPage.getIdADMaxLengthEM(),addADMPage.getTextADIdError(), "Order: Error Message not equal");
        System.out.println("==========================================");

        addADMPage.clickCloseADButton();
    }

    // data empty - trống thông tin
    @Test(priority = 4, testName = "AddADMErrorWithDataEmpty")
    public void TC_AddADM_04() {
        Object[] data = jsonReader.getTestCase(3);
        addADMPage.clickAddADButton();
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
        Assert.assertEquals(inputs.size(), 3, "Inputs have more than expect");

        for (WebElement input : inputs) {
            String label = input.getDomAttribute("aria-label");
            System.out.println(" - " + (label != null ? label.trim() : "not found"));
        }
        sleep(5);
        tools.checkContainsAriaLabelMessage(inputs, "Nhập mã học hàm, học vị");
        tools.checkContainsAriaLabelMessage(inputs, "Nhập tên học hàm, học vị");
        tools.checkContainsAriaLabelMessage(inputs, "Nhập thứ tự học hàm, học vị");

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


        addADMPage.enterCodeAD((String) data[0]);
        sleep(5);

        addADMPage.enterNameAD((String) data[1]);
        sleep(5);

        addADMPage.clearOrderAD();
        sleep(5);

        addADMPage.clickSaveADButton();
        sleep(2);

        addADMPage.clickSaveADButton();
        System.out.println("==========================================");
        System.out.println("Check Error ID: ");
        System.out.println("Actual: " + addADMPage.getTextADIdError());
        System.out.println("Expect: " + addADMPage.getIdADEmptyEM());
        Assert.assertEquals(addADMPage.getIdADEmptyEM(),addADMPage.getTextADIdError(), "ID: Error Message not equal");
        System.out.println("Check Error Name: ");
        System.out.println("Actual: " + addADMPage.getTextADNameError());
        System.out.println("Expect: " + addADMPage.getNameADEmptyEM());
        Assert.assertEquals(addADMPage.getNameADEmptyEM(),addADMPage.getTextADNameError(), "Name: Error Message not equal");
        System.out.println("Check Error Order: ");
        System.out.println("Actual: " + addADMPage.getTextADOrderError());
        System.out.println("Expect: " + addADMPage.getOrderADEmptyEM());
        Assert.assertEquals(addADMPage.getOrderADEmptyEM(),addADMPage.getTextADOrderError(), "Order: Error Message not equal");
        System.out.println("==========================================");

        addADMPage.clickCloseADButton();
        sleep(2);
    }


    // Data Invalid Format ID - định dạng ko hợp lệ
    @Test(priority = 5, testName = "AddADMErrorWithInvalidFormat")
    public void TC_AddADM_05 () {
        Object[] data = jsonReader.getTestCase(4);
        addADMPage.clickAddADButton();
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
        Assert.assertEquals(inputs.size(), 3, "Inputs have more than expect");

        for (WebElement input : inputs) {
            String label = input.getDomAttribute("aria-label");
            System.out.println(" - " + (label != null ? label.trim() : "not found"));
        }
        sleep(5);
        tools.checkContainsAriaLabelMessage(inputs, "Nhập mã học hàm, học vị");
        tools.checkContainsAriaLabelMessage(inputs, "Nhập tên học hàm, học vị");
        tools.checkContainsAriaLabelMessage(inputs, "Nhập thứ tự học hàm, học vị");

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


        addADMPage.enterCodeAD((String) data[0]);
        sleep(5);

        addADMPage.enterNameAD((String) data[1]);
        sleep(5);

        addADMPage.enterOrderAD(String.valueOf((int) data[2]));
        sleep(5);

        addADMPage.clickSaveADButton();
        sleep(2);

        System.out.println("==========================================");
        System.out.println("Check Error: ");
        System.out.println("Actual: " + addADMPage.getTextADIdError());
        System.out.println("Expect: " + addADMPage.getIdADInvalidFormatEM());
        Assert.assertEquals(addADMPage.getIdADInvalidFormatEM(), addADMPage.getTextADIdError(), "Error Message not equal");
        System.out.println("==========================================");

        sleep(3);
        addADMPage.clickCloseADButton();
    }

    // Data Order < minLength
    @Test(priority = 6, testName = "AddADMErrorWithDataMinLength")
    public void TC_AddADM_06() {
        Object[] data = jsonReader.getTestCase(5);
        addADMPage.clickAddADButton();
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
        Assert.assertEquals(inputs.size(), 3, "Inputs have more than expect");

        for (WebElement input : inputs) {
            String label = input.getDomAttribute("aria-label");
            System.out.println(" - " + (label != null ? label.trim() : "not found"));
        }
        sleep(5);
        tools.checkContainsAriaLabelMessage(inputs, "Nhập mã học hàm, học vị");
        tools.checkContainsAriaLabelMessage(inputs, "Nhập tên học hàm, học vị");
        tools.checkContainsAriaLabelMessage(inputs, "Nhập thứ tự học hàm, học vị");

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

        addADMPage.enterCodeAD((String) data[0]);
        sleep(5);

        addADMPage.enterNameAD((String) data[1]);
        sleep(5);

        addADMPage.enterOrderAD(String.valueOf((int) data[2]));
        sleep(5);

        addADMPage.clickSaveADButton();
        sleep(2);

        System.out.println("==========================================");
        System.out.println("Check Error: ");
        System.out.println("Actual: " + addADMPage.getTextADOrderError());
        System.out.println("Expect: " + addADMPage.getOrderADMinLengthEM());
        Assert.assertEquals(addADMPage.getTextADOrderError(), addADMPage.getOrderADMinLengthEM(), "Error Message not equal");
        System.out.println("==========================================");
    }
    @AfterClass
    public void cleanup() {
//        cleanupTest();
    }

}
