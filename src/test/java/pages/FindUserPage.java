package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Notification;
import java.time.Duration;
import java.util.List;

import org.testng.Assert;

public class FindUserPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Notification notifiCheck;

    // Input Elements
    private final By searchInput = By.xpath("(//input[@placeholder='Nhập tìm kiếm...'])[1]");
    private final By userTab = By.xpath("(//span[contains(text(),'Người dùng')])[1]");
    
    // Result Elements
    private final By searchResults = By.xpath("//table[@id='tblUser']//tbody/tr");
    private final By userIdColumn = By.xpath(".//td[2]");
    private final By userNameColumn = By.xpath(".//td[3]");
    private final By userEmailColumn = By.xpath(".//td[4]");

    // Constructor
    public FindUserPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.notifiCheck = new Notification(this.wait);
    }

    public void clickUserTab() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(userTab));
            element.click();
            System.out.println("Clicked User Tab successfully.");
        } catch (TimeoutException e) {
            System.out.println("Failed to find User Tab: " + e.getMessage());
            throw e;
        }
    }

    public void searchUser(String searchQuery) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
            element.clear();
            element.sendKeys(searchQuery);
            System.out.println("Entered search query: " + searchQuery);
            // Wait for search results to load
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Failed to enter search query: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean verifyUserById(String userId) {
        try {
            List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchResults));
            for (WebElement row : rows) {
                WebElement idCell = row.findElement(userIdColumn);
                if (idCell.getText().trim().equals(userId)) {
                    System.out.println("Found user with ID: " + userId);
                    return true;
                }
            }
            System.out.println("User with ID " + userId + " not found");
            return false;
        } catch (Exception e) {
            System.out.println("Error while verifying user by ID: " + e.getMessage());
            return false;
        }
    }

    public boolean verifyUserByName(String userName) {
        try {
            List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchResults));
            for (WebElement row : rows) {
                WebElement nameCell = row.findElement(userNameColumn);
                if (nameCell.getText().trim().equals(userName)) {
                    System.out.println("Found user with name: " + userName);
                    return true;
                }
            }
            System.out.println("User with name " + userName + " not found");
            return false;
        } catch (Exception e) {
            System.out.println("Error while verifying user by name: " + e.getMessage());
            return false;
        }
    }

    public boolean verifyUserByEmail(String emailPart) {
        try {
            List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchResults));
            for (WebElement row : rows) {
                WebElement emailCell = row.findElement(userEmailColumn);
                if (emailCell.getText().trim().contains(emailPart)) {
                    System.out.println("Found user with email containing: " + emailPart);
                    return true;
                }
            }
            System.out.println("User with email containing " + emailPart + " not found");
            return false;
        } catch (Exception e) {
            System.out.println("Error while verifying user by email: " + e.getMessage());
            return false;
        }
    }
}