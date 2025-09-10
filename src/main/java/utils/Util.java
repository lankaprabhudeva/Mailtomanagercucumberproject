package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Util {

    WebDriver driver;

    public Util(WebDriver driver) {
        this.driver = driver;
    }

    // Wait until element is visible
    public void waitForVisibility(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait until element is clickable
    public void waitForClickable(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Safe click
    public void clickElement(WebElement element, int seconds) {
        waitForClickable(element, seconds);
        element.click();
    }

    // Safe send keys
    public void sendKeys(WebElement element, String text, int seconds) {
        waitForVisibility(element, seconds);
        element.clear();
        element.sendKeys(text);
    }

    // Hover over element
    public void hoverOverElement(WebElement element, int seconds) {
        waitForVisibility(element, seconds);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
