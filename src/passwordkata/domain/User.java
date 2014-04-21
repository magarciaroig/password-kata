package passwordkata.domain;

public class User {
	private String userName;
	private Password password;
	
	public User(String userName, Password password){
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public Password getPassword() {
		return password;
	}	
}
