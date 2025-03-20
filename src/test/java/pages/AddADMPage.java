package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Notification;

import java.time.Duration;

public class AddADMPage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Notification notification;

    // Input Elements
    protected final By codeADInput = By.xpath("//input[@id='id']");
    protected final By nameADInput = By.xpath("//input[@id='name']");
    protected final By orderADInput = By.xpath("//input[@id='level']");

    // Button Elements
    protected final By openADButton = By.xpath("//button[@class='dt-button createNew btn btn-primary']");
    protected final By saveADButton = By.xpath("//button[contains(text(),'Lưu')]");
    protected final By okADButton = By.xpath("//button[normalize-space()='OK']");

    // Field Error Elements
    protected final By adIdError = By.id("id-error");
    protected final By adNameError = By.id("name-error");
    protected final By adOrderError = By.id("level-error");

    // Dialog Error Element
    protected final By dialogErrorField = By.id("swal2-html-container");

    // AD Id Error Messages
    protected final String idADEmptyEM = "Bạn chưa nhập mã học hàm, học vị";
    protected final String idADDuplicateEM = "Mã học hàm, học vị này đã tồn tại!";
    protected final String idADMaxLengthEM = "Tối đa 50 kí tự được cho phép";
    protected final String idADInvalidFormatEM = "Chỉ được nhập số-chữ không dấu và không có khoảng trắng!";

    // AD Name Error Messages
    protected final String nameADEmptyEM = "Bạn chưa nhập tên học hàm, học vị";
    protected final String nameADMaxLengthEM = "Tối đa 100 kí tự được cho phép";

    // AD Order Error Messages
    protected final String orderADEmptyEM = "Bạn chưa nhập thứ tự";
    protected final String orderADMaxLengthEM = "Vui lòng nhập nhỏ hơn hoặc bằng 100";
    protected final String orderADMinLengthEM = "Vui lòng nhập lớn hơn hoặc bằng 1";
    protected final String orderADInvalidFormatEM = "Thứ tự phải là một số hợp lệ!";

    public AddADMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.notification = new Notification(this.wait);
    }

    // Getters cho elements
    public By getDialogErrorField() { return dialogErrorField; }
    public Notification getNotification() { return notification; }
    public By getOpenADButton() { return openADButton; }
    public By getCodeADInput() { return codeADInput; }
    public By getNameADInput() { return nameADInput; }
    public By getOrderADInput() { return orderADInput; }
    public By getSaveADButton() { return saveADButton; }
    public By getOkADButton() { return okADButton; }

    // Getters cho error messages
    public String getIdADEmptyEM() { return idADEmptyEM; }
    public String getIdADDuplicateEM() { return idADDuplicateEM; }
    public String getIdADMaxLengthEM() { return idADMaxLengthEM; }
    public String getIdADInvalidFormatEM() { return idADInvalidFormatEM; }
    public String getNameADEmptyEM() { return nameADEmptyEM; }
    public String getNameADMaxLengthEM() { return nameADMaxLengthEM; }
    public String getOrderADEmptyEM() { return orderADEmptyEM; }
    public String getOrderADMaxLengthEM() { return orderADMaxLengthEM; }
    public String getOrderADMinLengthEM() { return orderADMinLengthEM; }
    public String getOrderADInvalidFormatEM() { return orderADInvalidFormatEM; }

    // Getters cho field error elements
    public By getAdIdError() { return adIdError; }
    public By getAdNameError() { return adNameError; }
    public By getAdOrderError() { return adOrderError; }
}