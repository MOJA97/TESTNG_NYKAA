package Nykaa_Runner_Class;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import NEW_PROJECT_Nykaa.Base_Class;
import POM_Nykaa.Login_Page;
import POM_Nykaa.Logout_Page;
import POM_Nykaa.Makeup_Remover_Page;
import POM_Nykaa.Product_Page;
import POM_Nykaa.Shipping_Page;
import POM_Nykaa.User_Homepage;

public class Runner_Class extends Base_Class {
	public static WebDriver driver = browser_Launch("chrome");
	
	static Login_Page login = new Login_Page(driver);
	static User_Homepage Uhp = new User_Homepage(driver);
	static Makeup_Remover_Page Mrp = new Makeup_Remover_Page(driver);
	static Product_Page page = new Product_Page(driver);
	static Shipping_Page Spage = new Shipping_Page(driver);
	static Logout_Page Lpage = new Logout_Page(driver);
	
	public static void main(String[] args) throws IOException, AWTException {
		
		
		getUrl("https://www.nykaa.com/auth/verify?ptype=auth&redirect=%2F");
		window_Manage("maximize");
		System.out.println("Nykaa browser launched and maximized");
		
		implicitly_Wait(5);
		
		send_Values(login.getLogin(), "itsmemojaved@gmail.com");
		System.out.println("email has been entered");
		implicitly_Wait(5);
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/loginpage.png");
		click(login.getProceedBTN());
		System.out.println("Proceed button clicked");
		
		click(login.getPass());
		System.out.println("OTP procedure started");
		
		//ENter your OTP
		Scanner s = new Scanner(System.in);
		System.out.println("enter your OTP::");
		String otp = s.next();
		WebElement OTPNo = driver.findElement(By.xpath("//input[@name='otpValue']"));
		click(OTPNo);
		send_Values(OTPNo, otp);
		WebElement verifyBTN = driver.findElement(By.xpath("//button[@type='submit']"));
		verifyBTN.click();
		
		System.out.println("OPT has been entered");
		
		moveTOelement(driver, Uhp.getMakeup());
		WebElement makeupremover = driver.findElement(By.linkText("Makeup Remover"));
		implicitly_Wait(4);
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/makeuppage.png");
		makeupremover.click();
		System.out.println("Browser open a makeup remover window tab");
		
		String title = driver.getTitle();
		multiple_WindowHandling(title);
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/makeupremoverPAGE.png");
		System.out.println("Driver moved to newly opened window");

	
		
		scrollIntoView(Mrp.getProductclick());
//		scroll(Mrp.getProductclick());
		
		System.out.println("Page has been scrolled to find the product");
		
		implicitly_Wait(5);
		

		moveToElement(Mrp.getProductclick());
		List<WebElement> product = driver.findElements(By.xpath("//div[@class='css-d5z3ro']"));
		for (WebElement name : product) {
			
			if (name.getText().contains("Clinique Take The Day Off Cleansing Balm (Makeup Remover)")) {
				  
				WebElement prd = driver.findElement(By.xpath("//div[text()='Clinique Take The Day Off Cleansing Balm (Makeup Remover)']"));
//				moveToElement(Mrp.getProductclick());
				prd.click();
				
			}
			
			
		}
		
		moveToElement(Mrp.getProductclick());
//		right_Click(Mrp.getProductclick());
//		down_Enter();
		System.out.println("Product has been found and clicked");
		
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
		
		//switching frame
		iframes(page.getFrameSwitch());
		click(page.getProceedbtn());
		
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
		
		
		
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/paymentMethod.png");
		
		
		implicitly_Wait(7);
		multiple_WindowhandlingURL("https://www.nykaa.com/");
		click(Lpage.getProfileBTN());
		click(Lpage.getLogoutBTN());
		screenshot(driver, "C:\\Users\\Javed\\eclipse-workspace\\maven\\Screenshot/logoutPage.png");
		click(Lpage.getLogoutPopup());
		System.out.println("Profile has been logged out");
		
		
		
		
		

		
		
		
		
		
	}

	

	

}
