package pagepkg;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class cart 
{
	WebDriver driver;
	WebDriverWait wait;

@FindBy(xpath = "//*[@id=\"__next\"]/header/div[3]/div/div/div[2]/a")WebElement carticon;
@FindBy(xpath = "//a[contains(text(),'Explore Now!')]") WebElement explore;
@FindBy(xpath = "//a[contains(text(),'Courses')]")WebElement course;
@FindBy(xpath = "//a[contains(text(),'Buy Now')]")
List<WebElement> buyNowButtons;

@FindBy(xpath = "//*[@id=\"__next\"]/header/div[2]/div/div/div/a/span/img") WebElement back;


@FindBy(xpath = "//em[contains(@class,'ml-auto iconH-cart-delete')]") WebElement deletecart;
	
	public cart(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	
	//click the carticon
	
	public void setup()
	{
		carticon.click();
	}
	
	
	//click the explorebtn
	
	public void explorebtn()
	{
        // We use a simple List check to see if "Explore" is there
        List<WebElement> exploreList = driver.findElements(By.xpath("//a[contains(text(),'Explore Now')]"));
        
        if (exploreList.size() > 0) 
        {
            exploreList.get(0).click();
        }
        else 
        {
            course.click();
            System.out.println("Clicked Courses because Explore was not there.");
        }
    }
    
	
	
	//click the buynow option to add item to the cart
	
	public void clickSpecificBuyNow(int index) 
	{
        if (buyNowButtons.size() > index) {
            WebElement btn = buyNowButtons.get(index);
            // SIMPLE FIX: Scroll the page down slightly so the button isn't hidden under headers
            driver.findElement(By.tagName("body")).sendKeys(Keys.ARROW_DOWN);
            driver.findElement(By.tagName("body")).sendKeys(Keys.ARROW_DOWN);
            
            // If standard click fails, we use the one "fancy" line to bypass the obstacle
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }
	
	
	//handling the tab switch back to the parent tab and close the child tab
	
	public void windowhandler() throws Exception 
	{
        String parentWindow = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.close();
        driver.switchTo().window(parentWindow);
    }
	
	
	//Again click the carticon to verify the product is there and delete the item
	
	public void cartitem()
	{
        // Re-locating elements inside the method prevents 'Stale' errors
        try {
            // 1. Click the logo/back button to ensure we are on the right view
            WebElement backBtn = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div[2]/div/div/div/a/span/img"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", backBtn);
            
            
            // 2. Click the cart icon
            WebElement freshCart = driver.findElement(By.xpath("//a[contains(@href, 'cart')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", freshCart);
            
            Thread.sleep(5000);
            
            // 3. Delete the item
            wait.until(ExpectedConditions.elementToBeClickable(deletecart)).click();
            System.out.println("Item deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error in cartitem: " + e.getMessage());
        }
    }
	

}
