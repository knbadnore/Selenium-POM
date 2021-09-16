package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

public class RegistrationPage {
	private WebDriver driver;
	private ElementUtils elementUtils;

	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By email = By.id("input-email");
	By telephone = By.id("input-telephone");
	By password = By.id("input-password");
	By confirmPwd = By.id("input-confirm");

	By subscribeYes = By.xpath("//label[@class='radio-inline'][position()=1]");
	By subscribeNo = By.xpath("//label[@class='radio-inline'][position()=2]");
	By iAgreeCheckbox = By.xpath("//input[@type='checkbox']");
	By continueBtn = By.xpath("//input[@class='btn btn-primary' and @value=\"Continue\"]");
	By successMessage = By.cssSelector("div#content h1");
	By registerHyperlink = By.linkText("Register");
	By logoutHyperlink = By.linkText("Logout");

	RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}

	public boolean FillRegistrationForm(String firstName, String lastName, String email, String telephone,
			String password, String subscribe) {
		elementUtils.doSendKeys(this.firstName, firstName);
		elementUtils.doSendKeys(this.lastName, lastName);
		elementUtils.doSendKeys(this.email, email);
		elementUtils.doSendKeys(this.telephone, telephone);
		elementUtils.doSendKeys(this.password, password);
		elementUtils.doSendKeys(this.confirmPwd, password);
		if (subscribe.equals("Yes")) {
			elementUtils.doClick(subscribeYes);

		} else {
			elementUtils.doClick(subscribeNo);

		}
		elementUtils.doClick(iAgreeCheckbox);
		elementUtils.doClick(continueBtn);
		String msg = elementUtils.waitForElementVisible(successMessage, 5).getText();
		System.out.println("Account created" + msg);

		if (msg.contains(Constants.REGISTER_SUCCESS_MSG)) {
			elementUtils.doClick(logoutHyperlink);
			elementUtils.doClick(registerHyperlink);
			return true;
		} else
			return false;

	}
}
