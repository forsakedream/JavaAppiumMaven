package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class article extends CoreTestCase {

    private SearchPageObject SearchPageObject;
    private ArticlePageObject ArticlePageObject;
    private MyListPageObject MyListPageObject;
    private NavigationUI NavigationUI;

    public void setUp() throws Exception
    {
        super.setUp();

        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);
        MyListPageObject = MyListPageObjectFactory.get(driver);
        NavigationUI = NavigationUIFactory.get(driver);
    }

    @Test
    @DisplayName("Swipe Article To Footer")
    @Step("Start test testSwipeArticle")
    public void testSwipeArticle()
    {
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickSearchResultByTitle("Appium");
        ArticlePageObject.waitForArticleTitle();
        ArticlePageObject.swipeUpToFooter();
    }

    @Test
    @DisplayName("Save 2 Articles to My List")
    @Description("Search For 2 Articles, Save Them To My List")
    public void testSaveTwoArticlesToMyList()
    {
        SearchPageObject.searchAndSelectArticle("Appium");
        if  (Platform.getInstance().isIOS()) {ArticlePageObject.skipTutorial();}
        ArticlePageObject.waitForArticleTitle();
        ArticlePageObject.addArticleToNewList("Learning");
        ArticlePageObject.closeArticle();
        SearchPageObject.searchAndSelectArticle("Java", "Object-oriented programming language");
        ArticlePageObject.waitForArticleTitle();
        ArticlePageObject.addArticleToExistingList("Learning");
        ArticlePageObject.closeArticle();
        NavigationUI.goToMyList();
        MyListPageObject.selectList("Learning");
        MyListPageObject.deleteArticleFromList("Appium");
        MyListPageObject.assertListHasArticle("Java (programming language)");
        MyListPageObject.clickArticleFromList("Java (programming language)");
        ArticlePageObject.assertArticleHasTitle("Java (programming language)");
    }

//    public void testAssertTitle(){
//        SearchPageObject.searchAndSelectArticle("Appium");
//        ArticlePageObject.assertTitlePresent();
//    }

}
