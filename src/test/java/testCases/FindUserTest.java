package testCases;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FindUserPage;
import java.util.List;

public class FindUserTest extends BaseTest {
    private FindUserPage FindUserPage;

    @BeforeClass
    public void setupClass() {
        FindUserPage = new FindUserPage(driver, wait);
    }

    @Test
    public void testFindUserById() {
        // Click on User tab and search for user by ID
        FindUserPage.clickUserTab();
        FindUserPage.searchUser("2274802010979");
        
        // Verify the search results
        boolean userFound = FindUserPage.verifyUserById("2274802010979");
        Assert.assertTrue(userFound, "User with ID 2274802010979 should be found");
    }

    @Test
    public void testFindUserByName() {
        // Click on User tab and search for user by name
        FindUserPage.clickUserTab();
        FindUserPage.searchUser("Bui Ke Ton Tuong");
        
        // Verify the search results
        boolean userFound = FindUserPage.verifyUserByName("Bui Ke Ton Tuong");
        Assert.assertTrue(userFound, "User with name 'Bui Ke Ton Tuong' should be found");
    }

    @Test
    public void testFindUserByEmail() {
        // Click on User tab and search for user by email
        FindUserPage.clickUserTab();
        FindUserPage.searchUser("tuong.2274802010979");
        
        // Verify the search results
        boolean userFound = FindUserPage.verifyUserByEmail("tuong.2274802010979");
        Assert.assertTrue(userFound, "User with email containing 'tuong.2274802010979' should be found");
    }
}