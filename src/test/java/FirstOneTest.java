import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FirstOneTest {

    @BeforeClass
    public static void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void webdriverManagerTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/v1/");
        driver.close();
    }
}
