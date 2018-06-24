package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {
  private static final String
          MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']",
          ARTICLE_ITEM = "id:org.wikipedia:id/item_container";

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
