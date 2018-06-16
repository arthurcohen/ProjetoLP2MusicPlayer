import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface PlaylistDAO {

	public static String playlistsFileLocation = "playlists.txt";
	

	static public void removePlaylist (Playlist playlist) {
		
	}
	
	public static void addPlaylist(Playlist playlist) {
		
	}
	
	public static List<Playlist> getPlaylists(User user) {
		return loadPlaylist(user);
	}
	
	public static ArrayList<Playlist> loadPlaylist(User user) {
		ArrayList <Playlist> playlists = new ArrayList<Playlist>();
		FileReader fr;

		try {
			fr = new FileReader(playlistsFileLocation);
			BufferedReader bf = new BufferedReader(fr);
			
			String line = bf.readLine();
			
			while(line != null) {
				line += ".txt";
				
				FileReader fr1 = new FileReader(line);
				BufferedReader bf1 = new BufferedReader(fr1);
				
				String line1 = bf1.readLine();
				String owner = line1;
				line1 = bf1.readLine();
				
				if (user.getUsername().equals(owner)) {	
					List<Song> songs = new ArrayList<Song>();
					
					while (line1 != null) {
						songs.add(new Song(line1));
						line1 = bf1.readLine();
					}
					playlists.add(new Playlist(line, owner, songs));
				}
				
				
				line = bf.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return playlists;
	}
	
	public static void savePlaylist(Playlist playlist) {
		
		try {
			FileWriter fw = new FileWriter(playlistsFileLocation, true);
			fw.append(playlist.getName() + "\n");
			fw.close();
			
			FileWriter fw1 = new FileWriter(playlist.getName()+".txt");
			
			fw1.append(playlist.getOwner() + "\n");
			
			for (Song song : playlist.getSongs()) {
				fw1.append(song.getPath() + "\n");
			}
			
			fw1.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
