import java.awt.EventQueue;
import java.awt.Point;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;

public class MusicPlayer extends JFrame {

	/**
	 * 
	 */

	private static final JFXPanel fxPanel = new JFXPanel();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private MediaPlayer player;
	private String currentSong;
	private Duration currentSongDuration;
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
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(MusicPlayer.this);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					int index = file.getName().lastIndexOf(".");
					JPopupMenu popup = new JPopupMenu("Popup");
					if (file.getName().substring(index+1).equals("mp3")) {
						SongDAO.addSong(new Song(file.getAbsolutePath()));
						popup.add(new JMenuItem("Musica adicionada a biblioteca"));
					}else {
						popup.add(new JMenuItem("Extenção inválida"));
					}
					popup.show(MusicPlayer.this, 250, 200);
				}
			}
		});
		

		JList<Song> list;
		DefaultListModel<Song> musicas = new DefaultListModel<Song>();
		
		for (Song song : SongDAO.getSongs()) {
			musicas.addElement(song);
		}
		
		list = new JList<Song>(musicas);
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(((Song)((JList)e.getSource()).getSelectedValue()).getPath());
				if (player != null) player.stop();
				File musicFile = new File(((Song)((JList)e.getSource()).getSelectedValue()).getPath());
				Media music = new Media(musicFile.toURI().toString());
				player = new MediaPlayer(music);	
				player.play();
				currentSongDuration = player.getBufferProgressTime();
			}
		});
		
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
				if (btnPlay.getText().toString().equals("Play")){
					if (player == null) {
						File musicFile = new File("Maroon.mp3");
						Media music = new Media(musicFile.toURI().toString());
						player = new MediaPlayer(music);	
						currentSongDuration = player.getBufferProgressTime();
					}
					player.play();
					btnPlay.setText("Pause");
					
				}else {
					player.pause();
					btnPlay.setText("Play");
					
				}
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
		ScheduledExecutorService loopDaLoop = Executors.newSingleThreadScheduledExecutor();
		loopDaLoop.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				if (player != null) {
					progressBar.setMaximum((int)player.getTotalDuration().toSeconds());
					progressBar.setValue((int)player.getCurrentTime().toSeconds());
				}else {
					System.out.println("erro");
				}
			}
		}, 5, 1, TimeUnit.SECONDS);
		
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
