package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Dialog {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By dialogConfirm = By.xpath("//div[@aria-labelledby='swal2-title' and @role='dialog']");
    private final By dialogContent = By.xpath("//div[@id='swal2-html-container']");
    private final By CloseBtn = By.xpath("//button[normalize-space()='OK']");
    private final By dialogTitle = By.xpath("//h2[@id='swal2-title']");
    private final By dialogManagement = By.xpath("//div[@role='dialog' and @class='ui-dialog ui-corner-all ui-widget ui-widget-content ui-front ui-draggable']");

    public Dialog(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }



    public boolean checkDialogConfirmDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement dialogElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(dialogConfirm));
//            System.out.println("===== Dialog is displayed =====");
            return true;
        } catch (TimeoutException timeEx) {
            Assert.fail("Dialog not displayed");
            return false;
        }
    }

    public List<WebElement> getElementsDialog() {
        if (checkDialogConfirmDisplayed()) {
            List<WebElement> buttons = wait.until(ExpectedConditions.presenceOfElementLocated(dialogConfirm)).findElements(By.tagName("button"));
            List<WebElement> filteredButtons = new ArrayList<>();

            for (WebElement button : buttons) {
                if (!button.getText().trim().isEmpty()) {
                    filteredButtons.add(button);
                }
            }

            return filteredButtons;
        }
        return new ArrayList<>();
    }

    public String getTitleDialog() {
        if (checkDialogConfirmDisplayed()) {
            return wait.until(ExpectedConditions.presenceOfElementLocated(dialogTitle)).getText();
        }
        return null;
    }

    public String getContentDialog() {
        if (checkDialogConfirmDisplayed()) {
            return wait.until(ExpectedConditions.presenceOfElementLocated(dialogContent)).getText();
        }
        return null;
    }

    public boolean checkDialogManagementDisplayed() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(dialogManagement)).isDisplayed();
        } catch (TimeoutException tm) {
            return false;
        }
    }

}
