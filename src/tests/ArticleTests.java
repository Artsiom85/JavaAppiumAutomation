package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
  @Test
  public void testCompareArticleTitle() {
    SearchPageObject searchPageObject = new SearchPageObject(driver);

    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Python");
    searchPageObject.clickByArticleWithSubstring("General-purpose, high-level programming language");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    String article_title = ArticlePageObject.getArticleTitle();

    assertEquals(
            "We see unexpected title!",
            "Python (programming language)",
            article_title
    );
  }
  @Test
  public void testSwipeArticle() {

    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Appium");
    searchPageObject.clickByArticleWithSubstring("Appium");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    ArticlePageObject.waitForTitleElement();
    ArticlePageObject.swipeArticleToFooter();
  }
}
