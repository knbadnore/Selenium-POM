package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtils;

public class SearchResultPage {
	private WebDriver driver;
	ElementUtils elementUtil;

	private By searchItemResult = By.cssSelector("div.product-thumb");
	private By resultItems = By.cssSelector("div.product-thumb h4 a");

	public SearchResultPage(WebDriver driver) {
		elementUtil = new ElementUtils(driver);
		this.driver = driver;

	}

	public int getProductResultCount() {
		return elementUtil.getElements(searchItemResult).size();
	}

	public ProductInfoPage selectProductFromResult(String productName) {
		List<WebElement> resultItemList = elementUtil.getElements(resultItems);
		System.out.println("Total Item" + productName);

		for (WebElement e : resultItemList) {
			if (e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}
