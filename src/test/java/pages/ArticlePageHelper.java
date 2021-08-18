package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePageHelper extends PageBase{
    @FindBy(id = "org.wikipedia:id/view_page_title_text")
    WebElement articleTitle;
    @FindBy(id = "org.wikipedia:id/view_page_subtitle_text")
    WebElement articleDescription;

    public ArticlePageHelper(WebDriver driver){
        this.driver = driver;
    }
    public void waitUntilPageIsLoaded(){
        waitUntilElementIsVisible(articleTitle,10);
        waitUntilElementIsVisible(articleDescription,10);
    }

    public String getArticleTitle(){
        return articleTitle.getText();
    }
    public String getArticleDescription(){
        return articleDescription.getText();
    }


}
