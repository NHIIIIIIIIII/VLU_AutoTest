package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddMajorPage;
import pages.MajorManagementPage;
import utils.Notification;

public class AddMajorTest extends BaseTest {
    AddMajorPage addMajorPage;
    private Notification notifiCheck;

    @BeforeClass
    public void setupClass() {
        addMajorPage = new AddMajorPage(driver, wait);
        notifiCheck = new Notification(wait);
    }

//    @Test(priority = 0, testName = "TC_CM_01")
//    public void testAddMajorSuccess() {
//        addMajorPage.clickTermAndMajorTab();
//        addMajorPage.clickMajorTab();
//        addMajorPage.clickAddMajorButton();
//        addMajorPage.enterMajorId("CNTT2023");
//        sleep(5);
//
//        addMajorPage.enterMajorName("Công nghệ thông tin 2023");
//        sleep(5);
//
//        addMajorPage.enterMajorAbbreviation("CNTT");
//        sleep(5);
//        addMajorPage.selectTrainingProgram("Đặc biệt");
//        addMajorPage.clickSaveButton();
//        notifiCheck.testAddNotification();
//    }


    @Test(priority = 1, testName = "TC_CM_02")
    public void testAddMajorFailWithExistId() {

        addMajorPage.clickTermAndMajorTab();
        addMajorPage.clickMajorTab();
        addMajorPage.clickAddMajorButton();
        addMajorPage.enterMajorId("CNTT2023");
        sleep(5);

        addMajorPage.enterMajorName("Công nghệ thông tin 2023");
        sleep(5);

        addMajorPage.enterMajorAbbreviation("CNTT");
        sleep(5);
        addMajorPage.selectTrainingProgram("Đặc biệt");
        addMajorPage.clickSaveButton();


        String duplicateMajorIdErrorMessage = "Mã ngành này đã tồn tại!";  // Hiển thị trên dialog

        String errorMessage = "Bạn chưa nhập tên ngành";
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + addMajorPage.checkDialogDisplayed().getText());
        System.out.println("Expect : " + duplicateMajorIdErrorMessage);
        Assert.assertEquals(addMajorPage.checkDialogDisplayed().getText(),
                            duplicateMajorIdErrorMessage,
                            "Major Id not duplicated");
        System.out.println("==========================================");
        addMajorPage.clickOkErrorButton();
    }


    @Test(priority = 2, testName = "TC_CM_03")
    public void testAddMajorFailWithSpace() {

        addMajorPage.clickTermAndMajorTab();
        addMajorPage.clickMajorTab();
        addMajorPage.clickAddMajorButton();
        addMajorPage.enterMajorId("CNTT202 3");
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
                            "Major Id not duplicated");
        System.out.println("==========================================");
        addMajorPage.clickCloseButton();
    }

    @Test(priority = 3, testName = "TC_CM_04")
    public void testAddMajorFailWithIdInvalid() {

        addMajorPage.clickTermAndMajorTab();
        addMajorPage.clickMajorTab();
        addMajorPage.clickAddMajorButton();
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
                            "Major Id not duplicated");
        System.out.println("==========================================");
        addMajorPage.clickCloseButton();
    }

    @Test(priority = 4, testName = "TC_CM_05")
    public void testAddMajorFailWithEmpty() {

        addMajorPage.clickTermAndMajorTab();
        addMajorPage.clickMajorTab();
        addMajorPage.clickAddMajorButton();
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

    @Test(priority = 5, testName = "TC_CM_06")
    public void testAddMajorFailWithInfoBoundary() {

        addMajorPage.clickTermAndMajorTab();
        addMajorPage.clickMajorTab();
        addMajorPage.clickAddMajorButton();
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
