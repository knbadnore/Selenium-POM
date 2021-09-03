package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {
	private WebDriver driver;
	ElementUtils elementUtils;

	By productHeader = By.cssSelector("div #content h1");
	By productImages = By.cssSelector("ul.thumbnails li");
	By productMetadata = By.cssSelector("div.col-sm-4 ul.list-unstyled:nth-of-type(1)");
	By priceData = By.cssSelector("div.col-sm-4 ul.list-unstyled:nth-of-type(2) li");
	By quantityField = By.id("input-quantity");
	By addToCartButton = By.id("button-cart");
	By successMessage = By.cssSelector("div.alert.alert-success.alert-dismissible");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}

	public String getProductHeaderText() {
		
		return elementUtils.doGetText(productHeader);
	}

	public int getImagesCount() {
		return elementUtils.getElements(productImages).size();
	}

	/*
	 * This method will return the Map of product meta data
	 */
	public Map<String, String> getProductInfo() {
		Map<String, String> productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getProductHeaderText());

		List<WebElement> metaDataList = elementUtils.getElements(productMetadata);
		System.out.println("LIST SIZE " + metaDataList.size());

		for (WebElement e : metaDataList) {
			String[] metaArray = e.getText().split(":");

			String metaKey = metaArray[0].trim();
			String metaValue = metaArray[1].trim();
			productInfoMap.put(metaKey, metaValue);

		}

		List<WebElement> priceList = elementUtils.getElements(priceData);
		String price = priceList.get(0).getText().trim();
		String exPrice = priceList.get(1).getText().trim();

		productInfoMap.put("priceKey", price);
		productInfoMap.put("exPriceKey", exPrice);

		return productInfoMap;
	}

	public void selectQuantity(String quantity) {
		elementUtils.doSendKeys(quantityField, quantity);
	}

	public void addToCartBtn() {
		elementUtils.doClick(addToCartButton);
	}

	public String successMessgae() {
		return elementUtils.doGetText(successMessage);
	}
}
