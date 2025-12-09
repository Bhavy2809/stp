import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration; 

public class LAB12 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
            driver.manage().window().maximize();

            String baseUrl = "https://practicetestautomation.com/practice-test-login/";
            driver.get(baseUrl);

            String[][] testData = {
                {"invaliduser", "Password123"},  
                {"student", "wrongPassword"},     
                {"", "Password123"},
                {"student", ""},
                {""}
            };

            for (String[] data : testData) {
                String username = data[0];
                String password = data[1];

                driver.findElement(By.id("username")).clear();
                driver.findElement(By.id("username")).sendKeys(username);
                driver.findElement(By.id("password")).clear();
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.id("submit")).click();

                try {
                    String errorMessage = driver.findElement(By.id("error")).getText();
                    System.out.println("Test Passed for [" + username + " / " + password + "]. Error displayed: " + errorMessage);
                } catch (Exception e) {
                    System.out.println("Test Failed for [" + username + " / " + password + "]. No error message found.");
                }
            }

        } finally {
            driver.quit();
            System.out.println("\nNegative login tests completed successfully!");
        }
    }
}