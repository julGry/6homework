import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FBStartPage {
  protected WebDriver driver;

  private By reg_button = By.xpath(".//*[@data-testid='open-registration-form-button']");
 
  public FBStartPage(WebDriver driver) {
      this.driver = driver;
      if (driver.findElements(reg_button).isEmpty()) {
          throw new IllegalStateException("This is not Facebook start page");
      }
  }

  public FBRegistrationPage FBStartPageDoRegistration() {
      driver.findElement(reg_button).click();
      return new FBRegistrationPage(driver);
  }

}