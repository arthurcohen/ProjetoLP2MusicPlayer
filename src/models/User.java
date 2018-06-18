package models;

public class User {

	private String username;
	private String password;
	private boolean vip;
	
	public User ( String username, String password, boolean vip) {
		this.username = username;
		this.password = password;
		this.vip = vip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toUsersFile() {
		return String.format("%s\n%s\n%s\n", this.username, this.password, this.vip);
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}
	
	
	
	
}
