package com.sematree.elastic.data;

import com.sematree.elastic.data.enums.ActionType;

public class CreateRequest extends STESPayload implements STESAction{
	@Override
	public String getAction() {
		return ActionType.CREATE.value();
	}
}
