package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Notification;

import java.time.Duration;

public class AcademicDegreeManagementPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Notification notification;

    // Input Elements
    private final By codeADInput = By.xpath("//input[@id='id']");
    private final By nameADInput = By.xpath("//input[@id='name']");
    private final By orderADInput = By.xpath("//input[@id='level']");

    // Button Elements
    private final By openADButton = By.xpath("//button[@class='dt-button createNew btn btn-primary']");
    private final By saveADButton = By.xpath("//button[contains(text(),'Lưu')]");
    private final By closeADButton = By.id("btnClose");
    private final By okADButton = By.xpath("//button[normalize-space()='OK']");
    private final By editADButton = By.xpath("//tbody/tr[1]/td[5]/a[1]/i[1]");

    // Field Error Elements
    public final By adIdError = By.id("id-error");
    public final By adNameError = By.id("name-error");
    public final By adOrderError = By.id("level-error");

    // Error Messages
    // AD Id Error Messages
    private final String idADEmptyEM = "Bạn chưa nhập mã học hàm, học vị";
    private final String idADDuplicateEM = "Mã học hàm, học vị này đã tồn tại!";
    private final String idADMaxLengthEM = "Tối đa 50 kí tự được cho phép";
    private final String idADInvalidFormatEM = "Chỉ được nhập số-chữ không dấu và không có khoảng trắng!";

    // AD Name Error Messages
    private final String nameADEmptyEM = "Bạn chưa nhập tên học hàm, học vị";
    private final String nameADMaxLengthEM = "Tối đa 100 kí tự được cho phép";

    // AD Order Error Messages
    private final String orderADEmptyEM = "Bạn chưa nhập thứ tự";
    private final String orderADMaxLengthEM = "Vui lòng nhập nhỏ hơn hoặc bằng 100";
    private final String orderADMinLengthEM = "Vui lòng nhập lớn hơn hoặc bằng 1";
    private final String orderADInvalidFormatEM = "Thứ tự phải là một số hợp lệ!";

    // Dialog Error Element
    private final By dialogErrorField = By.id("swal2-html-container");

    public AcademicDegreeManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.notification = new Notification(this.wait);
    }

    /**
     * Navigate to Academic Degree Management page
     */
    public void navigateToADPage() {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/AcademicDegree");
        wait.until(ExpectedConditions.elementToBeClickable(openADButton));
    }

    // Input methods
    public void enterCodeADInput(String id) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(codeADInput));
        input.clear();
        input.sendKeys(id);
    }

    public void enterNameADInput(String name) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(nameADInput));
        input.clear();
        input.sendKeys(name);
    }

    public void enterOrderADInput(int order) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(orderADInput));
        input.clear();
        input.sendKeys(String.valueOf(order));
    }

    // Button click methods
    public void clickOpenAD() {
        wait.until(ExpectedConditions.elementToBeClickable(openADButton)).click();
    }

    public void clickSaveAD() {
        wait.until(ExpectedConditions.elementToBeClickable(saveADButton)).click();
    }

    public void clickCloseAD() {
        wait.until(ExpectedConditions.elementToBeClickable(closeADButton)).click();
    }

    public void clickOkErrorAD() {
        wait.until(ExpectedConditions.elementToBeClickable(okADButton)).click();
    }

    public void clickEditOpenAD() {
        wait.until(ExpectedConditions.elementToBeClickable(editADButton)).click();
    }

    // Notification methods
    public void notificationAdd() {
        notification.checkAddNotification();
    }
    /**
     * Validation message checking methods
     */

//    public void checkSuccessMessage(WebElement messageElement, String expectedMessage, String messageType) {
//        System.out.println("==========================================");
//        System.out.println("Check " + messageType + " : ");
//        System.out.println("Actual : " + messageElement.getText());
//        System.out.println("Expect : " + expectedMessage);
//        Assert.assertEquals(messageElement.getText(), expectedMessage, messageType + " Message not equal");
//        System.out.println("==========================================");
//    }

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
            WebElement dialogElement = shortWait.until(ExpectedConditions.elementToBeClickable(dialogErrorField));
            System.out.println("Dialog is displayed");
            return dialogElement;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Validation methods
    public boolean checkADIdValid(String id) {
        if (id.isEmpty()) {
            System.out.println("Academic Degree ID Empty");
            checkADIdError(idADEmptyEM);
            return false;
        }
        if (id.length() > 50) {
            System.out.println("Academic Degree ID > 50");
            checkADIdError(idADMaxLengthEM);
            return false;
        }
        if (!id.matches("^[a-zA-Z0-9_-]{1,50}$")) {
            System.out.println("Academic Degree ID Invalid Format");
            checkADIdError(idADInvalidFormatEM);
            return false;
        }
        return true;
    }

    public boolean checkADNameValid(String name) {
        if (name.isEmpty()) {
            System.out.println("Academic Degree name Empty");
            checkADNameError(nameADEmptyEM);
            return false;
        }
        if (name.length() > 100) {
            System.out.println("Academic Degree name > 100");
            checkADNameError(nameADMaxLengthEM);
            return false;
        }
        return true;
    }

    public boolean checkADOrderValid(int order) {
        if (order < 1) {
            System.out.println("Academic Degree order must be greater than or equal to 1");
            checkADOrderError(orderADMinLengthEM);
            return false;
        }
        if (order > 100) {
            System.out.println("Academic Degree order cannot be greater than 100");
            checkADOrderError(orderADMaxLengthEM);
            return false;
        }
        return true;
    }

    // Main operation methods
    public void addNewADDetails(String id, String name, int order) {
        navigateToADPage();
        clickOpenAD();
        enterCodeADInput(id);
        enterNameADInput(name);
        enterOrderADInput(order);
        clickSaveAD();

        WebElement dialog = checkDialogDisplayed();
        if (dialog == null) {
            if (checkADIdValid(id) && checkADNameValid(name) && checkADOrderValid(order)) {
                notification.checkAddNotification();
            }
        } else {
            checkDialogError(idADDuplicateEM);
            clickOkErrorAD();
        }
    }

    public void editADDetails(String name, int order) {
        navigateToADPage();
        clickEditOpenAD();
        enterNameADInput(name);
        enterOrderADInput(order);
        clickSaveAD();

        WebElement dialog = checkDialogDisplayed();
        if (dialog == null) {
            if (checkADNameValid(name) && checkADOrderValid(order)) {
                notification.checkUpdateNotification();
            }
        } else {
            checkDialogError(idADDuplicateEM);
            clickOkErrorAD();
        }
    }

}