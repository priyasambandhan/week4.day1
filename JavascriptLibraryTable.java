package week4.day1;

import java.util.*;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavascriptLibraryTable {
	public LinkedHashMap findLibrary(String input)

	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://html.com/tags/table/");
		driver.manage().window().maximize();
		// Get table
		WebElement table = driver.findElement(By.xpath("(//table)[1]"));
		// get rows
		List<WebElement> rows = driver.findElements(By.tagName("tr"));
		// get titlerow and values
		WebElement titlerow = rows.get(0);
		List<WebElement> titlevalues = titlerow.findElements(By.tagName("th"));
		// store the values in a list
		List<String> titleArray = new ArrayList();
		for (int a = 1; a < titlevalues.size(); a++) {
			titleArray.add(titlevalues.get(a).getText());
		}

		// find the values based on library name and store in a list
		List<String> valueArray = new ArrayList();

		for (int i = 1; i < rows.size() - 1; i++) {
			WebElement eachrow = rows.get(i);
			List<WebElement> cols = eachrow.findElements(By.tagName("td"));
			for (int j = 1; j < cols.size(); j++) {
				if (cols.get(0).getText().contains(input))

					valueArray.add(cols.get(j).getText());

			}
		}

		// put the title and values into a map
		LinkedHashMap<String, String> libraryMap = new LinkedHashMap();

		for (int k = 0; k < titleArray.size(); k++) {
			libraryMap.put(titleArray.get(k), valueArray.get(k));
		}

		return libraryMap;

	}

	public static void main(String[] args) {

		JavascriptLibraryTable obj = new JavascriptLibraryTable();
		String libraryname = "Absolute Usage";
		LinkedHashMap library = obj.findLibrary(libraryname);
		System.out.println("The values for the library " + libraryname + " are " + library);
	}
}
