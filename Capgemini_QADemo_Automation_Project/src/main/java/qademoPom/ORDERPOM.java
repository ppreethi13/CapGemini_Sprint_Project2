
package qademoPom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ORDERPOM {

    public ORDERPOM(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


	@FindBy(css = "[data-testid='order-confirmation-page']")
    private WebElement orderConfirmationPage;

    @FindBy(css = "[data-testid='order-confirmation-heading']")
    private WebElement orderConfirmationHeading;

    @FindBy(css = "[data-testid='order-confirmation-number']")
    private WebElement orderConfirmationNumber;

    @FindBy(xpath = "//a[normalize-space()='Continue Shopping']")
    private WebElement continueShoppingButton;

    public boolean isOrderConfirmationPageDisplayed() {
        return orderConfirmationPage.isDisplayed();
    }

    public String getOrderConfirmationHeading() {
        return orderConfirmationHeading.getText();
    }

    public String getOrderNumber() {
        return orderConfirmationNumber.getText();
    }

    public void clickContinueShopping() {
        continueShoppingButton.click();
    }

}
