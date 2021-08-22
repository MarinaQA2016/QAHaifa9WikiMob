package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePageHelper;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {
    public AppiumDriver driver;
    HomePageHelper homePage;

    @BeforeMethod(alwaysRun = true)
    public void startUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName","AndroidQA");
        capabilities.setCapability("platformVersion","8.1.0");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("automationName", "Uiautomator2");
        capabilities.setCapability("app",
                "C:/Marina/TelRan/Auto/Groups/QAHaifa9/QAHaifa9WikiMob/apk/wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        homePage.returnPortrait();
        homePage.waitUntilPageIsLoaded();

    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
