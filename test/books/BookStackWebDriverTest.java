package books;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BookStackWebDriverTest {

	private WebDriver driver;

	@Test
	public void addAndRemoveATitle() throws InterruptedException {
		driver = new FirefoxDriver("WebDriver");
		openAppPage();
		
		addTitle("First title");
		addTitle("Second title");

		assertThat(titleOnStack(0), is("Second title"));
		assertThat(titleOnStack(1), is("First title"));
		
		removeTitle(0);
		
		assertThat(titleOnStack(0), is("First title"));
	}

	private void openAppPage() {
		driver.get("http://localhost:8080/BookStack.html");
	}

	private void removeTitle(int pos) {
		WebElement removeButton = driver.findElements(By.className("removeButton")).get(pos);
		removeButton.click();
	}

	private String titleOnStack(int pos) {
		return bookPanel(pos).getText();
	}

	private void addTitle(String title) {
		WebElement textBox = driver.findElement(By.className("gwt-SuggestBox"));
		textBox.sendKeys(title);
		
		WebElement addButton = driver.findElement(By.className("gwt-Button"));
		addButton.click();
	}

	private WebElement bookPanel(int pos) {
		List<WebElement> bookPanels = driver.findElements(By.className("bookPanel"));
		WebElement bookPanel = bookPanels.get(pos);
		return bookPanel;
	}

	
	
}
