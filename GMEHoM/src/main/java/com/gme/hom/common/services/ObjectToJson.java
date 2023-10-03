package com.gme.hom.common.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gme.hom.api.models.APIResponse;

public class ObjectToJson {

	public final static String toJson(APIResponse ar) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		try {
			/*
			 * ObjectNode node = mapper.valueToTree(ar);
			 * 
			 * 
			 * if (data != null) { node.put("data", mapper.convertValue(data,
			 * JsonNode.class).toPrettyString()); }
			 */

			// JsonNode node = mapper.convertValue(ar, JsonNode.class);

			return mapper.convertValue(ar, JsonNode.class).toPrettyString();
			// return mapper.writeValueAsString(node);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";

	}

	/*
	 * public final static String toJson(APIResponse ar, Object data) { try {
	 * 
	 * ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	 * 
	 * return ow.writeValueAsString(ar);
	 * 
	 * } catch (JsonProcessingException e) { e.printStackTrace(); }
	 * 
	 * return ""; }
	 */
}
