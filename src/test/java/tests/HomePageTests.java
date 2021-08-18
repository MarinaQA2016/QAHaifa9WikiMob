package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ArticlePageHelper;
import pages.HomePageHelper;

import java.net.MalformedURLException;
import java.net.URL;

public class HomePageTests extends TestBase{
 ArticlePageHelper articlePage;

    @BeforeMethod
    public void initTests(){
        articlePage = PageFactory.initElements(driver, ArticlePageHelper.class);
    }

    @Test
    public void wikiTest(){
        //System.out.println("Search text:" + homePage.getSearchFieldText());
        Assert.assertEquals("Search Wikipedia", homePage.getSearchFieldText());
    }

    @Test
    public  void searchByTextOpenFirstArticleRotation() {

        homePage.searchBy("Java");

        System.out.println("First article title: " + homePage.getFirstArticleTitle());
        System.out.println("First article description: " + homePage.getFirstArticleDescription());
        homePage.firstFoundArticleOpen();
        articlePage.waitUntilPageIsLoaded();
        articlePage.returnLandScape();
        articlePage.waitUntilPageIsLoaded();
        articlePage.returnPortrait();

        Assert.assertEquals("Java", articlePage.getArticleTitle());
        Assert.assertEquals("Indonesian island",articlePage.getArticleDescription());

    }

    @Test
    public  void searchByTextOpenFirstArticleBackGround() {
        homePage.searchBy("Java");
        System.out.println("First article title: " + homePage.getFirstArticleTitle());
        System.out.println("First article description: " + homePage.getFirstArticleDescription());
        homePage.firstFoundArticleOpen();

        articlePage.waitUntilPageIsLoaded();
        homePage.runBackGround(5);
        articlePage.waitUntilPageIsLoaded();


        Assert.assertEquals("Java", articlePage.getArticleTitle());
        Assert.assertEquals("Indonesian island",articlePage.getArticleDescription());

    }
    @Test
    public void searchAndPutToReadingList() throws InterruptedException {
        Thread.sleep(3000);
        WebElement searchField = driver.findElement(By.xpath("//*[@resource-id='org.wikipedia:id/search_container']"));
        searchField.click();
        Thread.sleep(3000);
        driver.findElement(By.id("org.wikipedia:id/search_src_text")).sendKeys("Java");
        Thread.sleep(3000);
        driver.findElement(By.id("org.wikipedia:id/page_list_item_container")).click();
        Thread.sleep(3000);
        // --- save link to article ----
        /*driver.findElement(By
                .xpath("//*[@resource-id='org.wikipedia:id/page_toolbar]//*[@class='android.widget.ImageView']")).click();
        Thread.sleep(3000);*/
        driver.findElement(By
                .xpath("//android.widget.ImageView[@content-desc='Add this article to a reading list']")).click();
        Thread.sleep(3000);
        // ---- press 'GOT IT' button ----
        driver.findElement(By.id("org.wikipedia:id/onboarding_button")).click();
        Thread.sleep(3000);
        //---- press 'OK' button -----
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(3000);
        driver.navigate().back();
        driver.findElement(By
                .xpath("//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView")).click();
        Thread.sleep(3000);
        //  ---- open 'My Reading' list ------
        driver.findElement(By.id("org.wikipedia:id/item_container")).click();
        Thread.sleep(3000);
        Assert.assertEquals("Java",driver.findElement(By.id("org.wikipedia:id/page_list_item_title")).getText());
        Assert.assertEquals("island of Indonesia", driver.findElement(By.id("org.wikipedia:id/page_list_item_description")).getText());





    }


}
