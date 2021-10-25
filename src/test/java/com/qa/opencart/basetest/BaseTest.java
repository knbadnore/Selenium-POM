package com.qa.opencart.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
	//added comment

	public WebDriver driver;
	public LoginPage loginPage;
	public AccountPage accountPage;
	public Properties prop;
	public SearchResultPage searchResultPage;
	public ProductInfoPage productInfoPage;
	public RegistrationPage registrationPage;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) {
		DriverFactory df = new DriverFactory();
		prop = df.init_prop();
		prop.setProperty("browser", browserName);
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);

	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
	public void test() {
		
	}
	public void test12() {
		
	}
}
