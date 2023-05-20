package NYKAA_TestNG;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import NEW_PROJECT_Nykaa.Base_Class;
import POM_Nykaa.Login_Page;
import POM_Nykaa.Logout_Page;
import POM_Nykaa.Makeup_Remover_Page;
import POM_Nykaa.Product_Page;
import POM_Nykaa.Shipping_Page;
import POM_Nykaa.User_Homepage;

public class TestNG_Parallel extends Base_Class {
	
	public static WebDriver driver = browser_Launch("chrome");

	static Login_Page login = new Login_Page(driver);
	static User_Homepage Uhp = new User_Homepage(driver);
	static Makeup_Remover_Page Mrp = new Makeup_Remover_Page(driver);
	static Product_Page page = new Product_Page(driver);
	static Shipping_Page Spage = new Shipping_Page(driver);
	static Logout_Page Lpage = new Logout_Page(driver);
	
	
	@BeforeClass( groups = "Team Red")
	public void a_Session_Initiated() {

		System.out.println("Compiling source code started");
	}
	

	@Test(priority = -11, groups = "Team Red")
	public void browser_Launch() {

		getUrl("https://www.nykaa.com/auth/verify?ptype=auth&redirect=%2F");
		System.out.println("Browser launched");
		window_Manage("maximize");
		System.out.println("Window Maximized");
	}
	
	@Test
	public void browser_Launch1() {

		getUrl("https://www.google.com/");
		window_Manage("maximize");
	}
	
	
	@AfterClass(groups = "Team Red")
	private void browser_quit() {

		browser_Close();
	}
	
	

}
