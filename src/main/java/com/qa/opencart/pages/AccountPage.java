package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

public class AccountPage {

	ElementUtils elementUtil;
	private WebDriver driver;

	private By accSectionHeader = By.cssSelector("div#content h2");
	private By header = By.cssSelector("div#logo h1 a");
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.xpath("//input[@class='form-control input-lg']");
	private By searchButton = By.xpath("//button[@class='btn btn-default btn-lg']");

	public AccountPage(WebDriver driver) {
		elementUtil = new ElementUtils(driver);
		this.driver = driver;
	}

	public String accountPageTitle() {
		return elementUtil.waitForTitle(5, Constants.ACCOUNT_PAGE_TITLE);
	}

	public String accountPageURL() {
		return elementUtil.getURL();
	}

	public String getAccountPageHeaders() {
		return elementUtil.doGetText(accSectionHeader);
	}

	public List<String> getAccountSectionList() {
		List<String> accountSectionValueList = new ArrayList<String>();
		List<WebElement> weList = elementUtil.waitForVisibilityOfElements(accSectionHeader, 5);
		for (WebElement l : weList) {
			accountSectionValueList.add(l.getText());

		}
		Collections.sort(accountSectionValueList);
		return accountSectionValueList;
	}

	public boolean isLogoutExists() {
		return elementUtil.doIsDisplayed(logoutLink);
	}

	public SearchResultPage searchProduct(String productName) {
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doActionsClick(searchButton);
		return new SearchResultPage(driver);
	}
}
