package api.resources;

import api.APIToken;
import api.config.APIUser;
import api.dtos.LoginDTO;
import static api.AuthorizationHeaders.venaBasic;
import static api.ContentTypes.APPLICATION_JSON;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

public class LoginResource {
	/**
	 * Log in as the specified user.
	 * <p>
	 * POST /login
	 */
	public static APIToken login(APIUser user) {
		return login(user.getUsername(), user.getPassword());
	}

	/**
	 * Log in as the user with the specified username and password.
	 * <p>
	 * POST /login
	 */
	public static APIToken login(String username, String password) {
		LoginDTO loginDTO = loginInner(username, password);
		return new APIToken(loginDTO.apiUser, loginDTO.apiKey);
	}

	private static LoginDTO loginInner(String username, String password) {
		return given()
				.header(AUTHORIZATION, venaBasic(username, password))
				.header(CONTENT_TYPE, APPLICATION_JSON)
				.post("/login")
				.then()
				.statusCode(200)
				.extract()
				.as(LoginDTO.class);
	}
}
