<<<<<<< HEAD
import lib.CoreTestCase;
import lib.ui.MainPageObject;
=======
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
<<<<<<< HEAD

public class ThirdTest extends CoreTestCase {

  private MainPageObject MainPageObject;
  public void setUp() throws Exception
  {
    super.setUp();

    MainPageObject = new MainPageObject(driver);
  }

  @Test
  public void testSaveTwoArticlesInList() {
    MainPageObject.waitForElementAndClick(
=======
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ThirdTest extends CoreTestCase {

  @Test
  public void testSaveTwoArticlesInList() {
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input!",
            5
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndSendKeys(
=======
    waitForElementAndSendKeys(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Python",
            "Cannot find search input!",
            5
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='General-purpose, high-level programming language']"),
            "Cannot find 'Search Wikipedia' input!",
            15
    );

<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//android.widget.ImageView[@content-desc='More options']"),
            "Cannot find button to open article options!",
            15
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[@class='android.widget.RelativeLayout']//*[@text='Add to reading list']"),
            "Cannot find option to add article to reading list!",
            15
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.id("org.wikipedia:id/onboarding_button"),
            "Cannot find 'Got it' tip overlay!",
            15
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndClear(
=======
    waitForElementAndClear(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.id("org.wikipedia:id/text_input"),
            "Cannot find input to set name of articles folder!",
            5
    );

    String name_of_folder = "Learning programming";
<<<<<<< HEAD
    MainPageObject.waitForElementAndSendKeys(
=======
    waitForElementAndSendKeys(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.id("org.wikipedia:id/text_input"),
            name_of_folder,
            "Cannot put text into articles folder input!",
            5
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[@text='OK']"),
            "Cannot press 'OK' button!",
            5
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
            "Cannot close article, cannot find X link!",
            5
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input!",
            5
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndSendKeys(
=======
    waitForElementAndSendKeys(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Java",
            "Cannot find search input!",
            15
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
            "Cannot find 'Search Wikipedia' input!",
            15
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//android.widget.ImageView[@content-desc='More options']"),
            "Cannot find button to open article options!",
            15
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[@class='android.widget.RelativeLayout']//*[@text='Add to reading list']"),
            "Cannot find option to add article to reading list!",
            15
    );

<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[@text='Learning programming'"),
            "Cannot find the folder!",
            15
    );

<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//android.widget.FrameLayout[@content_desc='My lists']"),
            "Cannot find navigation button to My list!",
            15
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[@text='" + name_of_folder + "']"),
            "Cannot find created folder!",
            15
    );

<<<<<<< HEAD
    MainPageObject.swipeElementToLeft(
=======
    swipeElementToLeft(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[@text='Python (programming language)']"),
            "Cannot find saved article!"
    );

<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//android.widget.FrameLayout[@content_desc='My lists']"),
            "Cannot find navigation button to My list!",
            5
    );
<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[@text='Learning programming'"),
            "Cannot find the folder!",
            5
    );
<<<<<<< HEAD
    WebElement title_element = MainPageObject.waitForElementPresent(
=======
    WebElement title_element = waitForElementPresent(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.id("org.wikipedia:id/view_page_title_text"),
            "cannot find article title",
            15

    );
    String article_title = title_element.getAttribute("Java (programming language)");

    Assert.assertEquals(
            "We see unexpected title",
            "Java (programming language)",
            article_title
    );
  }

  @Test
  public void testAmountOfNotEmptySearch() {
<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            5
    );
    String search_line = "Lenin a bio";
<<<<<<< HEAD
    MainPageObject.waitForElementAndSendKeys(
=======
    waitForElementAndSendKeys(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[contains(@text, 'Search…')]"),
            search_line,
            "Cannot find search input",
            5
    );
    String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
<<<<<<< HEAD
    MainPageObject.waitForElementPresent(
=======
    waitForElementPresent(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath(search_result_locator),
            "Cannot find anything by the request " + search_line,
            15
    );

<<<<<<< HEAD
    int amount_of_search_results = MainPageObject.getAmountOfElement(
=======
    int amount_of_search_results = getAmountOfElement(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath(search_result_locator)
    );

    Assert.assertTrue(
            "We found too few results!",
            amount_of_search_results > 0
    );
  }

  @Test
  public void testTitleElementIsPresent() {

<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.id("org.wikipedia:id/search_container"),
            "Can't find Search Input!",
            5
    );

<<<<<<< HEAD
    MainPageObject.waitForElementAndSendKeys(
=======
    waitForElementAndSendKeys(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Java",
            "Can't fill in Search Input!",
            5
    );

<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text ='Object-oriented programming language']"),
            "Can't find Entry!",
            10
    );

<<<<<<< HEAD
    MainPageObject.assertElementPresent(
=======
    assertElementPresent(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.id("org.wikipedia:id/view_page_title_text")
    );

  }

  @Test
  public void screenRotation() {

<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot click on the 'Search' Field!",
            5
    );
    String search_line = "Java";
<<<<<<< HEAD
    MainPageObject.waitForElementAndSendKeys(
=======
    waitForElementAndSendKeys(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[contains(@text, 'Search…')]"),
            search_line,
            "Cannot find search input!",
            5
    );
<<<<<<< HEAD
    MainPageObject.waitForElementPresent(
=======
    waitForElementPresent(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Object-oriented programming language']"),
            "Cannot find 'Object-oriented programming language' topic searching by" + search_line,
            15
    );

<<<<<<< HEAD
    String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
=======
    String title_before_rotation = waitForElementAndGetAttribute(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.id("org.wikipedia:id/view_page_title_text"),
            "text",
            "Cannot find title of article",
            15
    );
    driver.rotate(ScreenOrientation.LANDSCAPE);

<<<<<<< HEAD
    String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
=======
    String title_after_rotation = waitForElementAndGetAttribute(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.id("org.wikipedia:id/view_page_title_text"),
            "text",
            "Cannot find title of article",
            15
    );

    Assert.assertEquals(
            "Article title have been changed after screen rotation",
            title_before_rotation,
            title_after_rotation
    );

  }

  @Test
  public void testScreenRotation() {

    //Установили ориентацию экрана при инициализации драйвера в соответствии с заданным capability "orientation"

<<<<<<< HEAD
    MainPageObject.waitForElementAndClick(
=======
    waitForElementAndClick(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot click on the 'Search' Field!",
            5
    );

<<<<<<< HEAD
    MainPageObject.waitForElementAndSendKeys(
=======
    waitForElementAndSendKeys(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Java",
            "Cannot find search input!",
            5
    );
<<<<<<< HEAD
    MainPageObject.waitForElementPresent(
=======
    waitForElementPresent(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
            "Cannot find a title of article",
            15
    );

<<<<<<< HEAD
    String search_query_text = MainPageObject.waitForElementPresent(
=======
    String search_query_text = waitForElementPresent(
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
            By.id("org.wikipedia:id/search_src_text"),
            "Cannot get text from search field!",
            5
    ).getAttribute("text");

    Assert.assertEquals(
            "Text in search filed is not expected!",
            "Java",
            search_query_text
    );
  }
<<<<<<< HEAD
=======


  private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
    return element.getAttribute(attribute);
  }

  private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    return wait.until(
            ExpectedConditions.presenceOfElementLocated(by)
    );
  }

  private WebElement waitForElementPresent(By by, String error_message) {
    return waitForElementPresent(by, error_message, 5);
  }

  private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, 5);
    element.click();
    return element;
  }

  private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, 5);
    element.sendKeys(value);
    return element;
  }

  private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    return wait.until(
            ExpectedConditions.invisibilityOfElementLocated(by)
    );
  }

  private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
    element.clear();
    return element;
  }

  protected void swipeElementToLeft(By by, String error_message) {
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

  private int getAmountOfElement(By by) {
    List elements = driver.findElements(by);
    return elements.size();
  }

  protected void assertElementPresent(By by) {
    Assert.assertTrue(
            "Can't find article's title!",
            driver.findElements(by).size() == 1);
  }

>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6
}

