package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class UpdateMajorPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By trainingProgramDropdown = By.xpath("//select[@id='program_type']");
    private final By firstRowTableField = By.xpath("(//table[@id='tblMajor']//tbody//tr)[1]");

    //    Input Element
    private final By majorIdField = By.id("id");
    private final By majorNameField = By.id("name");
    private final By majorAbbreviationInput = By.id("abbreviation");
    private final By searchBoxField = By.xpath("//input[@class='form-control'][@placeholder='Nhập tìm kiếm...']");


    //    Button Element
    private final By majorTab = By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[2]/a[1]");
    private final By termAndMajorTab = By.xpath("//span[contains(text(),'Học kỳ và ngành')]");
    private final By addMajorButton = By.xpath("//*[@id=\"tblMajor_wrapper\"]/div[1]/div[2]/div/div[2]/button");
    private final By editMajorButton = By.xpath("//tbody/tr[1]/td[6]/a[1]/i[1]");
    private final By saveButton = By.xpath("//button[contains(text(),'Lưu')]");
    private final By closeButton = By.xpath("//button[@class='ui-dialog-titlebar-close btn-close']");
    private final By okErrorButton = By.xpath("//button[normalize-space()='OK']");
    private final By updateButton = By.xpath("(//table[@id='tblMajor']//tbody//a[@title='Chỉnh sửa'])[1]");

    //    Field Error
    private final By majorIdError = By.id("id-error");
    private final By majorNameError = By.id("name-error");
    private final By majorAbbreviationError = By.id("abbreviation-error");
    private final By programTypeError = By.id("program_type-error");

    //    Dialog Error Message
    private final By dialogErrorField = By.id("swal2-html-container");

    //    Constructor
    public UpdateMajorPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }



    /**
     * Trả By.xpath theo programName trong trainingProgramDropdown
     *
     * @param programName: Tên chương trình đào tạo
     */
    private By getTrainingProgramOption(String programName) {
        return By.xpath("//li[text()='" + programName + "']");
    }

    /**
     * Chọn chương trình đào tạo
     *
     * @param item: Tên chương trình đào tạo
     */
    public void selectTrainingProgram(String item) {
        Select trainingProgramDropdownElement = new Select(wait.until(ExpectedConditions.elementToBeClickable(trainingProgramDropdown)));
        trainingProgramDropdownElement.selectByVisibleText(item);

    }


    /**
     * Clicks on the Term and Major tab.
     *
     * @Caution: It will show Term Management Default
     */
    public void clickTermAndMajorTab() {
        wait.until(ExpectedConditions.elementToBeClickable(termAndMajorTab)).click();
    }

    /**
     * Clicks on the Major tab.
     */
    public void clickMajorTab() {
        wait.until(ExpectedConditions.elementToBeClickable(majorTab)).click();
    }

    /**
     * Click add Major Button
     */
    public void clickAddMajorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addMajorButton)).click();
    }

    /**
     * Nhập id Ngành
     */
    public void enterMajorId(String id) {
        wait.until(ExpectedConditions.elementToBeClickable(majorIdField)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, id);
    }

    /**
     * Nhập tên Ngành
     */
    public void enterMajorName(String majorName) {
        wait.until(ExpectedConditions.elementToBeClickable(majorNameField)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, majorName);
    }

    /**
     * Nhập tên viết tắt Ngành
     *
     * @param abbreviation Tên ngành viết tắt
     */
    public void enterMajorAbbreviation(String abbreviation) {
        wait.until(ExpectedConditions.elementToBeClickable(majorAbbreviationInput)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, abbreviation);
    }

    /**
     * Ấn nút lưu
     */
    public void clickSaveButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        } catch (Exception e) {
            Assert.fail("Save button not interacted");
        }
    }

    /**
     * Click close Major
     */
    public void clickCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }


    public WebElement checkDialogDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement dialogElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(dialogErrorField));
            System.out.println("Dialog is displayed");
            return dialogElement;
        } catch (TimeoutException timeEx) {
//            Assert.fail("Dialog not displayed");
            return null;
        }
    }


    public void clickOkErrorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(okErrorButton)).click();
    }

    public void clickUpdateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
    }
    public void searchMajor(String searchValue) {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxField)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, searchValue);
    }

    public String getTextMajorIdError(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(majorIdError)).getText();

    }

    public String getTextMajorNameError() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(majorNameError)).getText();
    }

    public String getTextMajorAbbreviationError(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(majorAbbreviationError)).getText();
    }

    public String getTextTrainingProgramError(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(programTypeError)).getText();

    }

}
