package ecom.qademo.Checkout;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import GenericUtility.ExcelUtility;
import GenericUtility.LoginUtility;
import GenericUtility.Screenshot;
import qademoPom.CHECKOUTPOM;
import qademoPom.PRODUCTPOM;

public class CheckoutTest extends BaseClass {
     
	ExcelUtility excel = new ExcelUtility();

    // Login, add product, open cart, and proceed to checkout
    public CHECKOUTPOM openCheckout() throws Exception {

        LoginUtility loginUtility = new LoginUtility();
        loginUtility.validUserSignIn(driver);

        PRODUCTPOM product = new PRODUCTPOM(driver);

        System.out.println("Step 1: Clicking product");
        product.clickProduct();
        Thread.sleep(1500);

        System.out.println("Step 2: Adding product to cart");
        product.clickAddToCart();
        Thread.sleep(1500);

        System.out.println("Step 3: Opening cart");
        product.clickViewCart();
        Thread.sleep(1500);

        CHECKOUTPOM checkout = new CHECKOUTPOM(driver);

        System.out.println("Step 4: Proceeding to checkout");
        checkout.clickProceedToCheckout();
        Thread.sleep(1500);

        return checkout;
    }

    // Positive test: Valid checkout details
    @Test(priority = 1, groups = {"pos", "smoke"})
    public void validCheckoutTest() throws Exception {

        CHECKOUTPOM checkout = openCheckout();

        checkout.enterFirstName(excel.getData("Checkout", 1, 0));
        checkout.enterLastName(excel.getData("Checkout", 1, 1));
        checkout.enterShippingAddress(excel.getData("Checkout", 1, 2));
        checkout.enterCardNumber(excel.getData("Checkout", 1, 3));
        checkout.enterExpiryDate(excel.getData("Checkout", 1, 4));
        checkout.enterCVV(excel.getData("Checkout", 1, 5));
        checkout.enterNameOnCard(excel.getData("Checkout", 1, 6));

        checkout.clickPlaceOrder();
        Thread.sleep(2000);

        Assert.assertFalse(driver.getCurrentUrl().contains("/login"),"Unexpectedly redirected to login after checkout");
        Reporter.log("Valid checkout test executed", true);
    }

    // Negative test: Invalid card details
    @Test(priority = 2, groups = {"neg", "ST"})
    public void invalidCardCheckoutTest() throws Exception {

        CHECKOUTPOM checkout = openCheckout();

        checkout.enterFirstName(excel.getData("Checkout", 1, 0));
        checkout.enterLastName(excel.getData("Checkout", 1, 1));
        checkout.enterShippingAddress(excel.getData("Checkout", 1, 2));
        checkout.enterCardNumber(excel.getData("Checkout", 3, 3));
        checkout.enterExpiryDate(excel.getData("Checkout", 3, 4));
        checkout.enterCVV(excel.getData("Checkout", 3, 5));
        checkout.enterNameOnCard(excel.getData("Checkout", 3, 6));

        checkout.clickPlaceOrder();
        Thread.sleep(2000);
        Assert.assertTrue( driver.getCurrentUrl().contains("checkout"),"Expected checkout to remain open for invalid card details");
        
        Screenshot.takeScreenshot(driver, "InvalidCardCheckoutResult1");

        Reporter.log("Invalid card checkout test executed", true);
    }
    
    
    @Test(priority = 3, groups = {"neg", "FT", "IT"})
    public void missingAddressCheckoutTest() throws Exception {

        CHECKOUTPOM checkout = openCheckout();

        checkout.enterFirstName(excel.getData("Checkout", 2, 0));
        checkout.enterLastName(excel.getData("Checkout", 2, 1));
        checkout.enterShippingAddress(excel.getData("Checkout", 2, 2));
        checkout.enterCardNumber(excel.getData("Checkout", 2, 3));
        checkout.enterExpiryDate(excel.getData("Checkout", 2, 4));
        checkout.enterCVV(excel.getData("Checkout", 2, 5));
        checkout.enterNameOnCard(excel.getData("Checkout", 2, 6));

        checkout.clickPlaceOrder();
        Thread.sleep(2000);

        Assert.assertTrue(
            driver.getCurrentUrl().contains("checkout"),
            "Expected checkout to remain open when address is missing"
        );

        Screenshot.takeScreenshot(driver, "MissingAddressCheckoutResult1");

        Reporter.log("Missing address checkout test executed", true);
    }
    @Test(priority = 4, groups = {"neg", "FT"})
    public void missingNameCheckoutTest() throws Exception {

        CHECKOUTPOM checkout = openCheckout();

        checkout.enterFirstName(excel.getData("Checkout", 4, 0));
        checkout.enterLastName(excel.getData("Checkout", 4, 1));
        checkout.enterShippingAddress(excel.getData("Checkout", 4, 2));
        checkout.enterCardNumber(excel.getData("Checkout", 4, 3));
        checkout.enterExpiryDate(excel.getData("Checkout", 4, 4));
        checkout.enterCVV(excel.getData("Checkout", 4, 5));
        checkout.enterNameOnCard(excel.getData("Checkout", 4, 6));

        checkout.clickPlaceOrder();
        Thread.sleep(2000);

        Assert.assertTrue(
            driver.getCurrentUrl().contains("checkout"),
            "Expected checkout to remain open when first name is missing"
        );

        Screenshot.takeScreenshot(driver, "MissingNameCheckoutResult");

        Reporter.log("Missing Name checkout test executed", true);
    }



}