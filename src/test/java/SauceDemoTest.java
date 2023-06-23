import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SauceDemoTest {

    WebDriver driver;

    @Before
    public void setup(){
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
    public void sauceDemoE2ETest() {
        //Open the webpage
        driver.get("https://www.saucedemo.com/v1/");

        //Login
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".btn_action")).click();

        //Add items to cart and checkout
        driver.findElement(By.xpath("(//*[text()= 'ADD TO CART'])[1]")).click();
        driver.findElement(By.cssSelector("[data-icon = 'shopping-cart']")).click();
        driver.findElement(By.cssSelector(".btn_action.checkout_button")).click();

        //Add address
        driver.findElement(By.id("first-name")).sendKeys("firstname");
        driver.findElement(By.id("last-name")).sendKeys("lastname");
        driver.findElement(By.id("postal-code")).sendKeys("22");
        driver.findElement(By.xpath("//input[@value='CONTINUE']")).click();

        //Finish the order
        driver.findElement(By.xpath("//*[@class='btn_action cart_button']")).click();

        //Validate the order completion by checking final message
        Assert.assertTrue(driver.findElement(By.cssSelector(".complete-header")).isDisplayed());
        System.out.println("Test Passed Successfully");


    }
}
