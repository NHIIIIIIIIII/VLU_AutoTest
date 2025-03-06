package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class AcademicDegreeManagementPage {
    private final WebDriver driver;
    private final WebDriverWait wait; // Thêm WebDriverWait để sử dụng cơ chế chờ

    private final By openADButton = By.xpath("//button[@class='dt-button createNew btn btn-primary']");
    private final By codeADInput = By.xpath("//input[@id='id']");
    private final By nameADInput = By.xpath("//input[@id='name']");
    private final By orderADInput = By.xpath("//input[@id='level']");;
    private final By saveADButton = By.xpath("//button[contains(text(),'Lưu')]");



    public AcademicDegreeManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Khởi tạo wait với timeout 10 giây
    }
    /**
     * Điều hướng đến trang Academic Degree Management
     */
    public void navigateToAcademicDegreePage() {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/AcademicDegree");
        // Chờ cho trang tải xong bằng cách kiểm tra một phần tử chính (ví dụ: openADButton)
        wait.until(ExpectedConditions.visibilityOfElementLocated(openADButton));
    }

    /**
     * Clicks on the Academic Degree.
     * @Caution It will show Academic Degree Management Default
     */
    public void clickOpenAD() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(openADButton));
        button.click();
    }

    /**
     * Click add Academic Degree Button
     */
    public void clickSaveAD() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(saveADButton));
        button.click();
    }

    public void EnterCodeADInput(String id) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(codeADInput));
        input.sendKeys(id);
    }

    public void EnterNameADInput(String name) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(nameADInput));
        input.sendKeys(name);
    }

    public void EnterOrderADInput(int order) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(orderADInput));
        input.sendKeys(String.valueOf(order));
    }

    public void addNewADDetails(String id, String name, int order) {
        clickOpenAD();
        EnterCodeADInput(id);
        EnterNameADInput(name);
        EnterOrderADInput(order);
        clickSaveAD();
    }
}