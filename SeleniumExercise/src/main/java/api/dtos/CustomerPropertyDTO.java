package api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerPropertyDTO {
	public String propertyName;
	public String value;

	/**
	 * The type of the property.
	 * <p>
	 * This isn't sent to/from the server and so won't usually be populated. It's here to make
	 * working with generators easier.
	 * <p>
	 * DO NOT COMPARE THIS FIELD
	 */
	@JsonIgnore
	public CustomerPropertyType _type;

	public CustomerPropertyDTO() {
	}

	public CustomerPropertyDTO(CustomerPropertyType type, String propertyName, String value) {
		this.propertyName = propertyName;
		this._type = type;
		this.value = value;
	}

	public enum CustomerPropertyType {
		ADDIN,
		USER_INTERFACE,
		SERVER
	}
}
