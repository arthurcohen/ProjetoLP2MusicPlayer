import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SongDAO {

	static public ArrayList <Song> songs = new ArrayList<Song>();
	private static String musicsFileLocation = "musics.txt";
	
	SongDAO () {
		
	}
	
	static public boolean removeSong ( Song song ) {
		if ( songs.isEmpty() ) {
			return false;
		} else {
			for ( int i = 0; i < SongDAO.songs.size(); i ++ ) {
				if (songs.get(i).getPath().equals(song.getPath())) {
					songs.remove(i);
					saveSongs();
					return true;
				}
			}
		}
		return false;
	}
	
	public static void addSong(Song song) {
		try {
			FileWriter fw = new FileWriter(musicsFileLocation, true);
			fw.write(song.getPath() + "\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Song> getSongs() {
		loadSongs();
		return songs;
	}
	
	private static void loadSongs() {
		FileReader fr;
		try {
			fr = new FileReader(musicsFileLocation);
			BufferedReader bf = new BufferedReader(fr);
			
			String line = bf.readLine();
			
			while(line != null) {
				songs.add(new Song(line));
				line = bf.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private static void saveSongs() {
		
		try {
			FileWriter fw = new FileWriter(musicsFileLocation);
			
			for (Song song : songs) {
				fw.append(song.getPath() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
