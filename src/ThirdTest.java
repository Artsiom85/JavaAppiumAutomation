import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

public class ThirdTest extends CoreTestCase {

  private MainPageObject MainPageObject;

  public void setUp() throws Exception {
    super.setUp();

    MainPageObject = new MainPageObject(driver);
  }

  @Test
  public void testSaveTwoArticlesInList() throws InterruptedException {

    SearchPageObject SearchPageObject = new SearchPageObject(driver);

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


  @Test
  public void testAmountOfNotEmptySearch() {

    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    String search_line = "Lenin a bio";
    SearchPageObject.typeSearchLine(search_line);
    int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

    Assert.assertTrue(
            "We found too few results!",
            amount_of_search_results > 0
    );
  }

  @Test
  public void testTitleElementIsPresent() {

    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject articlePageObject = new ArticlePageObject(driver);
    articlePageObject.assertTitleIsPresentOnArticlesPage();
  }

  @Test
  public void screenRotation() {

    MainPageObject.waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot click on the 'Search' Field!",
            5
    );

    String search_line = "Java";
    MainPageObject.waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            search_line,
            "Cannot find search input!",
            5
    );

    MainPageObject.waitForElementPresent(
            By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Object-oriented programming language']"),
            "Cannot find 'Object-oriented programming language' topic searching by" + search_line,
            15
    );

    String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
            By.id("org.wikipedia:id/view_page_title_text"),
            "text",
            "Cannot find title of article",
            15
    );

    driver.rotate(ScreenOrientation.LANDSCAPE);

    String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
            By.id("org.wikipedia:id/view_page_title_text"),
            "text",
            "Cannot find title of article",
            15
    );

    Assert.assertEquals(
            "Article title have been changed after screen rotation",
            title_before_rotation,
            title_after_rotation
    );

  }

  @Test
  public void testScreenRotation() {

    //Установили ориентацию экрана при инициализации драйвера в соответствии с заданным capability "orientation"

    MainPageObject.waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot click on the 'Search' Field!",
            5
    );

    MainPageObject.waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Java",
            "Cannot find search input!",
            5
    );

    MainPageObject.waitForElementPresent(
            By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
            "Cannot find a title of article",
            15
    );

    String search_query_text = MainPageObject.waitForElementPresent(
            By.id("org.wikipedia:id/search_src_text"),
            "Cannot get text from search field!",
            5
    ).getAttribute("text");

    Assert.assertEquals(
            "Text in search filed is not expected!",
            "Java",
            search_query_text
    );
  }

}

