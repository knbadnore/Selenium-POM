package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void setUp() {
		accountPage = loginPage.doLoginTest(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void productCountTest() {
		searchResultPage = accountPage.searchProduct("Macbook");
		Assert.assertTrue(searchResultPage.getProductResultCount() == 3);
	}

	@Test
	public void infoTest() {
		searchResultPage = accountPage.searchProduct("iMac");
		productInfoPage = searchResultPage.selectProductFromResult("iMac");
		Assert.assertEquals(productInfoPage.getProductHeaderText(), "iMac");
	}

	@Test
	public void imageCountTest() {
		searchResultPage = accountPage.searchProduct("Macbook");
		productInfoPage = searchResultPage.selectProductFromResult("MacBook Pro");
		Assert.assertTrue(productInfoPage.getImagesCount() == 4);
	}

	@Test
	public void getProductInfo() {
		searchResultPage = accountPage.searchProduct("Macbook");
		productInfoPage = searchResultPage.selectProductFromResult("MacBook Pro");
		Map<String, String> actualProdMetadata = productInfoPage.getProductInfo();
		//actualProdMetadata.forEach((k, v) -> System.out.println(k + ":" + v));
		Assert.assertEquals(actualProdMetadata.get("name"), "MacBook Pro");
		Assert.assertEquals(actualProdMetadata.get("priceKey"), "$2,000.00");
	}
}
