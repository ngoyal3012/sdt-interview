package api;

import api.config.APIAuth;

public class APIToken implements APIAuth {
	private final long customerId;
	private final long userId;
	private final String token;

	public APIToken(String apiUser, String apiKey) {
		if (apiUser == null) {
			throw new IllegalArgumentException("apiUser must not be null");
		}
		if (apiKey == null) {
			throw new IllegalArgumentException("apiKey must not be null");
		}
		String[] userIdCustomerIdSplit = apiUser.split("\\.");
		assert userIdCustomerIdSplit.length == 2;
		this.customerId = Long.parseLong(userIdCustomerIdSplit[1]);
		this.userId = Long.parseLong(userIdCustomerIdSplit[0]);
		this.token = apiKey;
	}

	@Override
	public APIToken toAPIToken() {
		return this;
	}

	public long getUserId() {
		return this.userId;
	}

	public long getCustomerId() {
		return this.customerId;
	}

	public String getToken() {
		return this.token;
	}
}
