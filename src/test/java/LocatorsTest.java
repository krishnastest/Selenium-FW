import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LocatorsTest {

    static WebDriver driver;

    @BeforeClass
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void webdriverManagerTest(){

        driver.get("https://www.saucedemo.com/v1/");

        driver.findElement(By.id("login-button"));
        driver.findElement(By.name("user-name"));
        driver.findElement(By.cssSelector("#login-button"));
        driver.findElement(By.className("btn_action"));
        driver.findElement(By.tagName("input"));
        driver.findElement(By.xpath("//input[@id=\"login-button\"]"));

        driver.get("https://ultimateqa.com/simple-html-elements-for-automation");
        driver.findElement(By.linkText("Click me using this link text!"));
        driver.findElement(By.partialLinkText("link text!"));

    }
}
