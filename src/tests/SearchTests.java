package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

  @Test
  public void testAvailabilityOfText() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Python");
    SearchPageObject.waitForSearchResult("General-purpose, high-level programming language");
  }

  @Test
  public void testCancelSearch() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Python");
    SearchPageObject.checkSearchResultsListForFewArticles();
    SearchPageObject.waitForCancelButtonToAppear();
    SearchPageObject.clickCancelSearch();
    SearchPageObject.checkSearchResultsListIsNotPresent();
  }

  @Test
  public void testCancelSearch2() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.waitForCancelButtonToAppear();
    SearchPageObject.clickCancelSearch();
    SearchPageObject.clickCancelSearch();
    SearchPageObject.waitForCancelButtonToDissappear();
  }

  @Test
  public void testAmountOfNotEmptySearch() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    String search_line = "Lenin a bio";
    SearchPageObject.typeSearchLine(search_line);
    int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

    assertTrue(
            "We found too few results!",
            amount_of_search_results > 0
    );
  }

  @Test
  public void testTitleElementIsPresent() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
    ArticlePageObject.assertTitleIsPresentOnArticlesPage();
  }

  @Test
  public void testFindArticleByTitleAndSubstring_ex9() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
    SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
    SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");
  }

  @Test
  public void testFindArticleByTitleAndSubstringIOS_ex11() throws InterruptedException{
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    MainPageObject MainPageObject = new MainPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
    searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
    searchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");
  }
}
