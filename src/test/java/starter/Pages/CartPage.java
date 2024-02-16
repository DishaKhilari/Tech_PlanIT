package starter.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import starter.StartBrowser.BaseClass;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage extends BaseClass {

    public void cartPage() {
        // Go to cart page
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/p[2]/a")).click();
    }

    public void addItemsToCart(int two, int five, int three) {

        // Add items to cart
        addItemsToCart("//*[@id='product-2']/div/p/a", two);
        addItemsToCart("//*[@id='product-4']/div/p/a", five);
        addItemsToCart("//*[@id='product-7']/div/p/a", three);
    }

    public void verifyItemsPrice() {

        // Find the element that contains the price of the item
        WebElement priceElement1 = driver.findElement(By.xpath("//*[@id='product-2']/div/p/span"));
        String price1 = priceElement1.getText();
        assertEquals(price1, "$10.99"); // Verify subtotal for Stuffed Frog

        WebElement priceElement2 = driver.findElement(By.xpath("//*[@id='product-4']/div/p/span"));
        String price2 = priceElement2.getText();
        assertEquals(price2,"$9.99"); // Verify subtotal for Fluffy Bunny

        WebElement priceElement3 = driver.findElement(By.xpath("//*[@id='product-7']/div/p/span"));
        String price3 = priceElement3.getText();
        assertEquals(price3, "$14.99");  // Verify subtotal for Valentine Bear

    }
    public void verifyItemsSubtotal() {
        // Go to cart page
        driver.findElement(By.id("nav-cart")).click();


        // Verify subtotal for each product
        String firstProductSubtotal = driver.findElement(By.xpath("//tr[1]/td[4]")).getText();
        String product1 = String.valueOf(2 * 10.99);
        assertEquals(firstProductSubtotal, "$"+product1); // Verify subtotal for Stuffed Frog

        String secondProductSubtotal = driver.findElement(By.xpath("//tr[2]/td[4]")).getText();
        String product2 = String.valueOf(5 * 9.99);
        assertEquals(secondProductSubtotal, "$"+product2); // Verify subtotal for Fluffy Bunny

        String thirdProductSubtotal = driver.findElement(By.xpath("//tr[3]/td[4]")).getText();
        String product3 = String.valueOf(3 * 14.99);
        assertEquals(thirdProductSubtotal, "$"+product3); // Verify subtotal for Valentine Bear
    }

    public void verifyTotal_OfItems() {
        // Verify total
        WebElement total = driver.findElement(By.xpath("//strong[starts-with(@class, 'total')]"));
        assertEquals(total.getText(), "Total: 116.9");
    }

    private void addItemsToCart(String itemName, int quantity) {
        for (int i = 0; i < quantity; i++) {
            // Initialize WebDriverWait with a timeout of 10 seconds
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
            WebElement productDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(itemName)));
            if(productDisplayed.isDisplayed())
                driver.findElement(By.xpath(itemName)).click();
        }
    }
}
