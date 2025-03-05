package pages;

import base.Notification;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MajorManagementPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private Notification notifiCheck;

    private final By trainingProgramDropdown = By.className("select2-selection__placeholder");

    //    Input Element
    private final By majorIdInput = By.id("id");
    private final By majorNameInput = By.id("name");
    private final By majorAbbreviationInput = By.id("abbreviation");
    private final By searchBoxInput = By.className("form-control");

    //    Button Element
    private final By majorTab = By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[2]/a[1]");
    private final By termAndMajorTab = By.xpath("//span[contains(text(),'Học kỳ và ngành')]");
    private final By addMajorButton = By.xpath("//*[@id=\"tblMajor_wrapper\"]/div[1]/div[2]/div/div[2]/button");
    private final By editMajorButton = By.xpath("//tbody/tr[1]/td[6]/a[1]/i[1]");
    private final By saveButton = By.xpath("//button[contains(text(),'Lưu')]");





    public MajorManagementPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        notifiCheck = new Notification(this.wait);
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
     * @param programName: Tên chương trình đào tạo
     */
    public void selectTrainingProgram(String programName) {
        wait.until(ExpectedConditions.elementToBeClickable(trainingProgramDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(getTrainingProgramOption(programName))).click();
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
        wait.until(ExpectedConditions.elementToBeClickable(majorIdInput)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, id);
    }

    /**
     * Nhập tên Ngành
     */
    public void enterMajorName(String majorName) {
        wait.until(ExpectedConditions.elementToBeClickable(majorNameInput)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, majorName);
    }

    /**
     * Nhập tên viết tắt Ngành
     */
    public void enterMajorAbbreviation(String abbreviation) {
        wait.until(ExpectedConditions.elementToBeClickable(majorAbbreviationInput)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, abbreviation);
    }

    /**
     * Ấn nút lưu
     */
    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }


    /**
     * Thêm ngành học mới và lưu
     *
     * @param id           Id ngành
     * @param name         Tên ngành
     * @param abbreviation Tên viết tắt
     * @param programName  Tên Chương trình đào tạo
     */
    public void addNewMajorDetails(String id, String name, String abbreviation, String programName) {
        clickTermAndMajorTab();
        clickMajorTab();
        clickAddMajorButton();
        enterMajorId(id);
        enterMajorName(name);
        enterMajorAbbreviation(abbreviation);
        selectTrainingProgram(programName);
        clickSaveButton();
        notifiCheck.checkAddNotification();
    }

    /**
     * Sửa ngành học đã có và lưu
     *
     * @param name         Tên ngành
     * @param abbreviation Tên viết tắt
     * @param programName  Tên Chương trình đào tạo
     * @Caution: Id ngành không được thay đổi
     */
    public void updateMajorDetails(String name, String abbreviation, String programName) {
        clickTermAndMajorTab();
        clickMajorTab();
        clickAddMajorButton();
        enterMajorName(name);
        enterMajorAbbreviation(abbreviation);
        selectTrainingProgram(programName);
        clickSaveButton();
        notifiCheck.checkAddNotification();
    }

    public void searchMajor(String search) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBoxInput)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, search);
    }


}
