package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class MyListPageObject extends MainPageObject {

  public static final String
          FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER NAME}']",
          ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";

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
    String article_xpath = getFolderXpathByName(article_title);
    this.swipeElementToLeft(
            article_xpath,
            "Cannot find saved article!"
    );

    this.waitForArticleToDissapearByTitle(article_title);
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

    assertEquals("Title on the articles list and title on article's page are not equals!",
            title_in_list,
            title_on_articles_page);
  }
}
