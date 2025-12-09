import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LAB14 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement username = driver.findElement(By.name("username"));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", username);
            username.sendKeys("testuser");

            driver.findElement(By.name("password")).sendKeys("password123");
            
            WebElement comment = driver.findElement(By.name("comments"));
            comment.sendKeys("This is a test comment.");

            WebElement fileUpload = driver.findElement(By.name("filename"));
            fileUpload.sendKeys("C:\\Users\\shukl\\Desktop\\file.txt");

            WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox'][value='cb1']"));
            if (!checkbox.isSelected()) {
                js.executeScript("arguments[0].click();", checkbox);
            }

            WebElement radio = driver.findElement(By.cssSelector("input[type='radio'][value='rd1']"));
            js.executeScript("arguments[0].click();", radio);

            driver.findElement(By.name("dropdown")).sendKeys("Drop Down Item 1");

            Thread.sleep(1000);
            
            comment.submit();

            WebElement successHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Processed')]")));
            
            System.out.println("Form submitted successfully!");
            System.out.println("Success Message: " + successHeader.getText());
            
            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}