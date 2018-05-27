package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import model.SubmissionModel;

public class GradeSubmissionRequest {

	private ObjectMapper mapper;
	
	public GradeSubmissionRequest() {
		mapper = new ObjectMapper();
	}
	
	public SubmissionModel updateGrade(Long submissionId, Long assignmentId, float grade) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			String request = "http://localhost:8080/grade";
			
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
	
			SubmissionModel updatedSubmission = mapper.readValue(answer, SubmissionModel.class);
	
			httpClient.getConnectionManager().shutdown();
			return updatedSubmission;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
}
