package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.UpdateADMPage;
import utils.JsonReader;
import utils.Notification;

/**
 * @author Nguyễn Liên Nhi - 2274802010612
 * @Title: Kiểm thử chức năng sửa học hàm, học vị
 * @since 24/03/2025
 */

//@Listeners(utils.ExcelTestListener.class)

public class UpdateADMTest extends BaseTest {
    private UpdateADMPage updateADMPage;
    private Notification notifiCheck;

    @BeforeClass
    public void setupClass() {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/AcademicDegree");
        updateADMPage = new UpdateADMPage(driver);
        jsonReader = new JsonReader();
        notifiCheck = new Notification(wait);
    }

    @Test(priority = 0, testName = "TC_UpdateADM_01")
    public void testValidUpdateNormalCase() {
        updateADMPage.clickEditADButton();
        sleep(5);

        updateADMPage.enterNameAD("Professor Updated");
        sleep(5);

        updateADMPage.enterOrderAD("12");
        sleep(5);

        updateADMPage.clickSaveADButton();
        notifiCheck.testUpdateNotification();
    }

    @Test(priority = 1, testName = "TC_UpdateADM_02")
    public void testInvalidUpdateEmptyName() {
        updateADMPage.clickEditADButton();
        sleep(5);

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

        updateADMPage.clickExitADButton();
    }

    @Test(priority = 2, testName = "TC_UpdateADM_03")
    public void testInvalidUpdateNameExceeds100Chars() {
        updateADMPage.clickEditADButton();
        sleep(5);

        updateADMPage.enterNameAD("A very long name exceeding 100 characters A very long name exceeding 100 characters A very long name exceeding 100 characters");
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

        updateADMPage.clickExitADButton();
    }

    @Test(priority = 3, testName = "TC_UpdateADM_04")
    public void testInvalidUpdateOrderEmpty() {
        updateADMPage.clickEditADButton();
        sleep(5);

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

        updateADMPage.clickExitADButton();
    }

    @Test(priority = 4, testName = "TC_UpdateADM_05")
    public void testInvalidUpdateOrderExceeds100() {
        updateADMPage.clickEditADButton();
        sleep(5);

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

        updateADMPage.clickExitADButton();
    }

    @Test(priority = 5, testName = "TC_UpdateADM_06")
    public void testInvalidUpdateOrderEqualsZero() {
        updateADMPage.clickEditADButton();
        sleep(5);

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