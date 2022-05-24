package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FBRegistrationPage {
    protected WebDriver driver;

    @FindBy(xpath=".//button[@name='websubmit']")
    public WebElement web_submit;

    @FindBy(xpath=".//*[@name='firstname']")
    public WebElement first_name;

    @FindBy(xpath=".//*[@name='lastname']")
    public WebElement last_name;

    @FindBy(xpath=".//*[@name='reg_email__']")
    public WebElement reg_email;

    @FindBy(xpath=".//*[@id='password_step_input']")
    public WebElement password;

    @FindBy(xpath=".//*[@id='day']")
    public WebElement birth_day;

    @FindBy(xpath=".//*[@id='month']")
    public WebElement birth_month;

    @FindBy(xpath=".//*[@id='year']")
    public WebElement birth_year;

    @FindBy(xpath=".//input[@name='sex']")
    public List<WebElement> sex;

    public FBRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void FBRegistrationPageSetUser(FBClassUser user) {
        first_name.sendKeys(user.first_name);
        last_name.sendKeys(user.last_name);
        reg_email.sendKeys(user.reg_email);
        password.sendKeys(user.password);

        new Select(birth_day).selectByValue(user.birth_day);
        new Select(birth_month).selectByValue(user.birth_month);
        new Select(birth_year).selectByValue(user.birth_year);

        sex.get(user.sex).click();
    }
}