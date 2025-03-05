package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class MajorManagementPage {
    private final WebDriver driver;


    private final By TermAndMajorTab = By.xpath("//span[contains(text(),'Học kỳ và ngành')]");
    private final By majorTab = By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[2]/a[1]");
    private final By addMajorButton = By.xpath("//*[@id=\"tblMajor_wrapper\"]/div[1]/div[2]/div/div[2]/button");
    private final By majorIdInput = By.id("id");
    private final By majorNameInput = By.id("name");
    private final By majorAbbreviationInput = By.id("abbreviation");
    private final By trainingProgramDropdown = By.className("select2-selection__placeholder");
    private final By saveButton = By.xpath("//button[contains(text(),'Lưu')]");

    public MajorManagementPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Trả By.xpath theo programName trong trainingProgramDropdown
     * @param programName: Tên chương trình đào tạo
     */
    private By getTrainingProgramOption(String programName) {
        return By.xpath("//li[text()='" + programName + "']");
    }

    /**
     * Chọn chương trình đào tạo
     * @param programName: Tên chương trình đào tạo
     */
    public void selectTrainingProgram(String programName) {
        driver.findElement(trainingProgramDropdown).click();
        driver.findElement(getTrainingProgramOption(programName)).click();
    }


    /**
     *  Clicks on the Term and Major tab.
     *  @Caution It will show Term Management Default
     */
    public void clickTermAndMajorTab() {
        driver.findElement(TermAndMajorTab).click();
    }

    /**
     *  Clicks on the Major tab.
     */
    public void clickMajorTab() {
        driver.findElement(majorTab).click();
    }

    /**
     *  Click add Major Button
     */
    public void clickAddMajorButton() {
        driver.findElement(addMajorButton).click();
    }

    public void enterMajorId(String id){
        driver.findElement(majorIdInput).sendKeys(id);
    }

    public void enterMajorName(String name){
        driver.findElement(majorNameInput).sendKeys(name);
    }

    public void enterMajorAbbreviation(String abbreviation){
        driver.findElement(majorAbbreviationInput).sendKeys(abbreviation);
    }

    public void clickSaveButton(){
        driver.findElement(saveButton).click();
    }



    public void addNewMajorDetails(String id, String name, String abbreviation, String programName){
        clickTermAndMajorTab();
        clickMajorTab();
        clickAddMajorButton();
        enterMajorId(id);
        enterMajorName(name);
        enterMajorAbbreviation(abbreviation);
        selectTrainingProgram(programName);
        clickSaveButton();
    }

}
