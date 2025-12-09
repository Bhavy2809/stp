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

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(), 'Remove')]"));
        removeButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        System.out.println("Checkbox removed dynamically.");

        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(), 'Add')]"));
        addButton.click();
        
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkbox")));
        System.out.println("Checkbox added back dynamically.");
        
        if (checkbox.isDisplayed()) {
            checkbox.click();
            System.out.println("Checkbox clicked successfully after being re-added.");
        }
        
        driver.quit();
    }
}