import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LAB1 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the login page
            driver.get("https://practicetestautomation.com/practice-test-login/");
            driver.manage().window().maximize();

            // Find and enter the valid username
            WebElement usernameField = driver.findElement(By.id("username"));
            usernameField.sendKeys("student");

            // Find and enter the valid password
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("Password123");

            // Find and click the login button
            WebElement loginButton = driver.findElement(By.id("submit"));
            loginButton.click();

            // Wait for the post-login page and verify successful login
            // It's more reliable to wait for an element on the new page, like the "Log out" button.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log out")));

            // Assert that the logout button is displayed to confirm success
            if (logoutButton.isDisplayed()) {
                System.out.println("✅ Login successful!");
            } else {
                System.out.println("❌ Login failed.");
            }

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}