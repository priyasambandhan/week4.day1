package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapdealAssigment {
	public static void main(String[] args) throws InterruptedException, IOException {

		//Launch https://www.snapdeal.com/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(30));
		// Search on  Training Shoes

		driver.findElement(By.xpath("(//a[@href='javascript:void(0);']//span)[2]")).click();
		driver.findElement(By.xpath("//a[@data-index='1.1.2']/span[text()='Sports Shoes']")).click();
		//Get the count of the Training Shoes
		String countShoes = driver
				.findElement(By.xpath("(//a[@href='/products/mens-footwear-sports-shoes#bcrumbLabelId:255']//div)[2]"))
				.getText();
		System.out.println("Count of shoes :" + countShoes);
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
		//Click on Sort by  and select Low to High
		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		driver.findElement(By.xpath("//li[@data-sorttype='plth']")).click();
	
		Thread.sleep(10000);
		 //Check if the items displayed are sorted correctly
		List<WebElement> shoes = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		String shoeprice = "";
		List<Integer> priceint = new ArrayList();

		for (int i = 0; i < shoes.size(); i++) {
			System.out.println(shoes.get(i).getText());
			shoeprice = shoes.get(i).getText().replace("Rs. ", "");
			shoeprice = shoeprice.replace(",", "");

			priceint.add(Integer.valueOf(shoeprice));

		}
		boolean flag = true;
		for (int j = 1; j < priceint.size(); j++) {
			if (priceint.get(j - 1) > priceint.get(j))
				flag = false;

		}
		if (flag == true)
			System.out.println("items displayed are sorted correctly ");
		else
			System.out.println("items displayed are not sorted correctly");
		// Enter the price range (900-1500)
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("500");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("1200");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();

		
		WebElement pricefilter = driver.findElement(By.xpath("//a[@data-key='Price|Price']"));
		if (pricefilter.getText().contains("500") && pricefilter.getText().contains("1200")) {
			System.out.println("Price filter applied successfully");
		}
		Thread.sleep(10000);
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
		if (driver.findElement(By.xpath("(//a[@class='clear-filter-pill  '])[1]")).getText().contains("Navy")) {
			System.out.println("Colour filter applied successfully");
		}
		List<WebElement> filteredshoes = driver.findElements(By.tagName("picture"));
		filteredshoes.get(0).click();
		Set<String> windows = driver.getWindowHandles();
		List<String> windowsList = new ArrayList(windows);
		driver.switchTo().window(windowsList.get(1));
		//Click on first resulting Training shoes
		Thread.sleep(10000);
		//Print the cost and the discount percentage
		System.out.println("First Shoe's price is: Rs. " + driver.findElement(By.className("payBlkBig")).getText());
		System.out.println("First Shoes discount is :" + driver.findElement(By.xpath("//span[@class='pdpDiscount ']")).getText());
		
		//Take the snapshot of the shoes.
		File source= driver.getScreenshotAs(OutputType.FILE);
		File dest= new File("./snap.jpg");
		FileUtils.copyFile(source, dest); 
		//Close the  window
		driver.quit();

	}

}
