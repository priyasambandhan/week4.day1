package week4.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTableAssignment {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(30));
		WebElement table = driver.findElement(By.tagName("table"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		System.out.println("Number of Rows " + rows.size());
		int col = 0;
		String progress = "";
		List<Integer> progressint = new ArrayList();
		Integer leastprogress = 100;
		int rowhavingleastprogress = 0;

		for (int i = 1; i < rows.size(); i++) {
			WebElement eachrow = rows.get(i);
			List<WebElement> cols = eachrow.findElements(By.tagName("td"));
			col = cols.size();
			for (int j = 0; j < col; j++) {
				if (cols.get(j).getText().contains("Elements")) {
					progress = cols.get(1).getText();
					progress = progress.replace("%", "");
					progressint.add(Integer.valueOf(progress));
					if (leastprogress > (Integer.valueOf(progress))) {
						leastprogress = Integer.valueOf(progress);
						rowhavingleastprogress = i;
					}

				}
			}
		}
		System.out.println("Number of columns:" + col);
		System.out.println("% Progress values of  'Learn to interact with Elements' are " + progressint);
		List<WebElement> vital = driver.findElements(By.tagName("input"));
		vital.get(rowhavingleastprogress - 1).click();
		System.out.println("Vital task for the least completed progress " + leastprogress + "% is checked");

	}
}
