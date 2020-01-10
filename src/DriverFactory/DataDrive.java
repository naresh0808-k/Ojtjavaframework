package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DataDrive 
{
WebDriver driver;
@BeforeTest
public void setUp()throws Throwable
{
	String launch=ERP_Functions.launchAPP("http://webapp.qedge.com");
	Reporter.log(launch,true);
	String login=ERP_Functions.verifyLogin("admin", "master");
}

@Test
public void supplierCreation() throws Throwable
{
		ExcelFileUtil xl=new ExcelFileUtil();
		int rc=xl.rowCount("Supplier");
		int cc=xl.colCount("Supplier");
		Reporter.log("no of rows:: "+rc+"  "+"no of colums are::"+cc,true);
		for (int i = 1; i <= rc; i++) 
		{
			String sname=xl.getCellData("Supplier", i, 0);
			String address=xl.getCellData("Supplier", i, 1);
			String city=xl.getCellData("Supplier", i, 2);
			String country=xl.getCellData("Supplier", i, 3);
			String cperson=xl.getCellData("Supplier", i, 4);
			String pnumber=xl.getCellData("Supplier", i, 5);
			String mail=xl.getCellData("Supplier", i, 6);
			String mnumber=xl.getCellData("Supplier", i, 7);
			String notes=xl.getCellData("Supplier", i, 8);
			
			String results=ERP_Functions.verifySupplier(sname, address, city, country, cperson, pnumber, mail, mnumber, notes);
			Reporter.log(results,true);
			xl.setCellData("Supplier", i, 9,results);
		}
}
	@AfterTest
	public void tearDown()
	{
		ERP_Functions.verifylogout();
	}
}
