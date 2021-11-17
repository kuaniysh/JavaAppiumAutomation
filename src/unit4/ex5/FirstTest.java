package unit4.ex5;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidAPI29");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/kuanysh/Desktop/JavaAppiumAutomation/apks/wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void saveTwoArticlesToMyList() {
        String search_text = "Java";
        String name_of_folder = "Learning programming";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_text,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' article",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='pcs']/android.view.View/android.view.View"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Save']"),
                "Cannot find button 'Save'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Cannot find button 'Add to list'",
                5
        );

        waitForElementAndClear(
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                "Cannot find input name",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input field for set name of article folder",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into article folder input field",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find OK button",
                5
        );

        driver.navigate().back();

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Cannot find close button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_text,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='High-level programming language']"),
                "Cannot find 'Object-oriented programming language' article",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='pcs']/android.view.View/android.view.View"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Save']"),
                "Cannot find button 'Save'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Cannot find button 'Add to list'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find folder",
                5
        );

        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();

        waitForElementAndClick(
                By.xpath("//*[@text='Saved']"),
                "Cannot find 'Saved' button",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find Add to reading list menu item",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find Learning programming folder",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find article name"
        );

        assertTrue(
                "JavaScript article is not presented",
                driver.findElement(By.xpath("//*[@text='JavaScript']")).isDisplayed()
        );

        waitForElementAndClick(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find JavaScript article",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@resource-id='pcs']/android.view.View/android.view.View"),
                "Cannot find article title",
                15
        );

        String actual_title = title_element.getAttribute("text");

        assertEquals(
                "Actual title doesn't match expected",
                "JavaScript",
                actual_title
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(600)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
}