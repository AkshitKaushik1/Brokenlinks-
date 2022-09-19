
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class Brokenlinks {

	public static void main(String[] args) throws InterruptedException, Throwable, Exception {
		// TODO Auto-generated method stub
 
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\akshi\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.get("https://www.hapleaf.com/");
	    
		Thread.sleep(5000);
		List<WebElement> Linkslist = driver.findElements(By.tagName("a"));
	    Linkslist.addAll(driver.findElements(By.tagName("img")));
	    
	    System.out.println("Size of full links and imgs --="+Linkslist.size());
	    
	    List <WebElement> activelinks = new ArrayList<WebElement>();
	    
	    for (int i=0; i<Linkslist.size();i++) {
	    	System.out.println(Linkslist.get(i).getAttribute("href"));
	    	if(Linkslist.get(i).getAttribute("href")!=null && (!Linkslist.get(i).getAttribute("href").contains("tel"))) {
	    		activelinks.add(Linkslist.get(i));
	    }
	
	}
	System.out.println("Size of active links and imgs --="+activelinks.size());
     
	for(int j=0; j<activelinks.size();j++){
	          HttpURLConnection connection   =(HttpURLConnection)new URL(activelinks.get(j).getAttribute("href")).openConnection();
	          
	          connection.connect();
	        String response =  connection.getResponseMessage();
	          connection.disconnect();
	          
	          System.out.println(activelinks.get(j).getAttribute("href")+"--->"+response);
	}
}
}