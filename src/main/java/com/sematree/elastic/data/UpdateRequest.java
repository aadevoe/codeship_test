package com.sematree.elastic.data;

import com.sematree.elastic.data.enums.ActionType;

public class UpdateRequest extends STESPayload implements STESAction {
	@Override
	public String getAction() {
		return ActionType.UPDATE.value();
	}
}
