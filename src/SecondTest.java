import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class SecondTest extends CoreTestCase {

  private AppiumDriver driver;

  @Before
  public void setUp() throws Exception
  {
    DesiredCapabilities capabilities = new DesiredCapabilities();

    capabilities.setCapability("platformName","Android");
    capabilities.setCapability("deviceName","AndroidTestDevice");
    capabilities.setCapability("platformVersion","6.0");
    capabilities.setCapability("automationName","Appium");
    capabilities.setCapability("appPackage","org.wikipedia");
    capabilities.setCapability("appActivity",".main.MainActivity");
    capabilities.setCapability("app","/Users/artsiomkaraliou/Documents/GitHub/JavaAppiumAutomation/apks/org.wikipedia.apk");

  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
  }
  @After
  public void tearDown()
  {
    driver.quit();
  }



  @Test
  public void testAvailabilityOfText()
  {
    waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find Search Wikipedia input",
            5
    );
    waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Python",
            "Cannot find search input",
            5
    );

    waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='General-purpose, high-level programming language']"),
            "Cannot find 'General-purpose, high-level programming language' topic searching by 'Python'",
            15

    );

  }

  @Test
  public void testCancelSearch ()
  {
    waitForElementAndClick(
            By.id("org.wikipedia:id/search_container"),
            "Cannot find 'Search Wikipedia' input",
            5

    );
    waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Python",
            "Cannot find search input",
            5
    );
    waitForElementAndClear(
            By.id("org.wikipedia:id/search_src_text"),
            "Cannot find search field",
            5
    );

    //Проверяем, что результатов поиска несколько ( >=2):
            checkForFewSearchResultsArePresented();

    waitForElementAndClick(
            By.id("org.wikipedia:id/search_close_btn"),
            "Cannot find X to cancel search",
            5

    );
    waitForElementNotPresent(
            By.id("org.wikipedia:id/search_close_btn"),
            "X is still present on the page",
            5
    );

  }



  @Test
  public void testCompareWordsInSearch()
  {
    waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            5
    );
    waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Python",
            "Cannot find search input",
            5
    );
    waitForElementAndClick(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='General-purpose, high-level programming language']"),
            "Cannot find 'Search Wikipedia' input",
            5
    );

    WebElement title_element = waitForElementPresent(
            By.id("org.wikipedia:id/view_page_title_text"),
            "cannot find article title",
            15

    );

    String article_title = title_element.getAttribute("text");

    Assert.assertEquals(
            "We see unexpected title",
            "Python (programming language)",
            article_title
    );




  }
  private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
  {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    return wait.until(
            ExpectedConditions.presenceOfElementLocated(by)
    );
  }

  private WebElement waitForElementPresent(By by, String error_message)
  {
    return waitForElementPresent(by, error_message, 5);
  }

  private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
  {
    WebElement element =  waitForElementPresent(by, error_message, 5);
    element.click();
    return element;
  }
  private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
  {
    WebElement element =  waitForElementPresent(by, error_message, 5);
    element.sendKeys(value);
    return element;
  }

  private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
  {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    return wait.until(
            ExpectedConditions.invisibilityOfElementLocated(by)
    );
  }

  private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
  {
    WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
    element.clear();
    return element;
  }
  private void checkForFewSearchResultsArePresented() {
    int numberOfSearchResults = driver.findElements(By.id("org.wikipedia:id/page_list_item_container")).size();
    Assert.assertTrue("Number of search results is less than a few!", numberOfSearchResults >= 2);

  }
}

