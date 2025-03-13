package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Notification;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static base.DriverConfig.sleep;

public class MajorManagementPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Notification notifiCheck;

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


    //    Major Id Message Error
    private final String deleteIdDataErrorMessage = "Không thể xoá do ngành này đã có dữ liệu!";  // Hiển thị trên dialog
    private final String duplicateMajorIdErrorMessage = "Mã ngành này đã tồn tại!";  // Hiển thị trên dialog
    private final String majorIdInvalidFormatErrorMessage = "Chỉ được nhập số-chữ không dấu và không có khoảng trắng!";
    private final String majorIdEmptyErrorMessage = "Bạn chưa nhập mã ngành";
    private final String majorIdMaxLengthErrorMessage = "Tối đa 50 kí tự được cho phép";


    //    Major Name Message Error
    private final String majorNameEmptyErrorMessage = "Bạn chưa nhập tên ngành";
    private final String majorNameMaxLengthErrorMessage = "Tối đa 255 kí tự được cho phép";


    //    Major Abbreviation Message Error
    private final String majorAbbreviationMaxLengthErrorMessage = "Tối đa 50 kí tự được cho phép";
    private final String majorAbbreviatioEmptyErrorMessage = "Bạn chưa nhập tên viết tắt của ngành";


    //    Major TrainingProgramOption Message Error
    private final String majorTrainingProgramNotSelectedErrorMessage = "Bạn chưa chọn CTĐT";

    //    Constructor
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

    public void getFirstRowTable() {
        WebElement firstRow = wait.until(ExpectedConditions.presenceOfElementLocated(firstRowTableField));
        System.out.println(firstRow.getText());
    }
    public void searchMajor(String searchValue) {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxField)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, searchValue);
    }
    public void checkErrorMessage(WebElement errorElement, String errorMessage) {
        System.out.println("==========================================");
        System.out.println("Check Error : ");
        System.out.println("Actual : " + errorElement.getText());
        System.out.println("Expect : " + errorMessage);
        Assert.assertEquals(errorElement.getText(), errorMessage, "Error Message not equal");
        System.out.println("==========================================");
    }


    public void checkMajorIdError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(majorIdError)), expectedMessage);
    }

    public void checkMajorNameError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(majorNameError)), expectedMessage);
    }

    public void checkMajorAbbreviationError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(majorAbbreviationError)), expectedMessage);
    }

    public void checkProgramTypeError(String expectedMessage) {
        if (checkMajorProgramNameErrorDisplayed())
            checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(programTypeError)), expectedMessage);
    }

    public void checkDialogError(String expectedMessage) {
        checkErrorMessage(wait.until(ExpectedConditions.presenceOfElementLocated(dialogErrorField)), expectedMessage);
    }

    /**
     * Kiểm tra hợp lệ Id
     *
     * @param id
     * @return boolean
     */
    public boolean checkMajorIdValid(String id) {
        if (id.isEmpty()) {
            System.out.println("Major ID Empty");
            checkMajorIdError(majorIdEmptyErrorMessage);
            return false;
        } else if (id.length() > 50) {
            System.out.println("Major ID > 50");
            checkMajorIdError(majorIdMaxLengthErrorMessage);
            return false;
        } else if (!id.matches("^[a-zA-Z0-9_-]{1,50}$")) {
            System.out.println("Major ID Invalid Format");
            checkMajorIdError(majorIdInvalidFormatErrorMessage);
            return false;
        } else return true;
    }
//    public List<String> checkMajorIdValid(String id) {
//        List<String> errors = new ArrayList<>();
//
//        if (id.isEmpty()) {
//            checkMajorIdError(majorIdEmptyErrorMessage);
//            errors.add(majorIdEmptyErrorMessage);
//        }
//        if (id.length() > 50) {
//            checkMajorIdError(majorIdMaxLengthErrorMessage);
//            errors.add(majorIdMaxLengthErrorMessage);
//        }
//        if (!id.matches("^[a-zA-Z0-9_-]{1,50}$")) {
//            checkMajorIdError(majorIdInvalidFormatErrorMessage);
//            errors.add(majorIdInvalidFormatErrorMessage);
//        }
//
//        return errors;
//    }

    /**
     * Kiểm tra hợp lệ Major Name
     *
     * @param name
     * @return boolean
     */
    public boolean checkMajorNameValid(String name) {
        if (name.isEmpty()) {
            System.out.println("Major name Empty");
            checkMajorNameError(majorNameEmptyErrorMessage);
            return false;

        } else if (name.length() > 255) {
            System.out.println("Major name > 255");
            checkMajorNameError(majorNameMaxLengthErrorMessage);
            return false;

        } else return true;

    }
//    public List<String> checkMajorNameValid(String name) {
//        List<String> errors = new ArrayList<>();
//
//        if (name.isEmpty()) {
//            checkMajorNameError(majorNameEmptyErrorMessage);
//            errors.add(majorNameEmptyErrorMessage);
//        }
//        if (name.length() > 255) {
//            checkMajorNameError(majorNameMaxLengthErrorMessage);
//            errors.add(majorNameMaxLengthErrorMessage);
//        }
//
//        return errors;
//    }

    /**
     * Kiểm tra hợp lệ Abbreviation
     *
     * @param abbreviation
     * @return boolean
     */
    public boolean checkMajorAbbreviationValid(String abbreviation) {
        if (abbreviation.isEmpty()) {
            System.out.println("Major abbreviation Empty");
            checkMajorAbbreviationError(majorAbbreviatioEmptyErrorMessage);
            return false;

        } else if (abbreviation.length() > 50) {
            System.out.println("Major abbreviation > 50");
            checkMajorAbbreviationError(majorAbbreviationMaxLengthErrorMessage);
            return false;

        } else return true;

    }
//    public List<String> checkMajorAbbreviationValid(String abbreviation) {
//        List<String> errors = new ArrayList<>();
//
//        if (abbreviation.isEmpty()) {
//            checkMajorAbbreviationError(majorAbbreviatioEmptyErrorMessage);
//            errors.add(majorAbbreviatioEmptyErrorMessage);
//        }
//        if (abbreviation.length() > 50) {
//            checkMajorAbbreviationError(majorAbbreviationMaxLengthErrorMessage);
//            errors.add(majorAbbreviationMaxLengthErrorMessage);
//        }
//
//        return errors;
//    }

    /**
     * Kiểm tra hợp lệ Program Name
     *
     * @param programName
     * @return boolean
     */
    public boolean checkMajorProgramNameValid(String programName) {
        if (programName.isEmpty()) {
            System.out.println("Major programName not selected");
            checkProgramTypeError(majorTrainingProgramNotSelectedErrorMessage);
            return false;

        } else return true;

    }
//    public List<String> checkMajorProgramNameValid(String programName) {
//        List<String> errors = new ArrayList<>();
//
//        if (programName.isEmpty() && checkMajorProgramNameErrorDisplayed()) {
//            checkProgramTypeError(majorTrainingProgramNotSelectedErrorMessage);
//            errors.add(majorTrainingProgramNotSelectedErrorMessage);
//        }
//
//        return errors;
//    }


    public boolean checkMajorProgramNameErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(programTypeError)).isDisplayed();
        } catch (TimeoutException timeoutException){
            return false;
        }
    }

    /**
     * Thêm ngành học mới và kiểm tra với nhiều case
     *
     * @param id           Id ngành
     * @param name         Tên ngành
     * @param abbreviation Tên viết tắt
     * @param programName  Tên Chương trình đào tạo
     */
    public void addMajorWithMultiCase(String id, String name, String abbreviation, String programName) {
        clickTermAndMajorTab();
        clickMajorTab();
        clickAddMajorButton();
        enterMajorId(id);
        sleep(5);

        enterMajorName(name);
        sleep(5);

        enterMajorAbbreviation(abbreviation);
        sleep(5);

        if (!programName.isEmpty()) selectTrainingProgram(programName);
        clickSaveButton();
        if (checkDialogDisplayed() == null) {
            if (checkMajorIdValid(id) && checkMajorNameValid(name) && checkMajorAbbreviationValid(abbreviation)) {
                notifiCheck.checkAddNotification();
//                clickCloseButton();
            }
        } else {
            checkDialogError(duplicateMajorIdErrorMessage);
            clickOkErrorButton();
        }

    }
//    public void addMajorWithMultiCase(String id, String name, String abbreviation, String programName) {
//        clickTermAndMajorTab();
//        clickMajorTab();
//
//        sleep(5);
//        clickAddMajorButton();
//        enterMajorId(id);
//        enterMajorName(name);
//        sleep(5);
//
//        enterMajorAbbreviation(abbreviation);
//        sleep(5);
//
//        if (!programName.isEmpty()) selectTrainingProgram(programName);
//
//        clickSaveButton();
//
//        if (checkDialogDisplayed() == null) {
//            List<String> errors = new ArrayList<>();
//            errors.addAll(checkMajorIdValid(id));
//            errors.addAll(checkMajorNameValid(name));
//            errors.addAll(checkMajorAbbreviationValid(abbreviation));
//            errors.addAll(checkMajorProgramNameValid(programName));
//
//            if (errors.isEmpty()) {
//                notifiCheck.checkUpdateNotification();
//            } else {
//                for (String error : errors) {
//                    System.out.println(error);
//                }
//            }
//        } else {
//            checkDialogError(duplicateMajorIdErrorMessage);
//            clickOkErrorButton();
//        }
//    }

    /**
     * Sửa ngành học và kiểm tra với nhiều case
     *
     * @param id           Nhập id để tìm kiếm
     * @param name         Tên ngành
     * @param abbreviation Tên viết tắt
     * @param programName  Tên Chương trình đào tạo
     */
    public void updateMajorWithMultiCase(String id, String name, String abbreviation, String programName) {
        clickTermAndMajorTab();
        clickMajorTab();
        searchMajor(id);
//        getFirstRowTable();
        clickUpdateButton();
//        enterMajorId(id);
//        sleep(5);

        enterMajorName(name);
        sleep(5);

        enterMajorAbbreviation(abbreviation);
        sleep(5);

        if (!programName.isEmpty()) selectTrainingProgram(programName);
//        else selectTrainingProgram("---- Chọn CTĐT ----");
        clickSaveButton();
        if (checkDialogDisplayed() == null) {
//            if (checkMajorProgramNameErrorDisplayed()) {
//                checkProgramTypeError(majorTrainingProgramNotSelectedErrorMessage);
//            }
            if (checkMajorNameValid(name) && checkMajorAbbreviationValid(abbreviation)) {
                notifiCheck.checkUpdateNotification();
//                clickCloseButton();
            }
        } else {
            checkDialogError(duplicateMajorIdErrorMessage);
            clickOkErrorButton();
        }

    }
//    public void updateMajorWithMultiCase(String id, String name, String abbreviation, String programName) {
//        clickTermAndMajorTab();
//        clickMajorTab();
//        searchMajor(id);
//        sleep(5);
//        getFirstRowTable();
//        clickUpdateButton();
//
//        enterMajorName(name);
//        sleep(5);
//
//        enterMajorAbbreviation(abbreviation);
//        sleep(5);
//
//        if (!programName.isEmpty()) selectTrainingProgram(programName);
//
//        clickSaveButton();
//
//        if (checkDialogDisplayed() == null) {
//            List<String> errors = new ArrayList<>();
////            errors.addAll(checkMajorIdValid(id));
//            errors.addAll(checkMajorNameValid(name));
//            errors.addAll(checkMajorAbbreviationValid(abbreviation));
////            errors.addAll(checkMajorProgramNameValid(programName));
//
//            if (errors.isEmpty()) {
//                notifiCheck.checkUpdateNotification();
//            } else {
//                for (String error : errors) {
//                    System.out.println(error);
//                }
//            }
//        } else {
//            checkDialogError(duplicateMajorIdErrorMessage);
//            clickOkErrorButton();
//        }
//    }

//    ================================================================================================================================

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
     * Thêm ngành học mới với các trường thông tin trống
     */
    public void addNewMajorDetailsEmpty() {
        clickTermAndMajorTab();
        clickMajorTab();
        clickAddMajorButton();
        enterMajorId("");
        enterMajorName("");
        enterMajorAbbreviation("");
//        selectTrainingProgram("");
        clickSaveButton();
        checkMajorIdError(majorIdEmptyErrorMessage);
        checkMajorNameError(majorNameEmptyErrorMessage);
        checkMajorAbbreviationError(majorAbbreviatioEmptyErrorMessage);
        checkProgramTypeError(majorTrainingProgramNotSelectedErrorMessage);
        clickCloseButton();
    }


    /**
     * Thêm ngành học mới với các id trùng
     */
    public void addNewMajorDetailsDuplicated(String id, String name, String abbreviation, String programName) {
        clickTermAndMajorTab();
        clickMajorTab();
        clickAddMajorButton();
        enterMajorId(id);
        enterMajorName(name);
        enterMajorAbbreviation(abbreviation);
        selectTrainingProgram(programName);
        clickSaveButton();
        clickOkErrorButton();
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
        clickCloseButton();
    }




}
