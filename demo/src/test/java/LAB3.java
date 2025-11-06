import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LAB3 {

    public static void main(String[] args) throws InterruptedException { // Added InterruptedException
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://the-internet.herokuapp.com/javascript_alerts");
            Thread.sleep(1500); // Pause after loading page

            // 1. SIMPLE ALERT
            driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
            Alert simpleAlert = wait.until(ExpectedConditions.alertIsPresent());
            Thread.sleep(1500); // Pause to see the alert

            simpleAlert.accept();
            System.out.println("✅ Simple Alert Accepted");
            Thread.sleep(1500); // Pause after accepting

            // 2. CONFIRM ALERT
            WebElement confirmAlertBtn = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
            confirmAlertBtn.click();
            
            Alert confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
            Thread.sleep(1500); // Pause to see the alert
            
            confirmAlert.accept();
            System.out.println("✅ Confirm Alert Accepted (OK)");
            Thread.sleep(1500); // Pause after accepting

            confirmAlertBtn.click();
            confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
            Thread.sleep(1500); // Pause to see the alert
            
            confirmAlert.dismiss();
            System.out.println("✅ Confirm Alert dismissed (Cancel)");
            Thread.sleep(1500); // Pause after dismissing

            // 3. PROMPT ALERT
            driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
            Alert promptAlert = wait.until(ExpectedConditions.alertIsPresent());
            Thread.sleep(1500); // Pause to see the alert

            promptAlert.sendKeys("Hello Selenium!");
            Thread.sleep(1500); // Pause to see the text being entered

            promptAlert.accept();
            System.out.println("✅ Prompt Alert Accepted");
            Thread.sleep(2000); // Final pause before closing

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}