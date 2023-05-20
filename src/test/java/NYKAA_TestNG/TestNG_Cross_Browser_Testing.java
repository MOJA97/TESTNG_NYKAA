package NYKAA_TestNG;

import org.testng.annotations.Parameters;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v109.browser.Browser;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import NEW_PROJECT_Nykaa.Base_Class;
import POM_Nykaa.Login_Page;
import POM_Nykaa.Logout_Page;
import POM_Nykaa.Makeup_Remover_Page;
import POM_Nykaa.Product_Page;
import POM_Nykaa.Shipping_Page;
import POM_Nykaa.User_Homepage;

public class TestNG_Cross_Browser_Testing extends Base_Class {
	
	public static WebDriver driver;

	static Login_Page login = new Login_Page(driver);
	static User_Homepage Uhp = new User_Homepage(driver);
	static Makeup_Remover_Page Mrp = new Makeup_Remover_Page(driver);
	static Product_Page page = new Product_Page(driver);
	static Shipping_Page Spage = new Shipping_Page(driver);
	static Logout_Page Lpage = new Logout_Page(driver);
	
	
	@BeforeClass(groups = "Team Red")
	public void a_Session_Initiated() {

		System.out.println("Compiling source code started");
	}
	
	@BeforeTest
	@Parameters ({"browser"})
	public void setup(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Javed\\eclipse-workspace\\maven\\Chrome_Driver\\chromedriver.exe");
			driver = new ChromeDriver();
			
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Javed\\eclipse-workspace\\maven\\firefox_Driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
	}
	

	@Test(priority = -11, groups = "Team Red")
	public void browser_Launch() {

		getUrl("https://www.nykaa.com/auth/verify?ptype=auth&redirect=%2F");
		System.out.println("Browser launched");
		window_Manage("maximize");
		System.out.println("Window Maximized");
	}
	
	@Parameters({"a"})
	@Test(priority = -9, groups = "Team Red")
	private void log_In(String username) throws IOException {

		send_Values(login.getLogin(), username);
		System.out.println("email has been entered");
		implicitly_Wait(5);
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/loginpage.png");
		click(login.getProceedBTN());
		System.out.println("Proceed button clicked");

		click(login.getPass());
		System.out.println("OTP procedure started");

		// ENter your OTP
		Scanner s = new Scanner(System.in);
		System.out.println("Enter your OTP:");
		String otp = s.next();
		WebElement OTPNo = driver.findElement(By.xpath("//input[@name='otpValue']"));
		click(OTPNo);
		send_Values(OTPNo, otp);
		WebElement verifyBTN = driver.findElement(By.xpath("//button[@type='submit']"));
		verifyBTN.click();

		System.out.println("OTP has been entered");
	}
	
	@Test(priority = -3)
	private void Makeup_Product() throws IOException {

		moveTOelement(driver, Uhp.getMakeup());
		WebElement makeupremover = driver.findElement(By.linkText("Makeup Remover"));
		implicitly_Wait(4);
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/makeuppage.png");
		makeupremover.click();
		System.err.println("Browser open a makeup remover window tab");
	}
	
	@Test(priority = -2)
	
	private void Makeup_Remover_Product() throws IOException {

		String title = driver.getTitle();
		multiple_WindowHandling(title);
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/makeupremoverPAGE.png");
		System.out.println("Driver moved to newly opened window");

		
	}
	
	@Test(priority = -1, description = "Product is clicked")
	
	private void product_Click() {
		
		scrollIntoView(Mrp.getProductclick());
		implicitly_Wait(5);
		moveToElement(Mrp.getProductclick());
		List<WebElement> product = driver.findElements(By.xpath("//div[@class='css-d5z3ro']"));
		for (WebElement name : product) {
			
			if (name.getText().contains("Clinique Take The Day Off Cleansing Balm (Makeup Remover)")) {
				  
				WebElement prd = driver.findElement(By.xpath("//div[text()='Clinique Take The Day Off Cleansing Balm (Makeup Remover)']"));
				prd.click();
				
			}
			
			
		}
		System.out.println("Product has been found and clicked");
	}
	
	@Test(priority = 0, alwaysRun = true)
	
	private void Product_Page_TO_Cart() throws IOException {
		
		String title2 = driver.getTitle();
		multiple_WindowHandling(title2);
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/productpage.png");
		System.out.println("moved to newly opened window");
		
		//add product into cart
		click(page.getAddtobag());
		implicitly_Wait(2);
		click(page.getCart());
		
		implicitly_Wait(3);
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/cartFrame.png");
		

		iframes(page.getFrameSwitch());
		click(page.getProceedbtn());
		
	}
	
	@Test(priority = 1)
	
	private void Payment_Method() throws IOException {

		click(Spage.getAddress1());
		clickElement(Spage.getPincode());
		send_Values(Spage.getPincode(), "613009");
		
		click(Spage.getCheckbox());
		
		click(Spage.getName());
		send_Values(Spage.getName(), "Mohammed Javed");
		click(Spage.getPhone());
		send_Values(Spage.getPhone(), "9342375858");
		click(Spage.getEmailID());
		send_Values(Spage.getEmailID(), "itsmemojaved@gmail.com");
		implicitly_Wait(5);
		send_Values(Spage.getHouseno(), "98");
		send_Values(Spage.getAddress(), "RajaRajan cholan nagar, near Bigtemple,");

		implicitly_Wait(4);
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/shippingpage.png");
		
		click(Spage.getShippingBTN());
		implicitly_Wait(8);
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/payment.png"); 
		
	}
	
	@Test(priority = 8, groups = "Team Red")
	private void log_Out() throws Exception{
		
		Thread.sleep(3000);
		multiple_WindowhandlingURL("https://www.nykaa.com/");
		click(Lpage.getProfileBTN());
		click(Lpage.getLogoutBTN());
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/logoutPage.png");
		click(Lpage.getLogoutPopup());
		System.out.println("Profile has been logged out");
		
		window_Manage("minimize");
		System.out.println("Browser maximize");
	}
	
	
	@AfterClass(groups = "Team Red")
	private void browser_quit() {

		browser_Close();
	}
	
	@AfterMethod(description = "Browser has been choosen succesfully")
	private void Browser() {
		// TODO Auto-generated method stub

	}
	
	
	
	

}
