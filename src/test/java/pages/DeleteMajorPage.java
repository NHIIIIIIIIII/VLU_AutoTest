package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Notification;

import java.time.Duration;

public class DeleteMajorPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Notification notifiCheck;



    private final By majorTab = By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[2]/a[1]");
    private final By termAndMajorTab = By.xpath("//span[contains(text(),'Học kỳ và ngành')]");
    private final By dialogConfirm = By.xpath("//div[contains(@role,'dialog')]");
    private final By deleteBtn = By.xpath("(//a[contains(@title,'Xoá')])[1]");
    private final By searchBoxField = By.xpath("//input[@class='form-control'][@placeholder='Nhập tìm kiếm...']");
    private final By failNotification = By.xpath("(//div[@id='swal2-html-container'])[1]");
    private final By failCloseBtn = By.xpath("//button[normalize-space()='OK']");
    private final By notificationTextDialog = By.xpath("//h2[@id='swal2-title']");
    private final By notificationMessageDialog = By.xpath("//div[@id='swal2-html-container']");
    private final By deleteDialogBtn = By.xpath("//button[normalize-space()='Xoá']");
    private final By closeDialogBtn = By.xpath("//button[contains(text(),'Huỷ')]");


    public DeleteMajorPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        notifiCheck = new Notification(this.wait);
    }


    public boolean checkDialogConfirmDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement dialogElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(dialogConfirm));
            System.out.println("Dialog is displayed");
            return true;
        } catch (TimeoutException timeEx) {
//            Assert.fail("Dialog not displayed");
            return false;
        }
    }

    public WebElement getFailNotification(){
        try{
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(this.failNotification));
        }
        catch (TimeoutException timeoutException){
            Assert.fail("Not found failNotification ");
        }
        return null;
    }

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

    public void checkDialog() {
        checkTextDialog(driver);
        checkMessageDialog(driver);
        checkDeleteDialogBtn(driver);
        checkCloseDialogBtn(driver);

    }



    public void clickTermAndMajorTab() {
        wait.until(ExpectedConditions.elementToBeClickable(termAndMajorTab)).click();
    }


    public void clickMajorTab() {
        wait.until(ExpectedConditions.elementToBeClickable(majorTab)).click();
    }
    public void clickDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
    }

    public void clickDeletedDialog(){
        wait.until(ExpectedConditions.elementToBeClickable(deleteDialogBtn)).click();

    }
    public void clickFailClose() {
        wait.until(ExpectedConditions.elementToBeClickable(failCloseBtn)).click();
    }

    public void searchMajor(String searchValue) {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxField)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, searchValue);
    }

    public String getTextFailNotification(){
        return getFailNotification().getText();
    }



}
