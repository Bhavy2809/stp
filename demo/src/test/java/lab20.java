import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class lab20 {
    public static void main(String[] args) {
        // Create two separate tasks (Runnables)
        Thread myntraThread = new Thread(new MyntraTest());
        Thread flipkartThread = new Thread(new FlipkartTest());

        // Start both tasks to run at the same time
        myntraThread.start();
        flipkartThread.start();

        try {
            // Wait for both tasks to finish before continuing
            myntraThread.join();
            flipkartThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n🏁 Both Myntra and Flipkart searches have finished.");
    }
}

/**
 * A task to perform a search on Myntra.
 */
class MyntraTest implements Runnable {
    @Override
    public void run() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.myntra.com/");
            driver.manage().window().maximize();
            WebElement searchBox = driver.findElement(By.cssSelector("input.desktop-searchBar"));
            searchBox.sendKeys("Shirt");
            searchBox.sendKeys(Keys.ENTER);
            System.out.println("✅ Myntra Search Completed: Page title is " + driver.getTitle());
        } finally {
            driver.quit();
        }
    }
}

/**
 * A task to perform a search on Flipkart.
 */
class FlipkartTest implements Runnable {
    @Override
    public void run() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.flipkart.com/");
            driver.manage().window().maximize();
            
            // Wait a moment for the login pop-up and close it
            Thread.sleep(2000); 
            try {
                driver.findElement(By.xpath("//span[text()='✕']")).click();
            } catch (Exception e) {
                // Ignore if the pop-up doesn't appear
            }

            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("T-shirt");
            searchBox.sendKeys(Keys.ENTER);
            System.out.println("✅ Flipkart Search Completed: Page title is " + driver.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}