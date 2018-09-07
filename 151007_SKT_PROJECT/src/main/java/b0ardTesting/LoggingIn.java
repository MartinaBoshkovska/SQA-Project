package b0ardTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoggingIn {

	//public static String baseURL = "http://zabegan-001-site31.itempurl.com";
	public static String baseURL = "http://b0ard.com/";
	public static String logged_in_user;
	WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new FirefoxDriver();
	}
	@Test
	public void logIn() throws InterruptedException {
		driver.get(baseURL);
		driver.findElement(By.id("Username")).clear();
		driver.findElement(By.id("Username")).sendKeys(CreatingUser.user_extern);
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys(CreatingUser.password);
		driver.findElement(By.name("button")).click();
		Thread.sleep(5000);
		WebElement element  = driver.findElement(By.cssSelector("a.dropdown-toggle > span"));
		assertEquals(CreatingUser.FirstName + " " + CreatingUser.LastName, element.getText());
		System.out.println(CreatingUser.user_extern + " has successfully logged in");
		logged_in_user = element.getText();
	}
	
	@After
	public void dispose() throws InterruptedException{
		Thread.sleep(3000);
		driver.quit();
	}
	

}
