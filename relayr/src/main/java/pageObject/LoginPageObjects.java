package pageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {
	
	WebDriver driver;
	Logger logger;
	
	public LoginPageObjects(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		logger=Logger.getLogger(this.getClass());
	}
	
	@FindBy(id="nblogin")
	WebElement signInOption;
	
	@FindBy(xpath="//span[contains(@class,'auth-sprite imdb-logo retina')]/ancestor::a")
	WebElement signInWithIMDB;
	
	@FindBy(id="ap_email")
	WebElement email;
	
	@FindBy(id="ap_password")
	WebElement password;
	

	@FindBy(id="signInSubmit")
	WebElement login;
		
	public void clickOnsignInOption(WebDriver driver) 
	{
		logger.info("clicked on signIn option");
		signInOption.click();
	}
	
	public void clickOnsignInImdb(WebDriver driver) 
	{
		logger.info("clicked on signIn with IMDB");
		signInWithIMDB.click();
	}	

	public void enterEmail(WebDriver driver,String str) 
	{
		logger.info("Email is entered: "+str);
		email.sendKeys(str);
	}
	
	public void enterPassword(WebDriver driver,String str) 
	{
		logger.info("Password is entered: "+str);
		password.sendKeys(str);
	}
	
	public void clickonLogin(WebDriver driver) 
	{
		logger.info("Clicked on Login");
		login.click();
	}
}
