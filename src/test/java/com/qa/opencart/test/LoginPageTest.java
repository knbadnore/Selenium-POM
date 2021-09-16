package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.basetest.BaseTest;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPagetitleTest() {
		String actual = loginPage.loginPageTitle();
		Assert.assertEquals(actual, "Account Login");
	}

	@Test
	public void loginPageUrl() {
		String actual = loginPage.loginPageUrl();
		Assert.assertTrue(actual.contains("account/login"));
	}

	@Test
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.forgotPasswordLink());
	}

	@Test
	public void loginTest() {
		loginPage.doLoginTest(prop.getProperty("username"), prop.getProperty("password"));
	}

}
