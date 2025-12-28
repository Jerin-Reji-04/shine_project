package testpkg;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import basepkg.Basepgm;
import pagepkg.Home_before_login;
import pagepkg.Profile;
import pagepkg.Login;

public class Profiletest extends Basepgm
{
	Profile hobj;
    Login obj;
    Home_before_login hbl;
    boolean isLogged = false;

    @Test(priority=1)
    public void login1() throws Exception {
        hbl = new Home_before_login(driver);
        obj = new Login(driver);
        hobj = new Profile(driver);

        String xl = "C:\\Users\\Lenovo\\OneDrive\\Desktop\\Selenium works\\Project.xlsx";
        
        // This one line does all the Excel work!
        isLogged = obj.loginWithExcel(xl, "login", hbl);
        
        AssertJUnit.assertEquals(isLogged, "Could not login with any user from Excel");
    }
	
//	@Test(priority=2)
//	public void hovering()
//	{
//		int pauseTime = 2;
//		hobj.navhovering(pauseTime);
//	}
	
	@Test(priority=2)
	public void profile()
	{
		hobj.profiledropdownhover();
		System.out.println("Profile drop down successfully");
	}
	
	@Test(priority=3)
	public void file_upload()
	{
       String resumefile = "C:\\Users\\Lenovo\\Downloads\\Jerin Reji's Resume.pdf";
       System.out.println("Starting Robot Class File Upload...");
       hobj.resumeuploadd(resumefile);
       try {
           Thread.sleep(5000); // Wait 5 seconds for the overlay to go away
       } catch (Exception e) {}
       System.out.println("Resume uploaded sucessfully");
       
       
	}
	
//	@Test(priority=4)
//	public void p_details() throws Exception
//	{
//		hobj.personaldetails();
//		hobj.DOB("1998", "Nov", "21");
//	}
	
//	@Test(priority=4)
//	public void d_job() {
//	    hobj.desiredjob();
//	    hobj.selectJobRole("QA", "Qa Analyst");
//	    
//	    // Test the CTC
//	    hobj.selectctc("3 LPA");
//	    
//	    // Verification: Get the text of the dropdown and check if it matches
//	    String actualValue = driver.findElement(By.xpath("//div[contains(@class,'customSelect_selectHeader')]")).getText();
//	    Assert.assertTrue(actualValue.contains("3 LPA"), "CTC selection did not update the UI!");
//	    
//	    hobj.savejobdetails();
//	}
//
//	@Test(priority=5, dependsOnMethods = "login1")
//	public void c_guide() throws Exception {
//	    hobj.careerguide();
//	}
//		
	
	@Test(priority = 6, dependsOnMethods = "profile")
	public void Signout() throws Exception {
	    hobj.signout();
	    // Verify that we are back at the home/login page
	    boolean isLoggedOut = driver.getPageSource().contains("signout");
	    AssertJUnit.assertEquals(isLoggedOut, "Signout failed: Login button not found.");
	}

	}
