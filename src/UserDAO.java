import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public interface UserDAO {

	public static String usersFileLocation = "users.txt";
	
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
		} catch (IOException e) {
			new UserIsNotAuthenticatedException("User and/or pass incorrect", e).printStackTrace();
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
