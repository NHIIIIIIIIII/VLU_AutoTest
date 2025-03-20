package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Notification;

import java.time.Duration;

public class UpdateADMPage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Notification notification;

    // Button Elements
    protected final By editADButton = By.xpath("//button[@class='dt-button buttons-edit btn btn-icon btn-warning me-1']"); // Giả định XPath cho nút Edit
    protected final By saveADButton = By.xpath("//button[contains(text(),'Lưu')]");
    protected final By okADButton = By.xpath("//button[normalize-space()='OK']");

    // Input Elements
    protected final By nameADInput = By.xpath("//input[@id='name']");
    protected final By orderADInput = By.xpath("//input[@id='level']");

    // Dialog Error Element
    protected final By dialogErrorField = By.id("swal2-html-container");

    // Error Messages
    protected final String idADDuplicateEM = "Mã học hàm, học vị này đã tồn tại!";
    protected final String nameADEmptyEM = "Bạn chưa nhập tên học hàm, học vị";
    protected final String nameADMaxLengthEM = "Tối đa 100 kí tự được cho phép";
    protected final String orderADEmptyEM = "Bạn chưa nhập thứ tự";
    protected final String orderADMaxLengthEM = "Vui lòng nhập nhỏ hơn hoặc bằng 100";
    protected final String orderADMinLengthEM = "Vui lòng nhập lớn hơn hoặc bằng 1";
    protected final String orderADInvalidFormatEM = "Thứ tự phải là một số hợp lệ!";

    public UpdateADMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.notification = new Notification(this.wait);
    }

    // Getters cho elements
    public By getEditADButton() { return editADButton; }
    public By getSaveADButton() { return saveADButton; }
    public By getOkADButton() { return okADButton; }
    public By getNameADInput() { return nameADInput; }
    public By getOrderADInput() { return orderADInput; }
    public By getDialogErrorField() { return dialogErrorField; }
    public Notification getNotification() { return notification; }

    // Getters cho error messages
    public String getIdADDuplicateEM() { return idADDuplicateEM; }
    public String getNameADEmptyEM() { return nameADEmptyEM; }
    public String getNameADMaxLengthEM() { return nameADMaxLengthEM; }
    public String getOrderADEmptyEM() { return orderADEmptyEM; }
    public String getOrderADMaxLengthEM() { return orderADMaxLengthEM; }
    public String getOrderADMinLengthEM() { return orderADMinLengthEM; }
    public String getOrderADInvalidFormatEM() { return orderADInvalidFormatEM; }
}