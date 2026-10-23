package ecom.qademo.Login;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;


import BaseClassUtility.BaseClass;
import GenericUtility.ExcelUtility;
import GenericUtility.Screenshot;
import qademoPom.SIGNUPPOM;
import qademoPom.LOGINPOM;

public class LoginTest extends BaseClass {

    ExcelUtility eu = new ExcelUtility();

    @Test(priority = 1, groups = {"pos", "smoke"})
    public void validUserSignUp() throws Exception {

        String email = eu.getData("SignUp", 1, 0);
        String phone = eu.getData("SignUp", 1, 1);
        String username = eu.getData("SignUp", 1, 2);
        String password = eu.getData("SignUp", 1, 3);
        String confirmPassword = eu.getData("SignUp", 1, 4);

        SIGNUPPOM signup = new SIGNUPPOM(driver);

        signup.clickSignUpLink();
        signup.getEmail(email);
        signup.getPhone(phone);
        signup.getUsername(username);
        signup.getPassword(password);
        signup.getConfirmPassword(confirmPassword);
        signup.clickSubmit();

        Thread.sleep(2000);

        boolean result = !driver.getCurrentUrl().equals("https://qademo.com/");

        Assert.assertTrue(result, "User signup failed");

        Reporter.log(email + " --> SIGNUP IS PASSED", true);
    }

    @Test(priority = 2,groups = {"pos", "smoke"})
    public void validUserSignIn() throws Exception {

        String email = eu.getData("SignIn", 1, 0);
        String password = eu.getData("SignIn", 1, 1);

        LOGINPOM login = new LOGINPOM(driver);

        login.clickSignInLink();
        login.getEmail(email);
        login.getPassword(password);
        login.clickSignIn();

        Thread.sleep(2000);

        boolean result = !driver.getCurrentUrl().equals("https://qademo.com/");

        Assert.assertTrue(result, "Valid user signin failed");

        Reporter.log(email + " --> SIGNIN IS PASSED", true);

    }
    @Test(priority = 3, groups = {"neg", "ST","IT"})
    public void invalidPasswordSignIn() throws Exception {

        // Valid email + invalid password
        String email = eu.getData("SignIn", 2, 0);
        String password = eu.getData("SignIn", 2, 1);

        LOGINPOM login = new LOGINPOM(driver);

        login.clickSignInLink();
        login.getEmail(email);
        login.getPassword(password);
        login.clickSignIn();

        Thread.sleep(2000);

        // Verify login was rejected.
        boolean result = driver.getCurrentUrl().contains("signin");

        Assert.assertFalse(result,"Login succeeded or the expected sign-in page was not displayed");

        Reporter.log("Invalid password login PASSED", true);
        Screenshot.takeScreenshot(driver, "Invalid_Password_SignIn");
    }
    @Test(priority = 4, groups = {"neg", "FT"})
    public void invalidUsername() throws Exception {

        // inValid email + valid password
        String email = eu.getData("SignIn", 3, 0);
        String password = eu.getData("SignIn", 3, 1);

        LOGINPOM login = new LOGINPOM(driver);

        login.clickSignInLink();
        login.getEmail(email);
        login.getPassword(password);
        login.clickSignIn();

        Thread.sleep(2000);

        // Verify login was rejected.
        boolean result = driver.getCurrentUrl().contains("signin");

        Assert.assertFalse(result,"Login succeeded or the expected sign-in page was not displayed");

        Reporter.log("Invalid password login PASSED", true);
        Screenshot.takeScreenshot(driver, "Invalid_UserName_SignIn");
    }
}