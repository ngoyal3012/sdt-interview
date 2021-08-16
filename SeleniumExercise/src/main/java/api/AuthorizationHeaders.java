package api;

import api.config.APIAuth;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthorizationHeaders {
	public static String venaBasic(APIAuth auth) {
		APIToken token = auth.toAPIToken();
		return venaBasic(token.getUserId(), token.getCustomerId(), token.getToken());
	}

	public static String venaBasic(long userId, long customerId, String token) {
		return "VenaBasic " + Base64.getEncoder().encodeToString((String.format("%d.%d:%s", userId, customerId, token)).getBytes(StandardCharsets.UTF_8));
	}

	public static String venaBasic(String username, String password) {
		return "VenaBasic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
	}
}
