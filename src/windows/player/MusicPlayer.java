package windows.player;
import java.awt.EventQueue;
import java.awt.MenuItem;
import java.awt.Point;

import javafx.beans.NamedArg;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
import models.Playlist;
import models.Song;
import models.User;
import models.dao.PlaylistDAO;
import models.dao.SongDAO;
import windows.login.LogIn;

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
import java.util.ArrayList;
import java.util.List;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class MusicPlayer extends JFrame {

	/**
	 * 
	 */

	private static final JFXPanel fxPanel = new JFXPanel();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private MediaPlayer player;
	private int currentSong;
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
		setBounds(100, 100, 370, 400);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);		

		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JList<String> list_1 = new JList<String>();
		scrollPane_1.setViewportView(list_1);
		
		DefaultListModel<Song> musicas = new DefaultListModel<Song>();
		
		for (Song song : SongDAO.getSongs()) {
			musicas.addElement(song);
		}
		
		JList<Song> list = new JList<Song>(musicas);
		scrollPane.setViewportView(list);

		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(((Song)((JList)e.getSource()).getSelectedValue()).getPath());
				if (player != null) player.stop();
				File musicFile = new File(((Song)((JList)e.getSource()).getSelectedValue()).getPath());
				Media music = new Media(musicFile.toURI().toString());
				player = new MediaPlayer(music);	
				player.play();
				currentSong = ((JList)e.getSource()).getSelectedIndex();
			}
		});
		
		list_1.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(((Song)((JList)e.getSource()).getSelectedValue()).getPath());
				if (player != null) player.stop();
				File musicFile = new File(((Song)((JList)e.getSource()).getSelectedValue()).getPath());
				Media music = new Media(musicFile.toURI().toString());
				player = new MediaPlayer(music);	
				player.play();
				currentSong = ((JList)e.getSource()).getSelectedIndex();
			}
		});
		
		
		
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JButton btnRemoveSong = new JButton("Remove song");
		mnEdit.add(btnRemoveSong);
		
		JMenu mnPlaylists = new JMenu("Playlists");
		JButton btnAddPlaylist = new JButton("Add Playlist");
		btnAddPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int returnVal = chooser.showOpenDialog(MusicPlayer.this);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File path = chooser.getSelectedFile();
					File[] files = path.listFiles();
					
					JPopupMenu popup = new JPopupMenu("Popup");
					int musicAddCount = 0;

					Playlist playlist = new Playlist(path.getName(), "arthur");
					
					for (File file : files) {
						int index = file.getName().lastIndexOf(".");
						if (file.getName().substring(index+1).equals("mp3")) {
							playlist.getSongs().add(new Song(file.getAbsolutePath()));
						}
					}
					
					PlaylistDAO.savePlaylist(playlist);
					
					popup.add(new JMenuItem(playlist.getSongs().size() + " musicas adicionadas a playlist " + path.getName()));
					popup.show(MusicPlayer.this, 250, 200);
					
					
				}
			}
		});
		mnPlaylists.add(btnAddPlaylist);
		
		JSeparator separator = new JSeparator();
		mnPlaylists.add(separator);
		
		for (Playlist playlist : PlaylistDAO.getPlaylists(new User("arthur", "123", true))){
			JMenuItem item = new JMenuItem(playlist.getName());
			item.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					DefaultListModel<Song> musicasAtt = new DefaultListModel<Song>();
					for (Song song : playlist.getSongs()) {
						System.out.println("att playlist");
						musicasAtt.addElement(song);
					}
					
					JList<Song> listAtt = new JList<Song>(musicasAtt);

					listAtt.addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e) {
							System.out.println(((Song)((JList)e.getSource()).getSelectedValue()).getPath());
							if (player != null) player.stop();
							File musicFile = new File(((Song)((JList)e.getSource()).getSelectedValue()).getPath());
							Media music = new Media(musicFile.toURI().toString());
							player = new MediaPlayer(music);	
							player.play();
							currentSong = ((JList)e.getSource()).getSelectedIndex();
						}
					});
					scrollPane_1.setViewportView(listAtt);
				}
			});
			mnPlaylists.add(item);
		}
		
		
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
						popup.add(new JMenuItem("Exten��o inv�lida"));
					}
					DefaultListModel<Song> musicasAtt = new DefaultListModel<Song>();
					for (Song song : SongDAO.getSongs()) {
						System.out.println("att");
						musicasAtt.addElement(song);
					}
					
					JList<Song> listAtt = new JList<Song>(musicasAtt);
					listAtt.addListSelectionListener(new ListSelectionListener() {
						
						@Override
						public void valueChanged(ListSelectionEvent e) {
							System.out.println("123" + ((Song)((JList)e.getSource()).getSelectedValue()).getPath());
							if (player != null) player.stop();
							File musicFile = new File(((Song)((JList)e.getSource()).getSelectedValue()).getPath());
							Media music = new Media(musicFile.toURI().toString());
							player = new MediaPlayer(music);	
							player.play();
							currentSong = ((JList)e.getSource()).getSelectedIndex();
						}
					});
					scrollPane.setViewportView(listAtt);
					
					popup.show(MusicPlayer.this, 250, 200);
				}
			}
		});

		mnFile.add(btnNewSong);
		
		menuBar.add(mnPlaylists);
		
		
		
		JMenu mnNewMenu = new JMenu("Options");
		menuBar.add(mnNewMenu);
		
		JButton btnLogout_1 = new JButton("Logout");
		btnLogout_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogIn newLogin = new LogIn ();
				newLogin.setVisible (true);
				player.dispose();
				dispose();
			}
		});
		mnNewMenu.add(btnLogout_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player == null && list.getModel().getSize() > 0) {
					System.out.println(list.getModel().getSize());
					File musicFile = new File(list.getModel().getElementAt(0).getPath());
					Media music = new Media(musicFile.toURI().toString());
					player = new MediaPlayer(music);						
				}
				if (player != null) {
					if (!player.getStatus().equals(Status.PLAYING)){
						player.play();
					}else{
						player.pause();
					}					
				}
			}
		});
		
		JButton button = new JButton(">|");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getModel().getSize() - 1 > currentSong) {
					player.stop();
					player = new MediaPlayer(new Media(new File(list.getModel().getElementAt(currentSong + 1).getPath()).toURI().toString()));
					player.play();
					currentSong++;	
					list.setSelectedIndex(currentSong);
				}
			}
		});
		
		JButton button_1 = new JButton("|<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (0 < currentSong) {
					player.stop();
					player = new MediaPlayer(new Media(new File(list.getModel().getElementAt(currentSong - 1).getPath()).toURI().toString()));
					player.play();
					currentSong--;	
					list.setSelectedIndex(currentSong);
				}
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
					btnPlay.setText(player.getStatus().equals(Status.PLAYING) ? "Pause" : "Play");
					
				}
			}
		}, 5, 1, TimeUnit.SECONDS);
		
		JButton button_2 = new JButton("<<");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player != null) {
					player.seek(Duration.seconds(player.getCurrentTime().toSeconds() - 10));
				}
			}
		});
		
		JButton button_3 = new JButton(">>");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player != null) {
					player.seek(Duration.seconds(player.getCurrentTime().toSeconds() + 10));
				}
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPlay)
						.addComponent(button_1)
						.addComponent(button_2)
						.addComponent(button_3)
						.addComponent(button))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		
		
		
		
		contentPane.setLayout(gl_contentPane);
	}
}
