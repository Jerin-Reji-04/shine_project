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

public class Home_before_login 
{
	WebDriver driver;
	
	@FindBy(xpath ="//*[@id=\"desktop_navbar\"]/ul/li/a")
	List<WebElement> navBarItems;
	@FindBy(xpath = "//*[@id=\"desktop_navbar\"]/ul/li[2]/a") WebElement jobs;
	@FindBy(xpath = "//*[@id=\"modalID\"]/div/button")WebElement popupclose;
	@FindBy(xpath = "//*[@id=\"webSearchBar\"]/input")WebElement Searchbar;
	@FindBy(xpath = "//*[@id=\"webSearchBar\"]/span") WebElement searchbtn;
	@FindBy(xpath = "//button[contains(text(),'Apply')]")
	List<WebElement> applyButtons;
	@FindBy(xpath="//div[contains(@class,'logo')]")
	WebElement logo;
	@FindBy(tagName = "a")
	List<WebElement>li;
	
	

	
	public Home_before_login(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	
	public void display()
	{
		boolean a=logo.isDisplayed();
		System.out.println(a);
		
			if(logo.isDisplayed())
			{
				System.out.println("--------Logo is present--------");
			}
			else
			{
				System.out.println("--------Logo is not present-------");
			}
			
	}
	
	
	
	public void home_title()
	{
		String exptitle="Shine.com";
		String acttitle=driver.getTitle();
		if(exptitle.equals(acttitle))
		{
			System.out.println("Title Matched");
		}
		else
		{
			System.out.println("Title not Matched");
		}
	}
	
	
	
	
	public void scrolldown()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    
	    // Get the current height of the page
	    long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

	    while (true) {
	        // Scroll down by 400 pixels
	        js.executeScript("window.scrollBy(0, 5000);");
	        
	        // Wait a fraction of a second to make it look "slow" and allow images to load
	        try {
	            Thread.sleep(300); 
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // Calculate new scroll height and compare with last scroll height
	        long newHeight = (long) js.executeScript("return document.body.scrollHeight");
	        
	        // If heights are the same, it means we have reached the absolute bottom
	        if (newHeight == lastHeight) {
	            System.out.println("Reached the end of the page.");
	            break;
	        }
	        lastHeight = newHeight;
	    }
	}
	
	
	
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
	
	
	
	public void closepopup() 
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("modalID")));
			
			  System.out.println("Popup detected.");
		
			 try {
				 popupclose.click();
				 System.out.println("Clicked popup close button");
				 
				
			} catch (Exception e) {
				driver.findElement(By.tagName("body")).click();
	            System.out.println("Clicked body to dismiss popup");
			}
		}
		catch (Exception e) {
			System.out.println("No popup found or already gone");
		}
		}

	
	
	public void search()
	{
		Searchbar.sendKeys("Software Testing");
		searchbtn.click();
		
	}
	
	public void searchclose()
	{
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        WebElement closeBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class, 'searchForm_btnClose')]")));
	        
	        // Use Javascript to click in case it's obscured by an overlay
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", closeBtn);
	        System.out.println("Search closed successfully.");
	        
	    } catch (Exception e)
	    {
	        System.out.println("Search close button not interactable: " + e.getMessage());
	    }
	
	}
	
	
	public void applyToRemoteJob() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    try {
	        // 1. Find the 'Remote' label specifically
	        // Using normalize-space() to handle extra spaces in the HTML
	        By remoteLocator = By.xpath("//*[contains(normalize-space(), 'Remote')]");
	        WebElement remoteLabel = wait.until(ExpectedConditions.presenceOfElementLocated(remoteLocator));

	        // 2. Scroll to the position (Centered so headers don't block it)
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", remoteLabel);
	        System.out.println("Scrolling back to Remote position...");

	        // 3. Wait for a second as requested
	        Thread.sleep(1000);

	        // 4. Find the Apply button that follows the Remote label
	        // This is much safer than absolute indexing
	        By applyBtnLocator = By.xpath("//*[contains(text(),'Remote')]/following::button[contains(.,'Apply')][2]");
	        WebElement applyBtn = wait.until(ExpectedConditions.elementToBeClickable(applyBtnLocator));

	        // 5. Click it (using JS click to bypass potential overlays)
	        js.executeScript("arguments[0].click();", applyBtn);
	        System.out.println("Successfully clicked the Apply button for the Remote position.");

	    } catch (Exception e) {
	        System.out.println("Failed to click Remote Apply button: " + e.getMessage());
	        
	        // Final fallback if the above fails: find by index from your @FindBy list
	        if (!applyButtons.isEmpty()) {
	            System.out.println("Attempting fallback to first available Apply button...");
	            js.executeScript("arguments[0].click();", applyButtons.get(0));
	        }
	    }
	}
	public void loginvalidation() 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("/myshine/mydashboard"));

		
	    String actualurl = driver.getCurrentUrl();
	    Assert.assertEquals(actualurl, "https://www.shine.com/myshine/mydashboard/");
	    System.out.println("-----------Login successful--------------");
	}
	public void titleverification()
	{	String actualtitle=driver.getTitle();
		String expectedtitle="Shine.com";
		System.out.println("Title: "+actualtitle);
		if (actualtitle.equals(expectedtitle))
		{
			System.out.println("--------------Title is as expected------------");
		}
		else
		{
			System.out.println("-------------Title is not as expected------------");
		}
	}
	public void logoverification() 
	{
		if(logo.isDisplayed())
		{
			System.out.println("--------Logo is present--------");
		}
		else
		{
			System.out.println("--------Logo is not present-------");
		}
		
	}
	public void linkscount() 
	{
	int linkcount=li.size();
	System.out.println("linkcount= "+linkcount);
	}

	public void linkverification() throws MalformedURLException, URISyntaxException, IOException 
	{
		for(WebElement e:li)
		{
			String links=e.getAttribute("href");
			String linktext=e.getText();
			Verify(links,linktext);
		}
	}
	public void Verify(String link,String linktext) throws URISyntaxException, MalformedURLException, IOException 
	{
	try {
			
		URI ob=new URI(link);
		HttpURLConnection con=(HttpURLConnection)ob.toURL().openConnection();
		
		if(con.getResponseCode()>=400)
		{
			System.out.println(linktext+"---------"+link+"---------Broken link, response code is 400");
		}
		else if(con.getResponseCode()==200)
		{
			System.out.println(linktext+"----------"+link+"-----------Successful link, response code is 200");
		}
		else
		{
			System.out.println(linktext+"---------"+link+"------------Other links");
		}
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
			}
	}
	
//	String originalTab;
//
//	public void switchToNewTab() {
//	    // 1. Save the current window handle
//	    originalTab = driver.getWindowHandle();
//	    
//	    // 2. Get all open window handles
//	    for (String handle : driver.getWindowHandles()) {
//	        if (!handle.equals(originalTab)) {
//	            // 3. Switch to the new tab
//	            driver.switchTo().window(handle);
//	            System.out.println("Switched to new tab: " + driver.getTitle());
//	        }
//	    }
//	}
//
//	public void switchBackToOriginalTab() {
//	    // Switch back using the stored ID
//	    if (originalTab != null) {
//	        driver.switchTo().window(originalTab);
//	        System.out.println("Switched back to: " + driver.getTitle());
//	    }
//	}
}