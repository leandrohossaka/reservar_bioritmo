package br.com.bioritmo.common;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.google.common.io.Files;

/*****************************************************************************
 * Author: Leandro Akio Hossaka
 * Description: Classe para comandos comuns ao Selenium
 *******************************************************************************/
public class SeleniumCommon {
	public static WebElement getElementByXpath(WebDriver driver, String xpath) throws Exception {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			return element;
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(
					"Elemento não encontrado: " + xpath + " Detalhes do erro - " + e.getMessage());
		} catch (WebDriverException e) {
			throw new WebDriverException(
					"Falha ao executar o comando:  " + xpath + " Detalhes do erro - " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Falha ao executar o comando:  " + xpath + " Detalhes do erro - " + e.getMessage());
		}
	}

	public static WebElement getElementById(WebDriver driver, String id) throws Exception {
		try {
			WebElement element = driver.findElement(By.id(id));
			return element;
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(
					"Elemento não encontrado: " + id + " Detalhes do erro - " + e.getMessage());
		} catch (WebDriverException e) {
			throw new WebDriverException(
					"Falha ao executar o comando:  " + id + " Detalhes do erro - " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Falha ao executar o comando:  " + id + " Detalhes do erro - " + e.getMessage());
		}
	}

	public static boolean isElementPresentByXpath(WebDriver driver, String xpath) {
		try {
			getElementByXpath(driver, xpath);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void print(WebDriver driver) throws Exception {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String nomeArquivo = System.currentTimeMillis() + ".png";
		Files.copy(screenshot, new File("C:/temp/" + nomeArquivo));
		System.out.println("Print: C:/temp/" + nomeArquivo);
	}
}
