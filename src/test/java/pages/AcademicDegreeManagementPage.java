package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.Notification;

import java.time.Duration;

public class AcademicDegreeManagementPage {
    private final WebDriver driver;
    private final WebDriverWait wait; // Thêm WebDriverWait để sử dụng cơ chế chờ
    private Notification notification;

    // Input Element
    private final By codeADInput = By.xpath("//input[@id='id']");
    private final By nameADInput = By.xpath("//input[@id='name']");
    private final By orderADInput = By.xpath("//input[@id='level']");

    // Btn Element
    private final By openADButton = By.xpath("//button[@class='dt-button createNew btn btn-primary']");
    private final By saveADButton = By.xpath("//button[contains(text(),'Lưu')]");
    private final By closeADButton = By.id("btnClose");
    private final By okADButton = By.xpath("//button[normalize-space()='OK']");

    // Field Error
    public final By adIdError = By.id("id-error");
    public final By adNameError  = By.id("name-error");
    public final By adOrderError  = By.id("level-error");

    // AD Id Message Error (Error message = EM)
    private final String idADEmptyEM = "Bạn chưa nhập mã học hàm, học vị";
    private final String idADDuplicateEM = "Mã học hàm, học vị này đã tồn tại!";  // Hiển thị trên dialog
    private final String idADMaxLengthEM = "Tối đa 50 kí tự được cho phép";
    private final String idADInvalidFormatEM = "Chỉ được nhập số-chữ không dấu và không có khoảng trắng!";

    // AD Name Message Error (Error message = EM)
    private final String nameADEmptyEM = "Bạn chưa nhập tên học hàm, học vị";
    private final String nameADMaxLengthEM = "Tối đa 100 kí tự được cho phép";

    // AD Name Message Error (Error message = EM)
    private final String orderADEmptyEM = "Bạn chưa nhập thứ tự";
    private final String orderADMaxLengthEM = "Vui lòng nhập nhỏ hơn hoặc bằng 100";
    private final String orderADMinLengthEM = "Vui lòng nhập lớn hơn hoặc bằng 1";

    // Dialog Error Message
    private final By dialogErrorField = By.id("swal2-html-container");
    // Order AD Invalid Format Message Error
    private final String orderADInvalidFormatEM = "Thứ tự phải là một số hợp lệ!";


    public AcademicDegreeManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Khởi tạo wait với timeout 10 giây
        notification = new Notification(this.wait);
    }

    /**
     * Điều hướng đến trang Academic Degree Management
     */
    public void navigateToADPage() {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/AcademicDegree");
        // Chờ cho trang tải xong bằng cách kiểm tra một phần tử chính (ví dụ: openADButton)
        wait.until(ExpectedConditions.visibilityOfElementLocated(openADButton));
    }

    /**
     * Clicks on the Academic Degree.
     * @Caution It will show Academic Degree Management Default
     */
    public void clickOpenAD() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(openADButton));
        button.click();
    }

    public void EnterCodeADInput(String id) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(codeADInput));
        input.sendKeys(id);
    }

    public void EnterNameADInput(String name) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(nameADInput));
        input.sendKeys(name);
    }

    public void EnterOrderADInput(int order) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(orderADInput));
        input.clear();
        input.sendKeys(String.valueOf(order));
    }

    /**
     * Click add, close Academic Degree Button
     */
    public void clickSaveAD() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveADButton));
        saveBtn.submit();
    }

    public void clickCloseAD() {
        WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(closeADButton));
        closeBtn.click();
    }

    public void clickOkErrorAD() {
        WebElement clickOkErrorBtn = wait.until(ExpectedConditions.elementToBeClickable(okADButton));
        clickOkErrorBtn.click();
    }

    /**
     * Check toast-message
     */
    public void notificationAdd() {
        notification.checkAddNotification();
    }

    public void checkErrorMessage(WebElement errorElement, String errorMessage) {
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + errorElement.getText());
        System.out.println("Expect : " + errorMessage);
        Assert.assertEquals(errorElement.getText(), errorMessage, "Error Message not equal");
        System.out.println("==========================================");
    }

    public void checkADIdError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(adIdError)), expectedMessage);
    }

    public void checkADNameError(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Thay đổi thời gian chờ ở đây
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(adNameError)), expectedMessage);
    }

    public void checkADOrderError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(adOrderError)), expectedMessage);
    }

    public void checkDialogError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(dialogErrorField)), expectedMessage);
    }

    public WebElement checkDialogDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement dialogElement =  shortWait.until(ExpectedConditions.visibilityOfElementLocated(dialogErrorField));
            System.out.println("Dialog is displayed");
            return dialogElement;
        } catch (TimeoutException timeEx) {
            return null;
        }
    }

    /**
     * Kiểm tra hợp lệ Academic Degree Id
     * @param-id
     * @return boolean
     */
    public boolean checkADIdValid(String id) {
        if (id.isEmpty()) {
            System.out.println("Academic Degree ID Empty");
            checkADIdError(idADEmptyEM);
            return false;
        } else if (id.length() > 50) {
            System.out.println("Academic Degree ID > 50");
            checkADIdError(idADMaxLengthEM);
            return false;
        } else if (!id.matches("^[a-zA-Z0-9_-]{1,50}$")) {
            System.out.println("Academic Degree ID Invalid Format");
            checkADIdError(idADInvalidFormatEM);
            return false;
        } else return true;
    }

    /**
     * Kiểm tra hợp lệ Academic Degree Name
     * @param-name
     * @return boolean
     */
    public boolean checkADNameValid(String name) {
        if (name.isEmpty()) {
            System.out.println("Academic Degree name Empty");
            checkADNameError(nameADEmptyEM);
            return false;

        } else if (name.length() > 100) {
            System.out.println("Academic Degree name > 100");
            checkADNameError(nameADMaxLengthEM);
            return false;

        } else return true;
    }

    public boolean checkADOrderValid(int order) {
        // Kiểm tra nếu order < 1 (thứ tự không hợp lệ)
        if (order < 1) {
            System.out.println("Academic Degree order must be greater than or equal to 1.");
            checkADOrderError(orderADMinLengthEM);  // Check error for the order field
            return false;  // Kết thúc kiểm thử nếu lỗi
        }

        // Kiểm tra nếu order > 100
        if (order > 100) {
            System.out.println("Academic Degree order cannot be greater than 100.");
            checkADOrderError(orderADMaxLengthEM);  // Check error for the order field
            return false;
        }

        else return true;
    }

    public void addNewADDetails(String id, String name, int order) {
        navigateToADPage();
        clickOpenAD();
        EnterCodeADInput(id);
        EnterNameADInput(name);
        EnterOrderADInput(order);
        clickSaveAD();

        if (checkDialogDisplayed() == null) {
            // Sửa lại đây để gọi đúng phương thức với các tham số đúng
            if (checkADIdValid(id) && checkADNameValid(name) && checkADOrderValid(order)) {
                notification.checkAddNotification();
            }
        } else {
            checkDialogError(idADDuplicateEM);
            clickOkErrorAD();
        }
    }
}
