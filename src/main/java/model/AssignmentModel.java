package model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignmentModel {
	
	private Long assignmentId;
	private String assignmentName;
	private Date deadline;
	private String description;
	private Long laboratory;
	 
	 public AssignmentModel() {
		 
	 }
	 
	 public AssignmentModel(String assignmentName, Date deadline, String description, Long laboratory) {
		 this.assignmentName = assignmentName;
		 this.deadline = deadline;
		 this.description = description;
		 this.laboratory = laboratory;
	 }

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getLaboratoryId() {
		return laboratory;
	}

	public void setLaboratoryId(Long laboratory) {
		this.laboratory = laboratory;
	}

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}
	
}
