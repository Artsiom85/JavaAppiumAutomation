package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

  protected AppiumDriver driver;

  public MainPageObject(AppiumDriver driver)

  {
    this.driver = driver;
  }

  public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
    return element.getAttribute(attribute);
  }

  public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    return wait.until(
            ExpectedConditions.presenceOfElementLocated(by)
    );
  }

  public WebElement waitForElementPresent(By by, String error_message) {
    return waitForElementPresent(by, error_message, 5);
  }

  public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, 5);
    element.click();
    return element;
  }


  public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, 5);
    element.sendKeys(value);
    return element;
  }

  public boolean waitForElementIsNotPresent(By by, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    return wait.until(
            ExpectedConditions.invisibilityOfElementLocated(by)
    );
  }


  public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
    element.clear();
    return element;
  }

  public WebElement checkForTextSearchAndSendKeys(By by, String value, String error_msg, long timeoutInSeconds) {
    WebElement search_field_element = waitForElementPresent(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Cannot   fill in Search Input!",
            5
    );
    Assert.assertEquals(
            "Text in Search Field before sending keys is not expected",
            "Search…",
            search_field_element.getAttribute("text")
    );
    return waitForElementAndSendKeys(by, value, error_msg, timeoutInSeconds);
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

  public void swipeUpToFindElement(By by, String error_message, int max_swipes) {

    int already_swiped = 0;


    while (driver.findElements(by).size() == 0) {

      if (already_swiped > max_swipes) {
        waitForElementPresent(by, "Can't find element by swiping up. \n" + error_message, 0);
        return;
      }

      swipeUpQuick();
      already_swiped++;
    }
  }


  public void swipeElementToLeft(By by, String error_message) {
    WebElement element = waitForElementPresent(by,
            error_message,
            10);

    int left_x = element.getLocation().getX();
    int right_x = left_x + element.getSize().getWidth();
    int upper_y = element.getLocation().getY();
    int lower_y = upper_y + element.getSize().getHeight();
    int middle_y = (upper_y + lower_y) / 2;

    TouchAction action = new TouchAction(driver);
    action
            .press(right_x, middle_y)
            .waitAction(300)
            .moveTo(left_x, middle_y)
            .release()
            .perform();

  }


  public int getAmountOfElements(By by) {
    List elements = driver.findElements(by);
    return elements.size();
  }

  public void checkForFewSearchResultsArePresented() {
    int numberOfSearchResults = driver.findElements(By.id("org.wikipedia:id/page_list_item_container")).size();
    Assert.assertTrue("Number of search results is less than a few!", numberOfSearchResults >= 2);
  }

  public void checkForSearchResultAreEmpty() {
    waitForElementIsNotPresent(
            By.id("org.wikipedia:id/search_results_list"),
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
            By.id("org.wikipedia:id/page_list_item_title"),
            "No search result titles available!",
            5
    );
    return driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
  }
}
