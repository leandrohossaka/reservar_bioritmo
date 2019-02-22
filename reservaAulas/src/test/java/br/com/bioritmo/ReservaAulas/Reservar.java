package br.com.bioritmo.ReservaAulas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import br.com.bioritmo.pages.BasicPage;
import br.com.bioritmo.pages.Confirmacao;
import br.com.bioritmo.pages.Inicial;
import br.com.bioritmo.pages.Login;
import br.com.bioritmo.pages.Reservas;

/*****************************************************************************
 * Author: Leandro Akio Hossaka Description: Script para reservar aulas no site
 * da BioRitmo
 *******************************************************************************/
public class Reservar {
	protected BasicPage paginaBase = new BasicPage();
	protected Login paginaLogin;
	protected Inicial paginaInicial;
	protected Reservas paginaReserva;
	protected Confirmacao paginaConfirmacao;
	protected String inputFile = "input.txt";

	@Test
	public void reservar() throws Exception {
		System.out.println("Iniciando script de reservas BioRitmo");
		LocalDate localDateHoje = LocalDate.now();
		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String hojeFormatado = localDateHoje.format(formatterData);

		while (true) {
			List<String> listaReservasHoje = new ArrayList<String>();
			try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
				String line;
				while ((line = br.readLine()) != null) {
					if (line.contains(hojeFormatado))
						listaReservasHoje.add(line);
				}
			}

			System.out.println("Hoje é: " + hojeFormatado);
			System.out.println("Quantidade de reservas para hoje: " + listaReservasHoje.size());

			if (listaReservasHoje.size() == 0) {
				System.out.println("Nenhuma reserva para hoje!");
				localDateHoje = LocalDate.now();
				hojeFormatado = localDateHoje.format(formatterData);
				Thread.sleep(60000);
				continue;
			}

			Thread.sleep(60000);
			for (String reserva : listaReservasHoje) {
				try {
					String[] split = reserva.split(";");

					String url = split[0];
					String usuario = split[1];
					System.out.println("Usuario: " + usuario);
					String senha = split[2];
					System.out.println("Senha: " + senha);
					String academia = split[3];
					System.out.println("Academia: " + academia);
					String data = split[4];
					System.out.println("Data: " + data);
					String aula = split[5];
					System.out.println("Aula: " + aula);
					String hora = split[6];
					System.out.println("Hora: " + hora);

					acessandoPaginaLogin(url);
					efetuarLogin(usuario, senha);
					verificarModalAlerta();
					selecionaAcademiaEData(academia, data);
					verificarModalAlerta();
					selecionaAula(aula, hora);
					while (this.paginaConfirmacao == null) {
						efetuarReserva();
					}

					tirarPrint();
					deslogar();

					System.out.println("Aula reservada com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		}
	}

	@AfterTest
	public void deslogarEFechar() throws Exception {
		deslogar();
		fecharNavegador();
	}

	private void acessandoPaginaLogin(String url) {
		System.out.println("Acessando URL: " + url);
		this.paginaBase.navigateTo(url);
	}

	private void efetuarLogin(String user, String pass) throws Exception {
		this.paginaLogin = new Login(this.paginaBase.getDriver());
		this.paginaInicial = paginaLogin.logar(user, pass);
	}

	private void verificarModalAlerta() throws Exception {
		this.paginaInicial.verificaModal();
	}

	private void selecionaAcademiaEData(String academia, String data) throws Exception {
		this.paginaInicial.selecionaAcademiaEData(academia, data);
	}

	private void selecionaAula(String aula, String horario) throws Exception {
		this.paginaReserva = this.paginaInicial.selecionaAula(aula, horario);
	}

	private void deslogar() throws Exception {
		this.paginaInicial.deslogar();
	}

	private void fecharNavegador() {
		this.paginaBase.closeNavigator();
	}

	private void efetuarReserva() throws Exception {
		this.paginaConfirmacao = this.paginaReserva.efetuarReserva();
	}

	private void tirarPrint() throws Exception {
		this.paginaConfirmacao.printConfirmation();
	}
}
