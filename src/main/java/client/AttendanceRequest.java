package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

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
import org.codehaus.jackson.type.TypeReference;

import model.AttendanceModel;

public class AttendanceRequest {
	
	private ObjectMapper mapper;
	
	public AttendanceRequest() {
		mapper = new ObjectMapper();
	}
	
	public List<AttendanceModel> getAllAttendances(Long studentId, Long laboratoryId) {
		
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			
			String request = "http://localhost:8080/attendance";
			
			if(studentId == null && laboratoryId == null) {
				request = "http://localhost:8080/attendance";
			}
			else if(studentId == null) {
				request += "?laboratoryId=" + laboratoryId;
			}
			else if(laboratoryId == null) {
				request += "?studentId=" + studentId;
			}
			else {
				request += "?studentId=" + studentId + "&laboratoryId=" + laboratoryId;
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
			List<AttendanceModel> attendances = mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, AttendanceModel.class));
			
			httpClient.getConnectionManager().shutdown();
			
			return attendances;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public Map<String, Boolean> getAttendances(Long studentId, Long laboratoryId) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			
			String request = "http://localhost:8080/attendance/map";
			
			if(studentId == null && laboratoryId == null) {
				request = "http://localhost:8080/attendance/map";
			}
			else if(studentId == null) {
				request += "?laboratoryId=" + laboratoryId;
			}
			else if(laboratoryId == null) {
				request += "?studentId=" + studentId;
			}
			else {
				request += "?studentId=" + studentId + "&laboratoryId=" + laboratoryId;
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
			
			Map<String, Boolean> attendances = mapper.readValue(jsonString, new TypeReference<Map<String, Boolean>>(){});
			
			httpClient.getConnectionManager().shutdown();
			
			return attendances;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public AttendanceModel saveAttendance(AttendanceModel attendance) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/attendance");
			
			String jsonInString = mapper.writeValueAsString(attendance);
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
			return attendance;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	public AttendanceModel updateAttendance(AttendanceModel attendance) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			String request = "http://localhost:8080/attendance";
			
			HttpPut putRequest = new HttpPut(request);
			
			String jsonInString = mapper.writeValueAsString(attendance);
			StringEntity input = new StringEntity(jsonInString);
			
			input.setContentType("application/json");
			putRequest.setEntity(input);
			
			HttpResponse response = httpClient.execute(putRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}
			
			httpClient.getConnectionManager().shutdown();
			return attendance;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	public String deleteByStudentAndLaboratory(Long studentId, Long laboratoryId) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
		
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/attendance?studentId=" + studentId + "&laboratoryId=" + laboratoryId);
			HttpResponse response = httpClient.execute(deleteRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			httpClient.getConnectionManager().shutdown();
			return "Attendance with student id = " + studentId + " and laboratory id = " + laboratoryId + " was successfully deleted!";

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
		
}
