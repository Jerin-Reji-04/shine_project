package basepkg;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;



public class Basepgm 
{
	public static WebDriver driver;
	
	@BeforeTest
	public void setup() throws Exception
	{
		driver=new ChromeDriver();
		driver.get("https://www.shine.com");
		driver.manage().window().maximize();
		
		
	}
	
	
	
	
	
//	@AfterTest
//	public void teardown()
//	{
//		driver.quit();
//	}

}
