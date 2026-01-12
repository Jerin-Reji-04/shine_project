package testpkg;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import basepkg.Basepgm;

public class Profiletest extends Basepgm
{
	
    boolean isLogged = false;
    

    @Test(priority=1)
    public void login1() throws Exception {
        
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
		hbl.closepopup();
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
       hbl.closepopup();
       
       
	}
	
//	@Test(priority=4)
//	public void p_details() throws Exception
//	{
//		hobj.personaldetails();
////		hobj.selectDOB( "1998", "Nov", "21");
//	}
	
//	@Test(priority=4)
//	public void d_job() {
//	    hobj.desiredjob();
//	    hobj.selectJobRole("QA", "Qa Analyst");
////	    hobj.selectjoblocation("Mum", "Mumbai");
//	    
//	    // Test the CTC
//	    hobj.selectctc("3 LPA");
//	    
//	    
//	    hobj.savejobdetails();
//	}

	
	
	@Test(priority = 5, dependsOnMethods = "profile")
	public void Signout() throws Exception 
	{
	    hobj.signout();
	   
	}

    
   
}
