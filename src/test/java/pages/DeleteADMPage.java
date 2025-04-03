package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Notification;

import java.time.Duration;

public class DeleteADMPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Notification notifiCheck;

    // Locators
    private final By deleteBtn = By.xpath("//a[@title='Xoá']");
    private final By searchBoxField = By.xpath("//input[@placeholder='Nhập tìm kiếm...']");
    private final By dialogConfirm = By.xpath("//div[contains(@role,'dialog')]");
    private final By failNotification = By.xpath("(//div[@id='swal2-html-container'])[1]");
    private final By failOKBtn = By.xpath("//button[normalize-space()='OK']");

    // Locators dialog
    private final By notificationTextDialog = By.xpath("//h2[@id='swal2-title']");
    private final By notificationMessageDialog = By.xpath("//div[@id='swal2-html-container']");
    private final By deleteDialogBtn = By.xpath("//button[normalize-space()='Xoá']");
    private final By closeDialogBtn = By.xpath("//button[contains(text(),'Huỷ')]");

    // Locators for Field Errors
    private final By findNotFound = By.xpath("//td[@class='dataTables_empty']");

    // Error Messages
    private final String errorDataAvailable = "Không thể xoá do học hàm, học vị này đã có dữ liệu!";
    private final String errorFindNotFound = "Không tìm thấy kết quả";

    // Constructor
    public DeleteADMPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.notifiCheck = new Notification(this.wait);
    }

    // Getters for Error Messages

    public String getErrorFindNotFound() {
        return errorFindNotFound;
    }

    // Getter for Notification
    public Notification getNotifiCheck() {
        return notifiCheck;
    }

    // Methods
    public void clickDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
    }

    public void clickFailClose() {
        wait.until(ExpectedConditions.elementToBeClickable(failOKBtn)).click();
    }

    public void searchADM(String searchValue) {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxField))
                .sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, searchValue);
    }

    public boolean checkDialogConfirmDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement dialogElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(dialogConfirm));
            System.out.println("Dialog is displayed");
            return true;
        } catch (TimeoutException timeEx) {
            return false;
        }
    }

    public WebElement getFailNotification() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(this.failNotification));
        } catch (TimeoutException timeoutException) {
            Assert.fail("Not found failNotification");
            return null;
        }
    }

    public String getTextFailNotification() {
        return getFailNotification().getText();
    }

    public String getTextFindNotFound() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(findNotFound)).getText();
    }

    // Methods Dialog
    public boolean checkTextDialog(WebDriver driver) {
        try {
            driver.findElement(notificationTextDialog);
            System.out.println("Text Dialog is displayed");
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Text Dialog not found displayed");
            return false;
        }
    }

    public boolean checkMessageDialog(WebDriver driver) {
        try {
            driver.findElement(notificationMessageDialog);
            System.out.println("Message Dialog is displayed");
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Message Dialog not found displayed");
            return false;
        }
    }

    public boolean checkDeleteDialogBtn(WebDriver driver) {
        try {
            driver.findElement(deleteDialogBtn);
            System.out.println("Delete Dialog Button is displayed");
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Delete Dialog Button not found displayed");
            return false;
        }
    }

    public boolean checkCloseDialogBtn(WebDriver driver) {
        try {
            driver.findElement(closeDialogBtn);
            System.out.println("Close Dialog Button is displayed");
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Close Dialog Button not found displayed");
            return false;
        }
    }

    public void clickDeleteDialog() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteDialogBtn)).click();
    }

    public void clickCloseDialog() {
        wait.until(ExpectedConditions.elementToBeClickable(closeDialogBtn)).click();
    }

    public void checkDialog() {
        checkTextDialog(driver);
        checkMessageDialog(driver);
        checkDeleteDialogBtn(driver);
        checkCloseDialogBtn(driver);

    }
}