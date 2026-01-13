package basepkg;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import pagepkg.Home;
import pagepkg.Login;
import pagepkg.Profile;
import pagepkg.cart;



public class Basepgm 
{
	public static WebDriver driver;
	public 	Home hbl;
	public cart car;
	public Login obj;
	public Profile hobj;
	
	
	
	@BeforeTest
	public void setup() throws Exception
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.get("https://www.shine.com/");	
	}
	
	
	
	@BeforeMethod
	public void objectcreation() 
	{
		hbl=new Home(driver);
		car=new cart(driver);
		obj=new Login(driver);
        hobj = new Profile(driver);
	}
	
	
	
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}

}
