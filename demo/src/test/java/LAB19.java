import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LAB19 {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://the-internet.herokuapp.com/javascript_alerts");
            Thread.sleep(1500);

            driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
            Alert simpleAlert = wait.until(ExpectedConditions.alertIsPresent());
            Thread.sleep(1500);

            simpleAlert.accept();
            System.out.println("Simple Alert Accepted");
            Thread.sleep(1500);

            WebElement confirmAlertBtn = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
            confirmAlertBtn.click();
            
            Alert confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
            Thread.sleep(1500);
            
            confirmAlert.accept();
            System.out.println("Confirm Alert Accepted (OK)");
            Thread.sleep(1500);

            confirmAlertBtn.click();
            confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
            Thread.sleep(1500);
            
            confirmAlert.dismiss();
            System.out.println("Confirm Alert dismissed (Cancel)");
            Thread.sleep(1500);

            driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
            Alert promptAlert = wait.until(ExpectedConditions.alertIsPresent());
            Thread.sleep(1500);

            promptAlert.sendKeys("Hello Selenium!");
            Thread.sleep(1500);

            promptAlert.accept();
            System.out.println("Prompt Alert Accepted");
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}