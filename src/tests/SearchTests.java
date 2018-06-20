package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase{
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
  public void testAmountOfNotEmptySearch() {

    SearchPageObject SearchPageObject = new SearchPageObject(driver);

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

    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    ArticlePageObject.assertTitleIsPresentOnArticlesPage();
  }

  @Test
  public void testFindArticleByTitleAndSubstring_ex9() {
           SearchPageObject searchPageObject = new SearchPageObject(driver);
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine("Java");
            searchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
            searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
            searchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");
        }
}
