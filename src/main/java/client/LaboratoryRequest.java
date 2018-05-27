package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import model.LaboratoryModel;

public class LaboratoryRequest {
	
	private ObjectMapper mapper;
	
	public LaboratoryRequest() {
		mapper = new ObjectMapper();
	}
	
	public List<LaboratoryModel> getAllLaboratories(String keyword) {
		
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			
			String request = "http://localhost:8080/laboratory";
			if(keyword != null) {
				request += "?keyword=" + keyword;
			}
			
			HttpGet getRequest = new HttpGet(request);
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			String jsonString = "";
			
			while ((output = br.readLine()) != null) {
				jsonString += output;
			}
			
			TypeFactory typeFactory = mapper.getTypeFactory();
			List<LaboratoryModel> laboratories = mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, LaboratoryModel.class));
			
			httpClient.getConnectionManager().shutdown();
			
			return laboratories;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	
	public LaboratoryModel getAllLaboratoryById(Long laboratoryId) {
		
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			HttpGet getRequest = new HttpGet("http://localhost:8080/laboratory/" + laboratoryId);
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			String jsonString = "";
			
			while ((output = br.readLine()) != null) {
				jsonString += output;
			}
			
			LaboratoryModel laboratory = mapper.readValue(jsonString, LaboratoryModel.class);
			
			httpClient.getConnectionManager().shutdown();
			
			return laboratory;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public LaboratoryModel saveLaboratory(LaboratoryModel laboratory) {
		 try {

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost("http://localhost:8080/laboratory");
				
				String jsonInString = mapper.writeValueAsString(laboratory);
				StringEntity input = new StringEntity(jsonInString);
				
				input.setContentType("application/json");
				postRequest.setEntity(input);

				HttpResponse response = httpClient.execute(postRequest);

				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				}

				BufferedReader br = new BufferedReader(
		                        new InputStreamReader((response.getEntity().getContent())));

				String output;
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

				httpClient.getConnectionManager().shutdown();
				return laboratory;

		 } catch (MalformedURLException e) {
			e.printStackTrace();	
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 return null;

	}
	
	public LaboratoryModel updateLaboratory(Long laboratoryId, LaboratoryModel laboratory) {
		 try {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				
				String request = "http://localhost:8080/laboratory/" + laboratoryId + "?";
				
				if(laboratory.getLaboratoryNumber() != 0) {
					request += "laboratoryNumber=" + laboratory.getLaboratoryNumber();
				}
				if(laboratory.getDate() != null) {
					request += "&date=" + laboratory.getDate();
				}
				if(laboratory.getTitle() != null) {
					request += "&title=" + laboratory.getTitle();
				}
				if(laboratory.getCurricula() != null) {
					request += "&curricula=" + laboratory.getCurricula();
				}
				if(laboratory.getLaboratoryText() != null) {
					request += "&laboratoryText=" + laboratory.getLaboratoryText();
				}
				
				HttpPut putRequest = new HttpPut(request);
				HttpResponse response = httpClient.execute(putRequest);
				
				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				}

				BufferedReader br = new BufferedReader(
		                        new InputStreamReader((response.getEntity().getContent())));

				String output;
				String answer = "";
				
				while ((output = br.readLine()) != null) {
					answer += output;
				}
				
				LaboratoryModel updatedLab = mapper.readValue(answer, LaboratoryModel.class);
				
				httpClient.getConnectionManager().shutdown();
				return updatedLab;

		 } catch (MalformedURLException e) {
			e.printStackTrace();	
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 return null;
		
	}
	
	public String deleteLaboratoryById(Long laboratoryId) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
		
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/laboratory/" + laboratoryId);
			HttpResponse response = httpClient.execute(deleteRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			httpClient.getConnectionManager().shutdown();
			return "Laboratory with id = " + laboratoryId + " was successfully deleted!";

	 } catch (MalformedURLException e) {
		e.printStackTrace();	
	 } catch (IOException e) {
		 e.printStackTrace();
	 }
	 return null;
	}
	
}
