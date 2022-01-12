package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.WebElement;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{TITLE}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESC_TPL = "xpath://*[contains(@text,'{TITLE}')]/following-sibling::*[contains(@text, '{DESCRIPTION}')]";
        SEARCH_RESULTS_CONTAINER = "id:org.wikipedia:id/search_results_list";
        SEARCH_RESULT = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
    }
    public AndroidSearchPageObject(AppiumDriver<WebElement> driver) {
        super(driver);
    }
}
