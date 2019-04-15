package utilities.FrameworkAndSelenium;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonSimpleWrite {

	public static void GeradorJson(String[] args, String identCasoTeste, String mensagem, String resultadoEsperado,
			String localJson) {

		try (FileWriter file = new FileWriter(localJson + "test_.json")) {

			if (mensagem.equals(resultadoEsperado)) {

				JSONObject obj_passed = new JSONObject();
				obj_passed.put("Test Case", identCasoTeste);
				obj_passed.put("Result_Final", "Passed");

				file.write(obj_passed.toJSONString());
				file.flush();
				System.out.print(obj_passed);

			} else {

				JSONObject obj_failed = new JSONObject();
				obj_failed.put("Test Case", identCasoTeste);
				obj_failed.put("Result_Final", "Failed");
				obj_failed.keySet();

				file.write(obj_failed.toJSONString());
				file.flush();
				System.out.print(obj_failed);

			}

			// file.write(obj.toJSONString());
			// file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
		//// i = i++;
		// System.out.print(obj);

	}

}
