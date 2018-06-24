package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

  private static final String
          TITLE= "id:org.wikipedia:id/view_page_title_text",
          FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
          OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
          OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
          ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
          MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
          SAVE_TO_MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
          CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
          EXISTING_LIST_NAME_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/item_title'][@text = '{LIST NAME}']";

  public ArticlePageObject(AppiumDriver driver) {
    super(driver);
  }

  public WebElement waitForTitleElement() {
    return this.waitForElementPresent(TITLE, "Cannot find an article title on a page title!", 15);
  }

  private static String getListXpathByName(String list_name) {
    return EXISTING_LIST_NAME_TPL.replace("{LIST NAME}", list_name);
  }

  public String getArticleTitle() {
    WebElement title_elemnt = waitForTitleElement();
    return title_elemnt.getAttribute("text");
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
    this.swipeUpToFindElement(
            FOOTER_ELEMENT,
            "Cannot find the end of article!",
            20
    );
  }
}

