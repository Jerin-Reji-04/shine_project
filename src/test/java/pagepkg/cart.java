package pagepkg;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class cart 
{
	WebDriver driver;

@FindBy(xpath = "//*[@id=\"__next\"]/header/div[3]/div/div/div[2]/a")WebElement carticon;
@FindBy(xpath = "//a[contains(text(),'Explore Now!')]") WebElement explore;
@FindBy(xpath = "//a[contains(text(),'Buy Now')]")
List<WebElement> buyNowButtons;

@FindBy(xpath = "//em[contains(@class,'ml-auto iconH-cart-delete')]") WebElement deletecart;
	
	public cart(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//click the carticon
	
	public void setup()
	{
		carticon.click();
	}
	
	
	//click the explorebtn
	
	public void explorebtn()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.visibilityOf(explore));
	    wait.until(ExpectedConditions.elementToBeClickable(explore)).click();
	    
	    System.out.println("Explore Now button clicked successfully.");
	}
	
	
	//click the buynow option to add item to the cart
	
	public void clickFirstBuyNow() 
	{
	    if (!buyNowButtons.isEmpty())
	    {
	        buyNowButtons.get(0).click();
	    } else 
	    {
	        System.out.println("No 'Buy Now' buttons found.");
	    }
	}
	
	
	//handling the tab switch back to the parent tab and close the child tab
	
	public void windowhandler() throws Exception 
	{
	    // 1. Save the Parent ID
	    String parentWindow = driver.getWindowHandle();
	    
	    // 2. Wait for the new tab to actually open
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.numberOfWindowsToBe(2));

	    // 3. Get all window handles and switch to the child (index 1)
	    List<String> windowDetails = new ArrayList<>(driver.getWindowHandles());
	    driver.switchTo().window(windowDetails.get(1));
	    
	    // --- CHILD TAB OPERATIONS ---
	    System.out.println("Child Tab Title: " + driver.getTitle());
	    Thread.sleep(3000); 
	    
	    // 4. Close the child tab (driver is currently focused here)
	    driver.close(); 
	    System.out.println("Child tab closed.");

	    // 5. Switch back to the parent window
	    driver.switchTo().window(parentWindow);
	    System.out.println("Returned to Parent Window: " + driver.getTitle());
	}
	
	
	//Again click the carticon to verify the product is there and delete the item
	
	public void cartitem()
	{
		carticon.click();
		System.out.println("item is persented");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(deletecart)).click();
	    System.out.println("Item is deleted from the cart");
		
		
	}
	

}
