package models;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
	private String name;
	private String owner;
	private ArrayList<Song> songs;
	
	public Playlist(String path, String owner, List<Song> songs) {
		this.name = path;
		this.owner = owner;
		this.songs = (ArrayList<Song>) songs;
	}
	
	public Playlist(String path, String owner) {
		this.name = path;
		this.owner = owner;
		this.songs = (ArrayList<Song>) new ArrayList<Song>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public ArrayList<Song> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}
	
	public String toString() {
		return name;
	}

}
