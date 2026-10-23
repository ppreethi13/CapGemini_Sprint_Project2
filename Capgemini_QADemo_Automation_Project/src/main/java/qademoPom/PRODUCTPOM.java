package qademoPom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PRODUCTPOM {

    public PRODUCTPOM(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-testid='product-link-3']")
    private WebElement product;

    @FindBy(css = "[data-testid='product-add-to-cart-button']")
    private WebElement addToCartButton;

 // Confirm this locator against the actual View Cart HTML
    @FindBy(css ="[class='lucide lucide-shopping-cart w-6 h-6']")
    private WebElement viewCartLink;

    public void clickProduct() {
        product.click();
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public void clickViewCart()  {
        viewCartLink.click();
    }
}