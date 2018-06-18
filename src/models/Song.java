package models;
import java.io.File;

public class Song {

	private String path;
	
	public Song ( String path ) {
		this.setPath(path);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}	
	
	public String getName() {
		File f = new File(this.path);
		
		return f.getName();
	}
	
	public String toString() {
		return getName();
	}
}
