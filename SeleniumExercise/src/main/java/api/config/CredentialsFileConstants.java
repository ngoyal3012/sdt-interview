package api.config;

public class CredentialsFileConstants {
	private String username;
	private String password;

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public String getPassword() {
		return password;
	}
}
