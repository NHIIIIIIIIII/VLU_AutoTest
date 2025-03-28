package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Notification;
import java.time.Duration;
import org.testng.Assert;

public class AddUserPage {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Notification notifiCheck;

    // Input Elements
    private static final By userIdInput = By.id("staff_id");
    private static final By userNameInput = By.xpath("(//input[@id='full_name'])[1]");
    private static final By emailInput = By.xpath("/html/body/div[3]/div[2]/form/div[3]/input");
    private static final By contractTypeDropdown = By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span[1]/span/span[1]");
    private static final By roleDropdown = By.xpath("/html/body/div[3]/div[2]/form/div[5]/div/span[1]/span/span[1]");

    // Button Elements
    private static final By userTab = By.xpath("(//span[contains(text(),'Người dùng')])[1]");
    private static final By addUserButton = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/div/div[2]/div/div[2]/button");
    private static final By saveButton = By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[2]");
    private static final By closeButton = By.xpath("//button[@class='ui-dialog-titlebar-close btn-close']");
    private static final By okErrorButton = By.xpath("//button[normalize-space()='OK']");

    // Dropdown values
    private static final By contractType_CoHuu = By.xpath("//li[normalize-space()='Cơ hữu']");
    private static final By contractType_ThinhGiang = By.xpath("//li[normalize-space()='Thỉnh giảng']");
    private static final By role_BCNKhoa = By.xpath("//li[normalize-space()='BCN khoa']");
    private static final By role_BoMon = By.xpath("//li[normalize-space()='Bộ môn']");
    private static final By role_GiangVien = By.xpath("//li[normalize-space()='Giảng viên']");

    // Error Fields
    private static final By userIdError = By.xpath("(//label[contains(text(),'Bạn chưa nhập mã giảng viên')])[1]");
    private static final By userNameError = By.xpath("/html/body[1]/div[3]/div[2]/form[1]/div[2]/label[2]");
    private static final By emailError = By.xpath("(//label[contains(text(),'Vui lòng nhập email Văn Lang hợp lệ!')])[1]");
    private static final By contractTypeError = By.xpath("/html/body[1]/div[3]/div[2]/form[1]/div[4]/div[1]/label[1]");
    private static final By roleError = By.xpath("/html/body[1]/div[3]/div[2]/form[1]/div[5]/div[1]/label[1]");

    public AddUserPage(WebDriver driver, WebDriverWait wait) {
        AddUserPage.driver = driver;
        AddUserPage.wait = wait;
        notifiCheck = new Notification(wait);
    }

    public static void addNewUserDetails(String id, String name, String email, String contract, String role) {
        clickUserTab();
        clickAddUserButton();
        enterUserId(id);
        enterUserName(name);
        enterEmail(email);
        if (!contract.isEmpty()) selectContractType(contract);
        if (!role.isEmpty()) selectRole(role);
        clickSaveButton();
    }
    public static void addNewUserDetails1(String id, String name, String email) {
        clickUserTab();
        clickAddUserButton();
        enterUserId(id);
        enterUserName(name);
        enterEmail(email);

        clickSaveButton();
    }

    public static void clickUserTab() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(userTab));
            element.click();
            System.out.println("Clicked User Tab successfully.");
        } catch (TimeoutException e) {
            System.out.println("Failed to find User Tab: " + e.getMessage());
            throw e;
        }
    }

    public static void clickAddUserButton() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addUserButton));
            element.click();
            System.out.println("Clicked Add User Button successfully.");
        } catch (TimeoutException e) {
            System.out.println("Failed to find Add User Button: " + e.getMessage());
            throw e;
        }
    }

    public static void enterUserId(String id) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(userIdInput));
            element.clear();
            element.sendKeys(id);
            System.out.println("Entered User ID: " + id);
        } catch (TimeoutException e) {
            System.out.println("Failed to find User ID input: " + e.getMessage());
            throw e;
        }
    }

    public static void enterUserName(String name) {
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

    public static void enterEmail(String email) {
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

    public static void selectContractType(String contractType) {
        try {
            WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(contractTypeDropdown));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
            dropdownElement.click();

            By optionLocator = contractType.equals("Cơ hữu") ? contractType_CoHuu : contractType_ThinhGiang;
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            option.click();
            System.out.println("Selected Contract Type: " + contractType);
        } catch (TimeoutException e) {
            System.out.println("Failed to select Contract Type: " + e.getMessage());
            throw e;
        }
    }

    public static void selectRole(String role) {
        try {
            WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
            dropdownElement.click();

            By optionLocator;
            switch (role) {
                case "BCN khoa": optionLocator = role_BCNKhoa; break;
                case "Bộ môn": optionLocator = role_BoMon; break;
                default: optionLocator = role_GiangVien;
            }
            
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            option.click();
            System.out.println("Selected Role: " + role);
        } catch (TimeoutException e) {
            System.out.println("Failed to select Role: " + e.getMessage());
            throw e;
        }
    }

    public static void clickSaveButton() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
            element.click();
            System.out.println("Clicked Save Button successfully.");
        } catch (TimeoutException e) {
            System.out.println("Failed to find Save Button: " + e.getMessage());
            throw e;
        }
    }

    public static void clickCloseButton() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(closeButton));
            element.click();
            System.out.println("Clicked Close Button successfully.");
        } catch (TimeoutException e) {
            System.out.println("Failed to find Close Button: " + e.getMessage());
            throw e;
        }
    }

    public static void checkSuccessNotification() {
        notifiCheck.testAddNotification();
    }

    public static void checkUserIdError(String expectedMessage) {
        WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(userIdError));
        Assert.assertEquals(errorElement.getText(), expectedMessage);
    }

    public static void checkUserNameError(String expectedMessage) {
        WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(userNameError));
        Assert.assertEquals(errorElement.getText(), expectedMessage);
    }

    public static void checkEmailError(String expectedMessage) {
        WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(emailError));
        Assert.assertEquals(errorElement.getText(), expectedMessage);
    }

    public static void checkContractTypeError(String expectedMessage) {
        WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(contractTypeError));
        Assert.assertEquals(errorElement.getText(), expectedMessage);
    }

    public static void checkRoleError(String expectedMessage) {
        WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(roleError));
        Assert.assertEquals(errorElement.getText(), expectedMessage);
    }
}