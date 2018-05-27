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

import model.SubmissionModel;

public class SubmissionRequest {
	
	private ObjectMapper mapper;
	
	public SubmissionRequest() {
		mapper = new ObjectMapper();
	}
	
	public List<SubmissionModel> getAllSubmissions(Long studentId, Long assignmentId) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			
			String request = "http://localhost:8080/submission";
			
			if(studentId == null && assignmentId == null) {
				request = "http://localhost:8080/submission";
			}
			else if(studentId == null) {
				request += "?assignmentId=" + assignmentId;
			 }
			 else if(assignmentId == null) {
				 request += "?studentId=" + studentId;
			 }
			 else {
				 request += "?studentId=" + studentId + "&assignmentId=" + assignmentId;
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
			List<SubmissionModel> submissions = mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, SubmissionModel.class));
			
			httpClient.getConnectionManager().shutdown();
			
			return submissions;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
		
	}
	
	public Map<String, Float> getAllGradesForAssignment(Long assignmentId) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
		
			HttpGet getRequest = new HttpGet("http://localhost:8080/submission/assignment/" + assignmentId);
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
			
			Map<String, Float> submissions = mapper.readValue(jsonString, new TypeReference<Map<String, Float>>(){});
			
			httpClient.getConnectionManager().shutdown();
			
			return submissions;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public SubmissionModel saveSubmission(SubmissionModel submission) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/submission");
			
			String jsonInString = mapper.writeValueAsString(submission);
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
			return submission;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	public SubmissionModel updateSubmission(SubmissionModel submission) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			String request = "http://localhost:8080/submission";
			
			HttpPut putRequest = new HttpPut(request);
			
			String jsonInString = mapper.writeValueAsString(submission);
			StringEntity input = new StringEntity(jsonInString);
			
			input.setContentType("application/json");
			putRequest.setEntity(input);
			
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
			//System.out.println(updatedSubmission.getGitLink() + " " + updatedSubmission.getRemark());
	
			httpClient.getConnectionManager().shutdown();
			return updatedSubmission;
		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public SubmissionModel updateGrade(Long submissionId, Long assignmentId, float grade) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			String request = "http://localhost:8080/submission/grade?submissionId=" + submissionId + "&assignmentId=" + assignmentId + "&grade=" + grade;
			
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
	
	public String deleteByStudentAndAssignment(Long studentId, Long assignmentId) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
		
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/submission");
			HttpResponse response = httpClient.execute(deleteRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			httpClient.getConnectionManager().shutdown();
			return "Attendance with student id = " + studentId + " and assignment id = " + assignmentId + " was successfully deleted!";

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
}
