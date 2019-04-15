package scenario.compras;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.util.HSSFColor.GOLD;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.everis.Action;
import com.everis.EFA;
import com.everis.Element;
import com.everis.ExecutionInfo;
import com.everis.GlobalData;
import com.everis.data.DataDictionary;
import com.everis.webautomation.ExecutionWrapper;

//import EfaPackage.Module;

@RunWith(Parameterized.class)
public class Pedido extends ExecutionWrapper {

	String mensagem = "";

	/**
	 * Script: Web simple form test
	 * 
	 * @param executionName
	 *            Test name
	 * @param data
	 *            Data for current interaction
	 */
	public Pedido(String executionName, DataDictionary data) {

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
		return loadData();
	}

	/**
	 * Scenario - Precondition for the test
	 * 
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	@Before
	public void beforeTest() throws Exception {
		EFA.loadExecutionInfo();
	}

	/**
	 * Simple example
	 */
	@Test
	public void script() {
		try {
			EFA.cs_setTries(5);
			
			//Element txtBusca = new Element("id", "lst-ib");
			//Element txtBusca = new Element("xpath", "//input[@id='lst-ib']");
			//Element linkEveris = new Element("xpath", "//a[@href='https://www.lovemondays.com.br/trabalhar-na-everis/avaliacoes']");
			
			
			//***Login
			Element fielddUser = new Element("xpath", "//input[@name='userName'] ");
			Element fieldPassword = new Element("xpath", "//input[@name=\'password\']");
			Element botaoSign = new Element("xpath", "//input[@name='login']");
			
			
			//***Reserva de Passagem
			
			Element radionRtrip = new Element ("xpath", "//input[@value='oneway']");
			Element selectQtde= new Element ("xpath", "//select[@name='passCount']");
			Element departFrom = new Element ("xpath", "//select[@name='fromPort']");
			Element fromMonth = new Element ("xpath", "//select[@name='fromMonth']");
			
			
			
			
			//Element selectQtde2 = new Element("xpath", "//select[@name='passCount']//option[@value='2']"); - exemplo
			
			
			/*Login*/
			EFA.executeAction(Action.Navigate, GlobalData.getData("vURL"));
			EFA.executeAction(Action.SendKeys, fielddUser, GlobalData.getData("vUser"));
			EFA.executeAction(Action.SendKeys, fieldPassword, GlobalData.getData("vPassword"));
			EFA.executeAction(Action.Click, botaoSign);
			
			
			/*Reserva de Passagem*/			
			
			EFA.executeAction(Action.Click, radionRtrip);			
			EFA.executeAction(Action.SelectByValue, selectQtde, GlobalData.getData("Qtde"));
			EFA.executeAction(Action.SelectByValue, departFrom, GlobalData.getData("Partida"));
			
			
			
						
			//EFA.executeAction(Action.SendKeys, txtBusca, GlobalData.getData("vuser")+Keys.ENTER);
						
			
			//Thread.sleep(9000);
			
			
			// Execution

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			// stack trace as a string
			EFA.stackTrace = sw.toString();
			mensagem = EFA.stackTrace;
		} finally {
			ExecutionInfo.setResult(mensagem);
		}
	}
}
