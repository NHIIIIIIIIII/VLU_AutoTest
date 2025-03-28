package pages;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Notification;

import java.time.Duration;

public class AddADMPage extends BaseTest {
    // Instance variables
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Notification notification;

    // Locators for Button Elements
    private final By addADButton = By.xpath("//button[@class='dt-button createNew btn btn-primary']");
    private final By saveADButton = By.xpath("//button[contains(text(),'Lưu')]");
    private final By okADButton = By.xpath("//button[normalize-space()='OK']");
    private final By closeADButton = By.xpath("//button[@id='btnClose']");
    // Locators for Input Elements
    private final By codeADInput = By.xpath("//input[@id='id']");
    private final By nameADInput = By.xpath("//input[@id='name']");
    private final By orderADInput = By.xpath("//input[@id='level']");

    // Locators for Field Errors
    private final By adIdError = By.id("id-error");
    private final By adNameError = By.id("name-error");
    private final By adOrderError = By.id("level-error");

    // Locator for Dialog Error Element
    private final By dialogErrorField = By.id("swal2-html-container");

    // Error Messages
    private final String idADEmptyEM = "Bạn chưa nhập mã học hàm, học vị";
    private final String idADDuplicateEM = "Mã học hàm, học vị này đã tồn tại!";
    private final String idADMaxLengthEM = "Tối đa 50 kí tự được cho phép";
    private final String idADInvalidFormatEM = "Chỉ được nhập số-chữ không dấu và không có khoảng trắng!";
    private final String nameADEmptyEM = "Bạn chưa nhập tên học hàm, học vị";
    private final String nameADMaxLengthEM = "Tối đa 100 kí tự được cho phép";
    private final String orderADEmptyEM = "Bạn chưa nhập thứ tự";
    private final String orderADMaxLengthEM = "Vui lòng nhập nhỏ hơn hoặc bằng 100";
    private final String orderADMinLengthEM = "Vui lòng nhập lớn hơn hoặc bằng 1";

    // Constructor
    public AddADMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.notification = new Notification(this.wait);
    }

    // Getters for Elements
    public Notification getNotification() { return notification; }

    // Getters for Error Messages
    public String getIdADEmptyEM() { return idADEmptyEM; }
    public String getIdADDuplicateEM() { return idADDuplicateEM; }
    public String getIdADMaxLengthEM() { return idADMaxLengthEM; }
    public String getIdADInvalidFormatEM() { return idADInvalidFormatEM; }
    public String getNameADEmptyEM() { return nameADEmptyEM; }
    public String getNameADMaxLengthEM() { return nameADMaxLengthEM; }
    public String getOrderADEmptyEM() { return orderADEmptyEM; }
    public String getOrderADMaxLengthEM() { return orderADMaxLengthEM; }
    public String getOrderADMinLengthEM() { return orderADMinLengthEM; }

    // Action Methods
    public void clickAddADButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addADButton)).click();
    }

    public void clickSaveADButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveADButton)).click();
    }

    public void clickOkADButton() {
        wait.until(ExpectedConditions.elementToBeClickable(okADButton)).click();
    }

    public void clickCloseADButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeADButton)).click();
    }

    public void enterCodeAD(String code) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(codeADInput));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, code);
    }

    public void enterNameAD(String name) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(nameADInput));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, name);
    }

    public void enterOrderAD(String order) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(orderADInput));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, order);
    }
    public void clearOrderAD() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(orderADInput));
        element.clear();
    }

    // Utility Methods
    public WebElement checkErrorDialogDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(dialogErrorField));
        } catch (Exception e) {
            return null;
        }
    }

    public String getTextADIdError() {
        sleep(3);
        return wait.until(ExpectedConditions.presenceOfElementLocated(adIdError)).getText();
    }

    public String getTextADNameError() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(adNameError)).getText();
    }

    public String getTextADOrderError() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(adOrderError)).getText();
    }

    // Error Checking Methods

//
//    public boolean hasOrderADInvalidFormatError() {
//        WebElement dialog = checkErrorDialogDisplayed();
//        return dialog != null && getDialogErrorText().equals(orderADInvalidFormatEM);
//    }
}