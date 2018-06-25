package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

  protected static String
          MY_LISTS_LINK,
          ARTICLE_ITEM;

  public NavigationUI(AppiumDriver driver) {
    super(driver);
  }

  public void openMyList() {
    this.waitForElementAndClick(
            MY_LISTS_LINK,
            "Can't find 'To my lists' button!",
            5
    );
  }

  public void checkFolderIsCreated() {
    this.waitForElementAndClick(
            ARTICLE_ITEM,
            "Can't find created folder!",
            5
    );
  }
}
