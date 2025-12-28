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
	
	@FindBy(xpath = "//button[contains(text(),'Login')]")WebElement slogin;
	@FindBy(xpath = "//input[@type='email' or @name='email']") WebElement semail;
	@FindBy(xpath = "//input[@type='password' or @name='password']") WebElement spwrd;
	@FindBy(xpath = "//button[contains(text(),'Log In')]") WebElement sloginbtn;
	@FindBy(xpath = "(//*[contains(@class,'btn-close')])[2]")WebElement popupclose;
	@FindBy(xpath = "//*[@id=\"_r_9_-checkbox-remember-me\"]") WebElement Checkbox;
	@FindBy(xpath = "//button[contains(text(),'Login Via Password')]")WebElement l_passwrd;
	
	public Login(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	
	public void loginclick()//to click the login button in the home page
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.elementToBeClickable(slogin));
	    slogin.click();
	    System.out.println("Login modal opened");
	}
	
	
	
	public void logindetail(String email, String password) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    l_passwrd.click();
	    
	    wait.until(ExpectedConditions.visibilityOf(semail));
	    
	    semail.sendKeys(email);
	    spwrd.sendKeys(password);
	    
	}

	public void loginbtn() 
	{
	    sloginbtn.click();
	    
	}
	
	
	public String validation()//to get the current url
	{
		return(driver.getCurrentUrl());
	}
	
//	public void closepopup() 
//	{
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("modalID")));
//			
//			  System.out.println("Popup detected.");
//		
//			 try {
//				 popupclose.click();
//				 System.out.println("Clicked popup close button");
//				 
//				
//			} catch (Exception e) {
//				driver.findElement(By.tagName("body")).click();
//	            System.out.println("Clicked body to dismiss popup");
//			}
//		}
//		catch (Exception e) {
//			System.out.println("No popup found or already gone");
//		}
//		}
	public void checkbox()
	{
		try {
	        if (!Checkbox.isSelected()) {
	            Checkbox.click();
	        }
	    } catch (Exception e) 
		{
	        System.out.println("Checkbox not interactable or not found");
	    }
	}
	
	// Add this inside public class Login
	public boolean loginWithExcel(String xlPath, String sheetName, Home_before_login hbl) throws Exception {
	    int rowcount = utilitiespkg.Excelutilis.getRowCount(xlPath, sheetName);

	    for (int i = 1; i <= rowcount; i++) {
	        String user = utilitiespkg.Excelutilis.getCellValString(xlPath, sheetName, i, 0);
	        String pass = utilitiespkg.Excelutilis.getCellValString(xlPath, sheetName, i, 1);

	        if (user == null) continue;

	        hbl.closepopup();
	        this.loginclick();
	        this.logindetail(user, pass);
	        this.loginbtn();

	        Thread.sleep(5000); 

	        // If we reach the dashboard, stop and return true
	        if (driver.getCurrentUrl().contains("home") || driver.getCurrentUrl().contains("dashboard")) {
	            return true; 
	        } 
	        driver.navigate().refresh();
	    }
	    return false; // None worked
	}
	
}



	


