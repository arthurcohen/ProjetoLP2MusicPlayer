import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField txtUsername;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setTitle ("Sign Up");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	setVisible(false);					
				LogIn login = new LogIn ();
				login.setVisible(true);
		    }
		});
		setBounds(100, 100, 300, 300);
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setEchoChar('*');
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if ( passwordField.getText().equals( passwordField_1.getText() ) ) {
					User newUser = new User ( textField.getText(), passwordField.getText() );
					UserDAO.setUsers(newUser);
					setVisible(false);
					LogIn login = new LogIn ();
					login.setVisible(true);
				}
				else {
					AlertMessage alert = new AlertMessage ();
					alert.setVisible(true);
				}
			}
		});
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtUsername.setEditable(false);
		txtUsername.setText("Username");
		txtUsername.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		textField_1.setEditable(false);
		textField_1.setText("Password");
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		textField_2.setEditable(false);
		textField_2.setText("Confirm Password");
		textField_2.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(88, Short.MAX_VALUE)
					.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addGap(76))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(textField)
							.addComponent(passwordField)
							.addComponent(passwordField_1, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addComponent(btnRegister)
					.addGap(36))
		);
		getContentPane().setLayout(groupLayout);
	}
}
