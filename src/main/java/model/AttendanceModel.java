package model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttendanceModel {

	private Long studentId;
	private Long laboratoryId;
	private boolean isPresent;
	
	public AttendanceModel() {
		
	}
	
	public AttendanceModel(Long studentId, Long laboratoryId, boolean isPresent) {
		this.studentId = studentId;
		this.laboratoryId = laboratoryId;
		this.isPresent = isPresent;
	}
	

	public boolean getPresent() {
		return isPresent;
	}
	
	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getLaboratoryId() {
		return laboratoryId;
	}

	public void setLaboratoryId(Long laboratoryId) {
		this.laboratoryId = laboratoryId;
	}
}
