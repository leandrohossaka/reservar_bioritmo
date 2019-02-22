package br.com.bioritmo.pages;

import org.openqa.selenium.WebDriver;

import br.com.bioritmo.common.SeleniumCommon;

/*****************************************************************************
 * Author: Leandro Akio Hossaka
 * Description: Page Object da pagina de
 * confirmação
 *******************************************************************************/
public class Confirmacao extends BasicPage {
	public Confirmacao(WebDriver driver) {
		super(driver);
	}

	public void printConfirmation() throws Exception {
		Thread.sleep(300);
		System.out.println("Tirando um print da página de confirmação");
		SeleniumCommon.print(getDriver());
	}
}
