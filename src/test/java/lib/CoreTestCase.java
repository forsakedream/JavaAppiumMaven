package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;

public class CoreTestCase{

    protected AppiumDriver<WebElement> driver;

    @Before
    @Step("Run device and create session")
    public void setUp() throws Exception
    {
        driver = Platform.getInstance().getDriver();
        this.skipWelcomeScreenForIOS();
    }

    @After
    @Step("Delete session")
    public void tearDown() throws Exception
    {
        driver.quit();
    }

    protected void skipWelcomeScreenForIOS()
    {
        if (Platform.getInstance().isIOS())
        {
            WelcomePageObject WelcomePage = new WelcomePageObject(driver);
            WelcomePage.skip();
        }
    }


}
