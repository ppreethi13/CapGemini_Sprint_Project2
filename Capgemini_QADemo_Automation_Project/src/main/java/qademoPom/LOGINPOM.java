package qademoPom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LOGINPOM {
	WebDriver driver;

    public LOGINPOM(WebDriver driver) {
    	this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Sign In navbar link
    @FindBy(css = "[data-testid='navbar-signin-link']")
    private WebElement signInLink;

    // Email field
    @FindBy(xpath = "//input[@placeholder='Enter your username or email']")
    private WebElement email;

    // Password field
    @FindBy(xpath = "//input[@placeholder='Enter your password']")
    private WebElement password;

    // Sign In submit button
    @FindBy(css = "[data-testid='login-submit-button']")
    private WebElement signInButton;

    public void clickSignInLink() {
        signInLink.click();
    }

    public void getEmail(String value) {
        email.sendKeys(value);
    }

    public void getPassword(String value) {
        password.sendKeys(value);
    }

    public void clickSignIn() {
        By signInButton =
                By.cssSelector("[data-testid='login-submit-button']");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(signInButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", button);

        button = wait.until(
                ExpectedConditions.elementToBeClickable(signInButton));

        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            // Fallback if the normal click is intercepted
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", button);
        }
    }
}
