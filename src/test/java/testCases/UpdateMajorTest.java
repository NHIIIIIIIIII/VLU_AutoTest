package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MajorManagementPage;
import pages.UpdateMajorPage;
import utils.Notification;

/**
 * @author Trần Văn Hiếu - 2274802010262
 * @Title: Kiểm thử chức năng sửa ngành học
 * @since 13/03/2025
 */
public class UpdateMajorTest extends BaseTest {
    UpdateMajorPage updateMajorPage;
    private Notification notifiCheck;

    @BeforeClass
    public void setupClass() {
        updateMajorPage = new UpdateMajorPage(driver, wait);
        notifiCheck = new Notification(wait);
    }


    @Test(priority = 0, testName = "TC_UM_01")
    public void addNewMajor() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        sleep(5);

        updateMajorPage.enterMajorName("Công nghệ thông tin 2023");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("CNTT");
        sleep(5);
        updateMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();
        notifiCheck.testUpdateNotification();
    }

    @Test(priority = 1, testName = "TC_UM_02")
    public void UpdateMajorWithEmptyName() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();

        updateMajorPage.enterMajorName("");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("CNTT");
        sleep(5);
        updateMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();

        String errorMessage = "Bạn chưa nhập tên ngành";
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + updateMajorPage.getTextMajorNameError());
        System.out.println("Expect : " + errorMessage);
        Assert.assertEquals(updateMajorPage.getTextMajorNameError(), errorMessage, "Error Message not equal");
        System.out.println("==========================================");
        updateMajorPage.clickCloseButton();
    }

    @Test(priority = 2, testName = "TC_UM_03")
    public void UpdateMajorWithEmptyMajorAbbreviation() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();

        updateMajorPage.enterMajorName("Cong Nghe Thong Tin");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("");
        sleep(5);
        updateMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();

        String errorMessage = "Bạn chưa nhập tên viết tắt của ngành";
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + updateMajorPage.getTextMajorAbbreviationError());
        System.out.println("Expect : " + errorMessage);
        Assert.assertEquals(updateMajorPage.getTextMajorAbbreviationError(), errorMessage, "Error Message not equal");
        System.out.println("==========================================");
        updateMajorPage.clickCloseButton();
    }


    @Test(priority = 3, testName = "TC_UM_04")
    public void UpdateMajorWithNoSelectTrainingProgram() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();

        sleep(5);

        updateMajorPage.enterMajorName("Cong Nghe Thong Tin");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("CNTT");
        sleep(5);
//        addMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();
        notifiCheck.testUpdateNotification();
    }

    @Test(priority = 4, testName = "TC_UM_05")
    public void UpdateMajorWithAllEmptyAndNoSelectTrainingProgram() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();

        sleep(5);

        updateMajorPage.enterMajorName("");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("");
        sleep(5);
//        addMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();


        String majorNameEmptyErrorMessage = "Bạn chưa nhập tên ngành";
        String majorAbbreviatioEmptyErrorMessage = "Bạn chưa nhập tên viết tắt của ngành";
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + updateMajorPage.getTextMajorNameError());
        System.out.println("Expect : " + majorNameEmptyErrorMessage);
        Assert.assertEquals(updateMajorPage.getTextMajorNameError(), majorNameEmptyErrorMessage, "Error Message not equal");


        System.out.println("Actual : " + updateMajorPage.getTextMajorAbbreviationError());
        System.out.println("Expect : " + majorAbbreviatioEmptyErrorMessage);
        Assert.assertEquals(updateMajorPage.getTextMajorAbbreviationError(), majorAbbreviatioEmptyErrorMessage, "Error Message not equal");
        System.out.println("==========================================");
        updateMajorPage.clickCloseButton();
    }

    @Test(priority = 5, testName = "TC_UM_06")
    public void UpdateMajorWithMajorAbbreviationMore50() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();

        sleep(5);

        updateMajorPage.enterMajorName("Cong Nghe Thong Tin");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("XVVE4O0F3Fqw5hPnDlT2d6poU8T7ttBVf4yW7HjxIOpLoC411Ah");
        sleep(5);
        updateMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();

        String majorAbbreviationMaxLengthErrorMessage = "Tối đa 50 kí tự được cho phép";
        System.out.println("==========================================");
        System.out.println("Check Error : ");

        System.out.println("Actual : " + updateMajorPage.getTextMajorAbbreviationError());
        System.out.println("Expect : " + majorAbbreviationMaxLengthErrorMessage);
        Assert.assertEquals(updateMajorPage.getTextMajorAbbreviationError(), majorAbbreviationMaxLengthErrorMessage, "Error Message not equal");
        System.out.println("==========================================");
        updateMajorPage.clickCloseButton();
    }

    @Test(priority = 6, testName = "TC_UM_07")
    public void UpdateMajorWithMajorNameMore255() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();

        sleep(5);

        updateMajorPage.enterMajorName("7Mx8lKc2VW3L4RnJcGvB80s8EPhm7SCcjnftmHtI8m48r7C1PAd0AxRuS6ghvg90aSlBfVB5VKqx44eDioBpywgjH77rfwBCqUSBuYAMO3WvUWz2a4lyx5A7IkQkiEo2l6VaKeraOcOPZg6D3aHUpftf9Uj8CwQxmrIdbo2ghljXmh56FnFUdgtaiA9TQfkalzjeJYMw0dYCzHPVYlmeMxSFRyPUZcmGb27DgpWz7n27lqsXAlHopkzpiXlyxO7c");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("CNTT");
        sleep(5);
        updateMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();

        String majorNameMaxLengthErrorMessage = "Tối đa 255 kí tự được cho phép";
        System.out.println("==========================================");
        System.out.println("Check Error : ");

        System.out.println("Actual : " + updateMajorPage.getTextMajorNameError());
        System.out.println("Expect : " + majorNameMaxLengthErrorMessage);
        Assert.assertEquals(updateMajorPage.getTextMajorNameError(), majorNameMaxLengthErrorMessage, "Error Message not equal");
        System.out.println("==========================================");
        updateMajorPage.clickCloseButton();
    }

    @Test(priority = 7, testName = "TC_UM_08")
    public void UpdateMajorWithMajorNameAndAbbreviationMore() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();

        sleep(5);

        updateMajorPage.enterMajorName("7Mx8lKc2VW3L4RnJcGvB80s8EPhm7SCcjnftmHtI8m48r7C1PAd0AxRuS6ghvg90aSlBfVB5VKqx44eDioBpywgjH77rfwBCqUSBuYAMO3WvUWz2a4lyx5A7IkQkiEo2l6VaKeraOcOPZg6D3aHUpftf9Uj8CwQxmrIdbo2ghljXmh56FnFUdgtaiA9TQfkalzjeJYMw0dYCzHPVYlmeMxSFRyPUZcmGb27DgpWz7n27lqsXAlHopkzpiXlyxO7c");
        sleep(5);

        updateMajorPage.enterMajorAbbreviation("XVVE4O0F3Fqw5hPnDlT2d6poU8T7ttBVf4yW7HjxIOpLoC411Ah");
        sleep(5);
        updateMajorPage.selectTrainingProgram("Đặc biệt");
        updateMajorPage.clickSaveButton();

        String majorNameMaxLengthErrorMessage = "Tối đa 255 kí tự được cho phép";
        String majorAbbreviationMaxLengthErrorMessage = "Tối đa 50 kí tự được cho phép";
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + updateMajorPage.getTextMajorNameError());
        System.out.println("Expect : " + majorNameMaxLengthErrorMessage);
        Assert.assertEquals(updateMajorPage.getTextMajorNameError(), majorNameMaxLengthErrorMessage, "Error Message not equal");


        System.out.println("Actual : " + updateMajorPage.getTextMajorAbbreviationError());
        System.out.println("Expect : " + majorAbbreviationMaxLengthErrorMessage);
        Assert.assertEquals(updateMajorPage.getTextMajorAbbreviationError(), majorAbbreviationMaxLengthErrorMessage, "Error Message not equal");
        System.out.println("==========================================");
        updateMajorPage.clickCloseButton();
    }


}
