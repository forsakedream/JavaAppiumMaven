package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {

    private static final String AppiumUrl = "http://127.0.0.1:4723/wd/hub/",
                                PLATFORM_ANDROID = "android",
                                PLATFORM_IOS = "ios";

    private static Platform instance;

    private Platform() {}

    public static Platform getInstance()
    {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver<WebElement> getDriver() throws Exception {

        URL URL = new URL(AppiumUrl);
        if (this.isAndroid()) {
            return new AndroidDriver<>(URL, this.getAndroidCapabilities());

        } else if (this.isIOS()) {
            return new IOSDriver<>(URL, this.getIOSCapabilities());

        } else {
            throw new Exception("Can't get Appium driver from env variable: " + this.getPlatform());
        }
    }

    private DesiredCapabilities getAndroidCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "avd1");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("orientation", "PORTRAIT");
        capabilities.setCapability("app", "/Users/dkylosov/IdeaProjects/JavaAppiumAutomation/apk/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 11");
        capabilities.setCapability("platformVersion", "15");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("udid", "8D8EC757-448A-4BD7-83D2-014F5862E819");
        capabilities.setCapability("app", "/Users/dkylosov/IdeaProjects/JavaAppiumAutomation/apk/Wikipedia.app");
        return capabilities;
    }

    private boolean isPlatform(String platform)
    {
        String env_platform = this.getPlatform();
        return env_platform.equals(platform);
    }

    private String getPlatform()
    {
        return System.getenv("PLATFORM");
    }

    public boolean isAndroid()
    {
        return this.isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIOS()
    {
        return this.isPlatform(PLATFORM_IOS);
    }
}
