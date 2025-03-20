package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Notification {

    public final By toastNotification = By.className("toast-container");
    private final WebDriverWait wait;
    //    Message Toast
    private final String addSuccess = "Lưu thành công!";
    private final String deleteSuccess = "Xoá thành công!";
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
    public void testAddNotification() {
        System.out.println("======== Check Notification Toast ========");
        System.out.println("Expect :" + addSuccess);
        System.out.println("Actual :" + getNotification().getText());
        Assert.assertEquals(
                getNotification().getText(),
                addSuccess,
                "Notification message not match with expect"
        );
    }

    /**
     * Kiểm tra cập nhật thông báo thành công
     */
    public void testUpdateNotification() {
        System.out.println("======== Check Notification Toast ========");
        System.out.println("Expect :" + updateSuccess);
        System.out.println("Actual :" + getNotification().getText());
        Assert.assertEquals(
                getNotification().getText(),
                updateSuccess,
                "Notification message not match with expect"
        );
    }

    /**
     * Kiểm tra thông báo xóa thành công
     */
    public void testDeleteNotification() {
        System.out.println("======== Check Notification Toast ========");
        System.out.println("Expect :" + deleteSuccess);
        System.out.println("Actual :" + getNotification().getText());
        Assert.assertEquals(
                getNotification().getText(),
                deleteSuccess,
                "Notification message not match with expect"
        );
    }

}
