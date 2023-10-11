package test;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import modelo.BaseExecution;


public class ApiScriptTestApache extends BaseExecution{
	
	@Test(dataProvider = "data-provider", enabled = true)
	public void AppiumScriptTest_test(HashMap<String, String> data) throws Exception {
		
		String baseURL = "https://reqres.in";
		
		/*************************************** PASO 1 GET ***************************************/
		addStep("ejecutar petición get");
		
			//se obtienen los datos del dataprovider
			String getPath = data.get("getPath");
			int getExpectedCode = Integer.parseInt(data.get("getExpectedCode"));
			
			//Se envía como evidencia el request a ejecutar
			testCase.addCodeEvidenceCurrentStep("request:" + baseURL + getPath);
			
			//Ejecutar el request
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(baseURL + getPath);
			//httpget.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
			HttpResponse httpresponse = httpclient.execute(httpget);
			
			//Obtener el status code
			int getStatusCode = httpresponse.getStatusLine().getStatusCode();
			testCase.addCodeEvidenceCurrentStep("statusCode: " + getStatusCode);
			assertEquals(getStatusCode, getExpectedCode); //Validación
			
			//Obtener body de respuesta
			String getBodyResponse = EntityUtils.toString(httpresponse.getEntity()); 
			testCase.addCodeEvidenceCurrentStep("bodyResponse:" + getBodyResponse);
			
			//Validación de json
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
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(baseURL + postPath);
			
			StringEntity requestEntity = new StringEntity(
					postBody,
				    ContentType.APPLICATION_JSON);
			
			httpPost.setEntity(requestEntity);
			
			httpresponse = httpclient.execute(httpPost);

			//Obtener el status code
			int postStatusCode = httpresponse.getStatusLine().getStatusCode();
			testCase.addCodeEvidenceCurrentStep("statusCode: " + postStatusCode);
			assertEquals(postStatusCode, postExpectedCode); //Validación
			
			//Obtener body de respuesta
			String postBodyResponse = EntityUtils.toString(httpresponse.getEntity()); 
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
			httpclient = HttpClients.createDefault();
			HttpPut httpPut = new HttpPut(baseURL + putPath);
			
			requestEntity = new StringEntity(
					putBody,
				    ContentType.APPLICATION_JSON);
			
			httpPut.setEntity(requestEntity);
			
			httpresponse = httpclient.execute(httpPut);
	
			//Obtener el status code
			int putStatusCode = httpresponse.getStatusLine().getStatusCode();
			testCase.addCodeEvidenceCurrentStep("statusCode: " + putStatusCode);
			assertEquals(putStatusCode, putExpectedCode); //Validación
			
			//Obtener body de respuesta
			String putBodyResponse = EntityUtils.toString(httpresponse.getEntity()); 
			testCase.addCodeEvidenceCurrentStep("bodyResponse:" + putBodyResponse);
			
			//Validación de json
			JSONObject putjson = new JSONObject(putBodyResponse);
			String nameput = putjson.getString("name");
			testCase.addTextEvidenceCurrentStep("name:" + nameput);
			assertEquals(nameput, "morpheus2");
			
	}

	@Override
	public String setTestFullName() {
		return "ApiScriptTestApache";
	}

	@Override
	public String setTestDescription() {
		return "Ejecucion api HttpClient";
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
		return "Prerequisitos";
	}

	@Override
	public void beforeTest() {
// TODO Auto-generated method stub
	}

	

}
