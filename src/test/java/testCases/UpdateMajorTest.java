package testCases;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.UpdateMajorPage;
import utils.Dialog;
import utils.Notification;

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


    @Test(priority = 0, testName = "TC_UM_01")
    public void addNewMajor() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        sleep(5);
        tools.checkEqualBoolean("Kiểm tra dialog Quản lý hiển thị",dialogUtils.checkDialogAddDisplayed(),true);

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
        tools.checkEqualBoolean("Kiểm tra dialog Quản lý hiển thị",dialogUtils.checkDialogAddDisplayed(),true);

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

    @Test(priority = 2, testName = "TC_UM_03")
    public void UpdateMajorWithEmptyMajorAbbreviation() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        tools.checkEqualBoolean("Kiểm tra dialog Quản lý hiển thị",dialogUtils.checkDialogAddDisplayed(),true);

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


    @Test(priority = 3, testName = "TC_UM_04")
    public void UpdateMajorWithAllEmptyAndNoSelectTrainingProgram() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        tools.checkEqualBoolean("Kiểm tra dialog Quản lý hiển thị",dialogUtils.checkDialogAddDisplayed(),true);

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

    @Test(priority = 4, testName = "TC_UM_05")
    public void UpdateMajorWithMajorAbbreviationMore50() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        tools.checkEqualBoolean("Kiểm tra dialog Quản lý hiển thị",dialogUtils.checkDialogAddDisplayed(),true);

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

    @Test(priority = 5, testName = "TC_UM_06")
    public void UpdateMajorWithMajorNameMore255() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        tools.checkEqualBoolean("Kiểm tra dialog Quản lý hiển thị",dialogUtils.checkDialogAddDisplayed(),true);

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

    @Test(priority = 6, testName = "TC_UM_07")
    public void UpdateMajorWithMajorNameAndAbbreviationMore() {
        updateMajorPage.clickTermAndMajorTab();
        updateMajorPage.clickMajorTab();
        updateMajorPage.searchMajor("CNTT2023");
        sleep(5);
        updateMajorPage.clickUpdateButton();
        tools.checkEqualBoolean("Kiểm tra dialog Quản lý hiển thị",dialogUtils.checkDialogAddDisplayed(),true);

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
