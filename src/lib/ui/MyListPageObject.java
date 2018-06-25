package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

abstract public class MyListPageObject extends MainPageObject {

  protected static String
          FOLDER_BY_NAME_TPL,
          ARTICLE_BY_TITLE_TPL,
          ARTICLE_ITEM_CONTAINER;

  private static String getFolderXpathByName(String name_of_folder) {
    return FOLDER_BY_NAME_TPL.replace("{FOLDER NAME}", name_of_folder);
  }

  private static String getTitleXpathByName(String title) {
    return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", title);
  }

  public MyListPageObject(AppiumDriver driver) {
    super(driver);
  }

  public void openFolderByName(String name_of_folder) {
    String folder_name_xpath = getFolderXpathByName(name_of_folder);
    this.waitForElementAndClick(
            folder_name_xpath,
            "Cannot find folder by name " + name_of_folder,
            5
    );
  }

  public void swipeByArticleToDelete(String article_title) {
    this.waitForArticleToApearByTitle(article_title);
    String article_xpath = getTitleXpathByName(article_title);
    this.swipeElementToLeft(
            article_xpath,
            "Cannot find saved article!"
    );
    if (Platform.getInstance().isIOS()) {
      this.clickElementToTheRightUpperCorner(
              article_xpath,
              "Cannot tap on delete article button!"
      );
    }
    if(Platform.getInstance().isAndroid()){

    this.waitForArticleToDissapearByTitle(article_title);}
  }

  public void waitForArticleToDissapearByTitle(String article_title) {

    String article_xpath = getFolderXpathByName(article_title);
    this.waitForElementIsNotPresent(
            article_xpath,
            "Saved article still present with title " + article_title,
            15
    );
  }

  public void waitForArticleToApearByTitle(String article_title) {
    String article_xpath = getFolderXpathByName(article_title);
    this.waitForElementPresent(
            article_xpath,
            "Cannot find saved article by title " + article_title,
            15
    );
  }

  public void checkTitleinMyListEqualToTitleOnArticlePage(String name) {
    String title_in_list = waitForElementPresent(
            "xpath://*[@text='" + name + "']",
            "Cannot find second's article entry in saved articles list!",
            5
    ).getAttribute("text");

    waitForElementAndClick(
            "xpath://*[@text='" + name + "']",
            "Cannot click on second's article entry in saved articles list!",
            5
    );

    String title_on_articles_page = waitForElementPresent(
            "id:org.wikipedia:id/view_page_title_text",
            "Cannot find " + name + " article's title on the page!",
            10
    ).getAttribute("text");

    assertEquals("Title on the articles list and title on article's page are not equals",
            title_in_list,
            title_on_articles_page);
  }

  public void checkArticleIsStillPresentByContentsItem(String name, String content_line_to_check) {

    waitForElementAndClick(
            "xpath://*[@text='" + name + "']",
            "Cannot click on second's article entry in saved articles list",
            20
    );
    waitForElementPresent(
            "xpath://*[@content-desc='Table of Contents']",
            "Cannot find 'Open contents' button",
            20);

    waitForElementAndClick(
            "xpath://*[@content-desc='Table of Contents']",
            "Cannot click on 'Open contents' button",
            20
    );

    waitForElementPresent(
            "xpath://*[@text='" + content_line_to_check + "']",
            "Cannot find '" + content_line_to_check + "' a content line",
            20
    );
  }
  public void checkArticleElementIsStillPresentInList(WebElement article_element) throws InterruptedException {
    List<WebElement> list = driver.findElements(By.xpath(ARTICLE_ITEM_CONTAINER));
    assertTrue("Not expected: Second article is not in the list", list.contains(article_element));
  }

  public WebElement getArticleElementByName(String article_title) {
    if (Platform.getInstance().isIOS()) {
      return this.waitForElementPresent(getTitleXpathByName(article_title) + "/..",
              "Cannot find target article with title" + article_title,
              20
      );
    } else {
      return this.waitForElementPresent(getTitleXpathByName(article_title) + "/../../..",
              "Cannot find target article with title" + article_title,
              20
      );
    }
  }

}
