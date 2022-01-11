public record LoginDTO
{
    public string apiUser { get; init; }
    public string apiKey { get; init; }
    public string userId { get; init; }
    public string sessionId { get; init; }
    public string sessionKey { get; init; }
    public string analyticsEndpoint { get; init; }
    public string location { get; init; }
    public Boolean websocketsEnabled { get; init; }
    public Boolean twoAuthEnabled { get; init; }
    public Boolean expired { get; init; }
    public string token { get; init; }
    public string authError { get; init; }
    public string subdomain { get; init; }
    public CustomerPropertyDTO[] uiProperties { get; init; }
    public UserPropertyDTO[] userProperties { get; init; }
    public Boolean googleAnalyticsEnabled { get; init; }
    public string venaSupportUser { get; init; }
    public string salesforceId { get; init; }
    public string tenantType { get; init; }
    public string hubspotContactId { get; init; }
    public string trialVersion { get; init; }
    public string status { get; init; }
    public string phoneNumber { get; init; }
    public bool canChangePassword { get; init; }
    public string organizationType { get; init; }
}

public enum LoginType
{
    ANY, CONTRIBUTOR, MANAGER, ADMIN, MODELER, DASHBOARDER, REPORTER
}