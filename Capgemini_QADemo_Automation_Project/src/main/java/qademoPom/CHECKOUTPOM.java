package qademoPom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;

public class CHECKOUTPOM {

	private WebDriver driver;

	public CHECKOUTPOM(WebDriver driver) {
	    this.driver = driver;
	    PageFactory.initElements(driver, this);
	}

    @FindBy(css = "[data-testid='proceed-to-checkout-button']")
    private WebElement proceedToCheckoutButton;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(css = "[data-testid='checkout-address']")
    private WebElement shippingAddress;

    // Payment Information fields

    @FindBy(css = "[data-testid='checkout-card-number']")
    private WebElement cardNumber;

    // Confirmed from your screenshot
    @FindBy(css = "[data-testid='checkout-expiry']")
    private WebElement expiryDate;

    @FindBy(css = "[data-testid='checkout-cvv']")
    private WebElement cvv;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCard;
    
    @FindBy(css = "[data-testid='place-order-button']")
    private WebElement placeOrderButton;

    public void clickProceedToCheckout() {
        proceedToCheckoutButton.click();
    }

    public void enterFirstName(String value) {
        firstName.clear();
        firstName.sendKeys(value);
    }

    public void enterLastName(String value) {
        lastName.clear();
        lastName.sendKeys(value);
    }

    public void enterShippingAddress(String value) {
        shippingAddress.clear();

        if (value != null && !value.trim().isEmpty()) {
            shippingAddress.sendKeys(value);
        }
    }

    public void enterCardNumber(String value) {
        cardNumber.clear();
        cardNumber.sendKeys(value);
    }

    public void enterExpiryDate(String value) {
        expiryDate.clear();
        expiryDate.sendKeys(value);
    }

    public void enterCVV(String value) {
        cvv.clear();
        cvv.sendKeys(value);
    }

    public void enterNameOnCard(String value) {
        nameOnCard.clear();
        nameOnCard.sendKeys(value);
    }

    public void clickPlaceOrder() {
        new Actions(driver)
                .moveToElement(placeOrderButton)
                .click()
                .perform();
    }
}