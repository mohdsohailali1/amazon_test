package com.amazon.test.amazon_test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdef {
	static public WebDriver driver;
	List itemList = new ArrayList<>();

	@Given("^I am on Amazon home page on \"([^\"]*)\"$")
	public void i_am_on_Amazon_home_page(String browser) throws Exception {

		String path = System.getProperty("user.dir");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", path + "\\Resources\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", path + "\\Resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", path + "\\Resources\\msedgedrivern.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.navigate().to("https://www.amazon.com");

	}

	@When("^user search for Teddy bear$")
	public void user_search_for_Teddy_bear() throws Exception {
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Teddy bear");
		driver.findElement(By.id("nav-search-submit-button")).click();

	}

	@And("^user sorts the result according to Customer Review$")
	public void user_sorts_the_result() throws Exception {

		driver.findElement(By.id("a-autoid-0-announce")).click();
		driver.findElement(By.id("s-result-sort-select_3")).click();
		// Select sortOption = new Select(driver.findElement(By.id("s-result-sort-select")));
		// sortOption.selectByVisibleText("Avg. Customer Review");

		Thread.sleep(2000);
	}

	@And("^user select the Age range between 5 to 7 years old$")
	public void user_select_the_Age_range() throws Exception {
		new WebDriverWait(driver, 5)
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("5 to 7 Years"))));
		WebElement element = driver.findElement(By.linkText("5 to 7 Years"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

		element.click();

	}

	@Then("^The user adds the first two items in his cart$")
	public void user_adds_items_in_his_cart() throws Exception {
		// ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,
		// -document.body.scrollHeight)");
		WebElement ele = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[2]/div[1]/h2/a/span"));
		itemList.add(ele.getText());
		ele.click();
		driver.findElement(By.name("submit.add-to-cart")).click();
		driver.navigate().back();
		driver.navigate().back();
		WebElement ele2 = driver.findElement(By.xpath("//div[3]/div/div/div/div/div[3]/div[1]/h2/a/span"));

		itemList.add(ele2.getText());

		ele2.click();
		driver.findElement(By.name("submit.add-to-cart")).click();
		Thread.sleep(2000);

	}

	@And("^user validates the two items in the cart.$")
	public void user_validates_cart() throws Exception {

		driver.navigate().back();
		driver.findElement(By.id("nav-cart")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@class='a-list-item']/a/span[1]/span/span[1]"));
		ArrayList<String> webtextList = new ArrayList<String>();
		webtextList.add(elements.get(1).getAttribute("innerText"));
		webtextList.add(elements.get(0).getAttribute("innerText"));

		Assert.assertEquals(itemList.get(0), webtextList.get(0));
		Assert.assertEquals(itemList.get(1), webtextList.get(1));

		driver.close();
	}
}
