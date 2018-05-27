package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.StudentModel;
import model.SubmissionModel;
import model.TeacherModel;
import model.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

public class LoginRequest {
	
	public User login(String email, String password) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			String request = "http://localhost:8080/login?email=" + email + "&password=" + password;
			
			HttpGet getRequest = new HttpGet(request);
			
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			String answer = "";

			while ((output = br.readLine()) != null) {
				answer += output;
			}
			
			ObjectMapper mapper = new ObjectMapper();
			TeacherModel teacher;
			StudentModel student;
						
			
			if(answer.contains("group")) {
				student = mapper.readValue(answer, StudentModel.class);
				return student;
			}
			else {
				teacher = mapper.readValue(answer, TeacherModel.class);
				return teacher;
			}
			
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}

}
