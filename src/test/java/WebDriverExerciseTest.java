import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class WebDriverExerciseTest {

    WebDriver driver;
    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @After
    public void teardown(){
        driver.quit();
    }

    @Test
    public void actionsTest(){
        driver.get("https://example.cypress.io/commands/actions");
        WebElement password = driver.findElement(By.cssSelector("#password1"));
        Actions actions = new Actions(driver);
        actions.moveToElement(password).click().perform();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@for='password1']")).
                getAttribute("style").contains("color: orange;"));
    }

    @Test
    public void cookiesTest(){
        driver.get("https://example.cypress.io/commands/cookies");
        driver.findElement(By.cssSelector(".set-a-cookie")).click();
        Cookie cookie = driver.manage().getCookieNamed("token");
        Assert.assertEquals("123ABC", cookie.getValue());
    }

    @Test
    public void scrollTest() throws InterruptedException {
        driver.get("https://ultimateqa.com/complicated-page");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);
    }
}
