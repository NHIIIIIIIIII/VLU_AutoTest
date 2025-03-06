package pages;

import utils.Notification;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserManagementPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private Notification notifiCheck;

    // Input Elements
    private final By userIdInput = By.xpath("/html/body/div[3]/div[2]/form/div[1]/input");
    private final By userNameInput = By.xpath("/html/body/div[3]/div[2]/form/div[2]/input");
    private final By emailInput = By.xpath("/html/body/div[3]/div[2]/form/div[3]/input");
    private final By contractTypeDropdown = By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span/span/span");
    private final By roleDropdown = By.xpath("/html/body/div[3]/div[2]/form/div[5]/div/span/span/span");


    // Button Elements
    private final By userTab = By.xpath("(//span[contains(text(),'Người dùng')])[1]");
    private final By addUserButton = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/div/div[2]/div/div[2]/button");
    private final By saveButton = By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[2]");

    public UserManagementPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        notifiCheck = new Notification(this.wait);
    }

    public void clickUserTab() {
        wait.until(ExpectedConditions.elementToBeClickable(userTab)).click();
    }

    public void clickAddUserButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addUserButton)).click();
    }

    public void enterUserId(String id) {
        wait.until(ExpectedConditions.elementToBeClickable(userIdInput)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, id);
    }

    public void enterUserName(String userName) {
        wait.until(ExpectedConditions.elementToBeClickable(userNameInput)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, userName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, email);
    }

    public void selectContractType(String contractType) {
        wait.until(ExpectedConditions.elementToBeClickable(contractTypeDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + contractType + "']"))).click();
    }

    public void selectUserRole(String role) {
        wait.until(ExpectedConditions.elementToBeClickable(roleDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + role + "']"))).click();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void addNewUserDetails(String id, String name, String email, String contract, String role) {
        clickUserTab();
        clickAddUserButton();
        enterUserId(id);
        enterUserName(name);
        enterEmail(email);
        selectContractType(contract);
        selectUserRole(role);
        clickSaveButton();
        notifiCheck.checkAddNotification();
  }
//
//    public void InvalidEmailFormat(String id, String name, String email, String contract, String role) {
//        clickUserTab();
//        clickAddUserButton();
//        enterUserId(id);
//        enterUserName(name);
//        enterEmail(email);
//        selectContractType(contract);
//        selectUserRole(role);
//        clickSaveButton();
//
//
//    }
//    public String getErrorMessage () {
//        By errorMessageLocator = By.xpath("//div[contains(text(),'Vui lòng nhập email Văn Lang hợp lệ!')]");
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)).getText();
//
//    }
}
