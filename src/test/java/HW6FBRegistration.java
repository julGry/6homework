import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HW6FBRegistration {
    WebDriver driver;
    WebDriverWait wait;
    FBStartPage page1;
    FBRegistrationPage page2;

    @DataProvider(name = "dp")
    public Object[][] parseData() {
        return new Object[][] {
                {"https://www.facebook.com/"}
        };
    }

    @DataProvider(name = "inputData")
    public FBClassUser[][] inputUser() {
        return new FBClassUser[][] {{new FBClassUser("Vasya",
                "Pupkin",
                "your_email@gmail.com",
                "QwertY123",
                "12",
                "10",
                "2000",
                0)}};
    }

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        System.setProperty("webdriver.gecko.driver", "/Users/yuliia/IdeaProjects/lesson3/source/geckodriver");
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
    }

    @Test(dataProvider = "dp", priority = 1 ) 
    public void method1(String start_page) {
        driver.get(start_page);
        page1 = new FBStartPage(driver);
        page2 = page1.FBStartPageDoRegistration();
    }

    @Test(dataProvider = "inputData", priority = 2, dependsOnMethods = "method1", groups = "input data")
    public void method2(FBClassUser user) {
        page2.FBRegistrationPageSetUser(user);
    }
    
    @Test(dataProvider = "inputData", priority = 3, dependsOnMethods = "method2", groups = "check data")
    public void method3(FBClassUser user) throws InterruptedException {
        Thread.sleep(5000);
        page2.FBRegistrationPageCheckUser(user);
    }

    @Test(dataProvider = "inputData", priority = 4, dependsOnMethods = "method2", groups = "check data")
    public void method4(FBClassUser user) throws InterruptedException {
        page2.FBRegistrationPageCheckSubmit();
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
