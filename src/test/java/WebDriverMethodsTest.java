import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class WebDriverMethodsTest {

    WebDriver driver;

    @Before
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void windowHandles(){
        driver.navigate().to("https://the-internet.herokuapp.com/windows");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://the-internet.herokuapp.com/windows/new')");

        String originalWindow = driver.getWindowHandle();
        Set windowHandles = driver.getWindowHandles();

        assertEquals("The Internet", driver.getTitle());
        windowHandles.remove(originalWindow);

        driver.switchTo().window(windowHandles.iterator().next().toString());
        assertEquals("New Window", driver.getTitle());

        //Even after the second window is closed, the scope is not switched to the first window, to access
        //the first window switch back to it
        driver.close();
        driver.switchTo().window(originalWindow);
        assertEquals("The Internet", driver.getTitle());
    }

    @Test
    public void framesTest(){
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame(0);
        assertEquals("LEFT", driver.findElement(By.tagName("body")).getText());

        //When in a nested frame, to switch to the frame one level higher we can use parentframe method
        driver.switchTo().parentFrame();

        //When we want to get to the topmost frame or the first frame in the page we use defaultcontent method
        driver.switchTo().defaultContent();

        Assert.assertNotNull(driver.findElement(By.name("frame-bottom")));
        Assert.assertEquals("frame-bottom", driver.findElement(By.xpath("//frame[@src='/frame_bottom']")).getAttribute("name"));
    }

    @Test
    public void alertsTest(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        driver.switchTo().alert().dismiss();
        assertEquals("You successfully clicked an alert", driver.findElement(By.cssSelector("#result")).getText());


        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Confirm']")).click();
        driver.switchTo().alert().accept(); 
        assertEquals("You clicked: Ok", driver.findElement(By.cssSelector("#result")).getText());

        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Prompt']")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.sendKeys("Keys mean text from keyboard");
        alert.accept();
        Assert.assertTrue(driver.findElement(By.cssSelector("#result")).getText().contains("keyboard"));
    }
}

