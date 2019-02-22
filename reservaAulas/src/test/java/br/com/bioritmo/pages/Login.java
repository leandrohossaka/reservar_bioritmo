package br.com.bioritmo.pages;

import org.openqa.selenium.WebDriver;

import br.com.bioritmo.common.SeleniumCommon;

/*****************************************************************************
 * Author: Leandro Akio Hossaka
 * Description: Page Object da pagina de login
 *******************************************************************************/
public class Login extends BasicPage {
	protected String userId = "site_session_registry_number";
	protected String passId = "site_session_password";
	protected String logarXpath = "//button[@type='submit']";

	public Login(WebDriver driver) {
		super(driver);
	}

	public Inicial logar(String userName, String userPassword) throws Exception {
		realizaLogin(userName, userPassword);
		return new Inicial(getDriver());
	}

	private void realizaLogin(String userName, String userPassword) throws Exception {
		System.out.println("Realizando login com usuáro " + userName + " e senha " + userPassword);
		SeleniumCommon.getElementById(getDriver(), userId).clear();
		SeleniumCommon.getElementById(getDriver(), userId).sendKeys(userName);
		SeleniumCommon.getElementById(getDriver(), passId).clear();
		SeleniumCommon.getElementById(getDriver(), passId).sendKeys(userPassword);
		SeleniumCommon.getElementByXpath(getDriver(), logarXpath).click();
	}
}
