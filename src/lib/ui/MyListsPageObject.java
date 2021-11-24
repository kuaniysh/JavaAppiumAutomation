package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByMyName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresent(
                By.xpath(folder_name_xpath),
                "cannot find folder by name: " + name_of_folder,
                15
        );

        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "cannot find and click folder by name: " + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(article_title_xpath), "Cannot find saved article with title: " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(By.xpath(article_title_xpath), "Saved article with title '" + article_title + "' is still presented", 15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(article_title_xpath),
                "cannot find saved article title"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void assertArticlePresent(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.assertElementPresent(
                By.xpath(article_title_xpath),
                "Cannot find article with title: " + article_title
        );
    }

    public void clickByArticleWithTitle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(
                By.xpath(article_title_xpath),
                "cannot find and click on article with title: " + article_title,
                5
        );
    }
}
