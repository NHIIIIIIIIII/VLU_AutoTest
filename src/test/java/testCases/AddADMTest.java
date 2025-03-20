package testCases;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddADMPage;
import utils.JsonReader;
import utils.Notification;

import java.io.IOException;

public class AddADMTest extends BaseTest {
    private AddADMPage addADMPage;
    private JsonReader jsonReader;
    private static final int MAX_RETRIES = 3;

    public AddADMTest() {
        super();
    }

    @BeforeClass
    public void setupTest() {
        addADMPage = new AddADMPage(driver);
        jsonReader = new JsonReader();

        System.out.println("Starting setup...");
        int retries = 0;
        boolean success = false;

        while (retries < MAX_RETRIES && !success) {
            try {
                driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/AcademicDegree");
                wait.until(ExpectedConditions.elementToBeClickable(addADMPage.getOpenADButton()));
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

    private void performAddTest(String id, String name, int order, String testDescription, String expectedErrorMessage, String errorField, boolean isDuplicate, boolean isInvalidFormatInDialog) {
        System.out.println("Running test: " + testDescription);
        System.out.println("Test data - ID: " + id + ", Name: " + name + ", Order: " + order);
        System.out.println("Expected error message: " + (expectedErrorMessage != null ? expectedErrorMessage : "None (expecting success)"));

        int retries = 0;
        boolean success = false;

        while (retries < MAX_RETRIES && !success) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(addADMPage.getOpenADButton()));
                System.out.println("Wait completed - Page is ready for test: " + testDescription);
                System.out.println("Current URL: " + driver.getCurrentUrl());

                WebElement openButton = wait.until(ExpectedConditions.elementToBeClickable(addADMPage.getOpenADButton()));
                openButton.click();

                WebElement codeInput = wait.until(ExpectedConditions.elementToBeClickable(addADMPage.getCodeADInput()));
                codeInput.clear();
                codeInput.sendKeys(id);

                WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(addADMPage.getNameADInput()));
                nameInput.clear();
                nameInput.sendKeys(name);

                WebElement orderInput = wait.until(ExpectedConditions.elementToBeClickable(addADMPage.getOrderADInput()));
                orderInput.clear();
                orderInput.sendKeys(String.valueOf(order));

                wait.until(ExpectedConditions.elementToBeClickable(addADMPage.getSaveADButton())).click();

                if (expectedErrorMessage != null && errorField != null) {
                    WebElement errorElement = null;
                    if (isDuplicate || isInvalidFormatInDialog) {
                        errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addADMPage.getDialogErrorField()));
                        String actualErrorMessage = errorElement.getText();
                        System.out.println("Actual dialog error text: " + actualErrorMessage);
                        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch for " + testDescription);
                        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(addADMPage.getOkADButton()));
                        System.out.println("Clicking OK button for dialog error...");
                        okButton.click();
                    } else {
                        switch (errorField) {
                            case "id":
                                errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addADMPage.getAdIdError()));
                                break;
                            case "name":
                                errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addADMPage.getAdNameError()));
                                break;
                            case "order":
                                errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addADMPage.getAdOrderError()));
                                break;
                        }
                        String actualErrorMessage = errorElement.getText();
                        System.out.println("Actual error text: " + actualErrorMessage);
                        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch for " + testDescription);
                    }
                } else {
                    Notification notification = addADMPage.getNotification();
                    notification.testAddNotification();
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
    public void testValidDataNormalCase() {
        Object[] data = jsonReader.getTestCase(0); // {"MECA001", "Professora", 12}
        performAddTest((String) data[0], (String) data[1], (int) data[2], "Valid data - Normal case", null, null, false, false);
    }

    @Test(priority = 2)
    public void testValidDataRandomMaxLength() {
        Object[] data = jsonReader.getTestCase(1); // {tools.generateRandomString(50), tools.generateRandomString(50), 20}
        performAddTest((String) data[0], (String) data[1], (int) data[2], "Valid data - Random max length", null, null, false, false);
    }

    @Test(priority = 3)
    public void testInvalidDataExceedAllLimits() {
        Object[] data = jsonReader.getTestCase(2); // {tools.generateRandomString(100), tools.generateRandomString(150), 110}
        performAddTest((String) data[0], (String) data[1], (int) data[2], "Invalid data - Exceed all limits",
                addADMPage.getIdADMaxLengthEM(), "id", false, false);
    }

    @Test(priority = 4)
    public void testInvalidDataEmptyID() {
        Object[] data = jsonReader.getTestCase(3); // {"", "Parofessorc", 13}
        performAddTest((String) data[0], (String) data[1], (int) data[2], "Invalid data - Empty ID",
                addADMPage.getIdADEmptyEM(), "id", false, false);
    }

    @Test(priority = 5)
    public void testInvalidDataDuplicateID() {
        Object[] data = jsonReader.getTestCase(4); // {"MDa001", "Another Valid Name", 2}
        performAddTest((String) data[0], (String) data[1], (int) data[2], "Invalid data - Duplicate ID",
                addADMPage.getIdADDuplicateEM(), "id", true, false);
    }

    @Test(priority = 6)
    public void testInvalidDataIDExceeds50Chars() {
        Object[] data = jsonReader.getTestCase(5); // {"AABavAAaaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "Valid Name", 1}
        performAddTest((String) data[0], (String) data[1], (int) data[2], "Invalid data - ID exceeds 50 characters",
                addADMPage.getIdADMaxLengthEM(), "id", false, false);
    }

    @Test(priority = 7)
    public void testInvalidDataIDInvalidFormat() {
        Object[] data = jsonReader.getTestCase(6); // {"MaB_002", "Valid Name", 1}
        performAddTest((String) data[0], (String) data[1], (int) data[2], "Invalid data - ID with invalid format (underscore)",
                addADMPage.getIdADInvalidFormatEM(), "id", false, true);
    }

    @Test(priority = 8)
    public void testInvalidDataEmptyName() {
        Object[] data = jsonReader.getTestCase(7); // {"MCC004", "", 2}
        performAddTest((String) data[0], (String) data[1], (int) data[2], "Invalid data - Empty Name",
                addADMPage.getNameADEmptyEM(), "name", false, false);
    }

    @Test(priority = 9)
    public void testInvalidDataNameExceeds100Chars() {
        Object[] data = jsonReader.getTestCase(8); // {"MDE005", "A very long name...", 3}
        performAddTest((String) data[0], (String) data[1], (int) data[2], "Invalid data - Name exceeds 100 characters",
                addADMPage.getNameADMaxLengthEM(), "name", false, false);
    }

    @Test(priority = 10)
    public void testInvalidDataOrderEqualsZero() {
        Object[] data = jsonReader.getTestCase(9); // {"MEC006", "Valid Name", 0}
        performAddTest((String) data[0], (String) data[1], (int) data[2], "Invalid data - Order equals 0",
                addADMPage.getOrderADMinLengthEM(), "order", false, false);
    }

    @Test(priority = 11)
    public void testInvalidDataOrderExceeds100() {
        Object[] data = jsonReader.getTestCase(10); // {"MES007", "Valid Name", 111}
        performAddTest((String) data[0], (String) data[1], (int) data[2], "Invalid data - Order exceeds 100",
                addADMPage.getOrderADMaxLengthEM(), "order", false, false);
    }

    @Test(priority = 12)
    public void testValidDataNormalOrder15() {
        Object[] data = jsonReader.getTestCase(11); // {"MEO008", "Valid Name", 15}
        performAddTest((String) data[0], (String) data[1], (int) data[2], "Valid data - Normal case with order 15", null, null, false, false);
    }
}