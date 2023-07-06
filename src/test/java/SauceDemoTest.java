import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.json.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SauceDemoTest {

    WebDriver driver;
    String jsonContent = new String(Files.readAllBytes
            (Paths.get("C:\\Users\\singh\\IdeaProjects\\Selenium-FW\\src\\main\\resources\\sauceDemo.json")));
    JSONObject jsonObject = new JSONObject(jsonContent);

    public SauceDemoTest() throws IOException {
    }

    @Before
    public void setup() {
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
        driver.findElement(By.cssSelector("#user-name")).sendKeys((CharSequence) jsonObject.get("username"));
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
