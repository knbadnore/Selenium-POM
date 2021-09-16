package com.qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void setUpRegister() {
		registrationPage = loginPage.clickRegister();
	}

	@DataProvider
	public Object[][] getRegisterMethod() {
		Object[][] registerData = ExcelUtils.getTestData(Constants.REGISTER_SHEET_NAME);
		return registerData;
	}

	@Test(dataProvider = "getRegisterMethod")
	public void userRegistrationTest(String firstname, String lastname, String telephone, String password,
			String subscribe) {

		Assert.assertTrue(registrationPage.FillRegistrationForm(firstname, lastname, generateEmail(), telephone,
				password, subscribe));
	}

	public String generateEmail() {
		Random rn = new Random();
		String email = "test" + rn.nextInt(1000) + "@yahoo.com";
		return email;
	}
}
