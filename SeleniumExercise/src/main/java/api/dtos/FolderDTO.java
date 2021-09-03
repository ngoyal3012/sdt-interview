package api.dtos;

public class FolderDTO {
	public Long id;
	public Long version;
	public String name;
	public Long parent;
	public String fileType = "Folder";
	public Long copyFromId;
}
