package b0ardTesting;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreatingUser {
	
	public static String base = "http://zabegan-001-site30.itempurl.com/Company";
	public static String password = "123456";
	public static String FirstName = "Selenium";
	public static String LastName = "User"; //we append the random extension to the last name of the user 
	public static String email = "martinaboshkovska45@gmail.com";
	public static String user_extern;
	public WebDriver driver;
	
	@Before
	public void setUp(){
		driver = new FirefoxDriver();
	}
	 
	@Test
	public void userCreate() throws InterruptedException {
		driver.get(base);

		driver.findElement(By.id("Username")).clear();
		driver.findElement(By.id("Username")).sendKeys("Selenium");
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.name("button")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("a.details:nth-child(1) > i:nth-child(1)")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("a.btn:nth-child(1)")).click();
		
		Thread.sleep(500);
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
    	c.setTime(new Date()); // Now use today date.
    	//Add a 5 day deadline
    	String date = fmt.format(c.getTime());
    	Random rnd = new Random();
    	int extension = rnd.nextInt(50000);
		String sessionUser = "SeleniumUser_" + date + "_" + extension;
		user_extern = sessionUser;
		LastName +=extension;
		driver.findElement(By.id("addUsername")).clear();
		driver.findElement(By.id("addUsername")).sendKeys(sessionUser);
		driver.findElement(By.id("addEmail")).clear();
		driver.findElement(By.id("addEmail")).sendKeys(email);
		driver.findElement(By.id("addPassword")).clear();
		driver.findElement(By.id("addPassword")).sendKeys(password);
		driver.findElement(By.id("addFirstName")).clear();	
		driver.findElement(By.id("addFirstName")).sendKeys(FirstName);
		driver.findElement(By.id("addLastName")).clear();
		driver.findElement(By.id("addLastName")).sendKeys(LastName);
		
		
		driver.findElement(By.cssSelector("input.btn-success")).click();
		
		Thread.sleep(5000);
		List<WebElement> list = driver.findElements(By.cssSelector(".table > tbody:nth-child(2) > tr"));
		boolean present = false;
		for(WebElement we : list){
			
			if(we.findElement(By.cssSelector("td:nth-child(3)")).getText().contains(sessionUser)){
				present = true;
				System.out.println(we.findElement(By.cssSelector("td:nth-child(3)")).getText());
				break;
			}
		}
		
		assertTrue(present);
		
		driver.get(base);
		driver.findElement(By.cssSelector("a.details:nth-child(2) > i:nth-child(1)")).click();
		Thread.sleep(6000);
		list = driver.findElements(By.cssSelector(".table > tbody:nth-child(2) > tr"));
		for(WebElement we: list){
			if(we.findElement(By.cssSelector("td:nth-child(2)")).getText().contains("Selenium")){
				we.findElement(By.cssSelector("td:nth-child(4) > a:nth-child(1)")).click();
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("a.btn:nth-child(1)")).click();
				Thread.sleep(1000);
				List<WebElement> el = driver.findElements(By.cssSelector("#addUserId > option"));
				int indeks = 0;
				for(WebElement elem : el){
					indeks++;
					if(elem.getText().contains(FirstName + " " + LastName+extension)){
						break;
					}
				}
				driver.findElement(By.cssSelector("#addUserId > option:nth-child(" + indeks+")")).click();
				driver.findElement(By.cssSelector("input.btn-success")).click();
				break;
			}
		}
		
		Thread.sleep(6000);
		list = driver.findElements(By.cssSelector(".table > tbody:nth-child(2) > tr"));
		present = false;
		for(WebElement we : list){
			
			if(we.findElement(By.cssSelector("td:nth-child(2)")).getText().contains(sessionUser)){
				present = true;
				System.out.println(we.findElement(By.cssSelector("td:nth-child(2)")).getText() + " was added to Selenium User group");
				break;
			}
		}
		
		assertTrue(present);
		
	}
	
	@After
	public void dispose(){
		driver.quit();
	}
	

}

