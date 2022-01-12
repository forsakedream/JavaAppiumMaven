package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.WebElement;

public class iOSNavigationUI extends NavigationUI {
    private static final String
    READING_LISTS = "chain:**/XCUIElementTypeStaticText[`label == \"Reading lists\"`]";

    static
    {
        MY_LIST = "chain:**/XCUIElementTypeButton[`label == \"Saved\"`]";
    }

    public iOSNavigationUI(AppiumDriver<WebElement> driver) {
        super(driver);
    }

    @Override
    public void goToMyList() {
        super.goToMyList();
        this.waitForElementAndClick(READING_LISTS);
    }
}
