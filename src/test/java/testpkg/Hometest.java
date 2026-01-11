package testpkg;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import basepkg.Basepgm;

public class Hometest extends Basepgm
{

	
	@Test(priority=1)
	public void navigate()
	{
		hbl.closepopup();
		hbl.logoverification();
		hbl.titleverification();
		hbl.NavigationHover(2);
		hbl.closepopup();
		
	}
	@Test(priority=2)
	public void Search() throws Exception
	{
		hbl.closepopup();
		hbl.search("software Testing", "kochi","2 yrs");
//		hbl.searchclose();
	}
	
	
	@Test(priority=3)
	public void validation() throws MalformedURLException, URISyntaxException, IOException
	{
		hbl.linkvalidation();
		hbl.linkscount();
		hbl.linkverification();
		
	}
	
	
	@Test(priority=4)
	public void applyjob()
	{
		hbl.applyToRemoteJob();
	}
	
	
	
	
	
	

}


