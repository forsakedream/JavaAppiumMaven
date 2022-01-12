package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.WebElement;

public class AndroidMyListPageObject extends MyListPageObject {

    static {
        LIST_TITLE = "id:org.wikipedia:id/item_title";
        ARTICLE_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']";
        ARTICLE_TITLE = "id:org.wikipedia:id/page_list_item_title";
    }

    public AndroidMyListPageObject(AppiumDriver<WebElement> driver) {
        super(driver);
    }

    public void deleteArticleFromList(String article)
    {
        String articleLocator = getArticleElementByTitle(article);
        this.swipeElementToLeft(articleLocator);
        this.waitForElementNotPresent(articleLocator);
    }
}
