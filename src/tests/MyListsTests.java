package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class MyListsTests extends CoreTestCase {

  private static final String name_of_folder = "Learning programming";

  @Test
  public void testSaveTwoArticlesInList() throws InterruptedException {

    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
    ArticlePageObject.waitForTitleElement();
    // String name_of_folder = "Learning programming";
    ArticlePageObject.addArticleToMyList(name_of_folder);
    ArticlePageObject.closeArticle();
    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Python");
    SearchPageObject.waitForSearchResult("General-purpose, high-level programming language");
    ArticlePageObject.waitForTitleElement();
    ArticlePageObject.addArticleToMyExistingList("Learning programming");
    ArticlePageObject.closeArticle();

    NavigationUI NavigationUI = NavigationUIFactory.get(driver);
    NavigationUI.openMyList();
    NavigationUI.checkFolderIsCreated();

    MyListPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
    MyListPageObject.openFolderByName(name_of_folder);
    MyListPageObject.swipeByArticleToDelete("Java (programming language)");
    MyListPageObject.checkTitleinMyListEqualToTitleOnArticlePage("Python");
  }

  @Test
  public void testSaveTwoArticles_ex10() throws InterruptedException {

    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
    NavigationUI NavigationUI = NavigationUIFactory.get(driver);
    MyListPageObject myListPageObject = MyListsPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
    ArticlePageObject.waitForTitleElement();

    // Open the article to your favorites for different platforms
    if (Platform.getInstance().isAndroid()) {
      ArticlePageObject.addArticleToMyList(name_of_folder);
    } else {
      ArticlePageObject.addArticleToMySaved();
    }

    // After the addition, close the article
    ArticlePageObject.closeArticle();
    SearchPageObject.initSearchInput();

    //before entering a new query, tap on the field clear button from the previous query
    if (Platform.getInstance().isIOS()) {
      SearchPageObject.clickCLearSearchInputButton();
    }
    SearchPageObject.typeSearchLine("Python");
    SearchPageObject.clickByArticleWithSubstring("General-purpose, high-level programming language");
    ArticlePageObject.waitForTitleElement();


    // Add an open article to favorites for different platforms
    if (Platform.getInstance().isAndroid()) {
      ArticlePageObject.addArticleToMyExistingList(name_of_folder);
    } else {
      ArticlePageObject.addArticleToMySaved();
    }


    ArticlePageObject.closeArticle();

    // Open the list of selected articles
    NavigationUI.openMyList();
    /*Implemented two different checks for iOS (by the presence of the article item in the list after deletion)
    and Android (on one of the lines in the content of the article)*/
    if (Platform.getInstance().isAndroid()) {
      // For Android preliminary go to the folder with the appropriate name
      myListPageObject.openFolderByName(name_of_folder);
      // Delete the article with the appropriate title
      myListPageObject.swipeByArticleToDelete("Java (programming language)");
      // Verify the remaining article in the list for one of the lines in the table of contents
      myListPageObject.checkArticleIsStillPresentByContentsItem("Python", "programming language");
    } else {
      // In the case of iOS, have to storage elements of the article, we will not remove (then to see if it still is)
      WebElement article_to_stay_element = myListPageObject.getArticleElementByName("Python");
      // Delete the article with the appropriate title
      myListPageObject.swipeByArticleToDelete("Java (programming language)");
      // For iOS in this method, at first get all the remaining containers after the removal of containers for articles,
      // fill them with the ArrayList and look for an item from the list that should remain
      myListPageObject.checkArticleElementIsStillPresentInList(article_to_stay_element);
    }
  }
}