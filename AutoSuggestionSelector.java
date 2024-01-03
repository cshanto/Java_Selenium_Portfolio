// Here's the script to select any of the auto suggested content of any search (here, Google's search).

package mpkg;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoSuggestionSelector {
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
    //go to Google's search page
		driver.get("https://www.google.com");
    //Search "iphone"
		driver.findElement(By.name("q")).sendKeys("iphone");
		Thread.sleep(4000);
    //collect all sentence coanting a word "iphone" abd store it in a list
		List<WebElement> allSugg = driver.findElements(By.xpath("//span[contains(text(),'iphone')]"));
    //get the size of the list
		System.out.println(allSugg.size());
//     print the list
		for(int i=0;i<allSugg.size();i++) {
			String text = allSugg.get(i).getText();
			System.out.println(text);
		}
    //select the first element of the list
		allSugg.get(0).click();
		Thread.sleep(5000);
		driver.close();
	}

}
