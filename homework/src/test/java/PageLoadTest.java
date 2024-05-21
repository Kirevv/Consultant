import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;

public class PageLoadTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  public String waitForWindow(int timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Set<String> whNow = driver.getWindowHandles();
    Set<String> whThen = (Set<String>) vars.get("window_handles");
    if (whNow.size() > whThen.size()) {
      whNow.removeAll(whThen);
    }
    return whNow.iterator().next();
  }
  @Test
  public void test() {
    driver.get("https://www.consultant.ru/cons/cgi/online.cgi?req=home&rnd=0.3720844080998448");
    driver.manage().window().maximize();
    driver.findElement(By.tagName("input")).click();
    driver.findElement(By.tagName("input")).sendKeys("нк ч2");
    driver.findElement(By.tagName("button")).click();
    vars.put("window_handles", driver.getWindowHandles());
    driver.findElement(By.cssSelector("div[data-index='1']")).click();
    vars.put("win5094", waitForWindow(2000));
    driver.switchTo().window(vars.get("win5094").toString());
    driver.findElement(By.cssSelector(".contents:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".x-input__field")).click();
    driver.findElement(By.cssSelector(".x-input__field")).sendKeys("статья 163");
    driver.findElement(By.cssSelector(".x-search-box__search-button > .x-button")).click();
    js.executeScript("window.scrollTo(0,0)");
    driver.findElement(By.cssSelector(".x-list__item--current > span:nth-child(2)")).click();
    driver.switchTo().frame(0);
    js.executeScript("window.scrollTo(0,0)");
    driver.findElement(By.id("z8")).click();
    driver.switchTo().defaultContent();
    driver.findElement(By.cssSelector(".print-16")).click();
  }
}
