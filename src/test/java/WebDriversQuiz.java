import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;

public class WebDriversQuiz {

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
    public void herokuTest(){
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement element = driver.findElement(By.xpath("//select[@id='dropdown']"));
        element.click();
        WebElement option1 = driver.findElement(By.cssSelector("[value ='1']"));
        WebElement option2 = driver.findElement(By.cssSelector("[value ='2']"));
        option1.click();
        Assert.assertTrue(option1.isSelected());
        Assert.assertFalse(option2.isSelected());

    }

    @Test
    public void hoverTest(){
        driver.get("https://the-internet.herokuapp.com/hovers");
        WebElement element = driver.findElement(By.cssSelector("[alt ='User Avatar']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        WebElement caption = driver.findElement(By.xpath("//*[text()='name: user1']"));
        Assert.assertTrue("The caption is displayed",caption.isDisplayed());
    }

    @Test
    public void alertTest(){
        driver.get("https://the-internet.herokuapp.com/context_menu");
        WebElement element = driver.findElement(By.cssSelector("#hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        System.out.println(driver.switchTo().alert().getText());
    }

    @Test
    public void keyboardTest(){
        driver.get("https://the-internet.herokuapp.com/key_presses");
        WebElement element = driver.findElement(By.cssSelector("#target"));
        Actions actions = new Actions(driver);
        actions.click(element).perform();
        actions.sendKeys(Keys.ARROW_RIGHT).pause(1000).perform();
        System.out.println(driver.findElement(By.id("result")).getText());
        Assert.assertEquals("You entered: RIGHT",driver.findElement(By.id("result")).getText());
    }
    
    @Test
    public void cssValueTest(){
        driver.get("https://ultimateqa.com/simple-html-elements-for-automation/");
        WebElement element = driver.findElement(By.xpath("//*[text()='Clickable Icon']"));
        String link = element.getAttribute("href");
        Assert.assertEquals("https://ultimateqa.com/link-success/",link);

        Assert.assertEquals("padding-box",element.getCssValue("background-origin"));

    }
}
