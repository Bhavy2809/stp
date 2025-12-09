import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LAB11 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://practicetestautomation.com/practice-test-login/");
            driver.manage().window().maximize();

            WebElement usernameField = driver.findElement(By.id("username"));
            usernameField.sendKeys("student");

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("Password123");

            WebElement loginButton = driver.findElement(By.id("submit"));
            loginButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log out")));

            if (logoutButton.isDisplayed()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed.");
            }

        } finally {
            driver.quit();
        }
    }
}