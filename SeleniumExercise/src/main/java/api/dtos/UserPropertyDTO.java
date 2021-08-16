package api.dtos;

public class UserPropertyDTO {
	public Long id;
	public Long version;
	public String name;
	public String value;

	public UserPropertyDTO() {
	}

	public UserPropertyDTO(String name, String value) {
		this.name = name;
		this.value = value;
	}
}
