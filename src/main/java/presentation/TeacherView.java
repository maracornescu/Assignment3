package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class TeacherView extends JFrame {

	private JPanel contentPane;
	private JTextField emailTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField groupTextField;
	private JTextField hobbyTextField;
	private JTextField laboratoryNumberTextField;
	private JTextField dateTextField;
	private JTextField titleTextField;
	private JTextField keywordTextField;
	private JTextField assignmentNameTextField;
	private JTextField deadlineTextField;
	private JTable laboratoryTable;
	private JTable assignmentTable;
	private JTable tableLaboratory;
	private JTable tableStudents;
	private JTable attendanceStudentsTable;
	private JTable attendanceLaboratoryTable;
	private JTable attendanceTable;
	private JTextField gradeTextField;
	private JScrollPane studentsTableScrollPane, laboratoryTableScrollPane, laboratoryTablescrollPane, assignmentTableScrollPane;
	private JScrollPane attendanceStudentsTableScrollPane, attendanceLaboratoryTableScrollPane, attendanceTableScrollPane;
	private JScrollPane submissionScrollPane, submissionStudentScrollPane, submissionAssignmentScrollPane;
	private JTable submissionStudentTable;
	private JTable submissionAssignmentTable;
	private JTable submissionTable;
	private JTextPane curriculaTextPane, laboratoryTextPane, descriptionTextPane;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherView frame = new TeacherView();
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
	public TeacherView() {
		setTitle("Teacher");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(12, 13, 998, 503);
		contentPane.add(tabbedPane);
		
		JPanel studentPanel = new JPanel();
		tabbedPane.addTab("Students", null, studentPanel, null);
		studentPanel.setLayout(null);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StudentRequest request = new StudentRequest();
					
					String firstName = firstNameTextField.getText();
					String lastName = lastNameTextField.getText();
					String email = emailTextField.getText();
					String group = groupTextField.getText();
					String hobby = hobbyTextField.getText();
					
					StudentModel student = new StudentModel(firstName, lastName, email, group, hobby);
					request.saveStudent(student);
					studentsTable();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
				
			}
		});
		btnAddStudent.setBounds(189, 61, 103, 25);
		studentPanel.add(btnAddStudent);
		
		JButton btnUpdateStudent = new JButton("Update Student");
		btnUpdateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = null;
				String lastName = null;
				String email = null;
				String group = null;
				String hobby = null;
				try {
					StudentRequest request = new StudentRequest();
					
					if(!firstNameTextField.getText().isEmpty()) {
						firstName = firstNameTextField.getText();
					}
					
					if(!lastNameTextField.getText().isEmpty()) {
						lastName = lastNameTextField.getText();
					}
					
					if(!emailTextField.getText().isEmpty()) {
						email = emailTextField.getText();
					}
				
					if(!groupTextField.getText().isEmpty()) {
						group = groupTextField.getText();
					}
					
					if(!hobbyTextField.getText().isEmpty()) {
						hobby = hobbyTextField.getText();
					}
					
					StudentModel student = new StudentModel(firstName, lastName, email, group, hobby);
					request.updateStudent((Long) tableStudents.getModel().getValueAt(tableStudents.getSelectedRow(), 0), student);
					studentsTable();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnUpdateStudent.setBounds(325, 61, 121, 25);
		studentPanel.add(btnUpdateStudent);
		
		JButton btnDeleteStudent = new JButton("Delete Student");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StudentRequest request = new StudentRequest();
			
					request.deleteStudentById((Long) tableStudents.getModel().getValueAt(tableStudents.getSelectedRow(), 0));
					studentsTable();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnDeleteStudent.setBounds(479, 61, 121, 25);
		studentPanel.add(btnDeleteStudent);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(12, 13, 56, 16);
		studentPanel.add(lblEmail);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(49, 10, 141, 22);
		studentPanel.add(emailTextField);
		emailTextField.setColumns(10);
		
		JLabel lblFirstName = new JLabel("first name");
		lblFirstName.setBounds(202, 13, 78, 16);
		studentPanel.add(lblFirstName);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(265, 10, 134, 22);
		studentPanel.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		JLabel lblLastName = new JLabel("last name");
		lblLastName.setBounds(411, 13, 56, 16);
		studentPanel.add(lblLastName);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(479, 10, 134, 22);
		studentPanel.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JLabel lblGroup = new JLabel("group");
		lblGroup.setBounds(625, 13, 56, 16);
		studentPanel.add(lblGroup);
		
		groupTextField = new JTextField();
		groupTextField.setBounds(668, 10, 108, 22);
		studentPanel.add(groupTextField);
		groupTextField.setColumns(10);
		
		JLabel lblHobby = new JLabel("hobby");
		lblHobby.setBounds(788, 13, 56, 16);
		studentPanel.add(lblHobby);
		
		hobbyTextField = new JTextField();
		hobbyTextField.setBounds(829, 10, 116, 22);
		studentPanel.add(hobbyTextField);
		hobbyTextField.setColumns(10);
		
		JButton btnShowAllStudents = new JButton("Show All Students");
		btnShowAllStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					studentsTable();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnShowAllStudents.setBounds(630, 61, 146, 25);
		studentPanel.add(btnShowAllStudents);
		
		studentsTableScrollPane = new JScrollPane();
		studentsTableScrollPane.setBounds(12, 118, 957, 326);
		studentPanel.add(studentsTableScrollPane);
		
		studentsTable();
		//tableStudents = new JTable();
		//studentsTableScrollPane.setViewportView(tableStudents);
		
		JPanel laboratoryPanel = new JPanel();
		tabbedPane.addTab("Laboratories", null, laboratoryPanel, null);
		laboratoryPanel.setLayout(null);
		
		JLabel lblLaboratoryNumber = new JLabel("Laboratory Number");
		lblLaboratoryNumber.setBounds(133, 13, 110, 16);
		laboratoryPanel.add(lblLaboratoryNumber);
		
		laboratoryNumberTextField = new JTextField();
		laboratoryNumberTextField.setBounds(251, 10, 116, 22);
		laboratoryPanel.add(laboratoryNumberTextField);
		laboratoryNumberTextField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(418, 13, 56, 16);
		laboratoryPanel.add(lblDate);
		
		dateTextField = new JTextField();
		dateTextField.setBounds(474, 10, 116, 22);
		laboratoryPanel.add(dateTextField);
		dateTextField.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(674, 13, 56, 16);
		laboratoryPanel.add(lblTitle);
		
		titleTextField = new JTextField();
		titleTextField.setBounds(717, 10, 116, 22);
		laboratoryPanel.add(titleTextField);
		titleTextField.setColumns(10);
		
		JLabel lblCurricula = new JLabel("Curricula");
		lblCurricula.setBounds(133, 51, 56, 16);
		laboratoryPanel.add(lblCurricula);
		
		JLabel lblLaboratoryText = new JLabel("Laboratory Text");
		lblLaboratoryText.setBounds(493, 51, 101, 16);
		laboratoryPanel.add(lblLaboratoryText);
		
		JButton btnAddLaboratory = new JButton("Add Laboratory");
		btnAddLaboratory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LaboratoryRequest request = new LaboratoryRequest();
					
					int laboratoryNumber = Integer.parseInt(laboratoryNumberTextField.getText());
					DateFormat formatter;
					formatter = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
					Date date = formatter.parse(dateTextField.getText());
					
					String title = titleTextField.getText();
					String curricula = curriculaTextPane.getText();
					String laboratoryText = laboratoryTextPane.getText(); 
					
					LaboratoryModel laboratory = new LaboratoryModel(laboratoryNumber, date, title, curricula, laboratoryText);
					
					request.saveLaboratory(laboratory);
					
					laboratoryTableLaboratories();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnAddLaboratory.setBounds(251, 96, 137, 25);
		laboratoryPanel.add(btnAddLaboratory);
		
		JButton btnUpdateLaboratory = new JButton("Update Laboratory");
		btnUpdateLaboratory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LaboratoryRequest request = new LaboratoryRequest();
		
					Long laboratoryId = (Long) tableLaboratory.getModel().getValueAt(tableLaboratory.getSelectedRow(), 0);
					
					int laboratoryNumber = 0;
					DateFormat formatter;
					formatter = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
					Date date = null;
					
					String title = null;
					String curricula = null;
					String laboratoryText = null; 
					 
					if(!laboratoryNumberTextField.getText().isEmpty()) {
						laboratoryNumber = Integer.parseInt(laboratoryNumberTextField.getText());
					}
					
					if(!dateTextField.getText().isEmpty()) {
						date = formatter.parse(dateTextField.getText());
					}
				
					if(!titleTextField.getText().isEmpty()) {
						title = titleTextField.getText();
					}
					
					if(!curriculaTextPane.getText().isEmpty()) {
						curricula = curriculaTextPane.getText();
					}
					
					if(laboratoryTextPane.getText().isEmpty()) {
						laboratoryText = laboratoryTextPane.getText();
					}
					
					LaboratoryModel laboratory = new LaboratoryModel(laboratoryNumber, date, title, curricula, laboratoryText);
					request.updateLaboratory(laboratoryId, laboratory);
					
					laboratoryTableLaboratories();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnUpdateLaboratory.setBounds(407, 96, 152, 25);
		laboratoryPanel.add(btnUpdateLaboratory);
		
		JButton btnDeleteLaboratory = new JButton("Delete Laboratory");
		btnDeleteLaboratory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LaboratoryRequest request = new LaboratoryRequest();
					
					request.deleteLaboratoryById((Long) tableLaboratory.getModel().getValueAt(tableLaboratory.getSelectedRow(), 0));
					
					laboratoryTableLaboratories();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnDeleteLaboratory.setBounds(578, 96, 152, 25);
		laboratoryPanel.add(btnDeleteLaboratory);
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LaboratoryRequest request = new LaboratoryRequest();
				
					List<LaboratoryModel> laboratories = request.getAllLaboratories(keywordTextField.getText());
					tableLaboratory = Table.createTable(laboratories);
					tableLaboratory.removeColumn(tableLaboratory.getColumnModel().getColumn(0));
					laboratoryTableScrollPane.setViewportView(tableLaboratory);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnFilter.setBounds(621, 133, 109, 25);
		laboratoryPanel.add(btnFilter);
		
		keywordTextField = new JTextField();
		keywordTextField.setBounds(493, 134, 116, 22);
		laboratoryPanel.add(keywordTextField);
		keywordTextField.setColumns(10);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					laboratoryTableLaboratories();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnShowAll.setBounds(251, 133, 137, 25);
		laboratoryPanel.add(btnShowAll);
		
		JLabel lblKeyword = new JLabel("keyword");
		lblKeyword.setBounds(418, 137, 56, 16);
		laboratoryPanel.add(lblKeyword);
		
		laboratoryTableScrollPane = new JScrollPane();
		laboratoryTableScrollPane.setBounds(12, 212, 957, 231);
		laboratoryPanel.add(laboratoryTableScrollPane);
		
		laboratoryTableLaboratories();
		
		JScrollPane curriculaScrollPane = new JScrollPane();
		curriculaScrollPane.setBounds(192, 38, 282, 45);
		laboratoryPanel.add(curriculaScrollPane);
		
		curriculaTextPane = new JTextPane();
		curriculaScrollPane.setViewportView(curriculaTextPane);
		
		JScrollPane laboratoryTextScrollPane = new JScrollPane();
		laboratoryTextScrollPane.setBounds(594, 38, 282, 45);
		laboratoryPanel.add(laboratoryTextScrollPane);
		
		laboratoryTextPane = new JTextPane();
		laboratoryTextScrollPane.setViewportView(laboratoryTextPane);
		
		JPanel assignmentPanel = new JPanel();
		tabbedPane.addTab("Assignments", null, assignmentPanel, null);
		assignmentPanel.setLayout(null);
		
		JLabel lblAssignmentName = new JLabel("Name");
		lblAssignmentName.setBounds(29, 13, 56, 16);
		assignmentPanel.add(lblAssignmentName);
		
		assignmentNameTextField = new JTextField();
		assignmentNameTextField.setBounds(79, 10, 158, 22);
		assignmentPanel.add(assignmentNameTextField);
		assignmentNameTextField.setColumns(10);
		
		JLabel lblAssignmentDeadline = new JLabel("Deadline");
		lblAssignmentDeadline.setBounds(304, 13, 68, 16);
		assignmentPanel.add(lblAssignmentDeadline);
		
		deadlineTextField = new JTextField();
		deadlineTextField.setBounds(366, 10, 142, 22);
		assignmentPanel.add(deadlineTextField);
		deadlineTextField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(551, 13, 85, 16);
		assignmentPanel.add(lblDescription);
		
		laboratoryTablescrollPane = new JScrollPane();
		laboratoryTablescrollPane.setBounds(28, 65, 891, 146);
		assignmentPanel.add(laboratoryTablescrollPane);
		
		laboratoryTableAssignments();
		
		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(634, 7, 282, 45);
		assignmentPanel.add(descriptionScrollPane);
		
		descriptionTextPane = new JTextPane();
		descriptionScrollPane.setViewportView(descriptionTextPane);
		
		JButton btnAddAssignment = new JButton("Add Assignment");
		btnAddAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AssignmentRequest request = new AssignmentRequest();
				
					String assignmentName = assignmentNameTextField.getText();
					
					DateFormat formatter;
					formatter = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
					Date deadline = formatter.parse(deadlineTextField.getText());
					
					String description = descriptionTextPane.getText();
					
					Long laboratory = (Long) laboratoryTable.getModel().getValueAt(laboratoryTable.getSelectedRow(), 0);
					
					AssignmentModel assignment = new AssignmentModel(assignmentName, deadline, description, laboratory);
					
					request.saveAssignment(assignment);
					assignmentTable();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnAddAssignment.setBounds(29, 224, 142, 25);
		assignmentPanel.add(btnAddAssignment);
		
		JButton btnUpdateAssignment = new JButton("Update Assignment");
		btnUpdateAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String assignmentName = null;
				Date deadline = null;
				String description = null;
				try {
					AssignmentRequest request = new AssignmentRequest();
	
					DateFormat formatter;
					formatter = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
				
					Long laboratory = (Long) assignmentTable.getModel().getValueAt(assignmentTable.getSelectedRow(), 4);
					Long assignmentId = (Long) assignmentTable.getModel().getValueAt(assignmentTable.getSelectedRow(), 0);
					
					if(!assignmentNameTextField.getText().isEmpty()) {
						assignmentName = assignmentNameTextField.getText();
					}
					if(!deadlineTextField.getText().isEmpty()) {
						deadline = formatter.parse(deadlineTextField.getText());
					}
					if(!descriptionTextPane.getText().isEmpty()) {
						description = descriptionTextPane.getText();
					}
					
					AssignmentModel assignment = new AssignmentModel(assignmentName, deadline, description, laboratory);
					
					request.updateAssignment(assignmentId, assignment);
					assignmentTable();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
				
			}
		});
		btnUpdateAssignment.setBounds(189, 224, 142, 25);
		assignmentPanel.add(btnUpdateAssignment);
		
		JButton btnDeteleAssignment = new JButton("Detele Assignment");
		btnDeteleAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AssignmentRequest request = new AssignmentRequest();
					Long assignmentId = (Long) assignmentTable.getModel().getValueAt(assignmentTable.getSelectedRow(), 0);
			
					request.deleteLaboratoryById(assignmentId);
					assignmentTable();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnDeteleAssignment.setBounds(343, 224, 148, 25);
		assignmentPanel.add(btnDeteleAssignment);
		
		JButton btnShowAllAssignments = new JButton("Show All Assignments");
		btnShowAllAssignments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					assignmentTable();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnShowAllAssignments.setBounds(500, 224, 189, 25);
		assignmentPanel.add(btnShowAllAssignments);
		
		assignmentTableScrollPane = new JScrollPane();
		assignmentTableScrollPane.setBounds(28, 274, 891, 175);
		assignmentPanel.add(assignmentTableScrollPane);
		
		JButton btnShowAssignmentsFor = new JButton("Show Assignments for Laboratory");
		btnShowAssignmentsFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					AssignmentRequest assignmentRequest = new AssignmentRequest();
					Long laboratoryId = (Long) (Long) laboratoryTable.getModel().getValueAt(laboratoryTable.getSelectedRow(), 0);
					List<AssignmentModel> assignments = assignmentRequest.getAssignmentByLaboratoryId(laboratoryId);
					assignmentTable = Table.createTable(assignments);
					assignmentTable.removeColumn(assignmentTable.getColumnModel().getColumn(0));
					assignmentTable.removeColumn(assignmentTable.getColumnModel().getColumn(3));
					assignmentTableScrollPane.setViewportView(assignmentTable);
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
				
			}
		});
		btnShowAssignmentsFor.setBounds(701, 224, 236, 25);
		assignmentPanel.add(btnShowAssignmentsFor);
		
		assignmentTable();
		
		JPanel attendancePanel = new JPanel();
		tabbedPane.addTab("Attendance", null, attendancePanel, null);
		attendancePanel.setLayout(null);
		
		attendanceStudentsTableScrollPane = new JScrollPane();
		attendanceStudentsTableScrollPane.setBounds(41, 13, 891, 108);
		attendancePanel.add(attendanceStudentsTableScrollPane);
		
		studentsTableAttendance();
		//attendanceStudentsTable = new JTable();
		//attendanceStudentsTableScrollPane.setViewportView(attendanceStudentsTable);
		
		attendanceLaboratoryTableScrollPane = new JScrollPane();
		attendanceLaboratoryTableScrollPane.setBounds(41, 151, 891, 108);
		attendancePanel.add(attendanceLaboratoryTableScrollPane);
		
		laboratoryTableAttendance();
		
		//attendanceLaboratoryTable = new JTable();
		//attendanceLaboratoryTableScrollPane.setViewportView(attendanceLaboratoryTable);
		
		attendanceTableScrollPane = new JScrollPane();
		attendanceTableScrollPane.setBounds(41, 346, 891, 114);
		attendancePanel.add(attendanceTableScrollPane);
		
		//attendanceTable();
		//attendanceTable = new JTable();
		//attendanceTableScrollPane.setViewportView(attendanceTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 298, 180, 35);
		attendancePanel.add(scrollPane);
		
		comboBox = new JComboBox();
		scrollPane.setViewportView(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Present", "Absent"}));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String value = comboBox.getSelectedItem().toString();
					
					Long studentId = (Long) attendanceStudentsTable.getModel().getValueAt(attendanceStudentsTable.getSelectedRow(), 0);
					Long laboratoryId = (Long) attendanceLaboratoryTable.getModel().getValueAt(attendanceLaboratoryTable.getSelectedRow(), 0);
					boolean isPresent;
					
					if(value.equals("Present")) {
						isPresent = true;
					}
					else {
						isPresent = false;
					}
					
					AttendanceRequest request = new AttendanceRequest();
					AttendanceModel attendanceModel = new AttendanceModel(studentId, laboratoryId, isPresent);
					
					request.saveAttendance(attendanceModel);
					
					attendanceTable(laboratoryId);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnAdd.setBounds(253, 298, 97, 25);
		attendancePanel.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String value = comboBox.getSelectedItem().toString();
					
					Long studentId = (Long) attendanceStudentsTable.getModel().getValueAt(attendanceStudentsTable.getSelectedRow(), 0);
					Long laboratoryId = (Long) attendanceLaboratoryTable.getModel().getValueAt(attendanceLaboratoryTable.getSelectedRow(), 0);
					boolean isPresent;
					
					if(value.equals("Present")) {
						isPresent = true;
					}
					else {
						isPresent = false;
					}
					
					AttendanceRequest request = new AttendanceRequest();
					AttendanceModel attendanceModel = new AttendanceModel(studentId, laboratoryId, isPresent);
					
					request.updateAttendance(attendanceModel);
			
					attendanceTable(laboratoryId);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}	
			}
		});
		btnUpdate.setBounds(391, 298, 97, 25);
		attendancePanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Long studentId = (Long) attendanceStudentsTable.getModel().getValueAt(attendanceStudentsTable.getSelectedRow(), 0);
					Long laboratoryId = (Long) attendanceLaboratoryTable.getModel().getValueAt(attendanceLaboratoryTable.getSelectedRow(), 0);
					
					AttendanceRequest request = new AttendanceRequest();
					
					request.deleteByStudentAndLaboratory(studentId, laboratoryId);
			
					attendanceTable(laboratoryId);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}	
			}
		});
		btnDelete.setBounds(525, 298, 97, 25);
		attendancePanel.add(btnDelete);
		
		JButton btnShow = new JButton("Show All");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(attendanceLaboratoryTable.getModel().getValueAt(attendanceLaboratoryTable.getSelectedRow(), 0) != null) {
						Long laboratoryId = (Long) attendanceLaboratoryTable.getModel().getValueAt(attendanceLaboratoryTable.getSelectedRow(), 0);
						AttendanceRequest attendanceRequest = new AttendanceRequest();
						Map<String, Boolean> attendances = attendanceRequest.getAttendances(null, laboratoryId);
						attendanceTable = Table.mapToTable(attendances);
						attendanceTableScrollPane.setViewportView(attendanceTable);
					}
					
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnShow.setBounds(661, 298, 97, 25);
		attendancePanel.add(btnShow);
		
		JPanel submissionPanel = new JPanel();
		tabbedPane.addTab("Submissions", null, submissionPanel, null);
		submissionPanel.setLayout(null);
		
		submissionScrollPane = new JScrollPane();
		submissionScrollPane.setBounds(35, 339, 891, 121);
		submissionPanel.add(submissionScrollPane);
		
		//submissionTable();
		//submissionTable = new JTable();
		//submissionScrollPane.setViewportView(submissionTable);
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setBounds(220, 305, 56, 16);
		submissionPanel.add(lblGrade);
		
		gradeTextField = new JTextField();
		gradeTextField.setBounds(292, 302, 116, 22);
		submissionPanel.add(gradeTextField);
		gradeTextField.setColumns(10);
		
		JButton btnAddGrade = new JButton("Add Grade");
		btnAddGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					SubmissionRequest submissionRequest = new SubmissionRequest();
					Long studentId =  (Long) submissionStudentTable.getModel().getValueAt(submissionStudentTable.getSelectedRow(), 0);
					Long assignmentId =  (Long) submissionAssignmentTable.getModel().getValueAt(submissionAssignmentTable.getSelectedRow(), 0);
					Float grade = Float.parseFloat(gradeTextField.getText());
					submissionRequest.updateGrade(studentId, assignmentId, grade);
					
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
				
			}
		});
		btnAddGrade.setBounds(432, 301, 97, 25);
		submissionPanel.add(btnAddGrade);
		
		submissionStudentScrollPane = new JScrollPane();
		submissionStudentScrollPane.setBounds(35, 13, 891, 125);
		submissionPanel.add(submissionStudentScrollPane);
		
		studentTableSubmission();
		//submissionStudentTable = new JTable();
		//submissionStudentScrollPane.setViewportView(submissionStudentTable);
		
		submissionAssignmentScrollPane = new JScrollPane();
		submissionAssignmentScrollPane.setBounds(35, 164, 891, 115);
		submissionPanel.add(submissionAssignmentScrollPane);
		
		JButton btnShowAllSubmissions = new JButton("Show Grades");
		btnShowAllSubmissions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						SubmissionRequest submissionRequst = new SubmissionRequest();
						Long assignmentId =  (Long) submissionAssignmentTable.getModel().getValueAt(submissionAssignmentTable.getSelectedRow(), 0);
						System.out.println(assignmentId);
						Map<String, Float> grades = submissionRequst.getAllGradesForAssignment(assignmentId);
						submissionTable = Table.tableGrades(grades);
						submissionScrollPane.setViewportView(submissionTable);	
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Nothing to show!");
				}
			}
		});
		btnShowAllSubmissions.setBounds(551, 301, 138, 25);
		submissionPanel.add(btnShowAllSubmissions);
		
		JButton btnShowSubmission = new JButton("Show Submission");
		btnShowSubmission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
						Long studentId =  (Long) submissionStudentTable.getModel().getValueAt(submissionStudentTable.getSelectedRow(), 0);
						Long assignmentId =  (Long) submissionAssignmentTable.getModel().getValueAt(submissionAssignmentTable.getSelectedRow(), 0);
						
						SubmissionRequest submissionRequst = new SubmissionRequest();
						List<SubmissionModel> submissions = submissionRequst.getAllSubmissions(studentId, assignmentId);
						submissionTable = Table.createTable(submissions);
						submissionTable.removeColumn(submissionTable.getColumnModel().getColumn(0));
						submissionTable.removeColumn(submissionTable.getColumnModel().getColumn(0));
						submissionScrollPane.setViewportView(submissionTable);
					
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Nothing to show!");
				}
			}
		});
		btnShowSubmission.setBounds(712, 301, 144, 25);
		submissionPanel.add(btnShowSubmission);
		
		assignmentTableSubmission();
		//submissionAssignmentTable = new JTable();
		//submissionAssignmentScrollPane.setViewportView(submissionAssignmentTable);
	}
	
	private void studentsTable() {
		StudentRequest studentRequst = new StudentRequest();
		List<StudentModel> students = studentRequst.getAllStudents();
		tableStudents = Table.createTable(students);
		tableStudents.removeColumn(tableStudents.getColumnModel().getColumn(0));
		studentsTableScrollPane.setViewportView(tableStudents);
	}
	
	private void laboratoryTableLaboratories() {
		LaboratoryRequest laboratoryRequst = new LaboratoryRequest();
		List<LaboratoryModel> laboratories = laboratoryRequst.getAllLaboratories(null);
		tableLaboratory = Table.createTable(laboratories);
		tableLaboratory.removeColumn(tableLaboratory.getColumnModel().getColumn(0));
		laboratoryTableScrollPane.setViewportView(tableLaboratory);
	}
	
	private void laboratoryTableAssignments() {
		LaboratoryRequest laboratoryRequst = new LaboratoryRequest();
		List<LaboratoryModel> laboratories = laboratoryRequst.getAllLaboratories(null);
		laboratoryTable = Table.createTable(laboratories);
		laboratoryTable.removeColumn(laboratoryTable.getColumnModel().getColumn(0));
		laboratoryTablescrollPane.setViewportView(laboratoryTable);
	}
	
	private void assignmentTable() {
		AssignmentRequest assignmentRequest = new AssignmentRequest();
		List<AssignmentModel> assignments = assignmentRequest.getAllAssignments();
		assignmentTable = Table.createTable(assignments);
		assignmentTable.removeColumn(assignmentTable.getColumnModel().getColumn(0));
		assignmentTable.removeColumn(assignmentTable.getColumnModel().getColumn(3));
		assignmentTableScrollPane.setViewportView(assignmentTable);
	}
	
	public void studentsTableAttendance() {
		StudentRequest studentRequst = new StudentRequest();
		List<StudentModel> students = studentRequst.getAllStudents();
		attendanceStudentsTable = Table.createTable(students);
		attendanceStudentsTable.removeColumn(attendanceStudentsTable.getColumnModel().getColumn(0));
		attendanceStudentsTableScrollPane.setViewportView(attendanceStudentsTable);
	}
	
	public void laboratoryTableAttendance() {
		LaboratoryRequest laboratoryRequst = new LaboratoryRequest();
		List<LaboratoryModel> laboratories = laboratoryRequst.getAllLaboratories(null);
		attendanceLaboratoryTable = Table.createTable(laboratories);
		attendanceLaboratoryTable.removeColumn(attendanceLaboratoryTable.getColumnModel().getColumn(0));
		attendanceLaboratoryTableScrollPane.setViewportView(attendanceLaboratoryTable);
	}
	
	public void attendanceTable(Long laboratoryId) {
		AttendanceRequest attendanceRequest = new AttendanceRequest();
		Map<String, Boolean> attendances = attendanceRequest.getAttendances(null, laboratoryId);
		attendanceTable = Table.mapToTable(attendances);
		attendanceTableScrollPane.setViewportView(attendanceTable);
	}
	
	/*
	public void submissionTable() {
		SubmissionRequest submissionRequst = new SubmissionRequest();
		List<SubmissionModel> submissions = submissionRequst.getAllSubmissions(null, null);
		submissionTable = Table.createTable(submissions);
		submissionTable.removeColumn(submissionTable.getColumnModel().getColumn(0));
		submissionTable.removeColumn(submissionTable.getColumnModel().getColumn(0));
		submissionScrollPane.setViewportView(submissionTable);
	}*/
	
	public void studentTableSubmission() {
		StudentRequest studentRequst = new StudentRequest();
		List<StudentModel> students = studentRequst.getAllStudents();
		submissionStudentTable = Table.createTable(students);
		submissionStudentTable.removeColumn(submissionStudentTable.getColumnModel().getColumn(0));
		submissionStudentScrollPane.setViewportView(submissionStudentTable);	
	}
	
	public void assignmentTableSubmission() {
		AssignmentRequest assignmentRequest = new AssignmentRequest();
		List<AssignmentModel> assignments = assignmentRequest.getAllAssignments();
		submissionAssignmentTable = Table.createTable(assignments);
		submissionAssignmentTable.removeColumn(submissionAssignmentTable.getColumnModel().getColumn(0));
		submissionAssignmentTable.removeColumn(submissionAssignmentTable.getColumnModel().getColumn(3));
		submissionAssignmentScrollPane.setViewportView(submissionAssignmentTable);
	}
}
