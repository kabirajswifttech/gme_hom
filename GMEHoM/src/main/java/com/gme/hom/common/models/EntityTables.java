package com.gme.hom.common.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class EntityTables {
	
	public static final Map<String, String> entityTables;
	static {
		Map<String, String> newMap = new HashMap<String, String>();
		newMap.put(EntityTypes.MERCHANT.toString(), "merchants");
		newMap.put(EntityTypes.USER_SIGNUP.toString(), "users_signup");	
		newMap.put(EntityTypes.USERS.toString(), "users");
		entityTables = Collections.unmodifiableMap(newMap);
	}
}
