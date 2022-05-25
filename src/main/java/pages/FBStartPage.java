package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import pages.FBRegistrationPage;

import java.util.List;

public class FBStartPage {
    protected WebDriver driver;

    @FindBy(xpath=".//*[@data-testid='open-registration-form-button']")
    private WebElement reg_button;

    @FindBy(xpath=".//*[@data-testid='open-registration-form-button']")
    private List<WebElement> reg_buttons;

    public FBStartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public FBRegistrationPage FBStartPageDoRegistration() {
        if (reg_buttons.isEmpty()) {
            throw new IllegalStateException("This is not Facebook start page");
        }
        reg_button.click();
        return new FBRegistrationPage(driver);
    }

}