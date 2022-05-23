package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {
    WebDriver driver;

    @FindBy(xpath = ".//*[@name='q']")
    WebElement etSearchField;

    public void performSearch(String text) {
        etSearchField.sendKeys(text);
        etSearchField.submit();
    }

    public GoogleSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
