
public interface UserDAO {
		
		public boolean getUsers( String username ); 
		
		public User authenticateUser(User user);

		public void  addUser(User user);

}
