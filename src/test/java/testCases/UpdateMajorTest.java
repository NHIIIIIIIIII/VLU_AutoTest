package testCases;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.UpdateMajorPage;
import utils.Dialog;
import utils.Notification;

import java.util.List;

/**
 * @author Trần Văn Hiếu - 2274802010262
 * @Title: Kiểm thử chức năng sửa ngành học
 * @since 13/03/2025
 */
public class UpdateMajorTest extends BaseTest {
    UpdateMajorPage updateMajorPage;
    Dialog dialogUtils;
    private Notification notifiCheck;

    @BeforeClass
    public void setupClass() {
        updateMajorPage = new UpdateMajorPage(driver, wait);
        notifiCheck = new Notification(wait);
        dialogUtils = new Dialog(driver, wait);
    }


    @Test(priority = 0, testName = "updateMajorSuccessfully")
    public void TC_UM_01() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();

        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        sleep(5);

        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý ngành học"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 4, "Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        tools.checkContainsMessageListElement(labels, "Mã ngành:");
        tools.checkContainsMessageListElement(labels, "Tên ngành:");
        tools.checkContainsMessageListElement(labels, "Tên viết tắt:");
        tools.checkContainsMessageListElement(labels, "CTĐT:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(20);

        List<WebElement> options = dialogUtils.getElementsOptionInSelect();
        System.out.println("Các option được tìm thấy: " + options.size());
        Assert.assertEquals(options.size(), 3, "Options have more or less than expected");

        for (WebElement option : options) {
            System.out.println(" - " + option.getText().trim());

        }
        tools.checkContainsMessageListElement(options, "Tiêu chuẩn");
        tools.checkContainsMessageListElement(options, "Đặc biệt");

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


        updateMajorPage.enterMajorName("Công nghệ thông tin 2023");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("CNTT");
        sleep(5);
        updateMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();
        notifiCheck.testUpdateNotification();
    }

    @Test(priority = 1, testName = "UpdateMajorWithEmptyName")
    public void TC_UM_02() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();

        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý ngành học"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 4, "Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        tools.checkContainsMessageListElement(labels, "Mã ngành:");
        tools.checkContainsMessageListElement(labels, "Tên ngành:");
        tools.checkContainsMessageListElement(labels, "Tên viết tắt:");
        tools.checkContainsMessageListElement(labels, "CTĐT:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(20);

        List<WebElement> options = dialogUtils.getElementsOptionInSelect();
        System.out.println("Các option được tìm thấy: " + options.size());
        Assert.assertEquals(options.size(), 3, "Options have more or less than expected");

        for (WebElement option : options) {
            System.out.println(" - " + option.getText().trim());

        }
        tools.checkContainsMessageListElement(options, "Tiêu chuẩn");
        tools.checkContainsMessageListElement(options, "Đặc biệt");

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


        updateMajorPage.enterMajorName("");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("CNTT");
        sleep(5);
        updateMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();

        String errorMessage = "Bạn chưa nhập tên ngành";
        tools.checkEqualMessage(updateMajorPage.getTextMajorNameError(), errorMessage);
        updateMajorPage.clickCloseButton();
    }

    @Test(priority = 2, testName = "UpdateMajorWithEmptyMajorAbbreviation")
    public void TC_UM_03() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý ngành học"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 4, "Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        tools.checkContainsMessageListElement(labels, "Mã ngành:");
        tools.checkContainsMessageListElement(labels, "Tên ngành:");
        tools.checkContainsMessageListElement(labels, "Tên viết tắt:");
        tools.checkContainsMessageListElement(labels, "CTĐT:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(20);

        List<WebElement> options = dialogUtils.getElementsOptionInSelect();
        System.out.println("Các option được tìm thấy: " + options.size());
        Assert.assertEquals(options.size(), 3, "Options have more or less than expected");

        for (WebElement option : options) {
            System.out.println(" - " + option.getText().trim());

        }
        tools.checkContainsMessageListElement(options, "Tiêu chuẩn");
        tools.checkContainsMessageListElement(options, "Đặc biệt");

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


        updateMajorPage.enterMajorName("Cong Nghe Thong Tin");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("");
        sleep(5);
        updateMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();

        String errorMessage = "Bạn chưa nhập tên viết tắt của ngành";
        tools.checkEqualMessage(updateMajorPage.getTextMajorAbbreviationError(), errorMessage);
        updateMajorPage.clickCloseButton();
    }


    @Test(priority = 3, testName = "UpdateMajorWithAllEmptyAndNoSelectTrainingProgram")
    public void TC_UM_04() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý ngành học"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 4, "Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        tools.checkContainsMessageListElement(labels, "Mã ngành:");
        tools.checkContainsMessageListElement(labels, "Tên ngành:");
        tools.checkContainsMessageListElement(labels, "Tên viết tắt:");
        tools.checkContainsMessageListElement(labels, "CTĐT:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(20);

        List<WebElement> options = dialogUtils.getElementsOptionInSelect();
        System.out.println("Các option được tìm thấy: " + options.size());
        Assert.assertEquals(options.size(), 3, "Options have more or less than expected");

        for (WebElement option : options) {
            System.out.println(" - " + option.getText().trim());

        }
        tools.checkContainsMessageListElement(options, "Tiêu chuẩn");
        tools.checkContainsMessageListElement(options, "Đặc biệt");

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


        sleep(5);

        updateMajorPage.enterMajorName("");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("");
        sleep(5);
//        addMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();

        String majorNameEmptyErrorMessage = "Bạn chưa nhập tên ngành";
        String majorAbbreviatioEmptyErrorMessage = "Bạn chưa nhập tên viết tắt của ngành";
        tools.checkEqualMessage(updateMajorPage.getTextMajorNameError(), majorNameEmptyErrorMessage);
        tools.checkEqualMessage(updateMajorPage.getTextMajorAbbreviationError(), majorAbbreviatioEmptyErrorMessage);

        updateMajorPage.clickCloseButton();
    }

    @Test(priority = 4, testName = "UpdateMajorWithMajorAbbreviationMore50")
    public void TC_UM_05() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý ngành học"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 4, "Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        tools.checkContainsMessageListElement(labels, "Mã ngành:");
        tools.checkContainsMessageListElement(labels, "Tên ngành:");
        tools.checkContainsMessageListElement(labels, "Tên viết tắt:");
        tools.checkContainsMessageListElement(labels, "CTĐT:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(20);

        List<WebElement> options = dialogUtils.getElementsOptionInSelect();
        System.out.println("Các option được tìm thấy: " + options.size());
        Assert.assertEquals(options.size(), 3, "Options have more or less than expected");

        for (WebElement option : options) {
            System.out.println(" - " + option.getText().trim());

        }
        tools.checkContainsMessageListElement(options, "Tiêu chuẩn");
        tools.checkContainsMessageListElement(options, "Đặc biệt");

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


        sleep(5);

        updateMajorPage.enterMajorName("Cong Nghe Thong Tin");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("XVVE4O0F3Fqw5hPnDlT2d6poU8T7ttBVf4yW7HjxIOpLoC411Ah");
        sleep(5);
        updateMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();

        String majorAbbreviationMaxLengthErrorMessage = "Tối đa 50 kí tự được cho phép";
        tools.checkEqualMessage(updateMajorPage.getTextMajorAbbreviationError(), majorAbbreviationMaxLengthErrorMessage);
        updateMajorPage.clickCloseButton();
    }

    @Test(priority = 5, testName = "UpdateMajorWithMajorNameMore255")
    public void TC_UM_06() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý ngành học"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 4, "Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        tools.checkContainsMessageListElement(labels, "Mã ngành:");
        tools.checkContainsMessageListElement(labels, "Tên ngành:");
        tools.checkContainsMessageListElement(labels, "Tên viết tắt:");
        tools.checkContainsMessageListElement(labels, "CTĐT:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(20);

        List<WebElement> options = dialogUtils.getElementsOptionInSelect();
        System.out.println("Các option được tìm thấy: " + options.size());
        Assert.assertEquals(options.size(), 3, "Options have more or less than expected");

        for (WebElement option : options) {
            System.out.println(" - " + option.getText().trim());

        }
        tools.checkContainsMessageListElement(options, "Tiêu chuẩn");
        tools.checkContainsMessageListElement(options, "Đặc biệt");

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


        sleep(5);

        updateMajorPage.enterMajorName("7Mx8lKc2VW3L4RnJcGvB80s8EPhm7SCcjnftmHtI8m48r7C1PAd0AxRuS6ghvg90aSlBfVB5VKqx44eDioBpywgjH77rfwBCqUSBuYAMO3WvUWz2a4lyx5A7IkQkiEo2l6VaKeraOcOPZg6D3aHUpftf9Uj8CwQxmrIdbo2ghljXmh56FnFUdgtaiA9TQfkalzjeJYMw0dYCzHPVYlmeMxSFRyPUZcmGb27DgpWz7n27lqsXAlHopkzpiXlyxO7c");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("CNTT");
        sleep(5);
        updateMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();

        String majorNameMaxLengthErrorMessage = "Tối đa 255 kí tự được cho phép";
        tools.checkEqualMessage(updateMajorPage.getTextMajorNameError(), majorNameMaxLengthErrorMessage);

        updateMajorPage.clickCloseButton();
    }

    @Test(priority = 6, testName = "UpdateMajorWithMajorNameAndAbbreviationMore")
    public void TC_UM_07() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);
        sleep(5);

        tools.checkEqualMessage(
                dialogUtils.getTitleManagement(),
                "Quản lý ngành học"
        );
        sleep(20);

        List<WebElement> labels = dialogUtils.getElementsLabelManagementDialog();
        System.out.println("Các label được tìm thấy: " + labels.size());
        Assert.assertEquals(labels.size(), 4, "Labels have more than expect");
        for (WebElement lable : labels) {
            System.out.println(" - " + lable.getText().trim());
        }
        tools.checkContainsMessageListElement(labels, "Mã ngành:");
        tools.checkContainsMessageListElement(labels, "Tên ngành:");
        tools.checkContainsMessageListElement(labels, "Tên viết tắt:");
        tools.checkContainsMessageListElement(labels, "CTĐT:");

        sleep(20);
        List<WebElement> inputs = dialogUtils.getElementsInputManagementDialog();
        System.out.println("Các input được tìm thấy: " + inputs.size());

        sleep(20);

        List<WebElement> options = dialogUtils.getElementsOptionInSelect();
        System.out.println("Các option được tìm thấy: " + options.size());
        Assert.assertEquals(options.size(), 3, "Options have more or less than expected");

        for (WebElement option : options) {
            System.out.println(" - " + option.getText().trim());

        }
        tools.checkContainsMessageListElement(options, "Tiêu chuẩn");
        tools.checkContainsMessageListElement(options, "Đặc biệt");

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


        sleep(5);

        updateMajorPage.enterMajorName("7Mx8lKc2VW3L4RnJcGvB80s8EPhm7SCcjnftmHtI8m48r7C1PAd0AxRuS6ghvg90aSlBfVB5VKqx44eDioBpywgjH77rfwBCqUSBuYAMO3WvUWz2a4lyx5A7IkQkiEo2l6VaKeraOcOPZg6D3aHUpftf9Uj8CwQxmrIdbo2ghljXmh56FnFUdgtaiA9TQfkalzjeJYMw0dYCzHPVYlmeMxSFRyPUZcmGb27DgpWz7n27lqsXAlHopkzpiXlyxO7c");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("XVVE4O0F3Fqw5hPnDlT2d6poU8T7ttBVf4yW7HjxIOpLoC411Ah");
        sleep(5);
        updateMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();

        String majorNameMaxLengthErrorMessage = "Tối đa 255 kí tự được cho phép";
        String majorAbbreviationMaxLengthErrorMessage = "Tối đa 50 kí tự được cho phép";
        tools.checkEqualMessage(updateMajorPage.getTextMajorNameError(), majorNameMaxLengthErrorMessage);
        tools.checkEqualMessage(updateMajorPage.getTextMajorAbbreviationError(), majorAbbreviationMaxLengthErrorMessage);

        updateMajorPage.clickCloseButton();
    }


}
