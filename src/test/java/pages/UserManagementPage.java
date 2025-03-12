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
    private final By userIdInput = By.id("staff_id");
    private final By userNameInput = By.xpath("(//input[@id='full_name'])[1]");
    private final By emailInput = By.xpath("/html/body/div[3]/div[2]/form/div[3]/input");
    private final By contractTypeDropdown = By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span[1]/span/span[1]");
    private final By roleDropdown = By.xpath("/html/body/div[3]/div[2]/form/div[5]/div/span[1]/span/span[1]");

    // Button Elements
    private final By userTab = By.xpath("(//span[contains(text(),'Người dùng')])[1]");
    private final By addUserButton = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/div/div[2]/div/div[2]/button");
    private final By saveButton = By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[2]");
    private final By closeButton = By.xpath("//button[@class='ui-dialog-titlebar-close btn-close']");
    private final By okErrorButton = By.xpath("//button[normalize-space()='OK']");
    private final By UpdateButton = By.xpath("(//a[@title='Chỉnh sửa'])[1]");
    private final By saveButtonup = By.xpath("(//button[contains(text(),'Lưu')])[1]");

    // Dropdown values
    private final By contractType_CoHuu = By.xpath("//li[normalize-space()='Cơ hữu']");
    private final By contractType_ThinhGiang = By.xpath("//li[normalize-space()='Thỉnh giảng']");
    private final By role_BCNKhoa = By.xpath("//li[normalize-space()='BCN khoa']");
    private final By role_BoMon = By.xpath("//li[normalize-space()='Bộ môn']");
    private final By role_GiangVien = By.xpath("//li[normalize-space()='Giảng viên']");

    // Error Fields
    private final By userIdError = By.xpath("(//label[contains(text(),'Bạn chưa nhập mã giảng viên')])[1]");
    private final By userIdError1 = By.xpath("(//label[contains(text(),'Chỉ được nhập số-chữ không dấu và không có khoảng ')])[1]");
    private final By userNameError = By.xpath("/html/body[1]/div[3]/div[2]/form[1]/div[2]/label[2]");
    private final By emailError = By.xpath("(//label[contains(text(),'Vui lòng nhập email Văn Lang hợp lệ!')])[1]");
    private final By contractTypeError = By.xpath("/html/body[1]/div[3]/div[2]/form[1]/div[4]/div[1]/label[1]");
    private final By roleError = By.xpath("/html/body[1]/div[3]/div[2]/form[1]/div[5]/div[1]/label[1]");
    private final By dialogErrorField = By.id("swal2-html-container");

    // Search User
    private final By findduser = By.xpath("(//input[@placeholder='Nhập tìm kiếm...'])[1]");

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
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(userTab));
            element.click();
            System.out.println("Clicked User Tab successfully.");
        } catch (TimeoutException e) {
            System.out.println("Failed to find User Tab: " + e.getMessage());
            throw e;
        }
    }

    // Click on the Add User button
    public void clickAddUserButton() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addUserButton));
            element.click();
            System.out.println("Clicked Add User Button successfully.");
        } catch (TimeoutException e) {
            System.out.println("Failed to find Add User Button: " + e.getMessage());
            throw e;
        }
    }

    // Click Update Button
    public void clickUpdateButton() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(UpdateButton));
            element.click();
            System.out.println("Clicked Update Button successfully.");
            // Chờ form cập nhật hiển thị bằng cách chờ userIdInput xuất hiện
            wait.until(ExpectedConditions.visibilityOfElementLocated(userIdInput));
            System.out.println("Update form displayed successfully (userIdInput is visible).");
        } catch (TimeoutException e) {
            System.out.println("Failed to find Update Button or form did not display: " + e.getMessage());
            throw e;
        }
    }

    // Click Save Button in Update Form
    public void clickSaveButtonup() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(saveButtonup));
            element.click();
            System.out.println("Clicked Save Button (Update) successfully.");
        } catch (TimeoutException e) {
            System.out.println("Failed to find Save Button (Update): " + e.getMessage());
            throw e;
        }
    }

    // Enter User ID
    public void enterUserId(String id) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(userIdInput));
            element.clear();
            element.sendKeys(id);
            System.out.println("Entered User ID: " + id);
        } catch (TimeoutException e) {
            System.out.println("Failed to find User ID input with locator " + userIdInput + ": " + e.getMessage());
            throw new NoSuchElementException("User ID input not found. Check locator: " + userIdInput, e);
        }
    }

    // Get User ID value
    public String getUserIdValue() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(userIdInput));
            String value = element.getAttribute("value");
            System.out.println("Got User ID value: " + value);
            return value;
        } catch (TimeoutException e) {
            System.out.println("Failed to get User ID value with locator " + userIdInput + ": " + e.getMessage());
            throw new NoSuchElementException("User ID input not found. Check locator: " + userIdInput, e);
        }
    }

    // Enter User Name
    public void enterUserName(String name) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(userNameInput));
            element.clear();
            element.sendKeys(name);
            System.out.println("Entered User Name: " + name);
        } catch (TimeoutException e) {
            System.out.println("Failed to find User Name input: " + e.getMessage());
            throw e;
        }
    }

    // Enter Email
    public void enterEmail(String email) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
            element.clear();
            element.sendKeys(email);
            System.out.println("Entered Email: " + email);
        } catch (TimeoutException e) {
            System.out.println("Failed to find Email input: " + e.getMessage());
            throw e;
        }
    }

    // Get Email value
    public String getEmailValue() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
            String value = element.getAttribute("value");
            System.out.println("Got Email value: " + value);
            return value;
        } catch (TimeoutException e) {
            System.out.println("Failed to get Email value: " + e.getMessage());
            throw e;
        }
    }

    // Enter Search Query
    public void enterSearchQuery(String query) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(findduser));
            element.clear();
            element.sendKeys(query);
            System.out.println("Entered Search Query: " + query);
        } catch (TimeoutException e) {
            System.out.println("Failed to find Search input: " + e.getMessage());
            throw e;
        }
    }

    // Select Contract Type
    public void selectContractType(String contractType) {
        try {
            WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(contractTypeDropdown));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
            dropdownElement.click();

            if (contractType.equalsIgnoreCase("Cơ hữu")) {
                WebElement option = wait.until(ExpectedConditions.elementToBeClickable(contractType_CoHuu));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
                option.click();
            } else if (contractType.equalsIgnoreCase("Thỉnh giảng")) {
                WebElement option = wait.until(ExpectedConditions.elementToBeClickable(contractType_ThinhGiang));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
                option.click();
            }
            System.out.println("Selected Contract Type: " + contractType);
        } catch (TimeoutException e) {
            System.out.println("Failed to find or select Contract Type: " + e.getMessage());
            throw e;
        }
    }

    // Select Role
    public void selectRole(String role) {
        try {
            WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
            dropdownElement.click();

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
            System.out.println("Selected Role: " + role);
        } catch (TimeoutException e) {
            System.out.println("Failed to find or select Role: " + e.getMessage());
            throw e;
        }
    }

    // Click Save Button
    public void clickSaveButton() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
            element.click();
            System.out.println("Clicked Save Button successfully.");
        } catch (TimeoutException e) {
            System.out.println("Failed to find Save Button: " + e.getMessage());
            throw e;
        }
    }

    // Click Close Button
    public void clickCloseButton() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(closeButton));
            element.click();
            System.out.println("Clicked Close Button (Exit) successfully.");
        } catch (TimeoutException e) {
            System.out.println("Failed to find Close Button (Exit): " + e.getMessage());
            throw e;
        }
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
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(okErrorButton));
            element.click();
            System.out.println("Clicked OK on error dialog successfully.");
        } catch (TimeoutException e) {
            System.out.println("Failed to find OK Button on error dialog: " + e.getMessage());
            throw e;
        }
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

    // Check success notification using notifiCheck (similar to MajorManagementPage)
    public void checkSuccessNotification() {
        try {

            notifiCheck.checkAddNotification(); // Giả định phương thức này kiểm tra thông báo thành công
            System.out.println("Success notification checked successfully using notifiCheck.");
        } catch (Exception e) {
            System.out.println("Failed to check success notification: " + e.getMessage());
            throw new RuntimeException("Error while checking success notification", e);
        }
    }

    // Check User ID Error
    public void checkUserIdError(String expectedMessage) {
        try {
            checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(userIdError1)), expectedMessage);
        } catch (TimeoutException e) {
            System.out.println("Failed to find User ID Error: " + e.getMessage());
            throw e;
        }
    }

    // Check User Name Error
    public void checkUserNameError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(userNameError)), expectedMessage);
    }

    // Check Email Error
    public void checkEmailError(String expectedMessage) {
        try {
            checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(emailError)), expectedMessage);
        } catch (TimeoutException e) {
            System.out.println("Failed to find Email Error: " + e.getMessage());
            throw e;
        }
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