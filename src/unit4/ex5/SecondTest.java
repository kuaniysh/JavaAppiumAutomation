package unit4.ex5;

import io.appium.java_client.AppiumDriver;
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

public class SecondTest {
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
    public void testAssertArticleTitlePresent()
    {
        String search_text = "Appium";

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

        String search_result_xpath = getResultSearchElement("Appium");
        waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Cannot find and click search result with substring" + "Appium",
                10
        );

        assertEquals("On page " + search_text + " not found title!", getArticleTitle(), search_text);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private static String getResultSearchElement(String substring)
    {
        return "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']".replace("{SUBSTRING}", substring);
    }

    public WebElement waitForTitleElement()
    {
        return waitForElementPresent(By.xpath("//*[@resource-id='pcs']/android.view.View/android.view.View"), "Cannot find title on page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getText();
    }
}