
public interface PlaylistDAO {

	public void addPlaylist ( Playlist playlist );
	
	public void removePlaylist ( Playlist playlist );

	public List<Playlist> getPlaylist ();
	
}
