import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface SongDAO {
	public static String musicsFileLocation = "musics.txt";
	
	public static boolean removeSong ( Song song ) {
		ArrayList <Song> songs = (ArrayList<Song>) loadSongs();
		if ( songs.isEmpty() ) {
			return false;
		} else {
			for ( int i = 0; i < songs.size(); i ++ ) {
				if (songs.get(i).getPath().equals(song.getPath())) {
					songs.remove(i);
					saveSongs(songs);
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
	
	public static List<Song> getSongs() {
		return loadSongs();
	}
	
	public static List<Song> loadSongs() {
		ArrayList <Song> songs = new ArrayList<Song>();
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
		
		return songs;
	}
	
	public static void saveSongs(List<Song> songs) {
		
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
