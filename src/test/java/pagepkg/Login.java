
package pagepkg;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login 
{
	
	WebDriver driver;
	
	@FindBy(xpath = "//button[contains(text(),'Login')]")WebElement flogin;
	@FindBy(xpath = "//button[contains(text(),'Login Via Password')]")WebElement vpassword;
	
	
	@FindBy(xpath = "//input[@id=\"_r_5_-input-email\"]")WebElement iemail;
	@FindBy(xpath = "//input[@id=\"_r_7_-input-password\"]")WebElement ipassword;
	@FindBy(xpath = "//button[@id=\"_r_c_\"]")WebElement loginbtn;
	@FindBy(xpath = "//*[@id=\"_r_9_-checkbox-remember-me\"]")WebElement checkbox;
	
	
	public Login(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void setup()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.elementToBeClickable(flogin)).click();
	    
	    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait1.until(ExpectedConditions.elementToBeClickable(vpassword)).click();
	    
	}
	
	public void setvalues(String email,String password)
	{
		iemail.sendKeys(email);
		ipassword.sendKeys(password);
		
	}
	
	public void login()
	{
		checkbox.click();
		loginbtn.click();
	}
	
	
}



	
