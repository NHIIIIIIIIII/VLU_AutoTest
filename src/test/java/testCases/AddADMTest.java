package testCases;

import base.BaseTest;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddADMPage;
import utils.JsonReader;
import utils.Notification;

import java.time.Duration;

public class AddADMTest extends BaseTest {
    private AddADMPage addADMPage;
    private Notification notifiCheck;
    private JsonReader jsonReader;

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
    }

    @Test(priority = 1, testName = "TC_AddADM_01")
    public void testAddADMSucces() {
        Object[] data = jsonReader.getTestCase(0); //
        addADMPage.clickAddADButton();
        sleep(5);

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

//   data > maxLength
    @Test(priority = 2, testName = "TC_AddADM_02")
    public void testAddADMErrorWithDataMaxLength() {
        Object[] data = jsonReader.getTestCase(1);
        addADMPage.clickAddADButton();
        sleep(5);

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
    @Test(priority = 3, testName = "TC_AddADM_03")
    public void testAddADMErrorWithDataEmpty() {
        Object[] data = jsonReader.getTestCase(2);
        addADMPage.clickAddADButton();
        sleep(5);

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

    // data duplicate id - trùng lặp id
    @Test(priority = 4, testName = "TC_AddADM_04")
    public void testAddADMErrorWithDuplicateID() {
        Object[] data = jsonReader.getTestCase(3);
        addADMPage.clickAddADButton();
        sleep(5);

        addADMPage.enterCodeAD((String) data[0]);
        sleep(5);

        addADMPage.enterNameAD((String) data[1]);
        sleep(5);

        addADMPage.enterOrderAD(String.valueOf((int) data[2]));
        sleep(5);

        addADMPage.clickSaveADButton();
        sleep(2);

        // Replace sleep with explicit wait for the error dialog
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(addADMPage.checkErrorDialogDisplayed()));
        System.out.println("==========================================");
        System.out.println("Check Error: ");
        System.out.println("Actual: " + addADMPage.checkErrorDialogDisplayed().getText());
        System.out.println("Expect: " + addADMPage.getIdADDuplicateEM());
        Assert.assertEquals(addADMPage.checkErrorDialogDisplayed().getText(), addADMPage.getIdADDuplicateEM(), "Error Id Duplicated");
        System.out.println("==========================================");

        addADMPage.clickOkADButton();
        addADMPage.clickCloseADButton();
        } catch (TimeoutException e) {
            Assert.fail("Error dialog did not appear within 10 seconds", e);
        }
    }

    // Data Invalid Format ID - định dạng ko hợp lệ
    @Test(priority = 5, testName = "TC_AddADM_05")
    public void testAddADMErrorWithInvalidFormat() {
        Object[] data = jsonReader.getTestCase(4);
        addADMPage.clickAddADButton();
        sleep(5);

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
    @Test(priority = 6, testName = "TC_AddADM_06")
    public void testAddADMErrorWithDataMinLength() {
        Object[] data = jsonReader.getTestCase(5);
        addADMPage.clickAddADButton();
        sleep(5);

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
