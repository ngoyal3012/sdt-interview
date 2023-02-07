public record CustomerPropertyDTO
{
    public string propertyName { get; init; }
    public string value { get; init; }
    public CustomerPropertyType _type { get; init; }
}

public enum CustomerPropertyType
{
    ADDIN,
    USER_INTERFACE,
    SERVER
}