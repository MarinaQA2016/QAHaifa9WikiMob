package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class HomePageHelper extends PageBase{
    @FindBy(xpath = "//*[@resource-id='org.wikipedia:id/search_container']/*[@class='android.widget.TextView']")
    WebElement searchField;
    @FindBy(id = "org.wikipedia:id/search_src_text")
    WebElement searchFieldReal;
    @FindBy(id = "org.wikipedia:id/page_list_item_container")
    WebElement firstArticle;
    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    WebElement firstArticleTitle;
    @FindBy(id = "org.wikipedia:id/page_list_item_description")
    WebElement firstArticleDescription;
    @FindBy (xpath = "//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView")
    WebElement myListsListIcon;
    @FindBy(id = "org.wikipedia:id/item_container")
    WebElement myFirstList;

    public HomePageHelper(WebDriver driver){
        this.driver=driver;
    }
    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(searchField,10);
    }

    public String getSearchFieldText(){
        return searchField.getText();
    }

    public void searchBy(String text) {
        searchField.click();
        waitUntilElementIsClickable(searchFieldReal,5);
        searchFieldReal.sendKeys(text);
        waitUntilElementIsClickable(firstArticle,10);
    }
    public void openMyFirstList(){
        myListsListIcon.click();
        waitUntilElementIsClickable(myFirstList,10);
        myFirstList.click();
    }

    public String getFirstArticleTitle(){
        return  firstArticleTitle.getText();
    }

    public String getFirstArticleDescription(){
        return firstArticleDescription.getText();
    }

    public void firstFoundArticleOpen() {
        firstArticle.click();
    }



}
