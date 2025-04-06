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
    private final By dialogTitleManagement = By.className("ui-dialog-title");
//    private final By dialogTextIDLabels = By.id("id");
//    private final By dialogInputFields = By.className("form-control text-box single-line");
//    private final By saveManagementButton = By.xpath("//button[contains(text(),'Lưu')]");
//    private final By closeManagementButton = By.xpath("//button[@id='btnClose']");



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

    public List<WebElement> getElementsButtonDialog() {
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

    public List<WebElement> getElementsLabelManagementDialog() {
        if (checkDialogManagementDisplayed()) {
            List<WebElement> labels  = wait.until(ExpectedConditions.presenceOfElementLocated(dialogManagement)).findElements(By.tagName("label"));
            List<WebElement> filteredLabels = new ArrayList<>();

            for (WebElement label : labels) {
                if (!label.getText().trim().isEmpty()) {
                    filteredLabels.add(label);
                }
            }

            return filteredLabels;
        }
        return new ArrayList<>();
    }


    public List<WebElement> getElementsInputManagementDialog() {
        if (checkDialogManagementDisplayed()) {
            WebElement dialog = wait.until(ExpectedConditions.presenceOfElementLocated(dialogManagement));
            List<WebElement> inputs = dialog.findElements(By.tagName("input"));
            List<WebElement> filteredInputs = new ArrayList<>();

            for (WebElement input : inputs) {
                String arialabel = input.getDomAttribute("aria-label");
                if (arialabel != null && !arialabel.trim().isEmpty()) {
                    filteredInputs.add(input);
                }
            }

            return filteredInputs;
        }
        return new ArrayList<>();
    }

    public List<WebElement> getElementsOptionInSelect() {
        if (checkDialogManagementDisplayed()) {
            WebElement selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(dialogManagement));
            List<WebElement> options = selectElement.findElements(By.tagName("option"));
            List<WebElement> filteredOptions = new ArrayList<>();

            for (WebElement option : options) {
                if (!option.getText().trim().isEmpty()) {
                    filteredOptions.add(option);
                }
            }

            return filteredOptions;
        }
        return new ArrayList<>();
    }

    public List<WebElement> getElementsButtonManagementDialog() {
        if (checkDialogManagementDisplayed()) {
            List<WebElement> buttons = wait.until(ExpectedConditions.presenceOfElementLocated(dialogManagement)).findElements(By.tagName("button"));
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


    public String getTitleManagement() {
        if (checkDialogManagementDisplayed()) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(dialogTitleManagement)).getText();
            } catch (TimeoutException e) {
                System.out.println("Title element not found: " + e.getMessage());
                return null;
            }
        }
        return null;
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
