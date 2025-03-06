package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Notification;
import java.time.Duration;
import org.testng.Assert;

public class UserManagementPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Notification notifiCheck;

    // Input Elements
    private final By userIdInput = By.xpath("/html/body/div[3]/div[2]/form/div[1]/input");
    private final By userNameInput = By.xpath("/html/body/div[3]/div[2]/form/div[2]/input");
    private final By emailInput = By.xpath("/html/body/div[3]/div[2]/form/div[3]/input");
    private final By contractTypeDropdown = By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span[1]/span/span[1]");
    private final By roleDropdown = By.xpath("/html/body/div[3]/div[2]/form/div[5]/div/span[1]/span/span[1]");

    // Button Elements
    private final By userTab = By.xpath("(//span[contains(text(),'Người dùng')])[1]");
    private final By addUserButton = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/div/div[2]/div/div[2]/button");
    private final By saveButton = By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[2]");
    private final By closeButton = By.xpath("//button[@class='ui-dialog-titlebar-close btn-close']");
    private final By okErrorButton = By.xpath("//button[normalize-space()='OK']");

    // Dropdown values
    private final By contractType_CoHuu = By.xpath("//li[normalize-space()='Cơ hữu']");
    private final By contractType_ThinhGiang = By.xpath("//li[normalize-space()='Thỉnh giảng']");
    private final By role_BCNKhoa = By.xpath("//li[normalize-space()='BCN khoa']");
    private final By role_BoMon = By.xpath("//li[normalize-space()='Bộ môn']");
    private final By role_GiangVien = By.xpath("//li[normalize-space()='Giảng viên']");

    // Success Notification
    private final By successNotification = By.xpath("/html[1]/body[1]/span[1]");

    // Error Fields
    private final By userIdError = By.xpath("/html[1]/body[1]/div[3]/div[2]/form[1]/div[1]/label[2]");
    private final By userNameError = By.xpath("/html[1]/body[1]/div[3]/div[2]/form[1]/div[2]/label[2]");
    private final By emailError = By.xpath("/html[1]/body[1]/div[3]/div[2]/form[1]/div[3]/label[2]");
    private final By contractTypeError = By.xpath("/html[1]/body[1]/div[3]/div[2]/form[1]/div[4]/div[1]/label[1]");
    private final By roleError = By.xpath("/html[1]/body[1]/div[3]/div[2]/form[1]/div[5]/div[1]/label[1]");
    private final By dialogErrorField = By.id("swal2-html-container");

    // Error Messages
    private final String userIdEmptyErrorMessage = "Bạn chưa nhập mã giảng viên";
    private final String userNameEmptyErrorMessage = "Bạn chưa nhập tên giảng viên";
    private final String emailInvalidErrorMessage = "Vui lòng nhập email Văn Lang hợp lệ!";
    private final String contractTypeEmptyErrorMessage = "Bạn chưa chọn loại giảng viên";
    private final String roleEmptyErrorMessage = "Bạn chưa chọn role";
    private final String duplicateUserIdErrorMessage = "Mã người dùng này đã tồn tại!";

    // Constructor
    public UserManagementPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        notifiCheck = new Notification(this.wait);
    }

    // Click on the User tab
    public void clickUserTab() {
        wait.until(ExpectedConditions.elementToBeClickable(userTab)).click();
    }

    // Click on the Add User button
    public void clickAddUserButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addUserButton)).click();
    }

    // Enter User ID
    public void enterUserId(String id) {
        wait.until(ExpectedConditions.elementToBeClickable(userIdInput)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, id);
    }

    // Enter User Name
    public void enterUserName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(userNameInput)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, name);
    }

    // Enter Email
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, email);
    }

    // Select Contract Type
    public void selectContractType(String contractType) {
        // Ensure the dropdown is clickable and opened
        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(contractTypeDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
        dropdownElement.click();

        // Wait for the dropdown options to be visible and select the appropriate option
        if (contractType.equalsIgnoreCase("Cơ hữu")) {
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(contractType_CoHuu));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            option.click();
        } else if (contractType.equalsIgnoreCase("Thỉnh giảng")) {
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(contractType_ThinhGiang));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            option.click();
        }
    }

    // Select Role
    public void selectRole(String role) {
        // Ensure the dropdown is clickable and opened
        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
        dropdownElement.click();

        // Wait for the dropdown options to be visible and select the appropriate option
        if (role.equalsIgnoreCase("BCN khoa")) {
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(role_BCNKhoa));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            option.click();
        } else if (role.equalsIgnoreCase("Bộ môn")) {
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(role_BoMon));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            option.click();
        } else if (role.equalsIgnoreCase("Giảng viên")) {
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(role_GiangVien));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            option.click();
        }
    }

    // Click Save Button
    public void clickSaveButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        } catch (Exception e) {
            Assert.fail("Save button not interacted");
        }
    }

    // Click Close Button
    public void clickCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }

    // Check if dialog is displayed
    public WebElement checkDialogDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement dialogElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(dialogErrorField));
            System.out.println("Dialog is displayed");
            return dialogElement;
        } catch (TimeoutException timeEx) {
            System.out.println("No error dialog displayed, proceeding to check success notification...");
            return null;
        }
    }

    // Click OK on error dialog
    public void clickOkErrorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(okErrorButton)).click();
    }

    // Check error message
    public void checkErrorMessage(WebElement errorElement, String errorMessage) {
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + errorElement.getText());
        System.out.println("Expect : " + errorMessage);
        Assert.assertEquals(errorElement.getText(), errorMessage, "Error Message not equal");
        System.out.println("==========================================");
    }

    // Check success notification
    public void checkSuccessNotification() {
        WebElement successElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successNotification));
        Assert.assertTrue(successElement.isDisplayed(), "Success notification not displayed");
        System.out.println("Success Notification Displayed: " + successElement.getText());
    }

    // Check User ID Error
    public void checkUserIdError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(userIdError)), expectedMessage);
    }

    // Check User Name Error
    public void checkUserNameError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(userNameError)), expectedMessage);
    }

    // Check Email Error
    public void checkEmailError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(emailError)), expectedMessage);
    }

    // Check Contract Type Error
    public void checkContractTypeError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(contractTypeError)), expectedMessage);
    }

    // Check Role Error
    public void checkRoleError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(roleError)), expectedMessage);
    }

    // Check Dialog Error
    public void checkDialogError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(dialogErrorField)), expectedMessage);
    }

    // Validate email format (must end with @vanlanguni.vn)
    public boolean checkEmailValid(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@vanlanguni\\.vn$")) {
            System.out.println("Email does not end with @vanlanguni.vn or invalid format");
            checkEmailError(emailInvalidErrorMessage);
            return false;
        }
        return true;
    }

    // Add new user details
    public void addNewUserDetails(String id, String name, String email, String contract, String role) {
        clickUserTab();
        clickAddUserButton();
        enterUserId(id);
        enterUserName(name);
        enterEmail(email);
        if (!contract.isEmpty()) selectContractType(contract);
        if (!role.isEmpty()) selectRole(role);
        clickSaveButton();
    }

    // Add new user and handle multiple cases
    public void addUserWithMultiCase(String id, String name, String email, String contract, String role) {
        clickUserTab();
        clickAddUserButton();
        enterUserId(id);
        enterUserName(name);
        enterEmail(email);
        if (!contract.isEmpty()) selectContractType(contract);
        if (!role.isEmpty()) selectRole(role);
        clickSaveButton();

        WebElement dialog = checkDialogDisplayed();
        if (dialog == null) {
            // No error dialog, check if input is valid and success notification is displayed
            if (!id.isEmpty() && !name.isEmpty() && checkEmailValid(email) && !contract.isEmpty() && !role.isEmpty()) {
                checkSuccessNotification();
                clickCloseButton();
            }
        } else {
            // Handle duplicate ID or other dialog errors
            checkDialogError(duplicateUserIdErrorMessage);
            clickOkErrorButton();
        }
    }
}