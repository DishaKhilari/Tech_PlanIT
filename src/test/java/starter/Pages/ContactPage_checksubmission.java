package starter.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import starter.StartBrowser.BaseClass;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContactPage_checksubmission extends BaseClass {

    public void clickedOnContactUs() {
        // Navigate to contact page
        driver.findElement(By.linkText("Contact")).click();
    }

    public void submitButton() {
        // Click submit button
        driver.findElement(By.cssSelector(".btn-contact")).click();
    }

    public void setMandatoryFields(String Forename, String Email, String Message) {

        // Populate mandatory fields
        driver.findElement(By.id("forename")).sendKeys(Forename);
        driver.findElement(By.id("email")).sendKeys(Email);
        driver.findElement(By.id("message")).sendKeys(Message);
    }

    public void setErrorMessage() throws InterruptedException {

        Thread.sleep(6000);


        // Find all error message elements
        List<WebElement> errorMessages = driver.findElements(By.id("message-err"));

        // Find all success message elements
        List<WebElement> successMessages = driver.findElements(By.className("alert-success"));

        // Check if error message elements exist
        if (!errorMessages.isEmpty()) {
            System.out.println("Error message exist on the page.");
        } else {
            System.out.println("Error message do not exist on the page.");
        }

        // Check if success message element exists
        if (!successMessages.isEmpty()) {
            System.out.println("Success message exist on the page.");
        } else {
            System.out.println("Success message do not exist on the page.");
        }

    }
}