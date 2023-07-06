import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.openqa.selenium.devtools.v85.debugger.Debugger.pause;

public class SelectorsHub {

    WebDriver driver;

    @Before
    public void setup(){
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @After
    public void teardown(){
        driver.quit();
    }

    @Test
    public void Test(){
        driver.navigate().to("https://selectorshub.com/xpath-practice-page/");
        WebElement element = driver.findElement(By.xpath("//*[contains(text(),'Inspect this element,')]"));
        System.out.println(element.getAccessibleName());
    }

    @Test
    public void iframeTest(){
        driver.get("https://selectorshub.com/iframe-scenario/");
        driver.switchTo().frame("pact1");
        String value1 = driver.findElement(By.xpath("//input[@id='inp_val']")).getAttribute("placeholder");
        System.out.println(value1);

        //Switching to frame 2 inside frame 1, we are already inside frame 1
        driver.switchTo().frame("pact2");
        String value2 = driver.findElement(By.xpath("//input[@id='jex']")).getAttribute("placeholder");
        System.out.println(value2);

        //Switching to a nested iframe
        driver.get("https://selectorshub.com/iframe-scenario/");
        driver.switchTo().frame("pact1").switchTo().frame("pact2").switchTo().frame("pact3");
        String value3 = driver.findElement(By.xpath("//input[@id='glaf']")).getAttribute("placeholder");
        System.out.println(value3);

    }

    @Test
    public void shadowDomTest() throws InterruptedException {
        driver.get("https://selectorshub.com/shadow-dom-in-iframe/");
        driver.switchTo().frame("pact");

//        WebElement root = driver.findElement(By.cssSelector(".jackPart"));
//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        WebElement dom1 = (WebElement) js.executeScript("return arguments[0].shadowRoot", root);
//        System.out.println(dom1);

        //This Element is inside single shadow DOM.
        SearchContext shadow = driver.findElement(By.cssSelector("#snacktime")).getShadowRoot();
        Thread.sleep(1000);
        System.out.println(shadow.findElement(By.cssSelector("#tea")).getAttribute("placeholder"));


//        WebElement shadowHost = driver.findElement(By.cssSelector("[data-id='cf2b7a5']"));
//        System.out.println(shadowHost.getAttribute("class"));
//        SearchContext shadowRoot = shadowHost.getShadowRoot();
//        System.out.println(shadowRoot.findElement(By.cssSelector(".food")).getText());
    }

    @Test
    public void watirTest(){
        driver.get("http://watir.com/examples/shadow_dom.html");
        WebElement shadowHost = driver.findElement(By.cssSelector("#shadow_host"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        System.out.println(shadowRoot.findElement(By.cssSelector(".info")).getText());
    }
}