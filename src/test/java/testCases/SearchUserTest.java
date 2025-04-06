package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SearchUserPage;

public class SearchUserTest extends BaseTest {
    private SearchUserPage FindUserPage;

    @BeforeClass
    public void setupClass() {
        FindUserPage = new SearchUserPage(driver, wait);
    }

    @Test
    public void TC_SU_01() {
        System.out.println("🔹 Kiểm tra tìm kiếm người dùng theo ID...");
        FindUserPage.clickUserTab();
        FindUserPage.searchUser("2274802010979");
        
        String expectedId = "2274802010979";
        String actualId = FindUserPage.getFoundUserId();
        
        System.out.println("==========================================");
        System.out.println("Check Search Result:");
        System.out.println("Expect: " + expectedId);
        System.out.println("Actual: " + actualId);
        Assert.assertEquals(actualId, expectedId, "User ID does not match expected");
        System.out.println("==========================================");
    }

    @Test
    public void TC_SU_02() {
        System.out.println("🔹 Kiểm tra tìm kiếm người dùng theo tên...");
        FindUserPage.clickUserTab();
        FindUserPage.searchUser("Bui Ke Ton Tuong");
        
        String expectedName = "Bui Ke Ton Tuong";
        String actualName = FindUserPage.getFoundUserName();
        
        System.out.println("==========================================");
        System.out.println("Check Search Result:");
        System.out.println("Expect: " + expectedName);
        System.out.println("Actual: " + actualName);
        Assert.assertEquals(actualName, expectedName, "User name does not match expected");
        System.out.println("==========================================");
    }

    @Test
    public void TC_SU_03() {
        System.out.println("🔹 Kiểm tra tìm kiếm người dùng theo email...");
        FindUserPage.clickUserTab();
        FindUserPage.searchUser("tuong.2274802010979");
        
        String expectedEmail = "tuong.2274802010979@vanlanguni.vn";
        String actualEmail = FindUserPage.getFoundUserEmail();
        
        System.out.println("==========================================");
        System.out.println("Check Search Result:");
        System.out.println("Expect: " + expectedEmail);
        System.out.println("Actual: " + actualEmail);
        Assert.assertTrue(actualEmail.contains(expectedEmail), "Email does not contain expected value");
        System.out.println("==========================================");
    }
}