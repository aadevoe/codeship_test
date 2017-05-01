package com.sematree.elastic.data;

import com.sematree.elastic.data.enums.ActionType;

public class GetRequest extends STESPayload implements STESAction {
	@Override
	public String getAction() {
		return ActionType.GET.value();
	}
}