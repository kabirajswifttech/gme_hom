package com.gme.hom.users.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserInfoRequest {
	    @JsonProperty("source_id")
	    private Integer sourceId;
	    @JsonProperty("user_type")
	    private String user_type;
}
