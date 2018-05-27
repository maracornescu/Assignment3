package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.AssignmentRequest;
import client.AttendanceRequest;
import client.LaboratoryRequest;
import client.StudentRequest;
import client.SubmissionRequest;
import model.AssignmentModel;
import model.AttendanceModel;
import model.LaboratoryModel;
import model.StudentModel;
import model.SubmissionModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentView extends JFrame {

	private JPanel contentPane;
	private JTable laboratoryTable;
	private JTable assignmentTable;
	private JTable submissionTable;
	private JTable attendanceTable;
	private JTextField gitTextField;
	private JScrollPane assignmentTableScrollPane, laboratoryTableScrollPane, submissionTableScrollPane, attendanceTableScrollPane;
	private JTextField keywordTextField;
	JTextPane remarkTextPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentRequest studentRequest = new StudentRequest();
					StudentModel s = studentRequest.getStudentById((long) 40);
					//System.out.println(student.getStudentId());
					StudentView frame = new StudentView(s);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentView(final StudentModel student) {
		setTitle("Student");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1071, 903);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		laboratoryTableScrollPane = new JScrollPane();
		laboratoryTableScrollPane.setBounds(22, 65, 942, 146);
		contentPane.add(laboratoryTableScrollPane);
		
		//create laboratory table
		LaboratoryRequest laboratoryRequst = new LaboratoryRequest();
		List<LaboratoryModel> laboratories = laboratoryRequst.getAllLaboratories(null);
		laboratoryTable = Table.createTable(laboratories);
		laboratoryTable.removeColumn(laboratoryTable.getColumnModel().getColumn(0));
		laboratoryTableScrollPane.setViewportView(laboratoryTable);
		
		assignmentTableScrollPane = new JScrollPane();
		assignmentTableScrollPane.setBounds(22, 271, 942, 146);
		contentPane.add(assignmentTableScrollPane);
		
		//create assignment table
		AssignmentRequest assignmentRequst = new AssignmentRequest();
		List<AssignmentModel> assignments = assignmentRequst.getAllAssignments();
		assignmentTable = Table.createTable(assignments);
		assignmentTable.removeColumn(assignmentTable.getColumnModel().getColumn(0));
		assignmentTable.removeColumn(assignmentTable.getColumnModel().getColumn(3));
		assignmentTableScrollPane.setViewportView(assignmentTable);
		
		
		submissionTableScrollPane = new JScrollPane();
		submissionTableScrollPane.setBounds(22, 503, 942, 146);
		contentPane.add(submissionTableScrollPane);
		
		//create submission table
		//SubmissionRequest submissionRequst = new SubmissionRequest();
		//List<SubmissionModel> submissions = submissionRequst.getAllSubmissions(student.getStudentId(), null);
		//submissionTable = Table.createTable(submissions);
		//submissionTable.removeColumn(submissionTable.getColumnModel().getColumn(0));
		//submissionTable.removeColumn(submissionTable.getColumnModel().getColumn(0));
		//submissionTableScrollPane.setViewportView(submissionTable);
		
		attendanceTableScrollPane = new JScrollPane();
		attendanceTableScrollPane.setBounds(22, 697, 942, 146);
		contentPane.add(attendanceTableScrollPane);
		
		//AttendanceRequest attendanceRequest = new AttendanceRequest();
		//List<AttendanceModel> attendances = attendanceRequest.getAllAttendances(student.getStudentId(), null);
		//if(attendances.size() != 0) {
		//attendanceTable = Table.createTable(attendances);
		//}
		//else {
		//	attendanceTable = new JTable();
		//}
		//attendanceTableScrollPane.setViewportView(attendanceTable);
		//attendanceTable = new JTable();
		//attendanceTableScrollPane.setViewportView(attendanceTable);
		
		gitTextField = new JTextField();
		gitTextField.setBounds(81, 430, 341, 22);
		contentPane.add(gitTextField);
		gitTextField.setColumns(10);
		
		JLabel lblGitLink = new JLabel("Git Link");
		lblGitLink.setBounds(22, 433, 56, 16);
		contentPane.add(lblGitLink);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setBounds(448, 433, 56, 16);
		contentPane.add(lblRemark);
		
		JScrollPane remarkScrollPane = new JScrollPane();
		remarkScrollPane.setBounds(516, 431, 448, 33);
		contentPane.add(remarkScrollPane);
		
		remarkTextPane = new JTextPane();
		remarkScrollPane.setViewportView(remarkTextPane);
		
		JButton btnAddSubmission = new JButton("Add Submission");
		btnAddSubmission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SubmissionRequest request = new SubmissionRequest();
				
					Long studentId = student.getStudentId();
					System.out.println(studentId);
					Long assignmentId = (Long) assignmentTable.getModel().getValueAt(assignmentTable.getSelectedRow(), 0);
					System.out.println(assignmentId);
					String gitLink = gitTextField.getText();
					System.out.println(gitLink);
					String remark = remarkTextPane.getText();
					System.out.println(remark);
					
					SubmissionModel submission = new SubmissionModel(studentId, assignmentId, gitLink, remark);
					
					SubmissionModel s = request.saveSubmission(submission);
					
					List<SubmissionModel> submissions = request.getAllSubmissions(studentId, null);
					submissionTable = Table.createTable(submissions);
					submissionTable.removeColumn(submissionTable.getColumnModel().getColumn(0));
					submissionTable.removeColumn(submissionTable.getColumnModel().getColumn(0));
					submissionTableScrollPane.setViewportView(submissionTable);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnAddSubmission.setBounds(32, 465, 154, 25);
		contentPane.add(btnAddSubmission);
		
		JButton btnUpdateSubmission = new JButton("Update Submission");
		btnUpdateSubmission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String gitLink;
				String remark;
				
				try {
					SubmissionRequest request = new SubmissionRequest();
				
					Long studentId = student.getStudentId();
					Long assignmentId = (Long) submissionTable.getModel().getValueAt(submissionTable.getSelectedRow(), 1);
					
					
					SubmissionModel oldSubmission = request.getAllSubmissions(studentId, assignmentId).get(0);
					
					if(gitTextField.getText() != null) {
						gitLink = gitTextField.getText();
					}
					else {
						gitLink = oldSubmission.getGitLink();
					}
					
					if(remarkTextPane.getText() != null) {
						remark = remarkTextPane.getText();;
					}
					else {
						remark = oldSubmission.getRemark();
					}
					
					SubmissionModel submission = new SubmissionModel(studentId, assignmentId, gitLink, remark);
					
					request.updateSubmission(submission);
					
					List<SubmissionModel> submissions = request.getAllSubmissions(studentId, null);
					submissionTable = Table.createTable(submissions);
					submissionTable.removeColumn(submissionTable.getColumnModel().getColumn(0));
					submissionTable.removeColumn(submissionTable.getColumnModel().getColumn(0));
					submissionTableScrollPane.setViewportView(submissionTable);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Number of possible submissions exceeded!");
				}
				
			}
		});
		btnUpdateSubmission.setBounds(198, 465, 176, 25);
		contentPane.add(btnUpdateSubmission);
		
		JButton btnShowAssignments = new JButton("Show Laboratory Assignments");
		btnShowAssignments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Long laboratoryId = (Long) laboratoryTable.getModel().getValueAt(laboratoryTable.getSelectedRow(), 0);
					AssignmentRequest assignmentRequst = new AssignmentRequest();
					List<AssignmentModel> assignments = assignmentRequst.getAssignmentByLaboratoryId(laboratoryId);
					assignmentTable = Table.createTable(assignments);
					assignmentTable.removeColumn(assignmentTable.getColumnModel().getColumn(0));
					assignmentTable.removeColumn(assignmentTable.getColumnModel().getColumn(3));
					assignmentTableScrollPane.setViewportView(assignmentTable);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnShowAssignments.setBounds(22, 230, 261, 25);
		contentPane.add(btnShowAssignments);
		
		JButton btnShowAllAssignments = new JButton("Show All Assignments");
		btnShowAllAssignments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AssignmentRequest assignmentRequst = new AssignmentRequest();
					List<AssignmentModel> assignments = assignmentRequst.getAllAssignments();
					assignmentTable = Table.createTable(assignments);
					assignmentTable.removeColumn(assignmentTable.getColumnModel().getColumn(0));
					assignmentTable.removeColumn(assignmentTable.getColumnModel().getColumn(3));
					assignmentTableScrollPane.setViewportView(assignmentTable);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments!");
				}
			}
		});
		btnShowAllAssignments.setBounds(354, 230, 220, 25);
		contentPane.add(btnShowAllAssignments);
		
		JLabel lblKeyword = new JLabel("keyword");
		lblKeyword.setBounds(32, 33, 56, 16);
		contentPane.add(lblKeyword);
		
		keywordTextField = new JTextField();
		keywordTextField.setBounds(91, 30, 116, 22);
		contentPane.add(keywordTextField);
		keywordTextField.setColumns(10);
		
		JButton btnFindLaboratory = new JButton("Find Laboratory");
		btnFindLaboratory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String keyword = keywordTextField.getText();
					LaboratoryRequest laboratoryRequst = new LaboratoryRequest();
					List<LaboratoryModel> laboratories = laboratoryRequst.getAllLaboratories(keyword);
					laboratoryTable = Table.createTable(laboratories);
					laboratoryTable.removeColumn(laboratoryTable.getColumnModel().getColumn(0));
					laboratoryTableScrollPane.setViewportView(laboratoryTable);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no laboratories!");
				}
			}
		});
		btnFindLaboratory.setBounds(224, 29, 132, 25);
		contentPane.add(btnFindLaboratory);
		
		JButton btnShowAllLaboratories = new JButton("Show All Laboratories");
		btnShowAllLaboratories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LaboratoryRequest laboratoryRequst = new LaboratoryRequest();
					List<LaboratoryModel> laboratories = laboratoryRequst.getAllLaboratories(null);
					laboratoryTable = Table.createTable(laboratories);
					laboratoryTable.removeColumn(laboratoryTable.getColumnModel().getColumn(0));
					laboratoryTableScrollPane.setViewportView(laboratoryTable);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no laboratories!");
				}
			}
		});
		btnShowAllLaboratories.setBounds(390, 29, 184, 25);
		contentPane.add(btnShowAllLaboratories);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//create submission table
					SubmissionRequest submissionRequst = new SubmissionRequest();
					List<SubmissionModel> submissions = submissionRequst.getAllSubmissions(student.getStudentId(), null);
					submissionTable = Table.createTable(submissions);
					submissionTable.removeColumn(submissionTable.getColumnModel().getColumn(0));
					submissionTable.removeColumn(submissionTable.getColumnModel().getColumn(0));
					submissionTableScrollPane.setViewportView(submissionTable);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no laboratories!");
				}
			}
		});
		btnShowAll.setBounds(387, 465, 117, 25);
		contentPane.add(btnShowAll);
		
		JButton btnShowAllAttendances = new JButton("Show All Attendances");
		btnShowAllAttendances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AttendanceRequest attendanceRequest = new AttendanceRequest();
					List<AttendanceModel> attendances = attendanceRequest.getAllAttendances(student.getStudentId(), null);
					
					attendanceTable = Table.createTable(attendances);
					attendanceTableScrollPane.setViewportView(attendanceTable);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "No attendances!");
				}
			}
		});
		btnShowAllAttendances.setBounds(22, 662, 168, 25);
		contentPane.add(btnShowAllAttendances);
	}
}
