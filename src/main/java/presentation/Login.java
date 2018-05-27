package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.LoginRequest;
import client.RegisterStudentRequest;
import client.StudentRequest;
import client.SubmissionRequest;
import encryption.Encryption;
import model.StudentModel;
import model.TeacherModel;
import model.User;

import javax.swing.JSplitPane;

public class Login {

	private JFrame frame;
	private JTextField emailLoginTextField;
	private JPasswordField passLoginTextField;
	private JTextField emailTextRegister;
	private JTextField tokenTextRegister;
	private JPasswordField passwordTextRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("LaboratoryManager");
		frame.setBounds(100, 100, 842, 239);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel LabelEmailLogin = new JLabel("e-mail");
		LabelEmailLogin.setBounds(41, 33, 56, 16);
		frame.getContentPane().add(LabelEmailLogin);
		
		emailLoginTextField = new JTextField();
		emailLoginTextField.setBounds(100, 30, 187, 22);
		frame.getContentPane().add(emailLoginTextField);
		emailLoginTextField.setColumns(10);
		
		JLabel labelLoginPassword = new JLabel("password");
		labelLoginPassword.setBounds(23, 71, 65, 16);
		frame.getContentPane().add(labelLoginPassword);
		
		passLoginTextField = new JPasswordField();
		passLoginTextField.setBounds(100, 68, 187, 22);
		frame.getContentPane().add(passLoginTextField);
		passLoginTextField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoginRequest request = new LoginRequest();
					String email = emailLoginTextField.getText();
					String password = String.valueOf(passLoginTextField.getPassword());
					
					String securePassword = Encryption.get_SHA_1_SecurePassword(password);
			
					User user = request.login(email, securePassword);
					
					if(user instanceof StudentModel) {
						StudentModel s = (StudentModel) user;
						StudentView frame = new StudentView(s);
						frame.setVisible(true);
					}
					else if(user instanceof TeacherModel) {
						TeacherView frame = new TeacherView();
						frame.setVisible(true);
					}
					
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Wrong email or password!");
					//err.printStackTrace();
				}
			}
		});
		loginButton.setBounds(141, 113, 97, 25);
		frame.getContentPane().add(loginButton);
		
		JLabel labelEmailRegister = new JLabel("e-mail");
		labelEmailRegister.setBounds(354, 33, 56, 16);
		frame.getContentPane().add(labelEmailRegister);
		
		JLabel labelTokenRegister = new JLabel("token");
		labelTokenRegister.setBounds(354, 71, 56, 16);
		frame.getContentPane().add(labelTokenRegister);
		
		JLabel labelPasswordRegister = new JLabel("password");
		labelPasswordRegister.setBounds(334, 106, 76, 16);
		frame.getContentPane().add(labelPasswordRegister);
		
		emailTextRegister = new JTextField();
		emailTextRegister.setBounds(432, 30, 326, 22);
		frame.getContentPane().add(emailTextRegister);
		emailTextRegister.setColumns(10);
		
		tokenTextRegister = new JTextField();
		tokenTextRegister.setBounds(432, 68, 326, 22);
		frame.getContentPane().add(tokenTextRegister);
		tokenTextRegister.setColumns(10);
		
		passwordTextRegister = new JPasswordField();
		passwordTextRegister.setBounds(432, 103, 326, 22);
		frame.getContentPane().add(passwordTextRegister);
		passwordTextRegister.setColumns(10);
		
		JButton buttonRegister = new JButton("Register");
		buttonRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RegisterStudentRequest request = new RegisterStudentRequest();
					String email = emailTextRegister.getText();
					String token = tokenTextRegister.getText();
					String password = String.valueOf(passwordTextRegister.getPassword());
					
					String securePassword = Encryption.get_SHA_1_SecurePassword(password);
					
					request.register(token, email, securePassword);
					JOptionPane.showMessageDialog(null, "You have been successfully registered!");
					
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Wrong token or email!");
					err.printStackTrace();
				}
			}
		});
		buttonRegister.setBounds(550, 149, 97, 25);
		frame.getContentPane().add(buttonRegister);
	}
}
