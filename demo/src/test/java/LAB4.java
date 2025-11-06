import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LAB4 {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open the test form page
        driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");

        // Scroll helper
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Fill Username
        WebElement username = driver.findElement(By.name("username"));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", username);
        username.sendKeys("testuser");

        // Fill Password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("password123");

        // Fill Comments
        WebElement comment = driver.findElement(By.name("comments"));
        comment.sendKeys("This is a test comment.");

        // Upload file (ensure the path exists)
        WebElement fileUpload = driver.findElement(By.name("filename"));
        // ⚠️ IMPORTANT: YOU MUST CHANGE THIS PATH TO MATCH THE FILE ON YOUR COMPUTER!
        fileUpload.sendKeys("C:\\Users\\shukl\\Desktop\\file.txt");

        // Select checkboxes
        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox'][value='cb1']"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        // Select radio button
        WebElement radio = driver.findElement(By.cssSelector("input[type='radio'][value='rd1']"));
        radio.click();

        // Select dropdown
        WebElement dropdown = driver.findElement(By.name("dropdown"));
        dropdown.sendKeys("Drop Down Item 1");

        // Submit the form
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        Thread.sleep(500); // brief wait to ensure scroll
        submitButton.click();

        // Wait to observe result
        Thread.sleep(3000);

        // Close browser
       driver.quit();
    }
}