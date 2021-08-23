package org.vena;

import api.APITesting;
import api.APIToken;
import api.config.CredentialsFileConstants;
import api.dtos.FolderDTO;
import api.resources.FoldersResource;
import api.resources.LoginResource;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import org.testng.annotations.BeforeSuite;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class BaseTest {
	private String CONSTANTS_YML_FILE_PATH = "./src/test/resources/credentials.yml";
	protected Yaml yaml = new Yaml(new Constructor(CredentialsFileConstants.class));
	protected CredentialsFileConstants credentials = new CredentialsFileConstants();

	@BeforeSuite
	public void beforeSuiteAPITesting() throws Exception {
		credentials = yaml.load(new FileInputStream(new File(CONSTANTS_YML_FILE_PATH)));
		APITesting.beforeSuite("https://dev.vena.io", true, true);
	}

	private APIToken getAdminToken() {
		return LoginResource.login(credentials.getUsername(), credentials.getPassword());
	}

	protected void deleteAllCustomFolders() {
		FolderDTO[] folders = FoldersResource.list(getAdminToken());
		Arrays.stream(folders).forEach(folder -> FoldersResource.delete(getAdminToken(), folder.id));
	}
}
