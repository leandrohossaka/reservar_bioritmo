package br.com.bioritmo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.bioritmo.common.SeleniumCommon;

/*****************************************************************************
 * Author: Leandro Akio Hossaka
 * Description: Page Object da pagina de reservas
 *******************************************************************************/
public class Reservas extends BasicPage {
	protected String estacaoVazia = "//div[input[not(@disabled) and @type='radio']]";
	protected String confirmar = "//input[@value='Confirmar']";

	public Reservas(WebDriver driver) {
		super(driver);
	}

	public Confirmacao efetuarReserva() throws Exception {
		System.out.println("Procurando por estações livres");
		List<WebElement> elements = getDriver().findElements(By.xpath(estacaoVazia));
		System.out.println("Encontrada " + elements.size() + " estações livres");
		if (elements.size() > 0) {
			System.out.println("Selecionando última estação livre");
			elements.get(elements.size() - 1).click();
			Thread.sleep(200);
			System.out.println("Confirmando");
			SeleniumCommon.getElementByXpath(getDriver(), confirmar).click();
			Thread.sleep(2000);
			if (getDriver().getPageSource().contains("Posição já está em uso"))
				return null;
			return new Confirmacao(getDriver());
		} else {
			throw new Exception("Nenhuma estação disponível!");
		}
	}
}
