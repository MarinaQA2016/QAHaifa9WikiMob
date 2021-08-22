package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ArticlePageHelper extends PageBase{
    @FindBy(id = "org.wikipedia:id/view_page_title_text")
    WebElement articleTitle;
    @FindBy(id = "org.wikipedia:id/view_page_subtitle_text")
    WebElement articleDescription;
    @FindBy(xpath = "//android.widget.ImageView[@content-desc='Add this article to a reading list']")
    WebElement saveToListIcon;
    @FindBy(id = "org.wikipedia:id/onboarding_button")
    WebElement gotItButton;
    @FindBy(id = "android:id/button1")
    WebElement confirmSavingToListButton;
    @FindBy(xpath = "//*[@resource-id='org.wikipedia:id/page_toolbar']//*[@class='android.widget.ImageView']")
    WebElement threePointsMenu;
    @FindBy(id = "org.wikipedia:id/title")
    List<WebElement> allThreePointsMenuTitlesList;

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


    public void putToReadingList() {
        saveToListIcon.click();
        waitUntilElementIsClickable(gotItButton,10);
        gotItButton.click();
        waitUntilElementIsClickable(confirmSavingToListButton,10);
        confirmSavingToListButton.click();

    }


    public void openThreePointsMenu() {
        waitUntilElementIsClickable(threePointsMenu,5);
        threePointsMenu.click();
    }

    public boolean existsAddToReadingListMenu() {
        waitUntilAllElementsAreVisible(allThreePointsMenuTitlesList,10);
        return allThreePointsMenuTitlesList.get(2).getAttribute("text").equals("Add to reading list");
    }
}
