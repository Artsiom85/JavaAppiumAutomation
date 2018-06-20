package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

  private static final String

          SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
          SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
          SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '{SUBSTRING}']",
          SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
          SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*{@resource-id='org.wikipedia:id/page_list_item_container']",
  //A locator template that finds the result of the search simultaneously by the title and description
          SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL = "//*[android.widget.TextView[@index=0 and @text='{TITLE}'] and android.widget.TextView[@index=1 and @text='{SUBSTRING}']]";

  public SearchPageObject(AppiumDriver driver) {
    super(driver);
  }

  //The method for substitution into the template
  private static String getResultSearchElementByTitleAndSubstring(String title, String substring) {
    return SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL.replace("{TITLE}", title).replace("{SUBSTRING}", substring);
  }

  //initialize Template method
  private static String getResultSearchElement(String substring) {
    return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
  }

  public void initSearchInput() {
    this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element!", 5);
    this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after click search init element!");
  }


  public void typeSearchLine(String search_line) {
    this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input!", 5);
  }

  public void waitForSearchResult(String substring) {

    String search_result_xpath = getResultSearchElement(substring);
    this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring! " + substring);
  }

  public void waitForCancelButtonToAppear() {
    this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button!", 5);
  }

  public void waitForCancelButtonToDissappear() {
    this.waitForElementIsNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present!", 5);
  }

  public void clickCancelSearch() {
    this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button!", 5);
  }

  public void clickByArticleWithSubstring(String substring) {
    this.waitForElementAndClick(By.xpath(getResultSearchElement(substring)), "Cannot find and click search result with substring! " + substring, 10);
  }

  public int getAmountOfFoundArticles() {

    this.waitForElementPresent(
            By.xpath(SEARCH_RESULT_ELEMENT),
            "Cannot find anything by the request",
            15
    );
    return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
  }

  public void checkSearchResultsListForFewArticles() {
    this.checkForFewSearchResultsArePresented();
  }

  public void checkSearchResultsListIsNotPresent() {

    this.checkForSearchResultAreEmpty();
  }

  //The method of clicking on an element with a locator with the specified name and substring
  public void waitForElementByTitleAndDescription(String title, String substring) {
    this.waitForElementPresent(By.xpath(getResultSearchElementByTitleAndSubstring(title, substring)),
            "Cannot find search result with title \"" + title + "\" and substring \"" + substring + "\"!",
            10);
  }
}
