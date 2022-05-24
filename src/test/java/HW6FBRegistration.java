import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FBClassUser;
import pages.FBRegistrationPage;
import pages.FBStartPage;

public class HW6FBRegistration {
    WebDriver driver;
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
    }

    @Test(dataProvider = "dp", priority = 1 ) 
    public void method1(String start_page) {
        driver.get(start_page);
        page1 = new FBStartPage(driver);
        page2 = page1.FBStartPageDoRegistration();
    }

    @Test(dataProvider = "inputData", dependsOnMethods = "method1")
    public void method2(FBClassUser user) {
        page2.FBRegistrationPageSetUser(user);
    }
    
    @Test(dataProvider = "inputData", dependsOnMethods = "method2")
    public void method3(FBClassUser user) throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertEquals(page2.first_name.getAttribute("value"), user.first_name);
        Assert.assertEquals(page2.last_name.getAttribute("value"), user.last_name);
        Assert.assertEquals(page2.reg_email.getAttribute("value"), user.reg_email);
        Assert.assertEquals(page2.password.getAttribute("value"), user.password);
        Assert.assertEquals(page2.birth_day.getAttribute("value"), user.birth_day);
        Assert.assertEquals(page2.birth_month.getAttribute("value"), user.birth_month);
        Assert.assertEquals(page2.birth_year.getAttribute("value"), user.birth_year);
        Assert.assertTrue(page2.sex.get(user.sex).isSelected());
    }

    @Test(dataProvider = "inputData", dependsOnMethods = "method2")
    public void method4(FBClassUser user) throws InterruptedException {
        Assert.assertTrue(page2.web_submit.isDisplayed());
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
