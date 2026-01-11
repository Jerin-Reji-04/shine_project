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
		car.clickFirstBuyNow();
	}
	
	@Test(priority=2)
	public void window() throws Exception
	{
		car.windowhandler();
		hbl.closepopup();
	}
	
	@Test(priority=3)
	public void delete()
	{
		car.cartitem();
	}
	
}
