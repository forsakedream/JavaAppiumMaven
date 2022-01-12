package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class WelcomePageObject extends MainPageObject{
    public static final String SKIP = "chain:**/XCUIElementTypeButton[`label == \"Skip\"`]",
            TODAY = "chain:**/XCUIElementTypeStaticText[`label == \"Today\"`]";


    public WelcomePageObject(AppiumDriver<WebElement> driver) {
        super(driver);
    }

    public void skip()
    {
        this.waitForElementAndClick(SKIP);
        waitForElementPresent(TODAY, 30);
    }
}
