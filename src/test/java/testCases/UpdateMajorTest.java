package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MajorManagementPage;
import utils.Notification;

public class UpdateMajorTest extends BaseTest {
    MajorManagementPage majorManagement;
    private Notification notifiCheck;

    @BeforeClass
    public void setupClass() {
        majorManagement = new MajorManagementPage(driver, wait);
        notifiCheck = new Notification(wait);

    }


    /**
     * @author Trần Văn Hiếu - 2274802010262
     * @Title: Kiểm thử chức năng thêm ngành học
     * @since 06/03/2025
     */
    @Test(priority = 0, testName = "TC_UM_01")
    public void addNewMajor() {
        majorManagement.clickTermAndMajorTab();
        majorManagement.clickMajorTab();
        majorManagement.searchMajor("CNTT2023");
        sleep(5);
        majorManagement.clickUpdateButton();
        sleep(5);

        majorManagement.enterMajorName("Công nghệ thông tin 2023");
        sleep(5);

        majorManagement.enterMajorAbbreviation("CNTT");
        sleep(5);
        majorManagement.selectTrainingProgram("Đặc biệt");
        majorManagement.clickSaveButton();
        notifiCheck.testUpdateNotification();
    }
    @Test(priority = 1, testName = "TC_UM_02")
    public void UpdateMajorWithEmptyName() {
        majorManagement.clickTermAndMajorTab();
        majorManagement.clickMajorTab();
        majorManagement.searchMajor("CNTT2023");
        sleep(5);
        majorManagement.clickUpdateButton();

        majorManagement.enterMajorName("");
        sleep(5);

        majorManagement.enterMajorAbbreviation("CNTT");
        sleep(5);
        majorManagement.selectTrainingProgram("Đặc biệt");
        majorManagement.clickSaveButton();

        String errorMessage = "Bạn chưa nhập tên ngành";
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + majorManagement.getTextMajorNameError());
        System.out.println("Expect : " + errorMessage);
        Assert.assertEquals(majorManagement.getTextMajorNameError(), errorMessage, "Error Message not equal");
        System.out.println("==========================================");
        majorManagement.clickCloseButton();
    }

    @Test(priority = 2, testName = "TC_UM_03")
    public void UpdateMajorWithEmptyMajorAbbreviation() {
        majorManagement.clickTermAndMajorTab();
        majorManagement.clickMajorTab();
        majorManagement.searchMajor("CNTT2023");
        sleep(5);
        majorManagement.clickUpdateButton();

        majorManagement.enterMajorName("Cong Nghe Thong Tin");
        sleep(5);

        majorManagement.enterMajorAbbreviation("");
        sleep(5);
        majorManagement.selectTrainingProgram("Đặc biệt");
        majorManagement.clickSaveButton();

        String errorMessage = "Bạn chưa nhập tên viết tắt của ngành";
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + majorManagement.getTextMajorAbbreviationError());
        System.out.println("Expect : " + errorMessage);
        Assert.assertEquals(majorManagement.getTextMajorAbbreviationError(), errorMessage, "Error Message not equal");
        System.out.println("==========================================");
        majorManagement.clickCloseButton();
    }


    @Test(priority = 3, testName = "TC_UM_04")
    public void UpdateMajorWithNoSelectTrainingProgram() {
        majorManagement.clickTermAndMajorTab();
        majorManagement.clickMajorTab();
        majorManagement.searchMajor("CNTT2023");
        sleep(5);
        majorManagement.clickUpdateButton();

        sleep(5);

        majorManagement.enterMajorName("Cong Nghe Thong Tin");
        sleep(5);

        majorManagement.enterMajorAbbreviation("CNTT");
        sleep(5);
//        majorManagement.selectTrainingProgram("Đặc biệt");
        majorManagement.clickSaveButton();
        notifiCheck.testUpdateNotification();
    }

    @Test(priority = 4, testName = "TC_UM_05")
    public void UpdateMajorWithAllEmptyAndNoSelectTrainingProgram() {
        majorManagement.clickTermAndMajorTab();
        majorManagement.clickMajorTab();
        majorManagement.searchMajor("CNTT2023");
        sleep(5);
        majorManagement.clickUpdateButton();

        sleep(5);

        majorManagement.enterMajorName("");
        sleep(5);

        majorManagement.enterMajorAbbreviation("");
        sleep(5);
//        majorManagement.selectTrainingProgram("Đặc biệt");
        majorManagement.clickSaveButton();


        String majorNameEmptyErrorMessage = "Bạn chưa nhập tên ngành";
        String majorAbbreviatioEmptyErrorMessage = "Bạn chưa nhập tên viết tắt của ngành";
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + majorManagement.getTextMajorNameError());
        System.out.println("Expect : " + majorNameEmptyErrorMessage);
        Assert.assertEquals(majorManagement.getTextMajorNameError(), majorNameEmptyErrorMessage, "Error Message not equal");


        System.out.println("Actual : " + majorManagement.getTextMajorAbbreviationError());
        System.out.println("Expect : " + majorAbbreviatioEmptyErrorMessage);
        Assert.assertEquals(majorManagement.getTextMajorAbbreviationError(), majorAbbreviatioEmptyErrorMessage, "Error Message not equal");
        System.out.println("==========================================");
        majorManagement.clickCloseButton();
    }

    @Test(priority = 5, testName = "TC_UM_06")
    public void UpdateMajorWithMajorAbbreviationMore50() {
        majorManagement.clickTermAndMajorTab();
        majorManagement.clickMajorTab();
        majorManagement.searchMajor("CNTT2023");
        sleep(5);
        majorManagement.clickUpdateButton();

        sleep(5);

        majorManagement.enterMajorName("Cong Nghe Thong Tin");
        sleep(5);

        majorManagement.enterMajorAbbreviation("XVVE4O0F3Fqw5hPnDlT2d6poU8T7ttBVf4yW7HjxIOpLoC411Ah");
        sleep(5);
        majorManagement.selectTrainingProgram("Đặc biệt");
        majorManagement.clickSaveButton();

        String majorAbbreviationMaxLengthErrorMessage = "Tối đa 50 kí tự được cho phép";
        System.out.println("==========================================");
        System.out.println("Check Error : ");

        System.out.println("Actual : " + majorManagement.getTextMajorAbbreviationError());
        System.out.println("Expect : " + majorAbbreviationMaxLengthErrorMessage);
        Assert.assertEquals(majorManagement.getTextMajorAbbreviationError(), majorAbbreviationMaxLengthErrorMessage, "Error Message not equal");
        System.out.println("==========================================");
        majorManagement.clickCloseButton();
    }

    @Test(priority = 6, testName = "TC_UM_07")
    public void UpdateMajorWithMajorNameMore255() {
        majorManagement.clickTermAndMajorTab();
        majorManagement.clickMajorTab();
        majorManagement.searchMajor("CNTT2023");
        sleep(5);
        majorManagement.clickUpdateButton();

        sleep(5);

        majorManagement.enterMajorName("7Mx8lKc2VW3L4RnJcGvB80s8EPhm7SCcjnftmHtI8m48r7C1PAd0AxRuS6ghvg90aSlBfVB5VKqx44eDioBpywgjH77rfwBCqUSBuYAMO3WvUWz2a4lyx5A7IkQkiEo2l6VaKeraOcOPZg6D3aHUpftf9Uj8CwQxmrIdbo2ghljXmh56FnFUdgtaiA9TQfkalzjeJYMw0dYCzHPVYlmeMxSFRyPUZcmGb27DgpWz7n27lqsXAlHopkzpiXlyxO7c");
        sleep(5);

        majorManagement.enterMajorAbbreviation("CNTT");
        sleep(5);
        majorManagement.selectTrainingProgram("Đặc biệt");
        majorManagement.clickSaveButton();

       String majorNameMaxLengthErrorMessage = "Tối đa 255 kí tự được cho phép";
        System.out.println("==========================================");
        System.out.println("Check Error : ");

        System.out.println("Actual : " + majorManagement.getTextMajorNameError());
        System.out.println("Expect : " + majorNameMaxLengthErrorMessage);
        Assert.assertEquals(majorManagement.getTextMajorNameError(), majorNameMaxLengthErrorMessage, "Error Message not equal");
        System.out.println("==========================================");
        majorManagement.clickCloseButton();
    }

    @Test(priority = 7, testName = "TC_UM_08")
    public void UpdateMajorWithMajorNameAndAbbreviationMore() {
        majorManagement.clickTermAndMajorTab();
        majorManagement.clickMajorTab();
        majorManagement.searchMajor("CNTT2023");
        sleep(5);
        majorManagement.clickUpdateButton();

        sleep(5);

        majorManagement.enterMajorName("7Mx8lKc2VW3L4RnJcGvB80s8EPhm7SCcjnftmHtI8m48r7C1PAd0AxRuS6ghvg90aSlBfVB5VKqx44eDioBpywgjH77rfwBCqUSBuYAMO3WvUWz2a4lyx5A7IkQkiEo2l6VaKeraOcOPZg6D3aHUpftf9Uj8CwQxmrIdbo2ghljXmh56FnFUdgtaiA9TQfkalzjeJYMw0dYCzHPVYlmeMxSFRyPUZcmGb27DgpWz7n27lqsXAlHopkzpiXlyxO7c");
        sleep(5);

        majorManagement.enterMajorAbbreviation("XVVE4O0F3Fqw5hPnDlT2d6poU8T7ttBVf4yW7HjxIOpLoC411Ah");
        sleep(5);
        majorManagement.selectTrainingProgram("Đặc biệt");
        majorManagement.clickSaveButton();

        String majorNameMaxLengthErrorMessage = "Tối đa 255 kí tự được cho phép";
        String majorAbbreviationMaxLengthErrorMessage = "Tối đa 50 kí tự được cho phép";
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + majorManagement.getTextMajorNameError());
        System.out.println("Expect : " + majorNameMaxLengthErrorMessage);
        Assert.assertEquals(majorManagement.getTextMajorNameError(), majorNameMaxLengthErrorMessage, "Error Message not equal");


        System.out.println("Actual : " + majorManagement.getTextMajorAbbreviationError());
        System.out.println("Expect : " + majorAbbreviationMaxLengthErrorMessage);
        Assert.assertEquals(majorManagement.getTextMajorAbbreviationError(), majorAbbreviationMaxLengthErrorMessage, "Error Message not equal");
        System.out.println("==========================================");
        majorManagement.clickCloseButton();
    }


}
