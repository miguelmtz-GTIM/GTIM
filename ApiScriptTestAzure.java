package test;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import modelo.BaseExecution;
import utils.ApiMethodsUtil;

public class ApiScriptTestAzure extends BaseExecution{
	
	@Test(dataProvider = "data-provider", enabled = true)
	public void AppiumScriptTest_test(HashMap<String, String> data) throws Exception {
		
		String baseURL = "https://reqres.in";
		ApiMethodsUtil api = new ApiMethodsUtil(baseURL);
		
		/*************************************** PASO 1 GET ***************************************/
		addStep("ejecutar petición GET");
		
			//se obtienen los datos del dataprovider
			String getPath = data.get("getPath");
			int getExpectedCode = Integer.parseInt(data.get("getExpectedCode"));
			
			//Se envía como evidencia el request a ejecutar
			testCase.addCodeEvidenceCurrentStep("request:" + baseURL + getPath);
			
			//Ejecutar el request*
			Response getRespuesta = api.getRequestMethod(getPath);
			
			//Obtener el status code
			int getStatusCode = getRespuesta.getStatusCode();
			testCase.addCodeEvidenceCurrentStep("statusCode: " + getStatusCode);
			assertEquals(getStatusCode, getExpectedCode); //Validación
			
			//Obtener body de respuesta
			String getBodyResponse = getRespuesta.asPrettyString();
			testCase.addCodeEvidenceCurrentStep("bodyResponse:" + getBodyResponse);
			
			//Validación de json*
			JSONObject getJson = new JSONObject(getBodyResponse);
			int total = getJson.getInt("total");
			testCase.addTextEvidenceCurrentStep("total de páginas:" + total);
			assertEquals(total, 12);
			
		/*************************************** PASO 2 POST ***************************************/
			addStep("ejecutar petición POST");
			
			//se obtienen los datos del dataprovider
			String postPath = data.get("postPath");
			String postBody = data.get("postBody");
			int postExpectedCode = Integer.parseInt(data.get("postExpectedCode"));
			
			//Se envía como evidencia el request a ejecutar
			testCase.addCodeEvidenceCurrentStep("request:" + baseURL + postPath);
			testCase.addCodeEvidenceCurrentStep("body:" + baseURL + postBody);
			
			//Ejecutar el request*
			Response postRespuesta = api.postRequestMethod(postPath, postBody);
			
			//Obtener el status code
			int postStatusCode = postRespuesta.getStatusCode();
			testCase.addCodeEvidenceCurrentStep("statusCode: " + postStatusCode);
			assertEquals(postStatusCode, postExpectedCode); //Validación
			
			//Obtener body de respuesta
			String postBodyResponse = postRespuesta.asPrettyString();
			testCase.addCodeEvidenceCurrentStep("bodyResponse:" + postBodyResponse);
			
			//Validación de json
			JSONObject postjson = new JSONObject(postBodyResponse);
			String name = postjson.getString("name");
			testCase.addTextEvidenceCurrentStep("name:" + name);
			assertEquals(name, "morpheus");
			
		/*************************************** PASO 3 PUT ***************************************/
		addStep("ejecutar petición PUT");
			
			//se obtienen los datos del dataprovider
			String putPath = data.get("putPath");
			String putBody = data.get("putBody");
			int putExpectedCode = Integer.parseInt(data.get("putExpectedCode"));
			
			//Se envía como evidencia el request a ejecutar
			testCase.addCodeEvidenceCurrentStep("request:" + baseURL + putPath);
			testCase.addCodeEvidenceCurrentStep("body:" + baseURL + putBody);
			
			//Ejecutar el request*
			Response putRespuesta = api.putRequestMethod(putPath, putBody);
			
			//Obtener el status code
			int putStatusCode = putRespuesta.getStatusCode();
			testCase.addCodeEvidenceCurrentStep("statusCode: " + putStatusCode);
			assertEquals(putStatusCode, putExpectedCode); //Validación
			
			//Obtener body de respuesta
			String putBodyResponse = putRespuesta.asPrettyString();
			testCase.addCodeEvidenceCurrentStep("bodyResponse:" + putBodyResponse);
			
			//Validación de json
			JSONObject putjson = new JSONObject(putBodyResponse);
			String nameput = putjson.getString("name");
			testCase.addTextEvidenceCurrentStep("name:" + nameput);
			assertEquals(nameput, "morpheus2");
			
		/*************************************** PASO 4 DELETE ***************************************/
		addStep("ejecutar petición DELETE");
				
		//se obtienen los datos del dataprovider
		String deletePath = data.get("deletePath");
		int deleteExpectedCode = Integer.parseInt(data.get("deleteExpectedCode"));
		
		//Se envía como evidencia el request a ejecutar
		testCase.addCodeEvidenceCurrentStep("request:" + baseURL + deletePath);
		
		//Ejecutar el request*
		Response deleteRespuesta = api.deleteRequestMethod(deletePath, "");
		
		//Obtener el status code
		int deleteStatusCode = deleteRespuesta.getStatusCode();
		testCase.addCodeEvidenceCurrentStep("statusCode: " + deleteStatusCode);
		assertEquals(deleteStatusCode, deleteExpectedCode); //Validación
		
		
		
	}

	@Override
	public String setTestFullName() {
		return "ApiScriptTestAzure";
	}

	@Override
	public String setTestDescription() {
		return "Ejecucion prueba API";
	}

	@Override
	public String setTestDesigner() {
		return "Roberto Flores";
	}

	@Override
	public String setTestInstanceID() {
		return null;
	}

	@Override
	public String setPrerequisites() {
		return null;
	}

	@Override
	public void beforeTest() {
	}

	

}
