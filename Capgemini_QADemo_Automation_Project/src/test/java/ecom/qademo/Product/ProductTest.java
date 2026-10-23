
package ecom.qademo.Product;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import BaseClassUtility.BaseClass;
import GenericUtility.LoginUtility;
import GenericUtility.Screenshot;

public class ProductTest extends BaseClass {

    // Positive test: Verify product details page
    @Test(priority = 1, groups = {"pos", "smoke"})
    public void verifyProduct() throws Exception {

        // Login using existing utility
        LoginUtility loginUtility = new LoginUtility();
        loginUtility.validUserSignIn(driver);

        Thread.sleep(2000);

        // Verify product details page
        Assert.assertTrue(driver.getCurrentUrl().contains("catalog"),"Product details page did not open");

        Reporter.log("Product test PASSED", true);
    }

    // Negative test: Verify invalid product URL
    @Test(priority = 2, groups = {"neg", "ST"})
    public void invalidProductTest() throws Exception {

        driver.get("https://qademo.com/products/invalid-product");

        Thread.sleep(2000);

        String pageText = driver.findElement(By.tagName("body")).getText().toLowerCase();

        Assert.assertTrue(
            pageText.contains("not found") || pageText.contains("404"),
            "Expected invalid-product error was not displayed");

        Screenshot.takeScreenshot(driver, "Invalid_Product");

        Reporter.log("Invalid product test PASSED", true);
    }
}
