package scenario.TicketAirTravel;

//import javax.ws.rs.GET;

import com.everis.Action;
import com.everis.EFA;
import com.everis.Element;
//import com.everis.GlobalData;

import utilities.FrameworkAndSelenium.Utilities;

/*
 * @Author: Leandro Estevão
 * @Date: 27/10/2017
 * @Description: This class is responsible for create Air Pass
 * 
 */

public class Tarefas {

		
	static String protocolo = "";
	
	/*** Login ***/

	public static String login(String urlEnd, String usuario, String senha) throws Exception {
		Element fieldUser = new Element("xpath", "//input[@name='userName']");
		Element fieldPassword = new Element("xpath", "//input[@name='password']");
		Element botaoSign = new Element("xpath", "//input[@name='login']");

		Element labelVerif1 = new Element("xpath", "//img[@alt='Featured Destination: Aruba']");

		Element labelVerif2 = new Element("xpath", "(//font[contains(text(),'Flight')])[2]");

		EFA.executeAction(Action.WaitForElementPresent, labelVerif1, 5);

		if (!(boolean) EFA.executeAction(Action.IsElementPresent, labelVerif1)) {

			EFA.executeAction(Action.Navigate, urlEnd);

			try {

				EFA.cv_driver.switchTo().alert();
				EFA.cv_driver.switchTo().alert().accept();
				EFA.cv_driver.switchTo().defaultContent();

			} catch (Exception e) {

			}

			EFA.executeAction(Action.SendKeys, fieldUser, usuario);

			EFA.executeAction(Action.SendKeys, fieldPassword, senha);

			EFA.executeAction(Action.Click, botaoSign);

			EFA.executeAction(Action.WaitForElementPresent, labelVerif2, 5);

			if (!(boolean) EFA.executeAction(Action.IsElementPresent, labelVerif2)) {

				System.out.println("Usuário e/ou Senha Incorretos");
				System.out.println(EFA.cv_onError);
				System.out.println("usuário digitado = " + usuario);
				System.out.println("senha digitada = " + senha);

				return "Usuário e/ou Senha Incorretos";

			} else {

				System.out.println("Login realizado com Sucesso");

			}
		}
		return "";

	}

	// *** Preechimento da Reserva de Passagem ***/

	public static String preenchimento(String tipoViagem, String qtdePassageiros, String partindoDe, String mesPartida,
			String diaPartida, String chegandoEm, String mesChegada, String diaChegada, String servicoClasse,
			String linhaArea)

			throws Exception {

		Element radionTypetrip = new Element("xpath", "//input[@value ='" + tipoViagem + "']");
		// Element radionTypetrip2 = new Element("xpath","//input[@value = 'oneway']");
		// modo para switch case

		Element selectQtde = new Element("xpath", "//select[@name='passCount']");
		Element departFrom = new Element("xpath", "//select[@name='fromPort']");
		Element fromMonth = new Element("xpath", "//select[@name='fromMonth']");
		Element fromDay = new Element("xpath", "//select[@name='fromDay']");

		// Exemplo: Element selectQtde2 = new Element("xpath",)
		// "//select[@name='passCount']//option[@value='2']");
		// Exemplo de busca EFA.executeAction(Action.SendKeys, txtBusca,
		// GlobalData.getData("vuser")+Keys.ENTER);

		Element toFrom = new Element("xpath", "//select[@name='toPort']");
		Element toMonth = new Element("xpath", "//select[@name='toMonth']");
		Element toDay = new Element("xpath", "//select[@name='toDay']");

		Element serviceClass = new Element("xpath", "//input[@value='" + servicoClasse + "']");

		Element airLine = new Element("xpath", "//select[@name='airline']");

		Element botaoContinuar = new Element("xpath", "//input[@name='findFlights']");

		Element labelCheckScreen = new Element("xpath", "//img[@src='/images/masts/mast_flightfinder.gif']");

		Element labelVerifPreenchimento = new Element("xpath", "(//font[contains(text(),'DEPART')])");

		Element labelVerifPreenchimento2 = new Element("xpath",
				"(//font[contains(text(),'" + partindoDe + " to " + chegandoEm + "')])");

		Element labelVerifPreenchimento3 = new Element("xpath",
				"(//font[contains(text(),'" + mesPartida + "/" + diaPartida + "/2018')])");

		Element botaoContinue = new Element("xpath", "//input[@name='reserveFlights']");

		/*
		 * switch (tipoViagem) {
		 * 
		 * case "roundtrip":
		 * 
		 * EFA.executeAction(Action.Click, radionTypetrip1, tipoViagem);
		 * 
		 * 
		 * case "oneway":
		 * 
		 * EFA.executeAction(Action.Click, radionTypetrip2, tipoViagem);
		 * 
		 * break; }
		 * 
		 */

		/**
		 * Ações*
		 **/

		EFA.executeAction(Action.WaitForElementPresent, labelCheckScreen, 5);

		System.out.println("Preenchendo os dados");

		EFA.executeAction(Action.Click, radionTypetrip, tipoViagem);
		EFA.executeAction(Action.SelectByValue, selectQtde, qtdePassageiros);
		EFA.executeAction(Action.SelectByValue, departFrom, partindoDe);
		EFA.executeAction(Action.SelectByValue, fromMonth, mesPartida);
		EFA.executeAction(Action.SelectByValue, fromDay, diaPartida);

		EFA.executeAction(Action.SelectByValue, toFrom, chegandoEm);
		EFA.executeAction(Action.SelectByValue, toMonth, mesChegada);
		EFA.executeAction(Action.SelectByValue, toDay, diaChegada);
		EFA.executeAction(Action.Click, serviceClass, servicoClasse);
		EFA.executeAction(Action.SelectByVisibleText, airLine, linhaArea);

		EFA.executeAction(Action.Click, botaoContinuar);

		System.out.println("Verificação dos Dados Preenchidos");

		// Verificação de Dados Preenchidos

		EFA.executeAction(Action.WaitForElementPresent, labelVerifPreenchimento, 5);

		if (!(boolean) EFA.executeAction(Action.IsElementPresent, labelVerifPreenchimento) &&

				!(boolean) EFA.executeAction(Action.IsElementPresent, labelVerifPreenchimento2) &&

				!(boolean) EFA.executeAction(Action.IsElementPresent, labelVerifPreenchimento3))

		{

			System.out.println("Preenchimento Não Realizado");
			System.out.println(EFA.cv_onError);
			return "Dados Incorretos";

		} else {

			System.out.println("Preenchimento Realizado com Sucesso");
			System.out.println("Label Esperado: " + EFA.executeAction(Action.GetText, labelVerifPreenchimento));
			System.out.println("Origem: " + EFA.executeAction(Action.GetText, labelVerifPreenchimento2));
			System.out.println("Data: " + EFA.executeAction(Action.GetText, labelVerifPreenchimento3));

			EFA.executeAction(Action.Click, botaoContinue);

		}
		return "";
	}

	// *** Verificação do Preechimento da Reserva de Passagem ***/

	public static String dadosDoPassageiro(String qtdePassageiros, String primeiroNome1, String ultimoNome1,
			String comida1, String primeiroNome2, String ultimoNome2, String comida2, String primeiroNome3,
			String ultimoNome3, String comida3, String primeiroNome4, String ultimoNome4, String comida4)
			throws Exception {

		Element passFirst1 = new Element("xpath", "//input[@name='passFirst0']");
		Element passLast1 = new Element("xpath", "//input[@name='passLast0']");
		Element meal1 = new Element("xpath", "//select[@name='pass.0.meal']");
		Element passFirst2 = new Element("xpath", "//input[@name='passFirst1']");
		Element passLast2 = new Element("xpath", "//input[@name='passLast1']");
		Element meal2 = new Element("xpath", "//select[@name='pass.1.meal']");
		Element passFirst3 = new Element("xpath", "//input[@name='passFirst2']");
		Element passLast3 = new Element("xpath", "//input[@name='passLast2']");
		Element meal3 = new Element("xpath", "//select[@name='pass.2.meal']");
		Element passFirst4 = new Element("xpath", "//input[@name='passFirst3']");
		Element passLast4 = new Element("xpath", "//input[@name='passLast3']");
		Element meal4 = new Element("xpath", "//select[@name='pass.3.meal']");
		Element labelVerifSumary = new Element("xpath", "//font[contains(text(), 'Summary')]");

		EFA.executeAction(Action.WaitForElementPresent, labelVerifSumary, 5);

		System.out.println("Label Esperado: " + EFA.executeAction(Action.GetText, labelVerifSumary));

		int qtde = Integer.parseInt(qtdePassageiros);

		if (qtde == 1) {

			EFA.executeAction(Action.SendKeys, passFirst1, primeiroNome1);
			EFA.executeAction(Action.SendKeys, passLast1, ultimoNome1);
			EFA.executeAction(Action.SelectByValue, meal1, comida1);

		} else if (qtde == 2) {

			EFA.executeAction(Action.SendKeys, passFirst1, primeiroNome1);
			EFA.executeAction(Action.SendKeys, passLast1, ultimoNome1);
			EFA.executeAction(Action.SelectByValue, meal1, comida1);

			EFA.executeAction(Action.SendKeys, passFirst2, primeiroNome2);
			EFA.executeAction(Action.SendKeys, passLast2, ultimoNome2);
			EFA.executeAction(Action.SelectByValue, meal2, comida2);

		} else if (qtde == 3) {

			EFA.executeAction(Action.SendKeys, passFirst1, primeiroNome1);
			EFA.executeAction(Action.SendKeys, passLast1, ultimoNome1);
			EFA.executeAction(Action.SelectByValue, meal1, comida1);

			EFA.executeAction(Action.SendKeys, passFirst2, primeiroNome2);
			EFA.executeAction(Action.SendKeys, passLast2, ultimoNome2);
			EFA.executeAction(Action.SelectByValue, meal2, comida2);

			EFA.executeAction(Action.SendKeys, passFirst3, primeiroNome3);
			EFA.executeAction(Action.SendKeys, passLast3, ultimoNome3);
			EFA.executeAction(Action.SelectByValue, meal3, comida3);

		} else if (qtde == 4) {

			EFA.executeAction(Action.SendKeys, passFirst1, primeiroNome1);
			EFA.executeAction(Action.SendKeys, passLast1, ultimoNome1);
			EFA.executeAction(Action.SelectByValue, meal1, comida1);

			EFA.executeAction(Action.SendKeys, passFirst2, primeiroNome2);
			EFA.executeAction(Action.SendKeys, passLast2, ultimoNome2);
			EFA.executeAction(Action.SelectByValue, meal2, comida2);

			EFA.executeAction(Action.SendKeys, passFirst3, primeiroNome3);
			EFA.executeAction(Action.SendKeys, passLast3, ultimoNome3);
			EFA.executeAction(Action.SelectByValue, meal3, comida3);

			EFA.executeAction(Action.SendKeys, passFirst4, primeiroNome4);
			EFA.executeAction(Action.SendKeys, passLast4, ultimoNome4);
			EFA.executeAction(Action.SelectByValue, meal4, comida4);

		} else {

			System.out.println("Erro");
			return "Dados Não Preenchidos Corretamente !";

		}

		System.out.println("Dados Preenchidos com Sucesso");

		return "";

	}

	public static String dadosDoCartao(String tipoCartao, String numeroCartao, String mesExpiracaoCartao,
			String anoExpiracaoCartao, String nomePrimeiroCartao, String nomeMeioCartao, String nomeUltimoCartao)
			throws Exception {

		Element creditCard = new Element("xpath", "//select[@name='creditCard']");
		Element creditNumber = new Element("xpath", "//input[@name='creditnumber']");
		Element monthExpiration = new Element("xpath", "//select[@name='cc_exp_dt_mn']");
		Element yearExpiration = new Element("xpath", "//select[@name='cc_exp_dt_yr']");
		Element firstName = new Element("xpath", "//input[@name='cc_frst_name']");
		Element middleName = new Element("xpath", "//input[@name='cc_mid_name']");
		Element lastName = new Element("xpath", "//input[@name='cc_last_name']");
		Element botaoSecurePurchase = new Element("xpath", "//input[@name='buyFlights']");

		EFA.executeAction(Action.SelectByVisibleText, creditCard, tipoCartao);
		EFA.executeAction(Action.SendKeys, creditNumber, numeroCartao);
		EFA.executeAction(Action.SelectByVisibleText, monthExpiration, mesExpiracaoCartao);
		EFA.executeAction(Action.SelectByVisibleText, yearExpiration, anoExpiracaoCartao);
		
		EFA.alertConfirmationMessage(true);
		
		

		EFA.executeAction(Action.SendKeys, firstName, nomePrimeiroCartao);
		EFA.executeAction(Action.SendKeys, middleName, nomeMeioCartao);
		EFA.executeAction(Action.SendKeys, lastName, nomeUltimoCartao);

		System.out.println("Dados do Cartão de Crédito Preenchidos com Sucesso");

		EFA.executeAction(Action.Click, botaoSecurePurchase);

		Element labelVeriFlight = new Element("xpath", "//font[contains(text(), 'Flight ')]");
		Element botaoLogout = new Element("xpath",
				"//a[@href='mercurysignoff.php']//img[@src='/images/forms/Logout.gif']");

		if (!(boolean) EFA.executeAction(Action.IsElementPresent, labelVeriFlight))

		{
			System.out.println("Erro ao Solicitar a Reserva !");
			System.out.println(EFA.cv_onError);

		} else {

			System.out.println("Label Esperado: " + EFA.executeAction(Action.GetText, labelVeriFlight));
			

		}
		
		gravarProtocolo();
		System.out.println("Label Esperado: " + protocolo);	

		EFA.executeAction(Action.Click, botaoLogout);
		return "";

	}

	public static void gravarProtocolo() throws Exception  {
		
		Element labelVeriFlight = new Element("xpath", "//font[contains(text(), 'Confirmation # 2018')]");
		
		protocolo = (String) EFA.executeAction(Action.GetText, labelVeriFlight);
		protocolo = protocolo.replaceAll("[\\sa-zA-Z\\p{Punct}]+", "");
		
		Utilities.armazenarDadosPlanilha("Protocolo", protocolo);
		
		
		/***  Alerta
					try {
					Alert alert = cv_driver.switchTo().alert();
					strRetorno = alert.getText();
					if (accept) {
					alert.accept();
					} else {
					alert.dismiss();
					}
					} catch (Exception e) {
					return null;
					}
		 * 
		 * 
		 * 
		 ***/
		
		

	}
	
	
}
