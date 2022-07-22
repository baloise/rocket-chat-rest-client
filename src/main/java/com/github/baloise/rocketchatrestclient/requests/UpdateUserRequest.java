package com.github.baloise.rocketchatrestclient.requests;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

	private String userId;
	private Data data;

	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter @Setter
	public static class Data {
		
		private String email;
		private String name;
		private String password;
		private String username;
		private String[] roles;
		private Boolean active;
		private Boolean verified;
		private Map<String, String> customFields;
	}

}
