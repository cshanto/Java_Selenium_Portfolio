// Here's the scrip to search top 10 most viewed videos on you tube and search any one amongst them in you tube

package mpkg;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ytSearch {
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	public static void main(String[] args) throws InterruptedException, AWTException {
		ChromeDriver driver = new ChromeDriver();
//		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
    //go to google search page
		driver.get("https://www.google.com/");
    //search-(could be any thing)
		driver.findElement(By.name("q")).sendKeys("top 10 most viewed youtube videos of all time");
		Robot R = new Robot();
    //press ENTER
		R.keyPress(KeyEvent.VK_ENTER);
    //release ENTER
		R.keyRelease(KeyEvent.VK_ENTER);
    //get all the text of the results page
		String alltext = driver.findElement(By.xpath("//div[@id='rcnt']")).getText();
    //convert the text into an array
		String alltextArray[]=alltext.split("\\n+");
    //here i=3, because we need to skip unnecessary lines. So, the actual results that we need starts from line 3 of the extracted results
		for(int i=3;i<=11;i++) {
      //here i-2, because to give numbering we need to substract 2 from i to (i.e.,3 - 2 = 1). eg:1. "results line 1".....
			System.out.println(i-2+". "+alltextArray[i]);
		}
    //navigate to youtube
		driver.navigate().to("https://www.youtube.com/"); 
    //send input to search bar (here alltextArray[4] = "results line 2" i.e.,i+1 = 3+1 = 4)
		driver.findElement(By.xpath("//input[@id='search']")).sendKeys(alltextArray[4]);
    //click search icon
		driver.findElement(By.xpath("//button[@id='search-icon-legacy']")).click();
		System.out.println(" ");
    //print the search results (optional)
		String allvids = driver.findElement(By.xpath("//ytd-item-section-renderer[@class='style-scope ytd-section-list-renderer']")).getText();
    //click the the first result
		driver.findElement(By.xpath("//ytd-item-section-renderer[@class='style-scope ytd-section-list-renderer']/div[3]/ytd-video-renderer[1]")).click();
//		driver.close();
	}
}
