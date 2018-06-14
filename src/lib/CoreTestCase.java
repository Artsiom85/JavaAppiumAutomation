package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase{

  protected AppiumDriver driver;
  private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

  @Override
<<<<<<< HEAD
  protected void setUp() throws Exception {
=======
  public void setUp() throws Exception {
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6

    super.setUp();

    DesiredCapabilities capabilities = new DesiredCapabilities();

    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("deviceName", "AndroidTestDevice");
    capabilities.setCapability("platformVersion", "6.0");
    capabilities.setCapability("automationName", "Appium");
    capabilities.setCapability("appPackage", "org.wikipedia");
    capabilities.setCapability("appActivity", ".main.MainActivity");
    capabilities.setCapability("app", "/Users/artsiomkaraliou/Documents/GitHub/JavaAppiumAutomation/apks/org.wikipedia.apk");
    capabilities.setCapability("orientation", "PORTRAIT");

    driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
  }

  @Override
<<<<<<< HEAD
  protected void tearDown() throws Exception{
=======
  public void tearDown() throws Exception{
>>>>>>> 9ffb5c085682fef9866d0996cdb31437b227ddf6

    driver.quit();

    super.tearDown();
  }

}
