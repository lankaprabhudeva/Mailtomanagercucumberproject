package pages;

import org.openqa.selenium.WebDriver;
import utils.Util;

public class BasePage {

    protected WebDriver driver;
    protected Util util;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.util = new Util(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
