import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class SecondTest extends CoreTestCase {
  private lib.ui.MainPageObject MainPageObject;

  protected void setUp() throws Exception {
    super.setUp();

    MainPageObject = new MainPageObject(driver);
  }

  @Test
  public void testAvailabilityOfText() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Python");
    SearchPageObject.waitForSearchResult("General-purpose, high-level programming language");
  }


  @Test
  public void testCancelSearch() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Python");
    SearchPageObject.checkSearchResultsListForFewArticles();
    SearchPageObject.waitForCancelButtonToAppear();
    SearchPageObject.clickCancelSearch();
    SearchPageObject.checkSearchResultsListIsNotPresent();

  }

  @Test
  public void testCancelSearch2() {
    SearchPageObject searchPageObject = new SearchPageObject(driver);

    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.waitForCancelButtonToAppear();
    searchPageObject.clickCancelSearch();
    searchPageObject.clickCancelSearch();
    searchPageObject.waitForCancelButtonToDissappear();
  }

  @Test
  public void testCompareArticleTitle() {
    SearchPageObject searchPageObject = new SearchPageObject(driver);

    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Python");
    searchPageObject.clickByArticleWithSubstring("General-purpose, high-level programming language");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    String article_title = ArticlePageObject.getArticleTitle();

    Assert.assertEquals(
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

