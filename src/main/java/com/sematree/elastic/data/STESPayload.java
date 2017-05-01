package com.sematree.elastic.data;

abstract public class STESPayload implements STESAction {
	private String payload;
	private String index;
	private String type;
	private String id;
	@SuppressWarnings("rawtypes")
	private Class jsonClass;
	private String query;

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setId(Long id) {
		this.id = String.valueOf(id);
	}
	
	public void setId(Integer id) {
		this.id = String.valueOf(id);
	}
	
	@SuppressWarnings("rawtypes")
	public Class getJsonClass() {
		return jsonClass;
	}
	
	@SuppressWarnings("rawtypes")
	public void setJsonClass(Class jsonClass) {
		this.jsonClass = jsonClass;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
