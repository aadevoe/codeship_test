package com.sematree.elastic.data;

import com.sematree.elastic.data.enums.ActionType;

public class SearchRequest extends STESPayload {

	@Override
	public String getAction() {
		return ActionType.SEARCH.value();
	}
	
}
