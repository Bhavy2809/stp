import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class LAB13 {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.myntra.com");

        WebElement menLink = driver.findElement(By.linkText("Men"));
        menLink.click();

        Thread.sleep(2000);

        System.out.println("Navigated to: " + driver.getCurrentUrl());

        driver.navigate().back();

        Thread.sleep(2000);

        System.out.println("Navigated back to: " + driver.getCurrentUrl());

        driver.quit();
    }
}