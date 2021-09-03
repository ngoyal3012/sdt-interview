package api.dtos;

public class LoginDTO {
	public String apiUser;
	public String apiKey;
	public Long userId;
	public LoginType[] loginTypes;
	public Long sessionId;
	public String sessionKey;
	public String analyticsEndpoint;
	public String location;
	public Boolean websocketsEnabled;
	public Boolean twoAuthEnabled;
	public Boolean expired;
	public String token;
	public String authError;
	public String subdomain;
	public CustomerPropertyDTO[] uiProperties;
	public UserPropertyDTO[] userProperties;
	public Boolean googleAnalyticsEnabled;
	public String venaSupportUser;
	public String salesforceId;
	public String tenantType;
	public Long hubspotContactId;
	public String trialVersion;
	public String status;
	public String phoneNumber;
	public boolean canChangePassword;

	public enum LoginType {
		ANY, CONTRIBUTOR, MANAGER, ADMIN, MODELER, DASHBOARDER, REPORTER
	}
}


