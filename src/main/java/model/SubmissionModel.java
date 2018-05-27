package model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubmissionModel {
	
	private Long studentId;
	private Long assignmentId;
	private float grade;
	private String gitLink;
	private String remark;
	private int numberOfSubmissions;
	
	public SubmissionModel() {
		
	}
	
	public SubmissionModel(Long studentId, Long assignmentId, float grade, String gitLink, String remark) {
		this.studentId = studentId;
		this.assignmentId = assignmentId;
		this.grade = grade;
		this.gitLink = gitLink;
		this.remark = remark;
	}
	
	public SubmissionModel(Long studentId, Long assignmentId, String gitLink, String remark) {
		this.studentId = studentId;
		this.assignmentId = assignmentId;
		this.gitLink = gitLink;
		this.remark = remark;
	}
	
	public float getGrade() {
		return grade;
	}
	
	public void setGrade(float grade) {
		this.grade = grade;
	}
	
	public String getGitLink() {
		return gitLink;
	}
	
	public void setGitLink(String gitLink) {
		this.gitLink = gitLink;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}

	public int getNumberOfSubmissions() {
		return numberOfSubmissions;
	}

	public void setNumberOfSubmissions(int numberOfSubmissions) {
		this.numberOfSubmissions = numberOfSubmissions;
	}
	
}
