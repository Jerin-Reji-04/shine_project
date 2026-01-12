package testpkg;

import org.testng.annotations.Test;

import basepkg.Basepgm;

public class carttest extends Basepgm
{

	@Test(priority=1)
	public void clickcart() 
	{
		car.setup();
		car.explorebtn();
	}
	
	@Test(priority=2)
	public void window() throws Exception
	{
		car.windowhandler();
		hbl.closepopup();
		car.clickSpecificBuyNow(1);
	}
	
	@Test(priority=3)
	public void delete()
	{
		car.cartitem();
			
	}
	
}
