import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration; // Modern import for waits

public class LAB1b {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Configure browser
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Updated syntax
            driver.manage().window().maximize();

            String baseUrl = "https://practicetestautomation.com/practice-test-login/";
            driver.get(baseUrl);

            // Define test data for negative scenarios
            String[][] testData = {
                {"invaliduser", "Password123"},  // Invalid username
                {"student", "wrongPassword"},     // Invalid password
                {"", "Password123"},              // Blank username
                {"student", ""},                  // Blank password
                {"", ""}                          // Both blank
            };

            // Loop through each test case
            for (String[] data : testData) {
                String username = data[0];
                String password = data[1];

                // Clear fields and enter new data
                driver.findElement(By.id("username")).clear();
                driver.findElement(By.id("username")).sendKeys(username);
                driver.findElement(By.id("password")).clear();
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.id("submit")).click();

                // Check for the error message
                try {
                    String errorMessage = driver.findElement(By.id("error")).getText();
                    System.out.println("✅ Test Passed for [" + username + " / " + password + "]. Error displayed: " + errorMessage);
                } catch (Exception e) {
                    System.out.println("❌ Test Failed for [" + username + " / " + password + "]. No error message found.");
                }
            }

        } finally {
            // Cleanup
            driver.quit();
            System.out.println("\n✅ Negative login tests completed successfully!");
        }
    }
}