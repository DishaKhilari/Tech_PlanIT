package starter.StartBrowser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseClass {

    public static WebDriver driver;
    public static FirefoxOptions options;
    WebElement element;

    //Method for Screenshot
    public static void screenShot(String name) throws IOException {
        TakesScreenshot tss = (TakesScreenshot)driver;
        File srcFile = tss.getScreenshotAs(OutputType.FILE);
        File desFile = new File("src/test/resources/features/search/OutputFile/"+name+".png");
        FileUtils.copyFile(srcFile, desFile);
    }

    //Method for launching Browser
    public static void openbrowser(String Browser) {

        //Using Try/Catch to show Exception
        try {
            //if want Chrome Browser
            if (Browser.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver",
                        "C:\\Users\\USER\\eclipse-workspace\\SeleniumProject\\Driver\\chromedriver.exe");
                driver = new ChromeDriver();
            }
            //if want FireFox Browser
            else if (Browser.equalsIgnoreCase("Firefox")) {
                System.setProperty("webdriver.geckodriver.driver",
                        "geckodriver");
                // Set Firefox options to run in headless mode
                WebDriverManager.firefoxdriver().setup();
                options = new FirefoxOptions();
                options.addArguments("--headless"); // Add headless mode argument
//                options.setHeadless(true); // Run Firefox in headless mode
                driver = new FirefoxDriver(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method for Close
    public static void Close() {
        driver.close();
    }

    //Method for Close
    public static void quit() {
        driver.quit();
    }

    //Method for maximize the screen
    public static void maximize() {
        driver.manage().window().maximize();
    }

    public static void deletecookies() {
        driver.manage().deleteAllCookies();
    }

    public static void getTitle() {
        driver.getTitle();
    }

    //Method for Get URL
    public static void SiteURL(String URL) {
        driver.get(URL);
    }

    public static void DefaultContent(){
        driver.switchTo().defaultContent();
    }

    public void waitForElementToBeClickable(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeoutInSeconds));
        element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToBeVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeoutInSeconds));
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBePresent(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeoutInSeconds));
        element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForTextToBePresentInElement(WebDriver driver, By locator, String text, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeoutInSeconds));
        boolean result = wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static void SwitchFrame(int framenumber){
        driver.switchTo().frame(framenumber);

    }
}
