package br.com.bioritmo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*****************************************************************************
 * Author: Leandro Akio Hossaka
 * Description: Page Object básico que contém comandos comuns ao webdrive
 *******************************************************************************/
public class BasicPage {
	WebDriver driver;

	public BasicPage(WebDriver driver) {
		this.driver = driver;
	}

	public BasicPage() {
		System.setProperty("webdriver.chrome.driver", "C:\\dev\\drivers\\chromedriver.exe");
		this.driver = new ChromeDriver();
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);
		driver.manage().window().maximize();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void closeNavigator() {
		getDriver().close();
	}
}
