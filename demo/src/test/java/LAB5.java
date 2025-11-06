import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; // Import this class
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LAB5 {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        // --- START OF FIX ---
        // 1. Create a ChromeOptions object to customize the browser session
        ChromeOptions options = new ChromeOptions();

        // 2. Set the User-Agent to mimic a real browser
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");

        // 3. Disable the flag that tells the website it's being automated
        options.addArguments("--disable-blink-features=AutomationControlled");
        
        // 4. Pass these options when creating the ChromeDriver
        WebDriver driver = new ChromeDriver(options);
        // --- END OF FIX ---
        
        driver.manage().window().maximize();

        try {
            driver.get("https://www.ilovepdf.com/pdf_to_word");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Click on the visible "Select PDF file" button
            WebElement selectFileButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("pickfiles")));
            selectFileButton.click();
            
            Thread.sleep(1000);

            // Locate the hidden input and send the file path
            WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
            // Correct version with double backslashes
fileInput.sendKeys("C:\\Users\\shukl\\Downloads\\BH.pdf");

            // Wait for the "Convert to Word" button
            WebElement convertButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("processTask")));
            convertButton.click();

            // Wait for the download button to appear
            WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pickfiles")));

            if (downloadButton.isDisplayed()) {
                System.out.println("✅ File uploaded and ready for download!");
            } else {
                System.out.println("❌ File upload failed.");
            }
            
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           // driver.quit();
        }
    }
}
