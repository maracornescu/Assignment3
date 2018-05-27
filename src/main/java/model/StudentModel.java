package model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentModel extends User{
	
	private Long studentId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String group;
	private String hobby;
	private String token;
	 
	 public StudentModel() {
		 
	 }
	 
	 public StudentModel(String firstName, String lastName, String email, String password, String group, String hobby, String token) {
		 this.firstName = firstName;
		 this.lastName = lastName;
		 this.email = email;
		 this.password = password;
		 this.group = group;
		 this.hobby = hobby;
		 this.token = token;
	 }
	 
	 public StudentModel(String firstName, String lastName, String email, String group, String hobby, String token) {
		 this.firstName = firstName;
		 this.lastName = lastName;
		 this.email = email;
		 this.group = group;
		 this.hobby = hobby;
		 this.token = token;
	 }
	 
	 public StudentModel(String firstName, String lastName, String email, String group, String hobby) {
		 this.firstName = firstName;
		 this.lastName = lastName;
		 this.email = email;
		 this.group = group;
		 this.hobby = hobby;
	 }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
}
