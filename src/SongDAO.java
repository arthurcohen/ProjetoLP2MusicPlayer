import java.util.ArrayList;

public class SongDAO {

	static public ArrayList <Song> songs = new ArrayList<Song>();
	
	SongDAO () {
		
	}
	
	static public boolean removeSong ( String title ) {
		Song newSong;
		if ( songs.size() == 0 ) {
			return false;
		}
		else {
			for ( int i = 0; i < SongDAO.songs.size(); i ++ ) {
				newSong = songs.get(i);
				if ( newSong.getTitle().equals(title) ) {
					songs.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	
}
