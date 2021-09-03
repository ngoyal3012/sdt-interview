package api.resources;

import api.config.APIAuth;
import api.dtos.FolderDTO;
import io.restassured.specification.RequestSpecification;
import static api.AuthorizationHeaders.venaBasic;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class FoldersResource {
	public static FolderDTO[] list(APIAuth auth) {
		return list(auth, null);
	}

	public static FolderDTO[] list(APIAuth auth, Boolean includeHidden) {
		RequestSpecification spec = given()
				.header(AUTHORIZATION, venaBasic(auth));
		if (includeHidden != null) {
			spec = spec.queryParam("includeHidden", includeHidden);
		}
		return spec
				.get("/api/manager/folders")
				.then()
				.statusCode(200)
				.extract()
				.as(FolderDTO[].class);
	}

	public static void delete(APIAuth auth, long folderId) {
		given()
				.header(AUTHORIZATION, venaBasic(auth))
				.pathParam("folderId", folderId)
				.delete("/api/manager/folders/{folderId}")
				.then()
				.statusCode(204);
	}
}
