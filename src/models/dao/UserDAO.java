package models.dao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.User;
import models.exceptions.UserIsNotAuthenticatedException;

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

			bf.close();
			fr.close();
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
	
	public static void saveUser(User user) {
		List<User> users = new ArrayList<User>();
		
		try {
			FileReader fr = new FileReader(usersFileLocation);
			BufferedReader bf = new BufferedReader(fr);
			String line = bf.readLine();
			
			while(line != null) {
				String username = line;
				String pass = bf.readLine();
				System.out.println(username);
				boolean vip = bf.readLine().equals("true");
				User fileUser = new User(username, pass, vip);
				if (user.getUsername().equals(fileUser.getUsername())){
					users.add(user);
				}else {
					users.add(fileUser);
				}
				line = bf.readLine();
			}
			bf.close();
			fr.close();
			
			FileWriter fw = new FileWriter(usersFileLocation);
			for (User sUser : users) {
				fw.append(sUser.toUsersFile());
				
			}
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
