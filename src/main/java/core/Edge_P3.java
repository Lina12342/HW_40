package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

@SuppressWarnings("unused")
public class Edge_P3 {

	static WebDriver driver;
	static String drivePath;
	static Properties p = new Properties();

	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

		p.load(new FileInputStream("input.properties"));
		
		if (System.getProperty("os.name").toUpperCase().contains("MAC")) 			drivePath = "/usr/local/bin/edge";
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) 	drivePath = "c:/windows/msedgedriver.exe";
		else 	throw new IllegalArgumentException("Unknown OS");
		
		
		System.setProperty("webdriver.edge.driver", "/usr/local/bin/edge");
		
		driver = new EdgeDriver();

		
		Capabilities caps = (((RemoteWebDriver) driver).getCapabilities());
		System.out.println("OS: " + System.getProperty("os.name"));
		System.out.println("Browser: " + caps.getBrowserName().substring(0, 1).toUpperCase() + caps.getBrowserName().substring(1).toLowerCase()  + " " + caps.getVersion());
        
		WebDriverWait wait = new WebDriverWait(driver, 15);
		final long start = System.currentTimeMillis();
		
		
		driver.get(p.getProperty("url"));
        
        
		driver.manage().window().setSize(new Dimension(1680, 1500));
		System.out.println("Windows Size: " + driver.manage().window().getSize());
		System.out.println("Title: " + driver.getTitle());
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("email_id")))).sendKeys("nicolaevaa113@gmail.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("password_id")))).sendKeys(System.getenv("fb_password"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("login_id")))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(p.getProperty("timeline_xpath")))).click();
		String friends = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(p.getProperty("friends_xpath")))).getText();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("settings_id")))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(p.getProperty("logout_linkText")))).click();
		
		
		final long finish = System.currentTimeMillis();
		driver.quit();
		System.out.println("You have " + friends + " friends");
		System.out.println("Response time: " + (finish - start) / 1000.0 + " seconds");
	}
}