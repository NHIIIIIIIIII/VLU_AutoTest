package testCases;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddMajorPage;
import utils.Dialog;
import utils.Notification;

import java.util.List;

public class AddMajorTest extends BaseTest {
    AddMajorPage addMajorPage;
    Dialog dialogUtils;
    private Notification notifiCheck;

    @BeforeClass
    public void setupClass() {
        addMajorPage = new AddMajorPage(driver, wait);
        dialogUtils = new Dialog(driver, wait);
        notifiCheck = new Notification(wait);
    }

    @Test(priority = 0, testName = "AddMajorSuccess")
    public void TC_CM_01() {
        addMajorPage.clickTermAndMajorTab();
        addMajorPage.clickMajorTab();
        addMajorPage.clickAddMajorButton();
        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);

        addMajorPage.enterMajorId("CNTT2023Success");
        sleep(5);

        addMajorPage.enterMajorName("Công nghệ thông tin 2023");
        sleep(5);

        addMajorPage.enterMajorAbbreviation("CNTT");
        sleep(5);
        addMajorPage.selectTrainingProgram("Đặc biệt");
        addMajorPage.clickSaveButton();
        notifiCheck.testAddNotification();
    }


    @Test(priority = 1, testName = "AddMajorFailWithExistId")
    public void TC_CM_02() {

        addMajorPage.clickTermAndMajorTab();
        addMajorPage.clickMajorTab();
        addMajorPage.clickAddMajorButton();
        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);

        addMajorPage.enterMajorId("CNTT2023");
        sleep(5);

        addMajorPage.enterMajorName("Công nghệ thông tin 2023");
        sleep(5);

        addMajorPage.enterMajorAbbreviation("CNTT");
        sleep(5);
        addMajorPage.selectTrainingProgram("Đặc biệt");
        addMajorPage.clickSaveButton();


        String duplicateMajorIdErrorMessage = "Mã ngành này đã tồn tại!";  // Hiển thị trên dialog
        System.out.println("=========================");

        tools.checkEqualBoolean("Kiểm tra dialog hiển thị",dialogUtils.checkDialogConfirmDisplayed(),true);

        tools.checkEqualMessage(
                dialogUtils.getTitleDialog(),
                "Thông báo"
        );

        tools.checkEqualMessage(
                dialogUtils.getContentDialog(),
                duplicateMajorIdErrorMessage
        );

        sleep(20);

        List<WebElement> buttons = dialogUtils.getElementsDialog();

        System.out.println("Các button được tìm thấy: " + buttons.size());
        Assert.assertEquals(buttons.size(), 1,"Buttons have more than expect");
        for (WebElement button : buttons) {
            System.out.println(" - " + button.getText().trim());
        }

        tools.checkContainsMessageListElement(buttons, "OK");


        addMajorPage.clickOkErrorButton();
    }


    @Test(priority = 2, testName = "AddMajorFailWithSpace")
    public void TC_CM_03() {

        addMajorPage.clickTermAndMajorTab();
        addMajorPage.clickMajorTab();
        addMajorPage.clickAddMajorButton();
        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);

        addMajorPage.enterMajorId("CNTT202 3");
        sleep(5);

        addMajorPage.enterMajorName("Công nghệ thông tin 2023");
        sleep(5);

        addMajorPage.enterMajorAbbreviation("CNTT");
        sleep(5);
        addMajorPage.selectTrainingProgram("Đặc biệt");
        addMajorPage.clickSaveButton();


        String majorIdInvalidFormatErrorMessage = "Chỉ được nhập số-chữ không dấu và không có khoảng trắng!";


        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + addMajorPage.getTextMajorIdError());
        System.out.println("Expect : " + majorIdInvalidFormatErrorMessage);
        Assert.assertEquals(addMajorPage.getTextMajorIdError(),
                            majorIdInvalidFormatErrorMessage,
                            "Major Id not error");
        System.out.println("==========================================");
        addMajorPage.clickCloseButton();
    }

    @Test(priority = 3, testName = "AddMajorFailWithIdInvalid")
    public void TC_CM_04 () {

        addMajorPage.clickTermAndMajorTab();
        addMajorPage.clickMajorTab();
        addMajorPage.clickAddMajorButton();
        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);

        addMajorPage.enterMajorId("CNTT2023#@$%^");
        sleep(5);

        addMajorPage.enterMajorName("Công nghệ thông tin 2023");
        sleep(5);

        addMajorPage.enterMajorAbbreviation("CNTT");
        sleep(5);
        addMajorPage.selectTrainingProgram("Đặc biệt");
        addMajorPage.clickSaveButton();


        String majorIdInvalidFormatErrorMessage = "Chỉ được nhập số-chữ không dấu và không có khoảng trắng!";


        String errorMessage = "Bạn chưa nhập tên ngành";
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + addMajorPage.getTextMajorIdError());
        System.out.println("Expect : " + majorIdInvalidFormatErrorMessage);
        Assert.assertEquals(addMajorPage.getTextMajorIdError(),
                            majorIdInvalidFormatErrorMessage,
                            "Major Id not error");
        System.out.println("==========================================");
        addMajorPage.clickCloseButton();
    }

    @Test(priority = 4, testName = "AddMajorFailWithEmpty")
    public void TC_CM_05() {

        addMajorPage.clickTermAndMajorTab();
        addMajorPage.clickMajorTab();
        addMajorPage.clickAddMajorButton();
        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);

        addMajorPage.enterMajorId("");
        sleep(5);

        addMajorPage.enterMajorName("");
        sleep(5);

        addMajorPage.enterMajorAbbreviation("");
        sleep(5);
//        addMajorPage.selectTrainingProgram("Đặc biệt");
        addMajorPage.clickSaveButton();


        String majorIdEmptyErrorMessage = "Bạn chưa nhập mã ngành";
        String majorNameEmptyErrorMessage = "Bạn chưa nhập tên ngành";
        String majorAbbreviatioEmptyErrorMessage = "Bạn chưa nhập tên viết tắt của ngành";
        String majorTrainingProgramNotSelectedErrorMessage = "Bạn chưa chọn CTĐT";


        System.out.println("==========================================");
        System.out.println("Check Error ID : ");
        System.out.println("Actual : " + addMajorPage.getTextMajorIdError());
        System.out.println("Expect : " + majorIdEmptyErrorMessage);
        Assert.assertEquals(addMajorPage.getTextMajorIdError(),
                            majorIdEmptyErrorMessage,
                            "Major Id not error field");


        System.out.println("==========================================");
        System.out.println("Check Error Name : ");
        System.out.println("Actual : " + addMajorPage.getTextMajorNameError());
        System.out.println("Expect : " + majorNameEmptyErrorMessage);
        Assert.assertEquals(addMajorPage.getTextMajorNameError(),
                            majorNameEmptyErrorMessage,
                            "Major name not error field");


        System.out.println("==========================================");
        System.out.println("Check Error Abbreviate : ");
        System.out.println("Actual : " + addMajorPage.getTextMajorAbbreviationError());
        System.out.println("Expect : " + majorAbbreviatioEmptyErrorMessage);
        Assert.assertEquals(addMajorPage.getTextMajorAbbreviationError(),
                            majorAbbreviatioEmptyErrorMessage,
                            "Major Abbreviate not error field");


        System.out.println("==========================================");
        System.out.println("Check Error Program Training : ");
        System.out.println("Actual : " + addMajorPage.getTextTrainingProgramError());
        System.out.println("Expect : " + majorTrainingProgramNotSelectedErrorMessage);
        Assert.assertEquals(addMajorPage.getTextTrainingProgramError(),
                            majorTrainingProgramNotSelectedErrorMessage,
                            "Major Program Training not error field");

        System.out.println("==========================================");
        addMajorPage.clickCloseButton();
    }

    @Test(priority = 5, testName = "AddMajorFailWithInfoBoundary")
    public void TC_CM_06() {

        addMajorPage.clickTermAndMajorTab();
        addMajorPage.clickMajorTab();
        addMajorPage.clickAddMajorButton();
        tools.checkEqualBoolean("Kiểm tra dialog add hiển thị", dialogUtils.checkDialogManagementDisplayed(), true);

        addMajorPage.enterMajorId("LoremipsumdolorsitametconsecteturadipiscingelitLoremipsumdolorsitametconsecteturadipiscingelitLoremipsumdolorsitametconsecteturadipiscingelitLoremipsumdolorsitametconsecteturadipiscingelitwdrgvggreag");
        sleep(5);

        addMajorPage.enterMajorName("LoremipsumdolorsitametconsecteturadipiscingelitSeddoeiusmodtemporincididuntutlaboreetdoloremagnaaliquaUtenimadminimveniamquisnostrudexercitationullamcolaborisnisiutaliquipexeacommodoconsLoremipsumdolorsitametconsecteturadipiscingelitSeddoeiusmodtemporincididuntutlaboreetdoloremagnaaliquaUtenimadminimveniamquisnostrudexercitationullamcolaborisnisiutaliquipexeacommodoconsequatDuisauteiruredolorinreprehenderitequatDuisauteiruredolorinreprehenderit");
        sleep(5);

        addMajorPage.enterMajorAbbreviation("LoremipsumdolorsitametconsecteturadipiscingelitLoremipsumdolorsitametconsecteturadipiscingelit");
        sleep(5);
        addMajorPage.selectTrainingProgram("Đặc biệt");
        addMajorPage.clickSaveButton();


        String majorIdMaxLengthErrorMessage = "Tối đa 50 kí tự được cho phép";
        String majorNameMaxLengthErrorMessage = "Tối đa 255 kí tự được cho phép";
        String majorAbbreviationMaxLengthErrorMessage = "Tối đa 50 kí tự được cho phép";


        System.out.println("==========================================");
        System.out.println("Check Error ID : ");
        System.out.println("Actual : " + addMajorPage.getTextMajorIdError());
        System.out.println("Expect : " + majorIdMaxLengthErrorMessage);
        Assert.assertEquals(addMajorPage.getTextMajorIdError(),
                            majorIdMaxLengthErrorMessage,
                            "Major Id not error field");


        System.out.println("==========================================");
        System.out.println("Check Error Name : ");
        System.out.println("Actual : " + addMajorPage.getTextMajorNameError());
        System.out.println("Expect : " + majorNameMaxLengthErrorMessage);
        Assert.assertEquals(addMajorPage.getTextMajorNameError(),
                            majorNameMaxLengthErrorMessage,
                            "Major name not error field");


        System.out.println("==========================================");
        System.out.println("Check Error Abbreviate : ");
        System.out.println("Actual : " + addMajorPage.getTextMajorAbbreviationError());
        System.out.println("Expect : " + majorAbbreviationMaxLengthErrorMessage);
        Assert.assertEquals(addMajorPage.getTextMajorAbbreviationError(),
                            majorAbbreviationMaxLengthErrorMessage,
                            "Major Abbreviate not error field");
        System.out.println("==========================================");
        addMajorPage.clickCloseButton();
    }
}
