package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ERP_Functions {
	public static WebDriver driver;
	//launch browser and url
	public static String launchAPP(String url)
	{
		String res="";
		System.setProperty("webdriver.chrome.driver", "D:\\ERP_Stock\\ERP_Stock\\CommonDriver\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		if (driver.findElement(By.id("btnsubmit")).isDisplayed())
		{
			res="Application Launch Success";
		}else 
		{
			res="Application Launch Fail";
		}
		return res;
	}
//login Method
	public static String verifyLogin(String username,String password) throws Throwable
	{
		String res="";
	WebElement objuser=driver.findElement(By.name("username"));
	objuser.clear();
	Thread.sleep(3000);
	objuser.sendKeys(username);
	WebElement objpass=driver.findElement(By.name("password"));
	objpass.clear();
	Thread.sleep(3000);
	objpass.sendKeys(password);
	driver.findElement(By.id("btnsubmit")).click();
	Thread.sleep(5000);
	if (driver.findElement(By.id("logout")).isDisplayed()) 
	{
		res=" Login Success";
	}else 
	{
		res="Login Fail";
	}
return res;
		
	}
	public static void verifylogout()
	{
		driver.close();
	}
	//method for Supplier Creation
	public static String verifySupplier(String sname,String address, String city,String country, String cperson,String pnumber,String email,String mnumber, String notes) throws Throwable
	{
		String res="";
		driver.findElement(By.linkText("Suppliers")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='panel-heading ewGridUpperPanel']//span[@class='glyphicon glyphicon-plus ewIcon']")).click();
		Thread.sleep(3000);
		String exp_data=driver.findElement(By.name("x_Supplier_Number")).getAttribute("value");
		driver.findElement(By.name("x_Supplier_Name")).sendKeys(sname);
		driver.findElement(By.name("x_Address")).sendKeys(address);
		driver.findElement(By.name("x_City")).sendKeys(city);
		driver.findElement(By.name("x_Country")).sendKeys(country);
		driver.findElement(By.name("x_Contact_Person")).sendKeys(cperson);
		driver.findElement(By.name("x_Phone_Number")).sendKeys(pnumber);
		driver.findElement(By.name("x__Email")).sendKeys(email);
		driver.findElement(By.name("x_Mobile_Number")).sendKeys(mnumber);
		driver.findElement(By.name("x_Notes")).sendKeys(notes);
		driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='OK!']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
		Thread.sleep(3000);
		if (!driver.findElement(By.name("psearch")).isDisplayed())
			//click on Search pannel
			driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon']")).click();
			//driver.findElement(By.xpath("(//*[id='ewContentColumn']/div[2]/div[2]/div/button")).click();
			driver.findElement(By.name("psearch")).clear();
		driver.findElement(By.name("psearch")).sendKeys(exp_data);
		Thread.sleep(3000);
		driver.findElement(By.name("btnsubmit")).click();
		Thread.sleep(3000);
		//get Supplier Number From table
		String Act_data=driver.findElement(By.id("el1_a_suppliers_Supplier_Number")).getText();
		Thread.sleep(3000);
		System.out.println(exp_data+"   "+Act_data);
		
		if (Act_data.equals(exp_data)) 
		{
			res="pass";
		}
		else {
			res="fail";
		}
		return res;
	}
//	public static void main(String[] args) throws Throwable 
//	{		
//		launchAPP("http://webapp.qedge.com/");
//		verifyLogin("admin", "master");
//		verifySupplier("Naresh", "Qedge", "Ameerpet", "Hyderabad", "Rangareddy", "9533021144", "nari.nnar@gmail.com", "123455844", "bad Customer");
//		verifylogout();
//	}
}





