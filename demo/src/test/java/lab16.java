import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class lab16 {

    public static void main(String[] args) {

        // Step 1: Launch the browser
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();

        // Step 2: Create a reusable WebDriverWait object
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 3: Find and click the 'Remove' button
        WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(), 'Remove')]"));
        removeButton.click();

        // Step 4: Wait until the checkbox is gone from the page
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        System.out.println("✅ Checkbox removed dynamically.");

        // Step 5: Find and click the 'Add' button
        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(), 'Add')]"));
        addButton.click();
        
        // Step 6: Wait for the checkbox to be present in the HTML again
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkbox")));
        System.out.println("✅ Checkbox added back dynamically.");
        
        // Step 7: Verify the checkbox is displayed and interact with it
        if (checkbox.isDisplayed()) {
            checkbox.click();
            System.out.println("✅ Checkbox clicked successfully after being re-added.");
        }
        
        // Step 8: Close the browser
        driver.quit();
    }
}