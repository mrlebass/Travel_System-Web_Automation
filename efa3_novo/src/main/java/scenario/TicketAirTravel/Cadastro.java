package scenario.TicketAirTravel;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.everis.EFA;
import com.everis.ExecutionInfo;
import com.everis.GlobalData;
import com.everis.data.DataDictionary;
import com.everis.webautomation.ExecutionWrapper;

import utilities.FrameworkAndSelenium.Utilities;

//import utilities.FrameworkAndSelenium.Utilities;

//import utilities.FrameworkAndSelenium.Utilities;

@RunWith(Parameterized.class)
public class Cadastro extends ExecutionWrapper {

	String mensagem = "";

	/**
	 * Script: Web simple form test
	 * 
	 * @param executionName
	 *            Test name
	 * @param data
	 *            Data for current interaction
	 */
	public Cadastro(String executionName, DataDictionary data) {

		ExecutionInfo.setExecutionName(executionName);
		// Fill Global Dictionary
		GlobalData.load(data);
	}

	/**
	 * Load data for all interactions
	 * 
	 * @return List all interactions with data for the test
	 * @throws Exception
	 */
	@Parameters(name = "{0}")
	public static List<Object> loadTestData() throws Exception {
		// Call data loader for all interactions
		try {
			return loadData();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Scenario - Precondition for the test
	 * 
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	@Before
	public void beforeTest() throws Exception {
		System.out.println("Before Test");
		// EFA.killProcess("chromedriver.exe");
		// EFA.loadExecutionInfo();
		EFA.cs_setTries(5);
		Utilities.mf_startSceneTimer();

	}

	/**
	 * Simple example
	 */
	@Test
	public void script() {

		System.out.println("Iniciando caso de teste...");
		System.out.println(GlobalData.getData("Test Case"));

		try {
			EFA.cs_setTries(5);

			Utilities.dataExcelBackup();

			String url = GlobalData.getData("URL");
			// EFA.executeAction(Action.Navigate, GlobalData.getData("URL"));

			String user = GlobalData.getData("User");
			String password = GlobalData.getData("Password");
			String tipoViagem = GlobalData.getData("Tipo de Viagem");
			String qtdePassageiros = GlobalData.getData("Quantidade de Passageiros");
			String partindoDe = GlobalData.getData("Partindo De");
			String mesPartida = GlobalData.getData("Mês de Partida");
			String diaPartida = GlobalData.getData("Dia de Partida");
			String chegandoEm = GlobalData.getData("Chegando em");
			String mesChegada = GlobalData.getData("Mês de Chegada");
			String diaChegada = GlobalData.getData("Dia de Chegada");
			String servicoClasse = GlobalData.getData("Serviço de Classe");
			String linhaArea = GlobalData.getData("Linha Áerea");
			String primeiroNome1 = GlobalData.getData("Nome Passageiro1");
			String ultimoNome1 = GlobalData.getData("Sobrenome Passageiro1");
			String comida1 = GlobalData.getData("Comida Passageiro1");
			String primeiroNome2 = GlobalData.getData("Nome Passageiro2");
			String ultimoNome2 = GlobalData.getData("Sobrenome Passageiro2");
			String comida2 = GlobalData.getData("Comida Passageiro2");
			String primeiroNome3 = GlobalData.getData("Nome Passageiro3");
			String ultimoNome3 = GlobalData.getData("Sobrenome Passageiro3");
			String comida3 = GlobalData.getData("Comida Passageiro3");
			String primeiroNome4 = GlobalData.getData("Nome Passageiro4");
			String ultimoNome4 = GlobalData.getData("Sobrenome Passageiro4");
			String comida4 = GlobalData.getData("Comida Passageiro4");
			String tipoCartao = GlobalData.getData("Tipo de Cartão");
			String numeroCartao = GlobalData.getData("Número do Cartão");
			String mesExpiracaoCartao = GlobalData.getData("Mês de Expiração");
			String anoExpiracaoCartao = GlobalData.getData("Ano de Expiração");
			String nomePrimeiroCartao = GlobalData.getData("Primeiro Nome Títular");
			String nomeMeioCartao = GlobalData.getData("Nome do Meio Títular");
			String nomeUltimoCartao = GlobalData.getData("Último Nome Títular");
//			String protocolo = GlobalData.getData("Protocolo");
			

			// -- Test --

			mensagem = Tarefas.login(url, user, password);
			if (!mensagem.trim().equals(""))

				return;

			mensagem = Tarefas.preenchimento(tipoViagem, qtdePassageiros, partindoDe, mesPartida, diaPartida,
					chegandoEm, mesChegada, diaChegada, servicoClasse, linhaArea);
			if (!mensagem.trim().equals(""))
				return;

			mensagem = Tarefas.dadosDoPassageiro(qtdePassageiros, primeiroNome1, ultimoNome1, comida1, primeiroNome2,
					ultimoNome2, comida2, primeiroNome3, ultimoNome3, comida3, primeiroNome4, ultimoNome4, comida4);
			if (!mensagem.trim().equals(""))
				return;

			mensagem = Tarefas.dadosDoCartao(tipoCartao, numeroCartao, mesExpiracaoCartao, anoExpiracaoCartao,
					nomePrimeiroCartao, nomeMeioCartao, nomeUltimoCartao);
			if (!mensagem.trim().equals(""))
				return;
			
			
			
			
					

			Thread.sleep(8000);

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			// stack trace as a string
			EFA.stackTrace = sw.toString();
			mensagem = EFA.stackTrace;
		} finally {
			Utilities.ms_writeTimeOutput();
			// ExecutionInfo.setResult(mensagem);
		}
	}

	@After
	public void teste() {

		System.out.println("Definindo resultado do caso...");
		if (mensagem.trim().equals("")) {
			mensagem = GlobalData.getData("Expected Result");
			Utilities.armazenarDadosPlanilha("Run Test?", "No");
		}
		System.out.println("Gravar resutado. Resultado: " + mensagem);
		System.out.println("");
		ExecutionInfo.setResult(mensagem);
		Utilities.calculaResumoExecucao(mensagem, GlobalData.getData("Expected Result"));

	}

}
