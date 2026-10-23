package ecom.qademo.Order;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import BaseClassUtility.BaseClass;
import GenericUtility.ExcelUtility;
import GenericUtility.LoginUtility;
import GenericUtility.Screenshot;
import qademoPom.CHECKOUTPOM;
import qademoPom.ORDERPOM;
import qademoPom.PRODUCTPOM;

public class OrderTest extends BaseClass {

    ExcelUtility excel = new ExcelUtility();

    // Login, add product to cart, and open checkout
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

    // Positive test: Verify successful order confirmation
    @Test(priority = 1, groups = {"pos", "smoke","IT"})
    public void verifyOrderConfirmationTest() throws Exception {

        CHECKOUTPOM checkout = openCheckout();

        checkout.enterFirstName(excel.getData("Checkout", 1, 0));
        checkout.enterLastName(excel.getData("Checkout", 1, 1));
        checkout.enterShippingAddress(excel.getData("Checkout", 1, 2));

        checkout.enterCardNumber(excel.getData("Checkout", 1, 3));
        checkout.enterExpiryDate(excel.getData("Checkout", 1, 4));
        checkout.enterCVV(excel.getData("Checkout", 1, 5));
        checkout.enterNameOnCard(excel.getData("Checkout", 1, 6));

        checkout.clickPlaceOrder();
      //  Thread.sleep(2000);

        ORDERPOM order = new ORDERPOM(driver);

        Assert.assertTrue(order.isOrderConfirmationPageDisplayed(),"Order confirmation page was not displayed");
        

        Reporter.log("Order confirmation verified successfully", true);
    }

    // Negative test: Verify an order is not placed with invalid card details
    @Test(priority = 2, groups = {"neg", "FT"})
    public void orderShouldNotBePlacedWithInvalidCardTest() throws Exception {

        CHECKOUTPOM checkout = openCheckout();

        checkout.enterFirstName(excel.getData("Checkout", 1, 0));
        checkout.enterLastName(excel.getData("Checkout", 1, 1));
        checkout.enterShippingAddress(excel.getData("Checkout", 1, 2));

        // Invalid payment data from Checkout sheet, row index 3
        checkout.enterCardNumber(excel.getData("Checkout", 3, 3));
        checkout.enterExpiryDate(excel.getData("Checkout", 3, 4));
        checkout.enterCVV(excel.getData("Checkout", 3, 5));
        checkout.enterNameOnCard(excel.getData("Checkout", 3, 6));

        checkout.clickPlaceOrder();
        Thread.sleep(2000);

        Screenshot.takeScreenshot(driver, "InvalidCardOrderResult");

        Assert.assertFalse(driver.getCurrentUrl().contains("/orders/"),"An order was placed with invalid card details");

        Reporter.log("Order was not placed with invalid card details",true);
    }
}

