/**
Here's the selenium script to upload resume/CV into naukri.com(or any job portal). This script should cover the practice to upload a file into a webpage.
**/
package naukri;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class naukriResume {
	static {
		System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
	}
	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//get the path of the file to be uploaded
		File f = new File("C:\\Users\\91600\\OneDrive\\Desktop\\TEST.pdf");
		//convert it into an absolute path
		String absolutepath = f.getAbsolutePath();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//navigate to naukri login page
		driver.get("https://www.naukri.com/");
		//click login section
		driver.findElement(By.xpath("//a[@id='login_Layer']")).click();
		//input E-mail ID
		driver.findElement(By.xpath("//input[@placeholder='Enter your active Email ID / Username']")).sendKeys("yourgmail.com");
		//input password
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("********");
		//Click login/submit button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//use try-catch in case the chat bot tries to interact with the process.
		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("//div[@class='crossIcon chatBot chatBot-ic-cross']")).click();
			}catch(NoSuchElementException e) {
				System.out.println(e);
			}finally {
			//click the profile icon
			driver.findElement(By.xpath("//div[@class='nI-gNb-drawer__icon']")).click();
			//click "update profile"
			driver.findElement(By.xpath("//a[text()='View & Update Profile']")).click();
			//This line will upload the file
			driver.findElement(By.xpath("//input[@id='attachCV']")).sendKeys(absolutepath);
			//now get the name of the file to verify whether it is uploaded successfully or not
			String filename = driver.findElement(By.xpath("//b[@class='truncate exten']")).getText();
			System.out.println(filename);
			//store the each words from the filename into an array
			String filenameArray[]=filename.split("\\s+");
			//convert the array into a list(for easier comparison)
			List<String> filenameList = Arrays.asList(filenameArray);
			//if the filename exists in the list, prints "Successful" else "Unsuccessful"
			if(filenameList.contains("TEST.pdf")==true)
				System.out.println("Successful");
			else
				System.out.println("Unsuccessfull");
			
			driver.close();
			}
	}
}
