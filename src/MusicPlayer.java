import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class MusicPlayer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusicPlayer frame = new MusicPlayer();
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
	public MusicPlayer() {
		setTitle ("Music Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 425);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JButton btnNewSong = new JButton("New song");
		btnNewSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		

		JList<String> list = new JList<String>( );
		
		JList<String> list_1 = new JList<String>();
		
		JList<String> list_2 = new JList<String>();
		
		mnFile.add(btnNewSong);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JButton btnRemoveSong = new JButton("Remove song");
		mnEdit.add(btnRemoveSong);
		
		JMenu mnNewMenu = new JMenu("Options");
		menuBar.add(mnNewMenu);
		
		JButton btnLogout_1 = new JButton("Logout");
		btnLogout_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				LogIn newLogin = new LogIn ();
				newLogin.setVisible (true);
			}
		});
		mnNewMenu.add(btnLogout_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnLogout = new JButton("Add playlist");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TO-DO
			}
		});

		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TO-DO
			}
		});
		
		JButton button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TO-DO
			}
		});
		
		JButton button_1 = new JButton("<<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TO-DO
			}
		});
		
		JProgressBar progressBar = new JProgressBar();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPlay)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 296, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(127)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
							.addGap(34)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(list_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
								.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogout)
						.addComponent(button_1)
						.addComponent(btnPlay)
						.addComponent(button))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
