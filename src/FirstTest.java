import lib.CoreTestCase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest extends CoreTestCase{

  @Test
  public void testSearch()
  {
    waitForElementByXpathAndClick(
            "//*[contains(@text, 'Search Wikipedia')]",
            "Cannot find Search Wikipedia input",
            5
    );
    waitForElementByXpathAndSendKeys(
            "//*[contains(@text, 'Search…')]",
            "Python",
            "Cannot find search input",
            5
    );

    waitForElementPresentByXpath(
            "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='General-purpose, high-level programming language']",
            "Cannot find 'General-purpose, high-level programming language' topic searching by 'Python'",
            15
    );

  }
  private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds)
  {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    By by = By.xpath(xpath);
    return wait.until(
            ExpectedConditions.presenceOfElementLocated(by)
    );
  }

  private WebElement waitForElementPresentByXpath(String xpath, String error_message)
  {
    return waitForElementPresentByXpath(xpath, error_message, 5);
  }

  private WebElement waitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds)
  {
    WebElement element =  waitForElementPresentByXpath(xpath, error_message, 5);
    element.click();
    return element;
  }
  private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds)
  {
    WebElement element =  waitForElementPresentByXpath(xpath, error_message, 5);
    element.sendKeys(value);
    return element;
  }
}
