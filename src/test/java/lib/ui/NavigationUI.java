package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

abstract public class NavigationUI extends MainPageObject{
    protected static String MY_LIST;

    public NavigationUI(AppiumDriver<WebElement> driver)
    {
        super(driver);
    }
    public void goToMyList()
    {
        this.waitForElementAndClick(MY_LIST);
    }

}
