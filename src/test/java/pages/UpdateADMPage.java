package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Notification;

import java.time.Duration;

public class UpdateADMPage {
    // Instance variables
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Notification notification;

    // Locators for Button Elements
    private final By editADButton = By.xpath("//tbody/tr[1]/td[5]/a[1]");
    private final By saveADButton = By.xpath("//button[contains(text(),'Lưu')]");
    private final By okADButton = By.xpath("//button[normalize-space()='OK']");
    private final By exitADButton = By.xpath("//button[@class='ui-dialog-titlebar-close btn-close']");

    // Locators for Input Elements
    private final By nameADInput = By.xpath("//input[@id='name']");
    private final By orderADInput = By.xpath("//input[@id='level']");

    // Locators for Field Errors
    private final By nameADMError = By.id("name-error");
    private final By orderADMError = By.id("level-error");

    // Error Messages
    private final String idADDuplicateEM = "Mã học hàm, học vị này đã tồn tại!";
    private final String nameADEmptyEM = "Bạn chưa nhập tên học hàm, học vị";
    private final String nameADMaxLengthEM = "Tối đa 100 kí tự được cho phép";
    private final String orderADEmptyEM = "Bạn chưa nhập thứ tự";
    private final String orderADMaxLengthEM = "Vui lòng nhập nhỏ hơn hoặc bằng 100";
    private final String orderADMinLengthEM = "Vui lòng nhập lớn hơn hoặc bằng 1";

    // Constructor
    public UpdateADMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.notification = new Notification(this.wait);
    }

    // Getters for Elements
    public Notification getNotification() { return notification; }

    // Getters for Error Messages
    public String getNameADEmptyEM() { return nameADEmptyEM; }
    public String getNameADMaxLengthEM() { return nameADMaxLengthEM; }
    public String getOrderADEmptyEM() { return orderADEmptyEM; }
    public String getOrderADMaxLengthEM() { return orderADMaxLengthEM; }
    public String getOrderADMinLengthEM() { return orderADMinLengthEM; }

    // Action Methods
    public void clickEditADButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editADButton)).click();
    }

    public void clickSaveADButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveADButton)).click();
    }

    public void clickExitADButton() {
        wait.until(ExpectedConditions.elementToBeClickable(exitADButton)).click();
    }

    public void enterNameAD(String name) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(nameADInput));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, name);
    }

    public void enterOrderAD(String order) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(orderADInput));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, order);
    }

    // Utility Methods

    public String getTextADMNameError() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(nameADMError)).getText();
    }

    public String getTextADMOderError() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(orderADMError)).getText();
    }
}