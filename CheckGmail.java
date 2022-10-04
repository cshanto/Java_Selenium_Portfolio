/**
I have written a selenium script to send mail from one account to another and verify it. This script should cover the basic practice of integration testing. 
**/

package GmailProject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java_prog.set;

public class CheckGmail {
	//Setting the variable paths/driver
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	//Method to write and send a mail
	@BeforeMethod
	public static void SendMail() throws InterruptedException, AWTException {
		//initiate the ChromeDriver
		ChromeDriver driver = new ChromeDriver();
		//maximize the browser
		driver.manage().window().maximize();
		//command the driver to wait for 30 seconds(implicitly -> for all elements)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//navigate to G-mail Login page
		driver.get("https://accounts.google.com/servicelogin/signinchooser?service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		//input the G-mail using the locator : xpath (put your own G-mail)
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("yourmail@gmail.com");
		//Click next
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		//wait for the element until its visible using explicitlywait(i.e.,one at a time)
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@type='password']")));
		//input the password (put your own password)
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("*******");
		//click next
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		//click compose mail
		driver.findElement(By.xpath("//div[text()='Compose']")).click();
		//wait for 5 sec (optional)
		Thread.sleep(5000);
		//input the recipient's mail
		driver.findElement(By.xpath("//input[@peoplekit-id]")).sendKeys("receivermail@gmail.com");
		//Robot class optional here
		Robot r = new Robot();
		//press Enter key
		r.keyPress(KeyEvent.VK_ENTER);
		//Release enter key
		r.keyRelease(KeyEvent.VK_ENTER);
		//input send
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("Test1");
		//Write the body of the mail
		driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf tS-tW']")).sendKeys("Dear Mr. Dummy, "
				+ "This is a test mail");
		Thread.sleep(8000);
		//click the "send" button
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();
		driver.findElement(By.xpath("//div[@data-tooltip='Sent']")).click();
		System.out.println("Email Sent Suucessfull");
		//close the browser
		driver.close();
	}
	//Method to Check if the mail sent is received
	@Test
	public static void checkReceivedMail() throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://accounts.google.com/servicelogin/signinchooser?service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		//input the recipient's mail
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("receivermail@gmail.com");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		Thread.sleep(6000);
		//input the recipient's password
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("********");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		String receivedMail="";
		Thread.sleep(9000);
		//handle the xpath even if gets old(StaleElementReferenceException)
		try {
			//getting the text of the latest mail in String form
			receivedMail = driver.findElement(By.xpath("//tr[@class='zA zE']/../tr[1]")).getText();
		} catch (StaleElementReferenceException e) {
			receivedMail = driver.findElement(By.xpath("//tr[@class='zA zE']/../tr[1]")).getText();
		} 	
		//convert each words of the mail into an array
		String words[] = receivedMail.split("\n");
		//convert the array into list (it becomes easier for comparison)
		List<String> word = Arrays.asList(words);
		//if the word passed in the contains function exists in the list -> true -> prints out "Received" else "Not received"
		if(word.contains("Test1")==true)
			System.out.println("Received");
		else
			System.out.println("Not received");
		//Finally close the browser
		driver.close();
	}
}
