package b0ardTesting;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewTicket {
	
	//private static String baseURL = "http://zabegan-001-site31.itempurl.com";
	public static String newTicketType = "CI Test" + CreatingUser.LastName;
	WebDriver driver;
	
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
		driver.findElement(By.cssSelector("#navbar-mobile > ul > li:nth-child(1) > a")).click();
		Thread.sleep(4000);
		
	}

	@Test
	public void createTicket() throws InterruptedException{
		
		driver.findElement(By.linkText(CreatingUser.LastName)).click();
		System.out.println(CreatingUser.LastName + " has entered his newly created board");
		Thread.sleep(5000);		

		driver.findElement(By.cssSelector("#board > div:first-child > div:first-child > div:last-child")).click(); 
		Thread.sleep(8000);
    	
		Ticket ticket = new Ticket(CreatingUser.LastName + " Basic Testing Ticket", "CI TEST", new String[]{"Martina Boshkovska"}, new String[]{"Martina Boshkovska"}, null, null, CreatingUser.user_extern + " is writing a test ticket");
		ticket.calculateDeadline();		
		
		driver.findElement(By.id("newTicketTitle")).clear();
		driver.findElement(By.id("newTicketTitle")).sendKeys(ticket.getTitle());

		driver.findElement(By.cssSelector("#addNewTicketModal > div > div > div.modal-body > form > div:nth-child(3) > div > button")).click();
		Thread.sleep(2000);
		new Select(driver.findElement(By.cssSelector("select#CardTypeId"))).selectByVisibleText(ticket.getTicketType());
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("#addNewTicketModal > div > div > div.modal-body > form > div:nth-child(4) > div > button"));
		new Select(driver.findElement(By.cssSelector("select#Assignees"))).selectByVisibleText(ticket.getAssignTo()[0]); //assign to the first one
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("#addNewTicketModal > div > div > div.modal-body > form > div:nth-child(5) > div > button"));
		new Select(driver.findElement(By.cssSelector("select#Watchers"))).selectByVisibleText(ticket.getWatchedBy()[0]); //watched by the first one
		Thread.sleep(1000);
		
		driver.findElement(By.id("deadline")).clear();
		driver.findElement(By.id("deadline")).sendKeys(ticket.getDeadline());
		Thread.sleep(1000);
		
		driver.findElement(By.id("newTicketDescription")).click();
		driver.findElement(By.id("newTicketDescription")).clear();
		driver.findElement(By.id("newTicketDescription")).sendKeys(ticket.getDescription());
		Thread.sleep(3000);
		
		driver.findElement(By.id("btnAddNewTicketSubmit")).click();
		Thread.sleep(5000);
		
		((JavascriptExecutor)driver).executeScript("document.location.reload()");
		Thread.sleep(6000);
		
		List<WebElement> weList = driver.findElements(By.cssSelector("#board > div:first-child > div:first-child > div:nth-child(2) > div" ));
		assertTrue(weList.size() == 2);
		
		String text = driver.findElement(By.cssSelector("#board > div > div > div.list-item-cards.inner-sortable.ui-sortable > div > div > div > div:nth-child(1) > div.col-md-10 > span:nth-child(2)")).getText();
		System.out.println("Ticket with title: " + text + " has been successfully created.");
		
		
	}
	
	@After
	public void dispose(){
		driver.quit();
	}

}
