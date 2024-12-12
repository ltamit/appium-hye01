//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileBy;
//import io.appium.java_client.MobileElement;
//import io.appium.java_client.android.AndroidElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.Test;
//
//import java.net.URL;
//import java.util.List;
//
//public class AndroidApp {
//
//    public static String userName = System.getenv("LT_USERNAME");
//    public static String accessKey = System.getenv("LT_ACCESS_KEY");
//
//    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";
//
//    AppiumDriver driver;
//
//    @Test
//    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
//    public void AndroidApp1(String device, String version, String platform) {
//        version = System.getProperty("platformVersion");
//        try {
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability("build","Java TestNG Android");
//            capabilities.setCapability("name",platform+" "+device+" "+version);
//            capabilities.setCapability("deviceName", device);
//            capabilities.setCapability("platformVersion",version);
//            capabilities.setCapability("platformName", platform);
//            capabilities.setCapability("isRealMobile", true);
//            //AppURL (Create from Wikipedia.apk sample in project)
//            capabilities.setCapability("app", "lt://proverbial-android"); //Enter your app url
//            capabilities.setCapability("deviceOrientation", "PORTRAIT");
//            capabilities.setCapability("console", true);
//            capabilities.setCapability("network", false);
//            // capabilities.setCapability("visual", true);
//            capabilities.setCapability("devicelog", true);
//            //capabilities.setCapability("geoLocation", "HK");
//
//            String hub = "https://" + userName + ":" + accessKey + gridURL;
//            driver = new AppiumDriver(new URL(hub), capabilities);
//
//            MobileElement color = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/color");
//            //Changes color to pink
//            color.click();
//            Thread.sleep(1000);
//            //Back to orginal color
//            color.click();
//
//            MobileElement text = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/Text");
//            //Changes the text to "Proverbial"
//            text.click();
//
//            //toast will be visible
//            MobileElement toast = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/toast");
//            toast.click();
//
//            //notification will be visible
//            MobileElement notification = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/notification");
//            notification.click();
//            Thread.sleep(2000);
//
//            //Opens the geolocation page
//            MobileElement geo = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/geoLocation");
//            geo.click();
//            Thread.sleep(5000);
//
//            //takes back to home page
//            MobileElement home = (MobileElement) driver.findElementByAccessibilityId("Home");
//            home.click();
//
//            //Takes to speed test page
//            MobileElement speedtest = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/speedTest");
//            speedtest.click();
//            Thread.sleep(5000);
//
//            MobileElement Home = (MobileElement) driver.findElementByAccessibilityId("Home");
//            Home.click();
//
//            //Opens the browser
//            MobileElement browser = (MobileElement) driver.findElementByAccessibilityId("Browser");
//            browser.click();
//
//            MobileElement url = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/url");
//            url.sendKeys("https://www.lambdatest.com");
//
//            MobileElement find = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/find");
//            find.click();
//
//            driver.quit();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            try{
//                driver.quit();
//            }catch(Exception e1){
//                e.printStackTrace();
//            }
//        }
//
//
//    }
//}

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;

public class AndroidApp {

    public static String userName = System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY");

    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";
    AppiumDriver driver;

    @BeforeClass(alwaysRun = true)
    @Parameters({"device", "version", "platform"})
    public void setUp(String device, String version, String platform) throws Exception {
        version = System.getProperty("platformVersion", version);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "Java TestNG Android01");
        capabilities.setCapability("name", platform + " " + device + " " + version);
        capabilities.setCapability("deviceName", device);
        capabilities.setCapability("platformVersion", version);
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("app", "lt://APP10160231121733121809257641");
        capabilities.setCapability("deviceOrientation", "PORTRAIT");
        capabilities.setCapability("console", true);
        capabilities.setCapability("network", false);
        capabilities.setCapability("devicelog", true);

        String hub = "https://" + userName + ":" + accessKey + gridURL;
        driver = new AppiumDriver(new URL(hub), capabilities);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "Test changing color and resetting it.")
    public void testChangeColor() {
        try {
            MobileElement color = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/color");
            color.click();
            Thread.sleep(1000);
            color.click();

            Assert.assertTrue(color.isDisplayed(), "Color element should be displayed after changes.");
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        } catch (AssertionError | Exception e) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
            throw new RuntimeException("Test testChangeColor failed: " + e.getMessage(), e);
        }
    }

    @Test(description = "Test changing text and triggering a toast message.")
    public void testTextAndToast() {
        try {
            MobileElement text = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/Text");
            text.click();

            MobileElement toast = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/toast");
            toast.click();
            Thread.sleep(1000);

            Assert.fail("Deliberate failure"); // This test fails on purpose for demonstration purposes

            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        } catch (AssertionError | Exception e) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
            throw new RuntimeException("Test testTextAndToast failed: " + e.getMessage(), e);
        }
    }

    @Test(description = "Test notification and navigate to geolocation feature.")
    public void testNotificationAndGeoLocation() {
        try {
            MobileElement notification = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/notification");
            notification.click();
            Thread.sleep(2000);

//            MobileElement geo = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/geoLocation");
//            geo.click();
//            Thread.sleep(5000);
//
            MobileElement home = (MobileElement) driver.findElementByAccessibilityId("Home");
//            home.click();

            Assert.assertTrue(home.isDisplayed(), "Home element should be displayed after returning.");
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        } catch (AssertionError | Exception e) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
            throw new RuntimeException("Test testNotificationAndGeoLocation failed: " + e.getMessage(), e);
        }
    }

    @Test(description = "Test navigating to speed test and then using the browser.")
    public void testSpeedTestAndBrowser() {
        try {
            MobileElement speedtest = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/speedTest");
            speedtest.click();
            Thread.sleep(5000);

            MobileElement home = (MobileElement) driver.findElementByAccessibilityId("Home");
            home.click();

            MobileElement browser = (MobileElement) driver.findElementByAccessibilityId("Browser");
            browser.click();

            MobileElement url = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/url");
            url.sendKeys("https://www.lambdatest.com");

            MobileElement find = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/find");
            find.click();
            Thread.sleep(3000);

            Assert.assertTrue(url.isDisplayed(), "URL element should remain displayed after navigation.");
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        } catch (AssertionError | Exception e) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
            throw new RuntimeException("Test testSpeedTestAndBrowser failed: " + e.getMessage(), e);
        }
    }
}
