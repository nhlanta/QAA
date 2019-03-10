import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import  org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test1 {
    public static WebDriver driver;
    public  static WebElement element;

    @BeforeMethod
    public void launchBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
        driver.get("http://todomvc.com/examples/emberjs/");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void test1(){
        element = driver.findElement(By.cssSelector("input#new-todo"));
        element.sendKeys("a");
        element.sendKeys(Keys.ENTER);
        element.sendKeys("b");
        element.sendKeys(Keys.ENTER);
        element.sendKeys("c");
        element.sendKeys(Keys.ENTER);
        List<WebElement> todos = driver.findElements(By.cssSelector("div>label"));
        for (int j=0;j<todos.size();j++){
            System.out.println("All Todoos: " + todos.get(j).getText());
            Assert.assertEquals(todos.get(0).getText(), "a");
            Assert.assertEquals(todos.get(1).getText(), "b");
            Assert.assertEquals(todos.get(2).getText(), "c");
        }
        driver.findElement(By.cssSelector("ul>li:nth-of-type(2)>div>input.toggle")).click();
        driver.findElement(By.cssSelector("a[href='#/completed']")).click();
        List<WebElement> completedTodos = driver.findElements(By.cssSelector("div>label"));
        for (int c = 0; c <completedTodos.size(); c ++){
            System.out.println("Compeleted Todos: " + completedTodos.get(c).getText());
            Assert.assertEquals(completedTodos.get(0).getText(), "b");
        }
        driver.findElement(By.cssSelector("a[href='#/active']")).click();
        List<WebElement> activeTodos = driver.findElements(By.cssSelector("div>label"));
        for (int active = 0; active < activeTodos.size() ;active++){
            System.out.println("Active Todos: " + activeTodos.get(active).getText());
            Assert.assertEquals(activeTodos.get(0).getText(), "a");
            Assert.assertEquals(activeTodos.get(1).getText(), "c");
        }
    }
}
