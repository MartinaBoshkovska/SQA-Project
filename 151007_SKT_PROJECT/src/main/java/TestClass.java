import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestClass {
	
	public static WebDriver driver;
	public static String baseUrl;
	
	@Before
	public void setUp() throws Exception {
	baseUrl = "http://google.com";
	driver = new FirefoxDriver();
	}
	@Test
	public void test() throws InterruptedException {
		//navigates to the given base url, for this example it's google.com
		driver.get(baseUrl);
		// Find the text input element by its name
		WebElement element = driver.findElement(By.name("q"));
		// Enter something to search for
		element.sendKeys("Cheese!");
		// Now submit the form. WebDriver will find the form for us from the element
		element.submit();
		//Just another way to do the same but with using the ID attribute
		//driver.findElement(By.id("lst-ib")).sendKeys("cheese");
		//driver.findElement(By.id("_fZl")).click();
		//Using sleep when the page doesn't load fast enough.
		//Always try to escape using it.
		Thread.sleep(1000);
		//Gets all the html elements that have a class name 'g' and stores them into a list
		List<WebElement> googleStories = driver.findElements(By.className("g"));
		System.out.println(googleStories.size());
		
		
		//Reaches to an inner html element from the first item of googleStories, by the tag name
		WebElement nameOfFirstStory = googleStories.get(0).findElement(By.tagName("h3"));
		String nameOfFirstStoryText = nameOfFirstStory.getText();
		//Using assertTrue we can check if the value of the text is as we expected
		assertTrue("The text does not contain cheese!",nameOfFirstStoryText.contains("Cheese"));
		//Click on the 'a' tag from first element of the googleStories list
		//This should get us into the wikipedia article for cheese
		googleStories.get(0).findElement(By.tagName("a")).click();
		//See different ways for getting the href attribute of the first shown image in the article
		//By using the className selector and then the tagName selector
		
		String cssSelector = driver.findElement(By.cssSelector("div.thumbinner:nth-child(1) a.image")).getAttribute("href");
		//By using the xpath selector
		String xpathSelector = driver.findElement(By.xpath("//div[@class='thumbinner'][position()=1]//a[@class='image']")).getAttribute("href");
		//Using assertEquals we can check if we got the same values with the different methods of selection
		assertEquals("The image url is the same!", cssSelector, xpathSelector);
	}

	@AfterClass
	public static void afterClass() {
			//closes the browser
			driver.quit();
	}

}
