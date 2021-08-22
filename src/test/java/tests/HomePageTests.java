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
import pages.MyReadingListHelper;

import java.net.MalformedURLException;
import java.net.URL;

public class HomePageTests extends TestBase{
 ArticlePageHelper articlePage;
 MyReadingListHelper myReadingList;

    @BeforeMethod(alwaysRun = true)
    public void initTests(){
        articlePage = PageFactory.initElements(driver, ArticlePageHelper.class);
        myReadingList = PageFactory.initElements(driver, MyReadingListHelper.class);
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
    public void searchByTextAndSwipeUp(){
        homePage.searchBy("Java");
        homePage.returnBack();
        //homePage.swipeUp();
        //homePage.swipeDown();
        homePage.swipeUpToElement(By.xpath("//*[@text = 'History of Indonesia']"),10);

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
    public void threePointsMenuTest()  {
        homePage.searchBy("Java");
        homePage.firstFoundArticleOpen();
        articlePage.waitUntilPageIsLoaded();
        articlePage.openThreePointsMenu();
        Assert.assertTrue(articlePage.existsAddToReadingListMenu());
    }

    @Test
    public void searchAndPutToReadingList()  {
        homePage.searchBy("Java");
        homePage.firstFoundArticleOpen();

        articlePage.waitUntilPageIsLoaded();
        articlePage.putToReadingList();
        articlePage.waitUntilPageIsLoaded();
        articlePage.returnBack();
        homePage.waitUntilPageIsLoaded();
        homePage.openMyFirstList();
        myReadingList.waitUntilPageIsLoaded();
        Assert.assertEquals("Java",myReadingList.getFirstTitleOffListText());
        Assert.assertEquals("island of Indonesia", myReadingList.getFirstDescriptionOffList());

    }

    @Test (groups = {"smoke", "regression"})
    public void searchPutToReadingListAndDelete()  {
        homePage.searchBy("Java");
        homePage.firstFoundArticleOpen();

        articlePage.waitUntilPageIsLoaded();
        articlePage.putToReadingList();
        articlePage.waitUntilPageIsLoaded();
        articlePage.returnBack();
        homePage.waitUntilPageIsLoaded();
        homePage.openMyFirstList();
        myReadingList.waitUntilPageIsLoaded();
        Assert.assertEquals("Java",myReadingList.getFirstTitleOffListText());
        Assert.assertEquals("island of Indonesia", myReadingList.getFirstDescriptionOffList());

        myReadingList.deleteFirstArticle();


    }


}
