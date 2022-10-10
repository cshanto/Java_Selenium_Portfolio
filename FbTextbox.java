/**
Here's the script to check whether gender radio button are aligned properly or not. This should cover the concept of getting axes from a webpage.
**/
package mpkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FbTextbox {
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
    //navigate to fb login page
		driver.get("https://www.facebook.com");
    //click on 'Create New Account' button
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		Thread.sleep(2000);
    //get the x-axis of the 'Female' radio button
		int FemaleX = driver.findElement(By.xpath("//label[text()='Female']")).getLocation().getX();
    //get the y-axis of the 'Female' radio button
		int FemaleY = driver.findElement(By.xpath("//label[text()='Female']")).getLocation().getY();
    //get the x-axis of the 'Male' radio button
    int MaleX = driver.findElement(By.xpath("//label[text()='Male']")).getLocation().getX();
    //get the y-axis of the 'Male' radio button
		int MaleY = driver.findElement(By.xpath("//label[text()='Male']")).getLocation().getY();
		System.out.println("Female radio button: "+FemaleX+" "+FemaleY);
		System.out.println("Female radio button: "+ MaleX+" "+MaleY);
    //comparison
		if(FemaleY == MaleY)
			System.out.println("Aligned");
		else
			System.out.println("Not aligned");
		Thread.sleep(3000);
		driver.close();
	}
}
