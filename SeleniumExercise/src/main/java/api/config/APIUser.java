package api.config;

import api.APIToken;
import api.resources.LoginResource;

public class APIUser implements APIAuth{
	private String email;
	private String username;
	private String password;
	private APIToken apiToken;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public APIToken toAPIToken() {
		ensureAPIToken();
		return this.apiToken;
	}

	public long getCustomerId() {
		ensureAPIToken();
		return this.apiToken.getCustomerId();
	}

	public long getUserId() {
		ensureAPIToken();
		return this.apiToken.getUserId();
	}

	public String getToken() {
		ensureAPIToken();
		return this.apiToken.getToken();
	}

	public void resetAPIToken() {
		this.apiToken = null;
	}

	private void ensureAPIToken() {
		if (this.apiToken == null) {
			this.apiToken = LoginResource.login(this);
		}
	}
}
