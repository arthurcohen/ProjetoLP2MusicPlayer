import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class AlertMessage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPasswordsDontMatch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlertMessage frame = new AlertMessage();
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
	public AlertMessage() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 268, 108);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnPasswordsDontMatch = new JButton("Close");
		btnPasswordsDontMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		contentPane.add(btnPasswordsDontMatch, BorderLayout.CENTER);
		
		txtPasswordsDontMatch = new JTextField();
		txtPasswordsDontMatch.setEditable(false);
		txtPasswordsDontMatch.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtPasswordsDontMatch.setForeground(Color.RED);
		txtPasswordsDontMatch.setText("                      Passwords dont match");
		contentPane.add(txtPasswordsDontMatch, BorderLayout.NORTH);
		txtPasswordsDontMatch.setColumns(10);
	}

}
