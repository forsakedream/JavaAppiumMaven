package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {
    protected static String SEARCH_INIT_ELEMENT, SEARCH_INPUT, SEARCH_RESULT_BY_TITLE_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESC_TPL, SEARCH_RESULTS_CONTAINER, SEARCH_RESULT,
            SEARCH_CANCEL_BUTTON;

    public SearchPageObject(AppiumDriver<WebElement> driver)
    {
        super(driver);
    }

    /* TEMPLATES_METHODS */
    private static String getResultSearchElementByTitle(String substring)
    {
        return SEARCH_RESULT_BY_TITLE_TPL.replace("{TITLE}", substring);
    }

    //    Метод был добавлен при рефакторинге тестов в предыдущем коммите
    private static String getSearchResultByTitleAndDesc(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_AND_DESC_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES_METHODS */

    @Step("Initializing search")
    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT);
    }

    @Step("Type value in a search field")
    public void typeSearchLine(String value)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, value);
    }

    public void assertSearchLineHasText(){
        this.assertElementHasText(SEARCH_INPUT, "Search",
                "This element has other text!");
    }

    @Step("Wait For Search Result")
    public void waitForSearchResult(String value)
    {
        String search_result_xpath = getResultSearchElementByTitle(value);
        this.waitForElementPresent(search_result_xpath);
    }

    public void waitForSearchResults()
    {
        this.waitForElementPresent(SEARCH_RESULTS_CONTAINER);
    }

    public void waitForSearchResultsDisappear()
    {
        this.waitForElementNotPresent(SEARCH_RESULTS_CONTAINER);
    }

    public List<WebElement> getSearchResults(String search)
    {
        waitForSearchResult(search);
        By by = getLocatorByString(getResultSearchElementByTitle(search));
        return driver.findElements(by);
    }

    public void clearSearchLine()
    {
        this.waitForElementAndClear(SEARCH_INPUT);
    }

    public void waitForSearchCancelButtonAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON);

    }

    public void waitForSearchCancelButtonDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON);

    }

    public void clickCancelSearch()
    {
        waitForElementAndClick(SEARCH_CANCEL_BUTTON);
    }

    public void clickSearchResultByTitle(String title)
    {
        String search_result_xpath = getResultSearchElementByTitle(title);
        this.waitForElementAndClick(search_result_xpath);
    }

    public WebElement waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getSearchResultByTitleAndDesc(title, description);
        return this.waitForElementPresent(search_result_xpath);
    }

    public void clickSearchResultByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getSearchResultByTitleAndDesc(title, description);
        this.waitForElementAndClick(search_result_xpath);
    }

    @Step("Search And Select Article with title {title}")
    public void searchAndSelectArticle(String title){
        initSearchInput();
        typeSearchLine(title);
        clickSearchResultByTitle(title);
    }

    @Step("Search And Select Article with title {title} and {description}")
    public void searchAndSelectArticle(String title, String description){
        initSearchInput();
        typeSearchLine(title);
        clickSearchResultByTitleAndDescription(title, description);
    }

    public int getSearchResultsAmount(String search)
    {
        List<WebElement> elements = getSearchResults(search);
        return elements.size();
    }

}
