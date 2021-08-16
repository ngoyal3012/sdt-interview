package api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.parsing.Parser;
import java.util.HashSet;
import java.util.Set;
import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;

public class APITesting {
	private static final int CONNECTION_TIMEOUT = 60000; // 1 minute
	private static final int SO_TIMEOUT = 600000; // 10 minutes

	public static void beforeSuite(String baseURI, boolean cleanupResults, boolean checkStatus) throws Exception {
		if (baseURI == null) {
			throw new IllegalArgumentException("baseURI must not be null");
		}

		RestAssured.config = RestAssuredConfig.config()
				// Configure an explicit timeout.
				.httpClient(HttpClientConfig.httpClientConfig()
						.setParam("http.connection.timeout", CONNECTION_TIMEOUT)
						.setParam("http.socket.timeout", SO_TIMEOUT)
						.setParam("http.socket.keepalive", true))

				// Don't follow redirects.
				.redirect(RedirectConfig.redirectConfig().followRedirects(false))
				// Override the default object mapper to only include non-null fields.
				.objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
						(type, s) -> getObjectMapper()
				))
				.logConfig(logConfig()
						.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)
						.blacklistHeaders(getBlacklistedHeaders())
				);

		RestAssured.baseURI = baseURI;
		RestAssured.defaultParser = Parser.JSON;

		if (checkStatus) {
			// Make sure mt-server is healthy by calling /status and /status/basic.
			given()
					.get("/status")
					.then()
					.statusCode(200);
			given()
					.get("/status/basic")
					.then()
					.statusCode(200);
		}
	}

	public static ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
		return objectMapper;
	}

	public static Set<String> getBlacklistedHeaders(){
		Set<String> blacklistedHeaders = new HashSet<>();
		blacklistedHeaders.add("authorization");
		blacklistedHeaders.add("Authorization");
		return blacklistedHeaders;
	}
}
