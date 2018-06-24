package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase{
  @Test
  public void testSaveTwoArticlesInList() throws InterruptedException {

    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    ArticlePageObject.waitForTitleElement();
    String name_of_folder = "Learning programming";
    ArticlePageObject.addArticleToMyList(name_of_folder);
    ArticlePageObject.closeArticle();
    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Python");
    SearchPageObject.waitForSearchResult("General-purpose, high-level programming language");
    ArticlePageObject.waitForTitleElement();
    ArticlePageObject.addArticleToMyExistingList("Learning programming");
    ArticlePageObject.closeArticle();

    NavigationUI NavigationUI = new NavigationUI(driver);
    NavigationUI.openMyList();
    NavigationUI.checkFolderIsCreated();

    MyListPageObject MyListPageObject = new MyListPageObject(driver);
    MyListPageObject.openFolderByName(name_of_folder);
    MyListPageObject.swipeByArticleToDelete("Java (programming language)");
    MyListPageObject.checkTitleinMyListEqualToTitleOnArticlePage("Python");
  }


}
