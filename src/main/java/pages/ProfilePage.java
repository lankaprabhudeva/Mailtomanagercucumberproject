package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage {

    @FindBy(xpath="//img[@class=\"nI-gNb-icon-img\"]") // profile pic
    private WebElement profilePicture;

    @FindBy(xpath="//a[contains(text(),'View & Update Profile')]") // link to profile
    private WebElement viewUpdateProfileLink;

    @FindBy(xpath="//input[@value=\"Update resume\"]") // resume input
    private WebElement uploadResumeInput;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickProfilePicture() {
        util.clickElement(profilePicture, 10);
    }

    public void clickViewUpdateProfile() {
        util.clickElement(viewUpdateProfileLink, 10);
    }

    public void scrollToResumeSection() {
    	
    	util.waitForVisibility(uploadResumeInput, 20);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView(true);", uploadResumeInput
        );
    }

    public void uploadResume(String filePath) {
    	
    	
    	util.waitForClickable(uploadResumeInput, 30);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].style.display='block';", uploadResumeInput
        );
        uploadResumeInput.sendKeys(filePath);
    }

    public WebElement getUploadResumeInput() {
        return uploadResumeInput;
    }
}
