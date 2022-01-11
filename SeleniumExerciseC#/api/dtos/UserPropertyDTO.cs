public record UserPropertyDTO
{
    public string id { get; init; }
    public long version { get; init; }
    public string name { get; init; }
    public string value { get; init; }
}