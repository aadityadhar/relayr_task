package tests;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import baseTests.BaseClass;
import utilities.IMDBUtilities;

public class IMDBTests extends BaseClass
{
	IMDBUtilities util=new IMDBUtilities();
	@DataProvider(name="movies")
    public String[][] getMovies() throws InvalidFormatException, IOException
	{
		return util.readExcelData("Movies");
    }
	
	@Test(priority=1,dataProvider="movies")
	public void verifySearch(String movieName) throws Exception //verify valid and invalid search
	{
		searchObject.writeOnIMDBSearch(driver, movieName.toLowerCase());
		testInfo.log(Status.INFO,"Searching "+movieName+" on IMDB search");
		
		if(!searchObject.isEmptySuggestion(driver)) //if movie name fetch results in suggestion box
		{
			int result=searchObject.checkSearchAccuracy(driver,movieName.toLowerCase()); //return search accuracy
			
			testInfo.log(Status.INFO,"Search accuracy of Search w.r.t. movie "+movieName.toLowerCase()+" is "+result+"%. Check Logs for more details");
			Assert.assertTrue(result >= 50); //Fail Test case if search accuracy is less than 50%
			
			searchObject.clickOnFirstElement(driver);
			testInfo.log(Status.INFO,"Clicking on First result of search suggestions");
			
			Assert.assertTrue(driver.getTitle().toLowerCase().contains(movieName.toLowerCase()));
			testInfo.log(Status.INFO,"Webpage of first result is loading properly");
			
		}
		else // if no search result found for given movie name
		{
			searchObject.enterOnIMDBSearch(driver);
			Assert.assertTrue(searchObject.getNoSearchResults(driver).contains("no results found"));
			testInfo.log(Status.INFO,"No result found for movie "+movieName);
			
		}
	}
	
	@Test(priority=2)
	public void verifyInvalidLogin() throws Exception //verify invalid login
	{
		loginPageObject.clickOnsignInOption(driver);
		testInfo.log(Status.INFO,"Clicking on Sign in Options");
		
		loginPageObject.clickOnsignInImdb(driver); 
		testInfo.log(Status.INFO,"Clicking on IMDB SignIn");
		
		loginPageObject.enterEmail(driver,read.getEmail());
		testInfo.log(Status.INFO,"Entering email: "+read.getEmail());
		
		loginPageObject.enterPassword(driver, read.getPassword());
		testInfo.log(Status.INFO,"Entering email: "+read.getPassword());
		
		loginPageObject.clickonLogin(driver);	
		testInfo.log(Status.INFO,"Clicking on login button");
		
		Assert.assertTrue(driver.getTitle().toLowerCase().contains("imdb sign in"));
		testInfo.log(Status.INFO,"User is not able to login into IMDB");
		
	}
}
