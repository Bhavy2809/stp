import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class lab20 {
    public static void main(String[] args) {
        Thread myntraThread = new Thread(new MyntraTest());
        Thread flipkartThread = new Thread(new FlipkartTest());

        myntraThread.start();
        flipkartThread.start();

        try {
            myntraThread.join();
            flipkartThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nBoth Myntra and Flipkart searches have finished.");
    }
}

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
            System.out.println("Myntra Search Completed: Page title is " + driver.getTitle());
        } finally {
            driver.quit();
        }
    }
}

class FlipkartTest implements Runnable {
    @Override
    public void run() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get("https://www.flipkart.com/");
            driver.manage().window().maximize();
            
            Thread.sleep(2000); 
            try {
                driver.findElement(By.xpath("//span[text()='✕']")).click();
            } catch (Exception e) {
            }

            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("T-shirt");
            searchBox.sendKeys(Keys.ENTER);
            System.out.println("Flipkart Search Completed: Page title is " + driver.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}