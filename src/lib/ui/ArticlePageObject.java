package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE_ELEMENT = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTION_ADD_TO_MY_LIST = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleToAppear() {
        return this.waitForElementPresent(By.id(TITLE_ELEMENT), "cannot find title element", 5);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleToAppear();
        return title_element.getAttribute("text");
    }

    public void addArticleToMyListForTheFirstTime(String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "cannot find button to open article options",
                5
        );

        this.waitForElementPresent(
                By.xpath(OPTION_ADD_TO_MY_LIST),
                "cannot find Add to reading list menu item",
                15
        );

        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_MY_LIST),
                "cannot click menu option 'Add article to reading list'",
                5
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "cannot find GOT IT button",
                5
        );

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "cannot find input field for set name of article folder",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "cannot put text into article folder input field",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "cannot find OK button",
                5
        );
    }

    public void addArticleToExistingList(String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "cannot find button to open article options",
                5
        );

        this.waitForElementPresent(
                By.xpath(OPTION_ADD_TO_MY_LIST),
                "cannot find Add to reading list menu item",
                15
        );

        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_MY_LIST),
                "cannot click menu option 'Add article to reading list'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "cannot find folder by name:" + name_of_folder,
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "cannot find close button",
                5
        );
    }

    public void assertArticleTitlePresent() {
        this.assertElementPresent(
                By.id(TITLE_ELEMENT),
                "article title is not present"
        );
    }
}
