import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class lab18 {

    public static void main(String[] args) {
        // --- Test on Chrome Browser ---
        System.out.println("--- Starting test on Google Chrome ---");
        WebDriverManager.chromedriver().setup();
        WebDriver chromeDriver = new ChromeDriver();
        performSearch(chromeDriver, "Chrome");

        // --- Test on Edge Browser (Manual Driver Setup) ---
        System.out.println("--- Starting test on Microsoft Edge ---");
        
        // **FIX**: Point directly to the msedgedriver.exe file you downloaded.
        // Make sure this path is correct for your system.
        System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
        
        WebDriver edgeDriver = new EdgeDriver();
        performSearch(edgeDriver, "Edge");
    }

    /**
     * This reusable method performs a search on Myntra.
     */
    public static void performSearch(WebDriver driver, String browserName) {
        try {
            driver.manage().window().maximize();
            driver.get("https://www.myntra.com");

            WebElement searchBox = driver.findElement(By.cssSelector("input.desktop-searchBar"));
            searchBox.sendKeys("Men T-shirt");
            searchBox.sendKeys(Keys.ENTER);

            System.out.println("✅ " + browserName + " - Search Completed. Page title: " + driver.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}