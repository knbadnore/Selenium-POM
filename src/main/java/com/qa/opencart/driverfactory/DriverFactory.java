package com.qa.opencart.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import com.qa.opencart.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public static String highlight = null;
	OptionsManager optionsManager;
	ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * 
	 * @param browserName
	 * @return This method will return the Driver Object
	 */

	public WebDriver init_driver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		String browserName = prop.getProperty("browser").trim();
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browserName.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else {
			System.out.println("Please Pass correct browser : " + browserName);
		}
		getLocalDriver().manage().window().maximize();
		getLocalDriver().manage().deleteAllCookies();
		getLocalDriver().get("https://demo.opencart.com/index.php?route=account/login");

		return getLocalDriver();

	}

	public WebDriver getLocalDriver() {
		return tldriver.get();
	}

	/**
	 * 
	 * @return This method will return the Properties Object
	 */

	public Properties init_prop() {

		FileInputStream fileInput = null;
		prop = new Properties();
		String env = System.getProperty("env");

		if (env == null) {
			System.out.println("Enviroment is  :" + env);
			try {
				fileInput = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			try {
				System.out.println("Enviroment is  :" + env);
				switch (env) {

				case "qa":
					fileInput = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "dev":
					fileInput = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "stage":
					fileInput = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;

	}
	public String getScreenshot() {
		File src = ((TakesScreenshot)getLocalDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}