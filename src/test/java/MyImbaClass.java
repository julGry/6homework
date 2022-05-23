import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.GoogleSearchPage;

import java.net.MalformedURLException;
import java.net.URL;

public class MyImbaClass {
//    WebDriver driver;

    @DataProvider(name = "dp", parallel = true)
    public Object[][] parseData() {
        return new Object[][]{
                {"https://www.google.com"},
                {"https://duckduckgo_.com"},

                {"https://www.google.com"},
                {"https://duckduckgo_.com"},
                {"https://www.google.com"},
                {"https://duckduckgo_.com"},
                {"https://www.google.com"},
                {"https://duckduckgo_.com"},
                {"https://www.google.com"},
                {"https://duckduckgo_.com"},
                {"https://www.google.com"},
                {"https://duckduckgo_.com"},
                {"https://www.google.com"},
                {"https://duckduckgo_.com"},

        };
    }

    @BeforeTest
    public void before() throws MalformedURLException {
        System.out.println("before");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();

//        ChromeOptions options = new ChromeOptions();
//        options.setCapability("se:name", "My Custom Name ololo");
//        driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
    }

    @Test(dataProvider = "dp")
    public void myImbaTest(String url) throws InterruptedException, MalformedURLException {
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        options.setCapability("se:name", "My Custom Name " + url);
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

        System.out.println("test: " + url);
        try {
            driver.get(url);
        } catch (Exception ex) {
            Thread.sleep(55_000);
            driver.quit();
        }
        GoogleSearchPage searchPage = new GoogleSearchPage(driver);
        searchPage.performSearch("WebDriver");
        System.out.println("Current title is: " + driver.getTitle());
        Thread.sleep(55_000);

        driver.quit();
    }

//    @AfterTest
//    public void afterTest() {
//        driver.quit();
//    }
}
