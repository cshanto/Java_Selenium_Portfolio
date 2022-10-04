/**
Here's the selenium script to get all links present in a website.
**/
package mpkg;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class getAllLinks {
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
    //navigate to amazon.com
		driver.get("https://www.amazon.com");
    //(//a) will get al the existing anchor tags within the webpage
		List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
    //get the number of links to use it in the loop
		int count = allLinks.size();
    //print the number of links(optional)
		System.out.println(count );
    //loop to print all the link in string form
		for(int i = 0;i<count;i++) {
			String text = allLinks.get(i).getText();
			System.out.println(text);
		}
		Thread.sleep(4000);
		driver.close();
	}
}
