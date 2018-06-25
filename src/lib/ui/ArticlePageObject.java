package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

  protected static String
          TITLE,
          FOOTER_ELEMENT,
          OPTIONS_BUTTON,
          OPTIONS_ADD_TO_MY_LIST_BUTTON,
          ADD_TO_MY_LIST_OVERLAY,
          MY_LIST_NAME_INPUT,
          SAVE_TO_MY_LIST_OK_BUTTON,
          CLOSE_ARTICLE_BUTTON,
          EXISTING_LIST_NAME_TPL;

  public ArticlePageObject(AppiumDriver driver) {
    super(driver);
  }

  public WebElement waitForTitleElement() {
    return this.waitForElementPresent(TITLE, "Cannot find an article title on a page title!", 25);
  }

  private static String getListXpathByName(String list_name) {
    return EXISTING_LIST_NAME_TPL.replace("{LIST NAME}", list_name);
  }

  public String getArticleTitle() {
    WebElement title_element = waitForTitleElement();
    if (Platform.getInstance().isAndroid()) {
      return title_element.getAttribute("text");
    } else {
      return title_element.getAttribute("name");
    }
  }

  public void addArticleToMyList(String name_of_folder) throws InterruptedException {
    this.waitForElementAndClick(
            OPTIONS_BUTTON,
            "Cannot find button to open article options!",
            5
    );

    this.waitForElementAndClick(
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            "Cannot find 'Add to reading list' option!",
            5
    );

    this.waitForElementAndClick(
            ADD_TO_MY_LIST_OVERLAY,
            "Cannot find 'GOT IT' overlay button!",
            5
    );

    this.waitForElementAndClear(
            MY_LIST_NAME_INPUT,
            "Cannot find input to set name of articles folder!",
            5
    );

    this.waitForElementAndSendKeys(
            MY_LIST_NAME_INPUT,
            name_of_folder,
            "Cannot put text to 'Name of the list' field",
            5
    );

    this.waitForElementAndClick(
            SAVE_TO_MY_LIST_OK_BUTTON,
            "Cannot press 'OK' button!",
            5
    );
  }

  public void addArticleToMyExistingList(String name_of_folder) throws InterruptedException {
    this.waitForElementAndClick(
            OPTIONS_BUTTON,
            "Cannot find 'More options' button!",
            5
    );

    this.waitForElementAndClick(
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            "Cannot find 'Add to reading list' option!",
            5
    );

    this.waitForElementAndClick(
            getListXpathByName(name_of_folder),
            "Cannot click on saved articles list for the second article!",
            5
    );
  }

  public void closeArticle() {
    this.waitForElementAndClick(
            CLOSE_ARTICLE_BUTTON,
            "Cannot find X button!",
            5
    );
  }

  public void assertTitleIsPresentOnArticlesPage() {
    this.assertElementPresent(By.id(TITLE));
  }

  public void swipeArticleToFooter() {

    if (Platform.getInstance().isAndroid()) {
      this.swipeUpToFindElement(
              FOOTER_ELEMENT,
              "Cannot find the end of article!",
              40
      );
    }else{
        swipeUpTillElementAppear(
                FOOTER_ELEMENT,
                "Cannot find footer after swipes!",
                40
        );
      }
    }
  public void addArticleToMySaved(){
    this.waitForElementAndClick(
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            "Cannot find option to add article to reading list",
            10
    );
  }
  }


