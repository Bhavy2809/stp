import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class lab15 {
    public static void main(String[] args) throws IOException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        String csvFile = "C:\\Users\\shukl\\Desktop\\login_data.csv";
        
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        br.readLine();

        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String username = data[0];
            String password = data[1];

            driver.findElement(By.id("username")).clear();
            driver.findElement(By.id("username")).sendKeys(username);

            driver.findElement(By.id("password")).clear();
            driver.findElement(By.id("password")).sendKeys(password);
            
            driver.findElement(By.id("submit")).click();

            try {
                driver.findElement(By.linkText("Log out"));
                System.out.println("Login successful for: " + username);
                driver.findElement(By.linkText("Log out")).click();
            } catch (Exception e) {
                System.out.println("Login failed for: " + username);
            }
        }
        
        br.close();
        driver.quit();
    }
}