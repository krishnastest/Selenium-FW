import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FirstTest {

    @Test
    public void firstTest() throws InterruptedException {

        //Set the path for chromedriver.exe file
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\singh\\IdeaProjects" +
                "\\Selenium-FW\\src\\main\\resources\\chromedriver.exe");

        //Using chromeoptions below as the link will not be accessed because only local connections are allowed.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //Initialising driver
        WebDriver driver = new ChromeDriver(options);

        //Get function to open the URL
        driver.get("https://github.com/krishnastest/Selenium-FW");

        //Putting the thread to sleep to pause the execution
        Thread.sleep(5000);

        //Close the browser at the end
        driver.quit();
    }
}
