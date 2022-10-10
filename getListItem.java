	/**
  Here's a selenium script to print all items present in a list. This script should cover the practice of handling listbox of any type.
  **/
  package mpkg;
	
	import java.lang.reflect.Array;
	import java.time.Duration;
	import java.util.ArrayList;
	import java.util.Collection;
	import java.util.List;
	import java.util.concurrent.TimeUnit;
	
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.Select;
	
	import java_prog.collection;
	
	public class getListItem {
		static {
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		}
		public static void main(String[] args) throws InterruptedException {
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(10));
			//navigate to facebook login page
			driver.get("https://www.facebook.com");
			//click on "create new account"
			driver.findElement(By.linkText("Create New Account")).click();
			//get all the items of the month list box
			WebElement montlistbox = driver.findElement(By.id("month"));
			//create "Select" reference to select the items
			Select S = new Select(montlistbox);
			//Store all the items into a list
			List<WebElement> allOptions = S.getOptions();
			//get the size of the list 
			int count = allOptions.size();
			//Create an ArrayList
			ArrayList<String> al = new ArrayList<>();
			//loop to store each item from the list into an ArrayList
			for(int i=0;i<count;i++) {
				String text = allOptions.get(i).getText();
				al.add(text);
			}
			//Print out each item of the ArrayList 
			for(String option : al) {
				System.out.println(option+" ");
			}
			driver.close();
		}
	}
