package testCases;

import base.BaseTest;
import org.openqa.selenium.By; // Thêm import này
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.UpdateADMPage;
import utils.JsonReader;

import java.io.IOException;

public class UpdateADMTest extends BaseTest {
    private UpdateADMPage updateADMPage;
    private JsonReader jsonReader;
    private static final int MAX_RETRIES = 3;

    public UpdateADMTest() {
        super();
    }

    @BeforeClass
    public void setupTest() {
        updateADMPage = new UpdateADMPage(driver);
        jsonReader = new JsonReader();

        System.out.println("Starting setup...");
        int retries = 0;
        boolean success = false;

        while (retries < MAX_RETRIES && !success) {
            try {
                driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/AcademicDegree");

                // Kiểm tra nếu trang yêu cầu đăng nhập (giả định)
                if (driver.getCurrentUrl().contains("login")) {
                    WebElement usernameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
                    WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
                    WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login")));

                    usernameInput.sendKeys("your_username"); // Thay bằng username thực tế
                    passwordInput.sendKeys("your_password"); // Thay bằng password thực tế
                    loginButton.click();

                    wait.until(ExpectedConditions.urlContains("AcademicDegree"));
                }

                wait.until(ExpectedConditions.elementToBeClickable(updateADMPage.getEditADButton()));
                System.out.println("Page title after load: " + driver.getTitle());
                success = true;
            } catch (Exception e) {
                retries++;
                System.err.println("Setup failed (attempt " + retries + "/" + MAX_RETRIES + "): " + e.getMessage());
                if (retries == MAX_RETRIES) {
                    throw new RuntimeException("Failed to setup test after " + MAX_RETRIES + " attempts", e);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println("Setup completed.");
    }

    private void performUpdateTest(String name, int order, String testDescription, String expectedErrorMessage, boolean isDuplicate) {
        System.out.println("Running test: " + testDescription);
        System.out.println("Test data - Name: " + name + ", Order: " + order);
        System.out.println("Expected error message: " + (expectedErrorMessage != null ? expectedErrorMessage : "None (expecting success)"));

        int retries = 0;
        boolean success = false;

        while (retries < MAX_RETRIES && !success) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(updateADMPage.getEditADButton()));
                System.out.println("Wait completed - Page is ready for test: " + testDescription);
                System.out.println("Current URL: " + driver.getCurrentUrl());

                WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(updateADMPage.getEditADButton()));
                editButton.click();

                WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(updateADMPage.getNameADInput()));
                nameInput.clear();
                nameInput.sendKeys(name);

                WebElement orderInput = wait.until(ExpectedConditions.elementToBeClickable(updateADMPage.getOrderADInput()));
                orderInput.clear();
                orderInput.sendKeys(String.valueOf(order));

                wait.until(ExpectedConditions.elementToBeClickable(updateADMPage.getSaveADButton())).click();

                if (expectedErrorMessage != null) {
                    WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(updateADMPage.getDialogErrorField()));
                    String actualErrorMessage = dialog.getText();
                    System.out.println("Actual dialog error text: " + actualErrorMessage);
                    Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch for " + testDescription);
                    if (isDuplicate) {
                        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(updateADMPage.getOkADButton()));
                        System.out.println("Clicking OK button for dialog error...");
                        okButton.click();
                    }
                } else {
                    updateADMPage.getNotification().checkUpdateNotification();
                }
                success = true;
            } catch (Exception e) {
                retries++;
                System.err.println("Test failed (attempt " + retries + "/" + MAX_RETRIES + "): " + e.getMessage());
                if (e.getCause() instanceof IOException) {
                    System.err.println("Detected IOException: Connection reset by peer");
                }
                if (retries == MAX_RETRIES) {
                    throw new RuntimeException("Test failed after " + MAX_RETRIES + " attempts: " + testDescription, e);
                }
                try {
                    Thread.sleep(2000);
                    driver.navigate().refresh();
                    System.out.println("Page refreshed for retry " + retries);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Test(priority = 1)
    public void testValidUpdateNormalCase() {
        Object[] data = jsonReader.getTestCase(0); // Giả định {"Professora Updated", 12}
        performUpdateTest((String) data[0], (int) data[1], "Valid update - Normal case", null, false);
    }

    @Test(priority = 2)
    public void testInvalidUpdateDuplicateID() {
        Object[] data = jsonReader.getTestCase(1); // Giả định {"Duplicate Name", 2}
        performUpdateTest((String) data[0], (int) data[1], "Invalid update - Duplicate ID", updateADMPage.getIdADDuplicateEM(), true);
    }

    @Test(priority = 3)
    public void testInvalidUpdateEmptyName() {
        Object[] data = jsonReader.getTestCase(2); // Giả định {"", 13}
        performUpdateTest((String) data[0], (int) data[1], "Invalid update - Empty Name", updateADMPage.getNameADEmptyEM(), false);
    }

    @Test(priority = 4)
    public void testInvalidUpdateNameExceeds100Chars() {
        Object[] data = jsonReader.getTestCase(3); // Giả định {"A very long name exceeding 100 characters...", 3}
        performUpdateTest((String) data[0], (int) data[1], "Invalid update - Name exceeds 100 characters", updateADMPage.getNameADMaxLengthEM(), false);
    }

    @Test(priority = 5)
    public void testInvalidUpdateOrderEmpty() {
        Object[] data = jsonReader.getTestCase(4); // Giả định {"Valid Name", ""}
        performUpdateTest((String) data[0], -1, "Invalid update - Order empty", updateADMPage.getOrderADEmptyEM(), false);
    }

    @Test(priority = 6)
    public void testInvalidUpdateOrderExceeds100() {
        Object[] data = jsonReader.getTestCase(5); // Giả định {"Valid Name", 111}
        performUpdateTest((String) data[0], (int) data[1], "Invalid update - Order exceeds 100", updateADMPage.getOrderADMaxLengthEM(), false);
    }

    @Test(priority = 7)
    public void testInvalidUpdateOrderEqualsZero() {
        Object[] data = jsonReader.getTestCase(6); // Giả định {"Valid Name", 0}
        performUpdateTest((String) data[0], (int) data[1], "Invalid update - Order equals 0", updateADMPage.getOrderADMinLengthEM(), false);
    }

    @Test(priority = 8)
    public void testInvalidUpdateOrderInvalidFormat() {
        Object[] data = jsonReader.getTestCase(7); // Giả định {"Valid Name", "abc"}
        performUpdateTest((String) data[0], -2, "Invalid update - Order invalid format", updateADMPage.getOrderADInvalidFormatEM(), false);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}