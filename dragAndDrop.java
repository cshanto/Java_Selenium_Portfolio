/**
Here's a script to perform drag and drop using selenium and java. This script should cover the concept of drag and drop as well as scrolling a webpage 
**/

package mpkg;

import java.awt.Desktop.Action;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class draganddrop {
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		//optional: just to track the actions
		Thread.sleep(5000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//navigate to drag and drop section of the demoqa website
		driver.get("https://demoqa.com/droppable/");
		//javascriptExecutor is used for scrolling
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,70)", "");
		//optional: just to track the actions i.e.,scrolling
		Thread.sleep(5000);
		//find the drag-able element 
		WebElement from=driver.findElement(By.xpath("//div[@class='simple-drop-container']/div[1]"));
		//find the drop-able element
		WebElement to=driver.findElement(By.xpath("//div[@class='simple-drop-container']/div[2]"));
		//Action class is used to perform mouse and keyboard action
		Actions act = new Actions(driver);
		//dragging from "from"(source) to "to"(destination)
		act.dragAndDrop(from,to).build().perform();
		//optional: just to track the actions i.e.,drag and drop
		Thread.sleep(6000);
		driver.close();
	}
}
