import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
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
import java.util.List;

public class FirstTest {

  private AppiumDriver driver;

  @Before
  public void setUP() throws Exception {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("deviceName", "AndroidTestDevice");
    capabilities.setCapability("platformVersion", "6.0");
    capabilities.setCapability("automationName", "Appium");
    capabilities.setCapability("appPackage", "org.wikipedia");
    capabilities.setCapability("appActivity", ".main.MainActivity");
    capabilities.setCapability("app", "/Users/artsiomkaraliou/Documents/GitHub/JavaAppiumAutomation/apks/org.wikipedia.apk");
    capabilities.setCapability("orientation", "PORTRAIT");

    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
  }

  @After
  public void tearDown() {
    driver.quit();
  }


  @Test
  public void ex4() {

    //Переменная ввода текста поискового запроса:
    String search_word = "Python";

    waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot click on Search Field!",
            5
    );
    waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            search_word,
            "Cannot fill in Search Input!",
            5
    );

    checkTitles(getListOfSearchResultTitles(), search_word);
  }

  private WebElement waitForElementPresent(By by, String error_msg, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_msg + "\n");
    return wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }

  private WebElement waitForElementAndClick(By by, String error_msg, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_msg, timeoutInSeconds);
    element.click();
    return element;
  }

  private WebElement waitForElementAndSendKeys(By by, String value, String error_msg, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_msg, timeoutInSeconds);
    element.sendKeys(value);
    return element;
  }

  private List<WebElement> getListOfSearchResultTitles() {
    waitForElementPresent(
            By.id("org.wikipedia:id/page_list_item_title"),
            "No search result titles available!",
            5
    );
    return driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
  }

  private void checkTitles (List<WebElement> results, String value) {
    for (WebElement element : results) {
      String text = element.getAttribute("text");
      Assert.assertTrue("Title doesn't contain search que text!",
              text.toLowerCase().contains(value.toLowerCase()));
    }
  }
}
