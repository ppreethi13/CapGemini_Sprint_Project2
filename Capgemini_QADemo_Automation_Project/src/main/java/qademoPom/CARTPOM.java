package qademoPom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CARTPOM {

    public CARTPOM(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Add Laptop Backpack to cart
    @FindBy(css = "[data-testid='product-add-to-cart-3']")
    private WebElement addToCartButton;

    // Shopping cart icon
    @FindBy(css = "[data-testid='navbar-cart-link']")
    private WebElement cartLink;

    // Cart items
    @FindBy(css = "[data-testid='cart-items-list']")
    private WebElement cartItems;
    
    @FindBy(css = "[data-testid='cart-item-remove-3']")
    private WebElement removeProductButton;

    public void clickRemoveProduct() {
        removeProductButton.click();
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public void clickCart() {
        cartLink.click();
    }

    public boolean isCartDisplayed() {
        return cartItems.isDisplayed();
    }
}
