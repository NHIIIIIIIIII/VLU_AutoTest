package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Notification {

    private final WebDriverWait wait;

    public final By toastNotification = By.className("toast-container");


    public Notification(WebDriverWait wait) {
        this.wait = wait;
    }

    /**
     * Kiểm tra thông báo
     * @param messageExpect Thông báo mong đợi
     */
    public void checkNotification(String messageExpect) {
        Assert.assertEquals(
                wait.until(ExpectedConditions.presenceOfElementLocated(toastNotification)).getText(),
                messageExpect,
                "Notification message not match with expect"
        );
    }
}
