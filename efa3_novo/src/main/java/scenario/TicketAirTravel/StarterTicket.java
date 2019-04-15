package scenario.TicketAirTravel;
import org.junit.runner.JUnitCore;
import com.everis.EFA;


public class StarterTicket {
	
	public static void main(String[] args) throws Exception 
	{
		System.out.println("Start starter");
		EFA.killProcess("chromedriver.exe");
		JUnitCore j = new JUnitCore();
		j.run(scenario.TicketAirTravel.Cadastro.class);
		Runtime.getRuntime().exec("cmd /c del %TEMP%\\*.* /f /s /q");
	}

}
