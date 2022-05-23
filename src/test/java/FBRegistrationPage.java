import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class FBRegistrationPage {
  protected WebDriver driver;
  protected WebDriverWait wait;

  private By web_submit = By.xpath(".//button[@name='websubmit']");
  private By first_name = By.xpath(".//*[@name='firstname']");
  private By last_name = By.xpath(".//*[@name='lastname']");
  private By reg_email = By.xpath(".//*[@name='reg_email__']");
  private By password = By.xpath(".//*[@id='password_step_input']");
  private By birth_day = By.xpath(".//*[@id='day']");
  private By birth_month = By.xpath(".//*[@id='month']");
  private By birth_year = By.xpath(".//*[@id='year']");
  private By sex = By.xpath(".//input[@name='sex']");

  public FBRegistrationPage(WebDriver driver) {
      this.driver = driver;
      this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
      wait.until(ExpectedConditions.presenceOfElementLocated(web_submit));
  }

  public void FBRegistrationPageSetUser(FBClassUser user) {
      driver.findElement(first_name).sendKeys(user.first_name);
      driver.findElement(last_name).sendKeys(user.last_name);
      driver.findElement(reg_email).sendKeys(user.reg_email);
      driver.findElement(password).sendKeys(user.password);
    
      Select etSelectField;
      etSelectField = new Select(driver.findElement(birth_day));
      etSelectField.selectByValue(user.birth_day);
      etSelectField = new Select(driver.findElement(birth_month));
      etSelectField.selectByValue(user.birth_month);
      etSelectField = new Select(driver.findElement(birth_year));
      etSelectField.selectByValue(user.birth_year);
      
      driver.findElements(sex).get(user.sex).click();
  }

  public void FBRegistrationPageCheckUser(FBClassUser user) {
      Assert.assertEquals(driver.findElement(first_name).getAttribute("value"), user.first_name);
      Assert.assertEquals(driver.findElement(last_name).getAttribute("value"), user.last_name);
      Assert.assertEquals(driver.findElement(reg_email).getAttribute("value"), user.reg_email);
      Assert.assertEquals(driver.findElement(password).getAttribute("value"), user.password);

      Assert.assertEquals(driver.findElement(birth_day).getAttribute("value"), user.birth_day);
      Assert.assertEquals(driver.findElement(birth_month).getAttribute("value"), user.birth_month);
      Assert.assertEquals(driver.findElement(birth_year).getAttribute("value"), user.birth_year);

      Assert.assertTrue(driver.findElements(sex).get(user.sex).isSelected());
   }

  public void FBRegistrationPageCheckSubmit() throws InterruptedException {
      Assert.assertTrue(driver.findElement(web_submit).isDisplayed());
  }

}