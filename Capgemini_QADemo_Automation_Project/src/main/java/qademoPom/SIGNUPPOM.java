package qademoPom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SIGNUPPOM {

    private WebDriver driver;

    public SIGNUPPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-testid='navbar-signup-link'] button")
    private WebElement signUpLink;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signUpButton;

    public void clickSignUpLink() {
        signUpLink.click();
    }

    public void getEmail(String value) {
        email.sendKeys(value);
    }

    public void getPhone(String value) {
        phone.sendKeys(value);
    }

    public void getUsername(String value) {
        username.sendKeys(value);
    }

    public void getPassword(String value) {
        password.sendKeys(value);
    }

    public void getConfirmPassword(String value) {
        confirmPassword.sendKeys(value);
    }

    public void clickSubmit() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", signUpButton);
    }
}