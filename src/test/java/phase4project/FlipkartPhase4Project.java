package phase4project;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class FlipkartPhase4Project {
	
	AndroidDriver<MobileElement> driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void launchApplication() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability("platformName", "ANDROID");
		cap.setCapability("appPackage", "com.flipkart.android");
		cap.setCapability("appActivity", "com.flipkart.android.SplashActivity");
		cap.setCapability("noReset", true);
		driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

	
	@Test
	public void searchProduct() {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		//Click on Search
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@text, 'Search')]"))).click();
		
		//Enter 'mobile' in the search text box
		WebElement searchTextBox= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@text, 'Search for Products')]")));
		searchTextBox.clear();
		
		searchTextBox.sendKeys("mobiles");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@text, 'mobiles')]"))).click();
		
		//Click on the first product option
		wait.until(ExpectedConditions.elementToBeClickable(By.className("android.widget.ImageView"))).click();
		
		//Click on 'Add to Cart'
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@text, 'Add to cart')]"))).click();
		
		//Click on 'Go to Cart'
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@text, 'Go to cart')] | //*[contains(@text, 'GO TO CART')] "))).click();
		
		//Click on 'Place Order'
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@text, 'Place Order')]"))).click();
		
		//Verify text 'Order Summary' coming on activity
		String ExpectedResult = "Order Summary";
		
		String ActualResult = driver.findElement(By.id("com.flipkart.android:id/title_action_bar")).getText();
		
		Assert.assertEquals(ExpectedResult, ActualResult);
		
	}
	
	@AfterTest
	public void tearDown() { 
		
		//Logout from Account
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@text, 'Account')]"))).click();
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@text, 'Log Out')]"))).click();
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@text, 'Logout from this device')]"))).click();
		
		driver.quit(); }
	
}
