
package testpkg;




import org.testng.annotations.Test;

import basepkg.Basepgm;
import utilitiespkg.Excelutilis;

public class Logintest extends Basepgm
{
	
	@Test(priority=1)
    public void login() throws Exception
	{
        
        String xl = "C:\\Users\\Lenovo\\OneDrive\\Desktop\\Selenium works\\Project.xlsx";
        String Sheet = "login";
        int rowcount = Excelutilis.getRowCount(xl, Sheet);

        for (int i = 1; i <= rowcount; i++) {
            String Username = Excelutilis.getCellValString(xl, Sheet, i, 0);
            String Pwrd = Excelutilis.getCellValString(xl, Sheet, i, 1);

            if (Username == null || Username.trim().isEmpty()) continue;

            hbl.closepopup(); // Handle initial site popups
            obj.setup(); // Open the login modal
            
            System.out.println("Testing Row " + i + ": " + Username);
            
            obj.setvalues(Username, Pwrd); // Fills fields & switches to Password tab
            obj.login();

            // Wait for the site to process the login request
            Thread.sleep(5000); 

            String currentUrl = driver.getCurrentUrl();
            
            // Validation: Shine uses both 'mydashboard' and 'home' for logged-in users
            if (currentUrl.contains("mydashboard") || currentUrl.contains("/myshine/home")) {
                System.out.println("SUCCESS: Logged in with Row " + i);
//                isLoginSuccessful = true;
                break; // Stop looping once we have a valid session
            } else {
                System.out.println("FAILED: Row " + i + " credentials did not lead to dashboard. Refreshing...");
                driver.navigate().refresh();
                // Loop continues to next row
            }
        }
	}
	
	
//	boolean isLoginSuccessful = false;
//	@Test(priority=2, dependsOnMethods = "login")
//    public void loginvalidation() {
//        // First, assert that we actually found a working user in the loop
//        AssertJUnit.assertEquals(isLoginSuccessful, "All Excel rows failed. No valid login found.");
//
//        String actualUrl = driver.getCurrentUrl();
//        boolean isValidPage = actualUrl.contains("mydashboard") || actualUrl.contains("home");
//        
//        AssertJUnit.assertEquals(isValidPage, "Final URL check failed. Actual URL: " + actualUrl);
//        System.out.println("Final Validation Passed: Dashboard reached successfully.");
//    }
}
		
