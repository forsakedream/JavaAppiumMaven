package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver<WebElement> driver;

    public MainPageObject(AppiumDriver<WebElement> driver)
    {
        this.driver = driver;
    }

    public void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int)(size.height * 0.8);
        int end_y = (int)(size.height * 0.2);
        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y)).release().perform();
    }

    public void swipeElementToLeft(String locator)
    {
        WebElement element = waitForElementPresent(locator);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int midle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x, midle_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                .moveTo(PointOption.point(left_x, midle_y)).release().perform();

    }

    public void swipeUpQuick()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElements(String locator)
    {
        By by = getLocatorByString(locator);
        driver.findElements(by);
        int max_swipes = 20;
        int already_swiped = 0;
        while (driver.findElements(by). size() == 0){
            if (already_swiped > max_swipes){
                waitForElementPresent(locator);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void waitForElementContainsTextAndCLick(String locator, String value, long timeoutInSeconds)
    {
        waitForElementPresent(locator, timeoutInSeconds);
        By by = getLocatorByString(locator);
        List<WebElement> elements = driver.findElements(by);
        for (WebElement element : elements){
            if (element.getText().contains(value)) {
                element.click();
                return;
            }
        }
    }

    public void waitForElementContainsTextAndCLick(String locator, String value)
    {
        waitForElementContainsTextAndCLick(locator, value, 15);
    }

    public void assertElementPresent(String locator){
        By by = getLocatorByString(locator);
        Assert.assertEquals("This page doesn't contains element " + by + " !",
                1, driver.findElements(by).size());
    }

    public WebElement waitForElementPresent(String locator, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("Can't find element " + locator + "!\n");
        By by = getLocatorByString(locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator)
    {
        return waitForElementPresent(locator, 15);
    }

    public WebElement waitForElementAndClick(String locator, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    public WebElement waitForElementAndClick(String locator)
    {
        return waitForElementAndClick(locator, 15);
    }

    public WebElement waitForElementAndSendKeys(String locator, String value)
    {
        return waitForElementAndSendKeys(locator, value, 15);
    }

    public boolean waitForElementNotPresent(String locator, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        By by = getLocatorByString(locator);
        wait.withMessage("Element "+ by + " is still present!");
        return  wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public boolean waitForElementNotPresent(String locator)
    {
        return  waitForElementNotPresent(locator, 15);
    }

    public WebElement waitForElementAndClear(String locator, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, timeoutInSeconds);
        element.clear();
        return element;
    }
    public WebElement waitForElementAndClear(String locator)
    {
        return waitForElementAndClear(locator, 15);
    }

    public void assertElementHasText(String locator, String expected, String error_message)
    {
        WebElement element = waitForElementPresent(locator);
        boolean isContains = element.getText().contains(expected);
        Assert.assertTrue(error_message, isContains);
    }
    public void assertElementHasText(String locator, String expected)
    {
        assertElementHasText(locator, expected, "This element has other text!");
    }

    protected By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        switch (by_type) {
            case "xpath":
                return By.xpath(locator);
            case "id":
                return By.id(locator);
            case "chain":
                return MobileBy.iOSClassChain(locator);
            default:
                throw new IllegalArgumentException("Can't get type of locator: " + locator_with_type);
        }
    }

}
