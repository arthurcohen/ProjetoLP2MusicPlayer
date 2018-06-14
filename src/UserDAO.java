import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserDAO {

	private static String usersFileLocation = "users.txt";
	private static String musicsFileLocation = "musics.txt";
	private static String playlistsFileLocation = "playlists.txt";
	
	static private ArrayList <User> users = new ArrayList<User> ();

	UserDAO () {
		
	}
	
	public static boolean getUsers( String username ) {
		
		User findUser;
		
		if ( users.isEmpty() ) {
			return false;
		}
		else {
			for ( int i = 0; i < UserDAO.users.size(); i ++ ) {
				findUser = users.get(i);
				if ( findUser.getUsername().equals(username) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static User authenticateUser(User user) {
		try {
			FileReader fr = new FileReader(usersFileLocation);
			BufferedReader bf = new BufferedReader(fr);
			String line = bf.readLine();
			
			while(line != null) {
				if (line.equals(user.getUsername()) && user.getPassword().equals(bf.readLine())) {
					return new User(user.getUsername(), user.getPassword(), bf.readLine().equals("true"));
				}
				
				line = bf.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public static void  addUser(User user) {
		try {
			FileWriter fw = new FileWriter(usersFileLocation, true);
			fw.append(user.toUsersFile());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
