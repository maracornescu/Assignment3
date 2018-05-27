package client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.AssignmentModel;
import model.LaboratoryModel;
import model.StudentModel;
import model.SubmissionModel;

public class ApacheHttpClientGet {

	public static void main(String[] args) throws ParseException {
		
		
		//LaboratoryRequest request = new LaboratoryRequest();
		//LaboratoryModel laboratory = new LaboratoryModel(0, null, "lab1", null, null);
		
		//LaboratoryModel l = request.updateLaboratory((long) 41, laboratory);
		
		//AssignmentRequest assignmentRequest = new AssignmentRequest();
		//AssignmentModel assignmentModel = new AssignmentModel("assignment3", null, "last", (long) 41);
		
		//AssignmentModel a = assignmentRequest.saveAssignment(assignmentModel);
		//System.out.println(a.getAssignmentName());
		
		
		SubmissionRequest submissionRequst = new SubmissionRequest();
		SubmissionModel s  = submissionRequst.updateGrade((long) 40, (long) 52, 9);
		System.out.println(s.getGrade());
		
		//SubmissionModel s = new SubmissionModel((long) 40, (long) 42, "aici2", "remark2");
		//SubmissionModel s1 = submissionRequst.updateSubmission(s);
		
		
		//List<SubmissionModel> submissions = submissionRequst.getAllSubmissions(null, null);
		
		//AssignmentRequest request = new AssignmentRequest();
		//List<AssignmentModel> assignments = request.getAssignmentByLaboratoryId((long) 37);	
		//for (SubmissionModel a : submissions) {
		//System.out.println(a.getRemark());
		//}
		
		/*
		List<StudentModel> students;
	
		students = request.getAllStudents();
		
		for(StudentModel l : students) {
			System.out.println(l.getFirstName() + " " + l.getEmail());
		}*/
		
		/*
		StudentModel student = request.getStudentById((long)36);
		System.out.println(student.getFirstName() + " " + student.getEmail());
		*/
		
		/*
		StudentModel s = new StudentModel("Bogdan", "Carcu", "bogdancrc@yahoo.com", "30431", "anime");
		request.saveStudent(s);
		*/
		
		/*
		StudentModel s = new StudentModel("danel", null, "bogdancrc@gmail.com", null, null);
		StudentModel updated = request.updateStudent((long) 51, s);
		System.out.println(updated.getFirstName() + " " + updated.getLastName() + " " + updated.getEmail());
		*/
		
		/*
		String d = request.deleteStudentById((long) 51);
		System.out.println(d);
		*/
	}
}

