package ecom.qademo.Cart;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import BaseClassUtility.BaseClass;
import GenericUtility.LoginUtility;
import GenericUtility.Screenshot;
import qademoPom.PRODUCTPOM;
import qademoPom.CARTPOM;

public class CartTest extends BaseClass {

    // Positive test: Verify product is added to cart
    @Test(priority = 1, groups = {"pos", "smoke"})
    public void verifyAddToCart() throws Exception {

        LoginUtility loginUtility = new LoginUtility();
        loginUtility.validUserSignIn(driver);

        PRODUCTPOM product = new PRODUCTPOM(driver);
        product.clickProduct();

        Thread.sleep(2000);

        product.clickAddToCart();

        Thread.sleep(2000);

        CARTPOM cart = new CARTPOM(driver);
        cart.clickCart();

        Thread.sleep(2000);

        Assert.assertTrue(cart.isCartDisplayed(),"Cart items are not displayed");

        Reporter.log("Add to cart test PASSED", true);
    }

    // Negative test: Verify empty cart
    @Test(priority = 2, groups = {"neg", "ST"})
    public void verifyEmptyCart() throws Exception {

        driver.get("https://qademo.com/cart");

        Thread.sleep(2000);

        String pageText = driver.findElement(By.tagName("body")).getText().toLowerCase();

        Assert.assertTrue(pageText.contains("empty") || pageText.contains("no items"),"Expected empty-cart message was not displayed");

        Screenshot.takeScreenshot(driver, "Empty_Cart");

        Reporter.log("Empty cart test PASSED", true);
    }
 // Positive test: Verify product is removed from cart
    @Test(priority = 3, groups = {"pos", "smoke","IT"})
    public void verifyRemoveProduct() throws Exception {

        LoginUtility loginUtility = new LoginUtility();
        loginUtility.validUserSignIn(driver);

        // Open the product details page
        driver.get("https://qademo.com/products/laptop-backpack");

        Thread.sleep(2000);

        PRODUCTPOM product = new PRODUCTPOM(driver);
        product.clickAddToCart();

        Thread.sleep(2000);

        CARTPOM cart = new CARTPOM(driver);
        cart.clickCart();

        Thread.sleep(2000);

        // Remove the product from cart
        cart.clickRemoveProduct();

        Thread.sleep(2000);

        // Verify the product was removed
        String pageText = driver.findElement(By.tagName("body")).getText().toLowerCase();

        Assert.assertTrue(
            pageText.contains("empty") || pageText.contains("no items"),"Product was not removed from cart");

        Reporter.log("Remove product test PASSED", true);
    }
}