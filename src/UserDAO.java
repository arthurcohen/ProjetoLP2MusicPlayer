import java.util.ArrayList;

public class UserDAO {

	static private ArrayList <User> users = new ArrayList<User> ();

	UserDAO () {
		
	}
	
	public static boolean getUsers( String username ) {
		
		User findUser;
		
		if ( users.size() == 0 ) {
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

	public static void setUsers(User newUser ) {
		UserDAO.users.add (newUser);
	}
	
}
