package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {

  static {
    MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    //ARTICLE_ITEM = "id:org.wikipedia:id/item_container";
    ARTICLE_ITEM = "xpath://android.widget.TextView[@text='Learning programming']";

  }

  public AndroidNavigationUI(AppiumDriver driver)
  {
    super(driver);
  }
}