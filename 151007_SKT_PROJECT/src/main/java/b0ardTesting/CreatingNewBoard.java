package b0ardTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreatingNewBoard {
	
	//private static String baseURL = "http://zabegan-001-site31.itempurl.com";
	WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new FirefoxDriver();
	}
	
	public void logIn() throws InterruptedException {
		driver.get(LoggingIn.baseURL);
		driver.findElement(By.id("Username")).clear();
		driver.findElement(By.id("Username")).sendKeys(CreatingUser.user_extern);
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys(CreatingUser.password);
		driver.findElement(By.name("button")).click();
		Thread.sleep(5000);
	}

	@Test
	public void createBoard() throws InterruptedException {
		
		logIn();
		WebElement element  = driver.findElement(By.cssSelector("a.dropdown-toggle > span"));
		assertEquals(CreatingUser.FirstName + " " + CreatingUser.LastName, element.getText());
		
		driver.findElement(By.cssSelector("#boardsDiv div.footerBoardNew button")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.id("board-name")).clear();
		driver.findElement(By.id("board-name")).sendKeys(CreatingUser.LastName); 
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		
		
		element = driver.findElement(By.cssSelector("#listBoards > li:last-child > a"));
		assertEquals(CreatingUser.LastName, element.getText());
		System.out.println(CreatingUser.user_extern + " has created a new board named: " + CreatingUser.LastName);

	}
	
	@After
	public void dispose(){
		driver.quit();
	}

}
