package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

  protected AppiumDriver driver;

  public MainPageObject(AppiumDriver driver)

  {
    this.driver = driver;
  }

  public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
    return element.getAttribute(attribute);
  }

  public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds)
  {
    By by = this.getLocatorByString(locator);
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    return wait.until(
            ExpectedConditions.presenceOfElementLocated(by)
    );
  }

  public WebElement waitForElementPresent(String locator, String error_message) {
    return waitForElementPresent(locator, error_message, 5);
  }

  public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(locator, error_message, 5);
    element.click();
    return element;
  }


  public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(locator, error_message, 5);
    element.sendKeys(value);
    return element;
  }

  public boolean waitForElementIsNotPresent(String locator, String error_message, long timeoutInSeconds) {

    By by = this.getLocatorByString(locator);
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    return wait.until(
            ExpectedConditions.invisibilityOfElementLocated(by)
    );
  }

  public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
    element.clear();
    return element;
  }

  public WebElement checkForTextSearchAndSendKeys(String locator, String value, String error_msg, long timeoutInSeconds) {
    WebElement search_field_element = waitForElementPresent(
            "xpath://*[contains(@text, 'Search…')]",
            "Cannot   fill in Search Input!",
            5
    );
    Assert.assertEquals(
            "Text in Search Field before sending keys is not expected",
            "Search…",
            search_field_element.getAttribute("text")
    );
    return waitForElementAndSendKeys(locator, value, error_msg, timeoutInSeconds);
  }

  public void checkTitles(List<WebElement> results, String value) {
    for (WebElement element : results) {
      String text = element.getAttribute("text");
      Assert.assertTrue("Title doesn't contain search que text!",
              text.toLowerCase().contains(value.toLowerCase()));
    }
  }

  public void swipeUp(int timeOfSwipe) {

    TouchAction action = new TouchAction(driver);
    Dimension size = driver.manage().window().getSize();
    int x = size.width / 2;
    int start_y = (int) (size.height * 0.8);
    int end_y = (int) (size.height * 0.2);

    action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
  }

  public void swipeUpQuick() {

    swipeUp(200);
  }

  public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {

    int already_swiped = 0;


    while (driver.findElements(this.getLocatorByString(locator)).size() == 0) {

      if (already_swiped > max_swipes) {
        waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message, 0);
        return;
      }

      swipeUpQuick();
      already_swiped++;
    }
  }
  public void swipeUpTillElementAppear (String locator, String error_message, int max_swipes) {
    int already_swiped = 0;

    while(!this.isElementLocatedOnTheScreen(locator)) {
      if(already_swiped > max_swipes){
        Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
      }

      swipeUpQuick();
      ++already_swiped;
    }
  }


  public boolean isElementLocatedOnTheScreen(String locator){

    int element_location_by_y = this.waitForElementPresent(
            locator,
            "Cannot find element by locator!",
            5
    ).getLocation().getY();

    int screen_size_by_y = driver.manage().window().getSize().getHeight();
    return element_location_by_y < screen_size_by_y;
  }

  public void clickElementToTheRightUpperCorner(String locator, String error_message) {

    WebElement element = this.waitForElementPresent(locator + "/..",error_message,5);
    int right_x = element.getLocation().getX();
    int upper_y = element.getLocation().getY();
    int lower_y = upper_y + element.getSize().getHeight();
    int middle_y = (upper_y + lower_y) / 2;
    int width = element.getSize().getWidth();

    int point_to_click_x = (right_x + width) - 3;
    int point_to_click_y = middle_y;

    TouchAction action = new TouchAction(driver);
    action.tap(point_to_click_x, point_to_click_y).perform();
  }

  public void swipeElementToLeft(String locator, String error_message) {
    WebElement element = waitForElementPresent(
            locator,
            error_message,
            10);

    int left_x = element.getLocation().getX();
    int right_x = left_x + element.getSize().getWidth();
    int upper_y = element.getLocation().getY();
    int lower_y = upper_y + element.getSize().getHeight();
    int middle_y = (upper_y + lower_y) / 2;

    TouchAction action = new TouchAction(driver);
    action.press(right_x, middle_y);
    action.waitAction(300);
    if (Platform.getInstance().isAndroid()){
      action.moveTo(left_x,middle_y);
    } else {
      int offset_x = (-1 * element.getSize().getWidth());
      action.moveTo(offset_x,0);
    }
    action.release();
    action.perform();

  }


  public int getAmountOfElements(String locator) {

    By by = this.getLocatorByString(locator);
    List elements = driver.findElements(by);
    return elements.size();
  }

  public void checkForFewSearchResultsArePresented() {
    int numberOfSearchResults = driver.findElements(By.id("org.wikipedia:id/page_list_item_container")).size();
    Assert.assertTrue("Number of search results is less than a few!", numberOfSearchResults >= 2);
  }

  public void checkForSearchResultAreEmpty() {
    waitForElementIsNotPresent(
            "id:org.wikipedia:id/search_results_list",
            "Search result list is present!",
            5
    );
  }

  public void assertElementPresent(By by) {
    Assert.assertTrue(
            "Cannot find article's title!",
            driver.findElements(by).size() >= 1);
  }

  public WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    By by = By.xpath(xpath);
    return wait.until(
            ExpectedConditions.presenceOfElementLocated(by)
    );
  }

  public List<WebElement> getListOfSearchResultTitles() {
    waitForElementPresent(
            "id:org.wikipedia:id/page_list_item_title",
            "No search result titles available!",
            5
    );
    return driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
  }

  private By getLocatorByString(String locator_with_type)
  {
    String[] explored_locator = locator_with_type.split(Pattern.quote(":"), 2);
    String by_type = explored_locator[0];
    String locator = explored_locator[1];

    if (by_type.equals("xpath")) {
      return By.xpath(locator);
    } else if (by_type.equals("id")) {
      return By.id(locator);
    } else if (by_type.equals("name")) {
      return By.name(locator);
    } else {
      throw new IllegalArgumentException("Cannot get type of locator.Locator " + locator_with_type);
    }
  }
}
