package com.qa.opencart.test;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.basetest.BaseTest;

import com.qa.opencart.utils.Constants;

public class AccountPageTest extends BaseTest {
	@BeforeClass
	public void accountPageSetup() {
		accountPage = loginPage.doLoginTest(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println("**************" + accountPage);
	}

	@Test
	public void accountPageTitleTest() {
		Assert.assertEquals(accountPage.accountPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void accountPageHeaderTest() {
		String header = accountPage.getAccountPageHeaders();
		System.out.println("Header" + header);
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void accSectionListTest() {
		List<String> accSecList = accountPage.getAccountSectionList();
		Collections.sort(Constants.expAccounrSectionList);

		Assert.assertEquals(accSecList, Constants.expAccounrSectionList);
	}

}
