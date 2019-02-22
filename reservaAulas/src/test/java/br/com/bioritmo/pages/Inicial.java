package br.com.bioritmo.pages;

import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;

import br.com.bioritmo.common.SeleniumCommon;

/*****************************************************************************
 * Author: Leandro Akio Hossaka
 * Description: Page Object da pagina inicial
 *******************************************************************************/
public class Inicial extends BasicPage {
	protected String url = "/klasses?date=%DATE%&klass_kind=&location_acronym=%GYM%&utf8=%E2%9C%93";
	protected String aulaFinder = "//div[div[div[h3[contains(.,'%AULA%')]] and div[contains(.,'%HORARIO%')]]]//a";
	protected String modalAlerta = "//div[@class='modal-dialog modal-md']";
	protected String fecharModalAlerta = "//button[.='Fechar']";
	protected String logout = "//li[@class='Header__menu__item']/a[@data-method='delete']";

	public Inicial(WebDriver driver) {
		super(driver);
	}

	public void selecionaAcademiaEData(String nomeAcademia, String data) throws URISyntaxException {
		System.out.println("Selecionando academia " + nomeAcademia + " e data " + data);
		String urlConvertida = url.replace("%DATE%", data).replace("%GYM%", nomeAcademia);
		System.out.println("URL convertida: " + urlConvertida);
		getDriver().get(new URI(getDriver().getCurrentUrl()).resolve(".." + urlConvertida).toString());
	}

	public Reservas selecionaAula(String nomeAula, String horario) throws Exception {
		System.out.println("Selecionando aula " + nomeAula + " e horário " + horario);
		String aulaConvertida = aulaFinder.replace("%AULA%", nomeAula).replace("%HORARIO%", horario);
		SeleniumCommon.getElementByXpath(getDriver(), aulaConvertida).click();
		return new Reservas(getDriver());
	}

	public void verificaModal() throws Exception {
		System.out.println("Verificando se existe algum alerta na página");
		if (SeleniumCommon.isElementPresentByXpath(getDriver(), modalAlerta)) {
			System.out.println("Fechando alerta");
			SeleniumCommon.getElementByXpath(getDriver(), fecharModalAlerta).click();
		}
	}

	public void deslogar() throws Exception {
		if (SeleniumCommon.isElementPresentByXpath(getDriver(), logout)) {
			System.out.println("Deslogando do sistema");
			SeleniumCommon.getElementByXpath(getDriver(), logout).click();
		}
	}
}
