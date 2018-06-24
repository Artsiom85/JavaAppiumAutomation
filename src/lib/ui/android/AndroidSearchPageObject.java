package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject
{
   static {
     SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
             SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
             SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '{SUBSTRING}']";
             SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
             SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*{@resource-id='org.wikipedia:id/page_list_item_container']";
             //A locator template that finds the result of the search simultaneously by the title and description
             SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL = "xpath://*[android.widget.TextView[@index=0 and @text='{TITLE}'] and android.widget.TextView[@index=1 and @text='{SUBSTRING}']]";
   }
  public AndroidSearchPageObject(AppiumDriver driver) {
    super(driver);
  }
}
