package com.sematree.elastic.utils;

import org.apache.commons.lang3.StringUtils;

import com.sematree.elastic.data.STESAction;
import com.sematree.elastic.data.STESPayload;
import com.sematree.elastic.data.enums.ActionType;

import io.searchbox.action.BulkableAction;
import io.searchbox.core.Delete;
import io.searchbox.core.DeleteByQuery;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

public class JestUtils {
	
	@SuppressWarnings("rawtypes")
	public static Object convert(STESAction action) {
		Object jestAction = null;
		
		String actionToPerform = action.getAction();
		
		STESPayload payload = (STESPayload) action;
		
		String source = payload.getPayload();
		
		if (source != null && source.contains("\n")) {
			source = source.replace("\n", "");
		}
		
		String index = payload.getIndex();
		String type = payload.getType();
		String id = payload.getId();
		String query = payload.getQuery();
		
		if (ActionType.CREATE.value().equalsIgnoreCase(actionToPerform)) {
			Index newDoc = new Index.Builder(source).index(index).type(type).id(id).build();
			
			jestAction = (BulkableAction) newDoc;
		}else if (ActionType.UPDATE.value().equalsIgnoreCase(actionToPerform)) {
			Index updatedDoc = new Index.Builder(source).index(index).type(type).id(id).build();
			
			jestAction = (BulkableAction) updatedDoc;
		}else if (ActionType.DELETE.value().equalsIgnoreCase(actionToPerform)) {
			if (StringUtils.isEmpty(query)) {
				Delete docToDelete = new Delete.Builder(id).index(index).type(type).build();
				
				jestAction = (BulkableAction) docToDelete;
			}else {
				DeleteByQuery deleteByQuery;
				DeleteByQuery.Builder builder = new DeleteByQuery.Builder(query).addIndex(index);
				
				if (!StringUtils.isEmpty(type)) {
					builder.addType(type);
				}
				
				deleteByQuery = builder.build();
				
				jestAction = deleteByQuery;
			}
		} else if (ActionType.SEARCH.value().equalsIgnoreCase(actionToPerform)) {
			if (StringUtils.isEmpty(query)) {
				
				jestAction = null;
			}else {
				Search search;
				Search.Builder builder = new Search.Builder(query).addIndex(index);
				
				if (!StringUtils.isEmpty(type)) {
					builder.addType(type);
				}
				
				search = builder.build();
				
				jestAction = search;
			}
		}
		else if (ActionType.GET.value().equalsIgnoreCase(actionToPerform)) {
			Get docToGet = new Get.Builder(index, id).type(type).build();
			
			jestAction = docToGet;
		}
		
		return jestAction;
	}
}
