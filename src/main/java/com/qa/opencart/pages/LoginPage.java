package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

public class LoginPage {
	private ElementUtils elementUtils;
	private WebDriver driver;
	// 1. private By locators

	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value=\"Login\"]");
	private By forgotPwdLink = By.xpath("//div[@class=\"form-group\"]//a[text()=\"Forgotten Password\"]");

	// 2.Constructor to access the driver object
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}
	// 3.Page Actions public in nature

	public String loginPageUrl() {
		return elementUtils.getURL();
	}

	public String loginPageTitle() {
		// return driver.getTitle();
		return elementUtils.waitForTitle(10, Constants.LOGIN_PAGE_TITLE);
	}

	public boolean forgotPasswordLink() {
		return elementUtils.doIsDisplayed(forgotPwdLink);
	}

	public AccountPage doLoginTest(String un, String pwd) {
		elementUtils.doSendKeys(username, un);
		elementUtils.doSendKeys(password, pwd);
		elementUtils.doClick(loginButton);

		return new AccountPage(driver);
	}
}
