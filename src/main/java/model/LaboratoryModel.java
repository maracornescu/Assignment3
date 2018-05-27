package model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LaboratoryModel {
	
	private Long laboratoryId;
	private int laboratoryNumber;
	private Date date;
	private String title;
	private String curricula;
	private String laboratoryText;
	
	public LaboratoryModel() {
		
	}
	
	public LaboratoryModel(int laboratoryNumber, Date date, String title, String curricula, String laboratoryText) {
		this.setLaboratoryNumber(laboratoryNumber);
		this.date = date;
		this.title = title;
		this.curricula = curricula;
		this.laboratoryText = laboratoryText;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCurricula() {
		return curricula;
	}
	public void setCurricula(String curricula) {
		this.curricula = curricula;
	}
	public String getLaboratoryText() {
		return laboratoryText;
	}
	public void setLaboratoryText(String laboratoryText) {
		this.laboratoryText = laboratoryText;
	}

	public int getLaboratoryNumber() {
		return laboratoryNumber;
	}

	public void setLaboratoryNumber(int laboratoryNumber) {
		this.laboratoryNumber = laboratoryNumber;
	}

	public Long getLaboratoryId() {
		return laboratoryId;
	}

	public void setLaboratoryId(Long laboratoryId) {
		this.laboratoryId = laboratoryId;
	}
	
}
