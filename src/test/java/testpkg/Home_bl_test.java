package testpkg;

import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.annotations.Test;

import basepkg.Basepgm;
import pagepkg.Home_before_login;

public class Home_bl_test extends Basepgm
{
	Home_before_login hbl;
	
	
	@Test(priority=1)
	public void logo() throws IOException, Exception, Exception
	{
		hbl=new Home_before_login(driver);
		hbl.display();
		hbl.linkscount();
		hbl.linkverification();
		hbl.logoverification();
		
	}
	
	@Test(priority=1)
	public void home_titleverification()
	{
		hbl=new Home_before_login(driver);
		hbl.home_title();
		
	}
	
	
	
	@Test(priority=2)
	public void home_navigation()
	{
		int pauseTime = 2;
		hbl.NavigationHover(pauseTime);
	}
	
	@Test(priority=3)
	public void home_scroll()
	{
		hbl.scrolldown();
		hbl.closepopup();
	}
	
	@Test(priority=4)
	public void Search()
	{
		hbl.search();
		hbl.searchclose();
	}
	
	@Test(priority=5)
	public void apply()
	{
		hbl.applyToRemoteJob();
		
	}
	
	
	
	
	
	

}
