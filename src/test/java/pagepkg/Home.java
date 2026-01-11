package pagepkg;



import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Home 
{
	WebDriver driver;
	
	@FindBy(xpath ="//*[@id=\"desktop_navbar\"]/ul/li/a")
	List<WebElement> navBarItems;
	
	@FindBy(xpath = "//*[@id=\"desktop_navbar\"]/ul/li[2]/a") WebElement jobs;
	
	@FindBy(xpath = "//*[@id=\"modalID\"]/div/button")WebElement popupclose;
		
	@FindBy(xpath = "//*[@id=\"webSearchBar\"]/input")WebElement Searchbar;
	
	@FindBy(xpath = "//*[@id=\"id_q\"]")WebElement Searchplace;
	
	@FindBy(xpath = "//*[@id=\"id_loc\"]")WebElement Searchloc;
	
	@FindBy(xpath = "//*[@id=\"id_exp\"]")WebElement Searchexp;
	
	@FindBy(xpath = "//button[contains(text(),'Search jobs')]")WebElement searchicon;
	
	@FindBy(xpath = "//*[@id=\"webHeaderNova\"]/div/div/div[1]/ul/li[1]/a") WebElement backhome;
	
	@FindBy(xpath = "//*[@id=\"webSearchBar\"]/span") WebElement searchbtn;
	
	@FindBy(xpath = "//button[contains(text(),'Apply')]")
	List<WebElement> applyButtons;
	
	@FindBy(xpath="//div[contains(@class,'logo')]")
	WebElement logo;
	
	@FindBy(tagName = "a")
	List<WebElement>li;

	

	
	public Home(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	
	
	//----checking the logo is present or not-----
	
	
	
	public void display() 
	{
	    // Check if the logo is displayed
	    boolean isLogoPresent = logo.isDisplayed();
	    Assert.assertTrue(isLogoPresent, "Validation Failed: Logo is not displayed on the page.");
	    System.out.println("Validation Passed: Logo is present.");
	}
	
	
	
	
	//---checking the home title is matches----
	
	
	
	public void home_title() 
	{
	    String expectedTitle = "Shine.com";
	    String actualTitle = driver.getTitle();
	    Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch found on Home Page!");	    
	    System.out.println("Title Matched successfully.");
	}
	
		
	//-------navigate through all the item in the menu bar-----
	
	
	
	public void NavigationHover(int waitTimeInSeconds)
	 {
	        Actions actions = new Actions(driver);
	        
	        System.out.println("Starting sequential hover through " + navBarItems.size() + " navigation items.");
	        
	        for (WebElement item : navBarItems) {
	            try 
	            {
	                actions.moveToElement(item).build().perform();
	                String linkText = item.getText().isEmpty() ? item.getAttribute("title") : item.getText();
	                System.out.println("Hovered over: " + linkText);
	             	Thread.sleep(waitTimeInSeconds * 1000); 
	            }
	            catch (InterruptedException e) 
	            {
	                Thread.currentThread().interrupt();
	                System.out.println("Hovering sequence interrupted.");
	            } 
	            catch (Exception e) 
	            {
	                 System.out.println("Could not interact with item: " + item.getText() + ". Error: " + e.getMessage());
	            }
	        }
	        	        actions.moveByOffset(0, 0).build().perform();
	    }
	
	
	
	
	//check for popup and close the popup
	
	
	public void closepopup() 
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("modalID")));
			
			  System.out.println("Popup detected.");
		
			 try 
			 {
				 popupclose.click();
				 System.out.println("Clicked popup close button");
				 
			 }
			 catch (Exception e) 
			 {
				driver.findElement(By.tagName("body")).click();
	            System.out.println("Clicked body to dismiss popup");
	         }
		    }
		catch (Exception e) 
		{
			System.out.println("No popup found or already gone");
		}
	}
	

	//---check whether the search is fuctioning
	
	
	
	
	public void search(String jobRole, String location, String exp) throws Exception 
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(Searchbar)).click();
	    
	    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait1.until(ExpectedConditions.elementToBeClickable(Searchplace)).sendKeys(jobRole);
	    Searchloc.sendKeys(location);
	    
	    Searchexp.click(); 
	    driver.findElement(By.xpath("//li[text()='" + exp + "']")).click();
	    searchicon.click();
	    
	    Thread.sleep(5000);
	    
	
	    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait2.until(ExpectedConditions.elementToBeClickable(backhome)).click();
	    
	    System.out.println("Search item finded");
    }
	
	
	//check if click on apply button it navigate to login page
	
	

	public void applyToRemoteJob() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    try {
	        // 1. Identify the 'Remote Jobs' header anchor (seen in your screenshot)
	    	
	        By remoteHeaderLocator = By.xpath("//h2[contains(text(),'Remote Jobs')] | //h1[contains(text(),'Remote Jobs')]");
	        WebElement remoteHeader = wait.until(ExpectedConditions.presenceOfElementLocated(remoteHeaderLocator));

	        // 2. Scroll UP to this specific section
	        
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", remoteHeader);
	        System.out.println("Scrolling back up to Remote Jobs section...");
	        Thread.sleep(2000); // Allow time for cards to settle

	        // 3. RELATIVE XPATH: Find the first 'Apply' button that follows the Remote Jobs header
	        // This ignores the other 53 buttons and picks the one inside the Remote section
	        
	        By relativeApply = By.xpath("//h2[contains(text(),'Remote Jobs')]/following::button[contains(text(),'Apply')][2]");
	        
	        WebElement targetApplyBtn = wait.until(ExpectedConditions.elementToBeClickable(relativeApply));

	        // 4. Click using JavaScript (Safest for dynamic job boards)
	        
	        js.executeScript("arguments[0].click();", targetApplyBtn);
	        System.out.println("Successfully clicked the correct Remote Apply button.");

	    } catch (Exception e)
	    {
	        System.out.println("Relative XPath failed. Trying exact text match fallback...");
	        try 
	        {
	            // Fallback: Click the button by its specific text if the header anchor fails
	        	
	            WebElement fallback = driver.findElement(By.xpath("(//button[contains(text(),'Apply')])[2]"));
	            js.executeScript("arguments[0].click();", fallback);
	        } 
	        catch (Exception ex)
	        {
	            Assert.fail("Could not locate the Apply button for Remote Jobs: " + e.getMessage());
	        }
	    }
	    
	    System.out.println("Scrollup to Remote job and link");
	}
	
	
	//check the link is work properly
	
	public void linkvalidation() {
	    String actualurl = driver.getCurrentUrl();
	    System.out.println("Current URL is: " + actualurl);
	    
	    Assert.assertTrue(actualurl.contains("shine.com"), "Not on a Shine.com domain!");
	}
	
	
	
	//---check for the title of the page is correct
	
	
	public void titleverification() {
	    String actualTitle = driver.getTitle();
	    Assert.assertTrue(actualTitle.contains("Shine.com"), 
	        "Title Verification Failed! Actual title was: " + actualTitle);
	    
	    System.out.println("--------------Title contains Shine.com as expected------------");
	}
	
	
	//check for logo is present or not
	
	
	public void logoverification()
	{
	    Assert.assertTrue(logo.isDisplayed(), "Verification Failed: Shine Logo is not displayed on the page!");
	    
	    System.out.println("--------Logo is present and verified--------");
	}
	
	
	//---to get the link count
	
	
	public void linkscount() 
	{
	int linkcount=li.size();
	System.out.println("linkcount= "+linkcount);
	}
	
	//----to check the link reponse

	public void linkverification() throws MalformedURLException, URISyntaxException, IOException {
	    for (WebElement e : li) {
	        String links = e.getAttribute("href");
	        String linktext = e.getText();

	        // ONLY verify if the link is not null and starts with http
	        if (links != null && (links.startsWith("http") || links.startsWith("https"))) {
	            verifyLink(links, linktext);
	        } else {
	            System.out.println("Skipping non-HTTP link: " + links);
	        }
	    }
	}
	
	//----to get all the link present in the page
	
	
	public void verifyLink(String link, String linktext) {
	    try {
	        URI ob = new URI(link);
	        HttpURLConnection con = (HttpURLConnection) ob.toURL().openConnection();
	        con.setRequestMethod("HEAD");
	        con.setConnectTimeout(3000); // Don't wait forever for slow links
	        con.connect();

	        int responseCode = con.getResponseCode();

	        if (responseCode >= 400)
	        {
	            System.err.println("BROKEN: " + linktext + " [" + link + "] - Status: " + responseCode);
	        } else {
	            System.out.println("VALID: " + linktext + " - Status: " + responseCode);
	        }
	        
	    } 
	    catch (Exception e) 
	    {
	        System.out.println("Could not verify link: " + link + ". Reason: " + e.getMessage());
	    }
	}


}
