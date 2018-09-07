package b0ardTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreatingNewPanel {

	//private static String baseURL = "http://zabegan-001-site31.itempurl.com";
	WebDriver driver;
	public static String panel_name = "CI Panel " + CreatingUser.LastName;
	
	@Before
	public void setUp() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.get(LoggingIn.baseURL);
		driver.findElement(By.id("Username")).clear();
		driver.findElement(By.id("Username")).sendKeys(CreatingUser.user_extern);
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys(CreatingUser.password);
		driver.findElement(By.name("button")).click();
		Thread.sleep(4000);
		
	}

	@Test
	public void createPanel() throws InterruptedException{
		
		driver.findElement(By.linkText(CreatingUser.LastName)).click();;
		System.out.println(CreatingUser.LastName + " has entered his newly created board");
		Thread.sleep(5000);
		
		
		driver.findElement(By.cssSelector("span.list-item-add-placeholder")).click();
		driver.findElement(By.cssSelector("input.list-item-add-input.list-item-add-input-shown")).clear();
		driver.findElement(By.cssSelector("input.list-item-add-input.list-item-add-input-shown")).sendKeys(panel_name);
		driver.findElement(By.cssSelector("input.list-item-add-save")).click();
		
		Thread.sleep(6000);
		//WebElement we = driver.findElement(By.cssSelector("#board > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > span"));
		WebElement we = driver.findElement(By.cssSelector("#board > div > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > p:nth-child(1)"));
		Thread.sleep(4000);
		assertEquals(panel_name, we.getText());
		System.out.println(CreatingUser.user_extern + " has created the panel " + panel_name);

	}
	
	@After
	public void dispose(){
		driver.quit();
	}

}
