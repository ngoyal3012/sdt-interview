public record FolderDTO
{
    public string id { get; init; }
    public long version { get; init; }
    public string name { get; init; }
    public Nullable<Int64> parent { get; init; }
    public string fileType = "Folder";
    public Nullable<long> copyFromId { get; init; }
}