package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "login_Layer")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@placeholder='Enter your active Email ID / Username']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Enter your password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver); // initializes driver + Util
        PageFactory.initElements(driver, this);
    }

    public void clickLogin() {
        util.clickElement(loginButton, 10);
    }

    public void enterEmail(String email) {
        util.sendKeys(emailField, email, 10);
    }

    public void enterPassword(String password) {
        util.sendKeys(passwordField, password, 10);
    }

    public void clickSubmit() {
        util.clickElement(submitButton, 10);
    }
}
