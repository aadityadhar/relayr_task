package pageObject;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchObjects {
	
WebDriver driver;	
Logger logger;
	
	public SearchObjects(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		logger=Logger.getLogger(this.getClass());
		}

	@FindBy(id="navbar-query")
	WebElement searchBar;
	
	@FindBy(xpath="//*[@class='findHeader']")
	WebElement noSearchResults;
	
	By suggestions=By.xpath("//div[@class='navbar-suggestionsearch__search-result']");
	
	List<WebElement> searchSuggestions=new ArrayList<WebElement>();
	
	public void writeOnIMDBSearch(WebDriver driver, String str) throws InterruptedException
	{
		searchBar.clear();
		logger.info("Searching "+str+" on IMDB");
		searchBar.sendKeys(str);
		Thread.sleep(3000); //Using thread.sleep because suggestions of IMDB reloads in between while capturing them, so after 2/3 seconds 
		                    //E.g.- on writing harry potter, sometime search starts giving result of harry then reloads to harry potter
		searchSuggestions=driver.findElements(suggestions);
	}
	
	public void enterOnIMDBSearch(WebDriver driver)
	{
		logger.info("Search is entered");
		searchBar.sendKeys(Keys.ENTER);
		
	}
	
	public String clickOnFirstElement(WebDriver driver)
	{
		logger.info("Clicking on first element");
		searchSuggestions.get(0).click();
		return driver.getTitle();	
	}
	
	public boolean isEmptySuggestion(WebDriver driver)
	{
		return searchSuggestions.isEmpty();
	}
	
	public String getNoSearchResults(WebDriver driver)
	{
		logger.info(noSearchResults.getText().toLowerCase());
		return noSearchResults.getText().toLowerCase();	
	}
	
	
	//Accuracy: Number of results which contains movie(string) which is written in search bar of IMDB
	public int checkSearchAccuracy(WebDriver driver,String str)
	{
		logger.info("Checking Accuracy of Search w.r.t. movie: "+str+" in suggestions");
		int total=searchSuggestions.size();
		int count=0;
		for (WebElement  element:searchSuggestions)
		{
			if(element.getText().toLowerCase().contains(str.toLowerCase()))
			{
				logger.info("Matching suggestions: "+element.getText());
				count++;
			}
			else
			{
				logger.error("Non-Matching suggestions: "+element.getText());
			}
		}
		int accuracy=(int)(count*100/total);
		logger.info("Search accuracy of Search w.r.t. movie "+str+" is "+accuracy+"%");
		return accuracy;
	}
	
}
