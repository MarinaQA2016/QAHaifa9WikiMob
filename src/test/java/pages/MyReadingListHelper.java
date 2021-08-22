package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyReadingListHelper extends PageBase{
    @FindBy(xpath = "org.wikipedia:id/page_list_item_container")
    List<WebElement> myListElementList;
    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    WebElement firstTitleOffList;
    @FindBy(id= "org.wikipedia:id/page_list_item_description")
    WebElement firstDescriptionOffList;

    public MyReadingListHelper(WebDriver driver){
        this.driver = driver;
    }

    public void waitUntilPageIsLoaded(){
        waitUntilAllElementsAreVisible( myListElementList,10);
    }
    public String getFirstTitleOffListText(){
        waitUntilElementIsVisible(firstTitleOffList,5);
        return firstTitleOffList.getText();
    }
    public String getFirstDescriptionOffList(){
        waitUntilElementIsVisible(firstDescriptionOffList,5);
        return  firstDescriptionOffList.getText();
    }
}
