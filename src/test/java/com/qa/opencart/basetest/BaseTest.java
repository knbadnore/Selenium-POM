package com.qa.opencart.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {

	DriverFactory df = new DriverFactory();
	public WebDriver driver;
	public LoginPage loginPage;
	public AccountPage accountPage;
	public Properties prop;
	public SearchResultPage searchResultPage;
	public ProductInfoPage productInfoPage;

	@BeforeTest
	public void setup() {

		driver = df.init_driver("chrome");
		prop = df.init_prop();
		loginPage = new LoginPage(driver);

	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
