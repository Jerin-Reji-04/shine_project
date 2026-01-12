package pagepkg;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Profile 
{
	WebDriver driver;
	Scanner sc=new Scanner(System.in);
	
	@FindBy(xpath="//*[@id=\"ReactContainer\"]/div[1]/header/nav/div/div/ul[1]/li/a") List<WebElement>homenav;
	@FindBy(xpath = "//*[@id=\"webHeaderNova\"]/div/div/div[3]/div[2]/a")WebElement profiledropdown;	
	@FindBy(xpath = "//*[@id=\"webHeaderNova\"]/div/div/div[3]/div[2]/div/ul/li[1]/a") WebElement myprofile;
	@FindBy(xpath = "(//button[contains(@class,'uploadResume_uploadButton__XM7Lv')])") WebElement uploadbtn;
	@FindBy(name="resume_file")WebElement browsefile;
	@FindBy(xpath = "(//button[contains(@class,'editIconButton_editBtn__OWFPw')])[4]")WebElement personalediticon;
	@FindBy(xpath = "//*[@id=\"__next\"]/div[5]/div/button")WebElement closebtn;
	@FindBy(xpath = "//input[contains(@class,'personalDetails_dateInput')]")WebElement dateofbirthtextarea;
	@FindBy(xpath = "//th[contains(@class,'rdtSwitch')]") 
	WebElement calendarSwitcher;	
	@FindBy(xpath = "//th[contains(@class,'rdtNext')]") 
	WebElement nextButton;
	@FindBy(xpath = "(//button[contains(@class,'editIconButton_editBtn__OWFPw')])[3]")WebElement desiredjobedit;
	@FindBy(xpath = "//input[contains(@placeholder,'Type to search job roles...')]")WebElement jobrole;
	@FindBy(xpath = "//input[contains(@placeholder,'Type to search locations...')]")WebElement joblocation;
	@FindBy(xpath = "//button[contains(@class,'desiredProfileModal_saveBtn__ce362')]")WebElement savedesiredjob;
	@FindBy(xpath = "//button[contains(text(),'Any')]")WebElement desiredjobtype;
	@FindBy(xpath = "//div[contains(text(),'Select Salary')]")WebElement salary;
	@FindBy(xpath = "//div[contains(@class,'customSelect_selectHeader')]") 
	WebElement ctcdropdown;

	@FindBy(xpath = "//a[contains(text(),'More')]") 
	WebElement morebtn;

	@FindBy(xpath = "//span[contains(text(),'Career Guidance')]") 
	WebElement careerguide;
	@FindBy(xpath = "(//*[@id=\"categories\"]/li/a)[7]")WebElement datascience;
	
	@FindBy(xpath = "//a[contains(@class,'headerWebNova_btn__knfkg')]") // Update if needed
	WebElement signoutBtn;
	
	@FindBy(xpath = "//button[contains(@class,'modalNova_modalClose__sxVHP')]") WebElement close;
	
	public Profile(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	

	public void profiledropdownhover() 
	{
		
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        
	        // 1. Wait for visibility to ensure it's not hidden by a popup
	        wait.until(ExpectedConditions.visibilityOf(profiledropdown));
	        
	        // 2. Scroll into view (helps if the header is sticky or partially off-screen)
	        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", profiledropdown);
	        
	        // 3. Perform the hover
	        Actions act = new Actions(driver);
	        act.moveToElement(profiledropdown).build().perform();
	        System.out.println("Successfully hovered over profile dropdown.");
	        
	    } 
		catch (Exception e) 
		{
	        System.out.println("Could not hover over profile: " + e.getMessage());
	    }
		
		myprofile.click();
//		closebtn.click();
	}
	
	
	
	public void resumeuploadd(String resumefile)
	{
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        // Explicitly wait for the upload button to be visible
	        wait.until(ExpectedConditions.visibilityOf(uploadbtn));
	        
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", uploadbtn);
	        
	        uploadbtn.click();
	        Thread.sleep(3000); 

	        StringSelection strSelection = new StringSelection(resumefile);
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSelection, null);

	        Robot robot = new Robot();
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        
	        System.out.println("File path pasted via Robot class.");
	    }
		catch (Exception e)
		{
	        System.out.println("Upload failed: " + e.getMessage());
	    }
	}
	
//	public void personaldetails() {
//	    try {
//	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	        
//	        // 1. Wait until the element is present in the DOM
//	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//button[contains(@class,'editIconButton_editBtn')])[4]")));
//	        
//	        // 2. Scroll the element into the middle of the screen to avoid header/footer overlaps
//	        JavascriptExecutor js = (JavascriptExecutor) driver;
//	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", personalediticon);
//	        
//	        // Short pause for scrolling to finish
//	        Thread.sleep(1000); 
//
//	        // 3. Perform the click using JavaScript
//	        js.executeScript("arguments[0].click();", personalediticon);
//	        
//	        System.out.println("Clicked on Personal Details Edit icon via JavaScript.");
//	    }
//	    catch (Exception e) 
//	    {
//	        System.out.println("Failed to click Personal Details: " + e.getMessage());
//	    }
//		 
//	}
//	
//	
//	public void selectDOB(String year, String month, String day) throws InterruptedException {
//	    dateofbirthtextarea.click();
//	}
//	
	
//	public void desiredjob() {
//	    try {
//	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//	        // Wait for the element to be present
//	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[contains(@class,'editIconButton_editBtn__OWFPw')])[3]")));
//	        
//	        JavascriptExecutor js = (JavascriptExecutor) driver;
//	        // Scroll it into the center of the view first
//	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", desiredjobedit);
//	        Thread.sleep(1000); // Small pause for stability
//	        
//	        // Use JS Click to avoid "ElementClickInterceptedException"
//	        js.executeScript("arguments[0].click();", desiredjobedit);
//	        System.out.println("Desired job edit icon clicked successfully via JS.");
//	    } catch (Exception e) {
//	        System.out.println("Failed to click desired job: " + e.getMessage());
//	    }
//	}
//	
//	public void selectctc(String salaryValue) {
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//	    // 1. Click the dropdown to open the list
//	    wait.until(ExpectedConditions.elementToBeClickable(salary)).click();
//
//	    // 2. Locate the specific salary option from the 72 available
//	    // We use a dynamic XPath to find the div containing your specific text
//	    String optionXpath = "//div[contains(@class,'customSelect_option__L7DDI') and text()='" + salaryValue + "']";
//	    
//	    try {
//	        WebElement targetOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(optionXpath)));
//
//	        // 3. Scroll to the option (necessary if the salary is far down the list of 72)
//	        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetOption);
//
//	        // 4. Click the option
//	        wait.until(ExpectedConditions.elementToBeClickable(targetOption)).click();
//	        System.out.println("Selected salary: " + salaryValue);
//
//	    } catch (Exception e) {
//	        Assert.fail("Could not find or click the salary option: " + salaryValue + ". Error: " + e.getMessage());
//	    }
//	}
//	
//	
//	
//    public void selectJobRole(String partialInput, String fullOptionText) 
//    {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        jobrole.clear();
//        jobrole.sendKeys(partialInput);
//        By listLocator = By.xpath("//ul[contains(@class,'desiredProfileModal_dropdown')]/li");
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(listLocator));
//            List<WebElement> options = driver.findElements(listLocator);
//            for (WebElement opt : options)
//            {
//                if (opt.getText().trim().equalsIgnoreCase(fullOptionText)) {
//                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt);
//                    break;
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            jobrole.sendKeys(Keys.ESCAPE);
//        }
//    }
//
//    
//    public void selectjoblocation(String partialInput, String locationToSelect) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        
//        // 1. Type input
//        joblocation.clear();
//        joblocation.sendKeys(partialInput);
//
//        // 2. Direct wait and click on the specific suggestion
//        try {
//            String xpath = "//li[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" 
//                           + locationToSelect.toLowerCase() + "')]";
//            
//            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
//            
//            // 3. Use JavaScript click to ensure it works even if partially hidden
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
//            
//        } catch (Exception e) {
//            System.out.println("Location not found: " + locationToSelect);
//            joblocation.sendKeys(Keys.ESCAPE);
//        }
//    }
//    public void savejobdetails() {
//        try {
//            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(savedesiredjob)).click();
//            System.out.println("Desired job details updated successfully");
//        } catch (Exception e) {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", savedesiredjob);
//        }
//    }
//        
//        
//   
        
    
    public void signout() throws Exception {
    	try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        
	        // 1. Wait for visibility to ensure it's not hidden by a popup
	        wait.until(ExpectedConditions.visibilityOf(profiledropdown));
	        
	        // 2. Scroll into view (helps if the header is sticky or partially off-screen)
	        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", profiledropdown);
	        
	        // 3. Perform the hover
	        Actions act = new Actions(driver);
	        act.moveToElement(profiledropdown).build().perform();
	        System.out.println("Successfully hovered over profile dropdown.");
	        
	    } 
		catch (Exception e) 
		{
	        System.out.println("Could not hover over profile: " + e.getMessage());
	    }
    	signoutBtn.click();
        
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File("C:\\Users\\Lenovo\\OneDrive\\Desktop\\Selenium works\\screenshot\\signout.png"));
    }
        
    }

	
		


	
	
	
	
	
	


