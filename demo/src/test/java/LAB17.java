import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LAB17 {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");

        options.addArguments("--disable-blink-features=AutomationControlled");
        
        WebDriver driver = new ChromeDriver(options);
        
        driver.manage().window().maximize();

        try {
            driver.get("https://www.ilovepdf.com/pdf_to_word");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            WebElement selectFileButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("pickfiles")));
            selectFileButton.click();
            
            Thread.sleep(1000);

            WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
            fileInput.sendKeys("C:\\Users\\shukl\\Downloads\\BH.pdf");

            WebElement convertButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("processTask")));
            convertButton.click();

            WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pickfiles")));

            if (downloadButton.isDisplayed()) {
                System.out.println("File uploaded and ready for download!");
            } else {
                System.out.println("File upload failed.");
            }
            
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}