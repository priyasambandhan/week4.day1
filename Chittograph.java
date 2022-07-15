package week4.day1;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chittograph {
	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.chittorgarh.com/");
		driver.manage().window().maximize();
		driver.findElement(By.id("navbtn_stockmarket")).click();
		driver.findElement(By.linkText("NSE Bulk Deals")).click();
		Thread.sleep(20000);
		WebElement tableBulkDeals = driver.findElement(By.xpath("(//table)[2]"));
		List<WebElement> tableBulkDealsRow = tableBulkDeals.findElements(By.tagName("tr"));
		//System.out.println(tableBulkDealsRow);
		int colSecurity = 0;
		WebElement titleRow = tableBulkDealsRow.get(0);
		List<WebElement> titlecells = titleRow.findElements(By.tagName("th"));
		for (int j = 0; j < titlecells.size(); j++) {

			if (titlecells.get(j).getText().contains("Security Name"))

				colSecurity = j;
		}
		List<String> securitynames = new ArrayList();
		for (int i = 1; i < tableBulkDealsRow.size(); i++) {

			WebElement eachrow = tableBulkDealsRow.get(i);

			List<WebElement> cols = eachrow.findElements(By.tagName("td"));
			securitynames.add(cols.get(colSecurity).getText());
		}
		Set<String> secritynamesSet = new LinkedHashSet(securitynames);
		boolean duplicateflag = true;
		for (int i = 0; i < securitynames.size(); i++) {

			if (secritynamesSet.add(securitynames.get(i)) == false)

				duplicateflag = false;

		}
		if (duplicateflag == false)

			System.out.println("Security names contain duplicates");

		else
			System.out.println("Security names do not contain duplicates");
		System.out.println("unique values are " + secritynamesSet);

	}
}
