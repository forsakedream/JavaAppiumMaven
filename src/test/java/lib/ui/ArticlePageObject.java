package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String FOOTER, TITLE, OPTIONS, ADD_TO_LIST, ONBOARDING_BUTTON, LIST_TITLE_FIELD,
            OK, LIST_TITLE, CLOSE_BUTTON;

    public ArticlePageObject(AppiumDriver<WebElement> driver)
    {
        super(driver);
    }

    public void swipeUpToFooter(){
        this.swipeUpToFindElements(FOOTER);
    }

    public void waitForArticleTitle(){
        this.waitForElementPresent(TITLE);
    }

    public void closeArticle(){
        this.waitForElementAndClick(CLOSE_BUTTON);
    }

    public void assertArticleHasTitle(String title)
    {
        waitForElementPresent(TITLE);
        assertElementHasText(TITLE, title);
    }

    public void assertTitlePresent()
    {
        assertElementPresent(TITLE);
    }

    public void skipTutorial(){
        String pop_up = "chain:**/XCUIElementTypeStaticText[`label == \"Tap to go back\"`]";
        waitForElementPresent(pop_up);
        waitForElementNotPresent(pop_up);
    }

    public abstract void addArticleToNewList(String list_title);

    public abstract void addArticleToExistingList(String list_title);
}
