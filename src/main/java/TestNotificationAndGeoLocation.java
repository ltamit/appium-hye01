import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;

public class TestNotificationAndGeoLocation {

    public static String userName = System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY");
    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";
    AppiumDriver driver;

    @BeforeClass(alwaysRun = true)
    @Parameters({"device", "version", "platform"})
    public void setUp(String device, String version, String platform) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", device);
        capabilities.setCapability("platformVersion", version);
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("network", true);
        capabilities.setCapability("name", "Notification Test");
        capabilities.setCapability("visual", true);
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("build", "Android Parallel03");
        capabilities.setCapability("app", "lt://APP10160231121733121809257641");

        String hub = "https://" + userName + ":" + accessKey + gridURL;
        driver = new AppiumDriver(new URL(hub), capabilities);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testNotificationAndGeoLocation() {
        try {
            MobileElement notification = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/notification");
            notification.click();
            Assert.assertTrue(notification.isDisplayed());
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
            throw new RuntimeException("Test testNotificationAndGeoLocation failed: " + e.getMessage(), e);
        }
    }
}
