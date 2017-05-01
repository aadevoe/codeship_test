package com.sematree.elastic.data.enums;

public enum ActionType {
	CREATE("Create"),
	DELETE("Delete"),
	UPDATE("Update"),
	GET("Get"),
	SEARCH("Search");
	
	private final String value;
	
	ActionType(String v) {
		value = v;
	}
	
	public String value() {
		return value;
	}
	
	public static ActionType fromValue(String v) {
		for (ActionType s : ActionType.values()) {
			if (s.value.equals(v)) {
				return s;
			}
		}
		
		throw new IllegalArgumentException(v);
	}
}
