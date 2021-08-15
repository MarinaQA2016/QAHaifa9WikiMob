package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class WikiApplVerification {
    public AppiumDriver driver;

    @BeforeMethod
    public void startUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName","AndroidQA");
        capabilities.setCapability("platformVersion","11.0");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("automationName", "Uiautomator2");
        capabilities.setCapability("app",
                "C:/Marina/TelRan/Auto/Groups/QAHaifa9/QAHaifa9WikiMob/apk/wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }
    @Test
    public void wikiTest(){
        System.out.println("Search text:" + driver.findElement(By
                .xpath("//*[@resource-id='org.wikipedia:id/search_container']/*[@class='android.widget.TextView']")).getText());
    }

    @Test
    public  void searchTest() throws InterruptedException {
        Thread.sleep(3000);
        WebElement searchField = driver.findElement(By.xpath("//*[@resource-id='org.wikipedia:id/search_container']"));
        searchField.click();
        Thread.sleep(3000);
        driver.findElement(By.id("org.wikipedia:id/search_src_text")).sendKeys("Java");
        Thread.sleep(3000);
        System.out.println("First article title: " + driver
                .findElement(By.id("org.wikipedia:id/page_list_item_title")).getText());
        System.out.println("First article description: " + driver
                .findElement(By.id("org.wikipedia:id/page_list_item_description")).getText());
        driver.findElement(By.id("org.wikipedia:id/page_list_item_container")).click();
        Thread.sleep(3000);
        Assert.assertEquals("Java", driver.findElement(By.id("org.wikipedia:id/view_page_title_text")).getText());
        Assert.assertEquals("Indonesian island",driver.findElement(By.id("org.wikipedia:id/view_page_subtitle_text")).getText());

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
