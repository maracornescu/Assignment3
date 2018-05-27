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

import model.StudentModel;

public class StudentRequest {
	
	private ObjectMapper mapper;
	
	public StudentRequest() {
		mapper = new ObjectMapper();
	}
	
	public List<StudentModel> getAllStudents() {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			
			HttpGet getRequest = new HttpGet("http://localhost:8080/student");
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
			List<StudentModel> students = mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, StudentModel.class));
			
			httpClient.getConnectionManager().shutdown();
			
			return students;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public StudentModel getStudentById(Long studentId) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			HttpGet getRequest = new HttpGet("http://localhost:8080/student/" + studentId);
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
			
			StudentModel student = mapper.readValue(jsonString, StudentModel.class);
			
			httpClient.getConnectionManager().shutdown();
			
			return student;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public StudentModel saveStudent(StudentModel student) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/student");
			
			String jsonInString = mapper.writeValueAsString(student);
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
			return student;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	public StudentModel updateStudent(Long studentId, StudentModel student) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			String request = "http://localhost:8080/student/" + studentId + "?";
			
			if(student.getFirstName() != null) {
				request += "firstName=" + student.getFirstName();
			}
			if(student.getLastName() != null) {
				request += "&lastName=" + student.getLastName();
			}
			if(student.getEmail() != null) {
				request += "&email=" + student.getEmail();
			}
			if(student.getGroup() != null) {
				request += "&group=" + student.getGroup();
			}
			if(student.getHobby() != null) {
				request += "&hobby=" + student.getHobby();
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
			
			StudentModel updatedStudent = mapper.readValue(answer, StudentModel.class);
			
			httpClient.getConnectionManager().shutdown();
			return updatedStudent;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	public String deleteStudentById(Long studentId) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
		
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/student/" + studentId);
			HttpResponse response = httpClient.execute(deleteRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			httpClient.getConnectionManager().shutdown();
			return "Student with id = " + studentId + " was successfully deleted!";

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
}
