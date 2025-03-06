package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Notification {

    private final WebDriverWait wait;

    public final By toastNotification = By.className("toast-container");

    //    Message Successfully
    private final String addSuccess = "Lưu thành công!";
    private final String deleteSuccess = "Xóa thành công!";
    private final String updateSuccess = "Cập nhật thành công!";


    public Notification(WebDriverWait wait) {
        this.wait = wait;
    }

    public WebElement getNotification() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(toastNotification));
        } catch (TimeoutException timeEx) {
            Assert.fail("toastNotification not found");
            return null;
        }
    }

    /**
     * Kiểm tra thông báo thêm thành công
     */
    public void checkAddNotification() {
        Assert.assertEquals(
                getNotification().getText(),
                addSuccess,
                "Notification message not match with expect"
        );
    }

    /**
     * Kiểm tra cập nhật thông báo thành công
     */
    public void checkUpdateNotification() {
        Assert.assertEquals(
                getNotification().getText(),
                updateSuccess,
                "Notification message not match with expect"
        );
    }

    /**
     * Kiểm tra thông báo xóa thành công
     */
    public void checkDeleteNotification() {
        Assert.assertEquals(
                getNotification().getText(),
                deleteSuccess,
                "Notification message not match with expect"
        );
    }

}
