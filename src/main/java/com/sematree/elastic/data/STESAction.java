package com.sematree.elastic.data;

public interface STESAction {
	public String getAction();
	
	/**
	 * For retrieving the list of objects after a search, we need this class to get the type.
	 * result.getSourceAsObjectList();
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Class getJsonClass() ;
}
