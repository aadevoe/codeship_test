package com.sematree.elastic.processor;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import io.searchbox.action.BulkableAction;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.client.http.JestHttpClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.DeleteByQuery;
import io.searchbox.core.Get;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import com.google.gson.GsonBuilder;
import com.sematree.elastic.data.STESAction;
import com.sematree.elastic.data.STESActionResponse;
import com.sematree.elastic.data.STESPayload;
import com.sematree.elastic.data.enums.ActionType;
import com.sematree.elastic.utils.JestUtils;
import com.sematree.elastic.utils.MainConfig;

public class STESClient {
	JestHttpClient client = null;
	String jodaDateFormat = "MMM d, yyyy h:mm:ss a";
	MainConfig config = MainConfig.getInstance();
	
	public STESClient() {
		JestClientFactory factory = new JestClientFactory();
		 
		String serverUrl = config.getString("server.url");
		
		factory.setHttpClientConfig(new HttpClientConfig
		                        .Builder(serverUrl)
		                        .multiThreaded(true)
		                        .gson(new GsonBuilder().setDateFormat(jodaDateFormat).create())
		                        .build());
		 
		 client = (JestHttpClient) factory.getObject();
	}
	
	public void close() {
		client.shutdownClient();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public STESActionResponse executeAction(STESAction action) {
		boolean success = false;
		String errorMessage = null;
		Object doc = null;
		
		String actionToPerform = action.getAction();
		
		STESPayload payload = (STESPayload) action;
		
		String index = payload.getIndex();
		String type = payload.getType();
		String id = payload.getId();
		String query = payload.getQuery();
		
		if (ActionType.GET.value().equalsIgnoreCase(actionToPerform)) {
			if (payload.getJsonClass() == null) {
				errorMessage = "No JSON class specified";
				
				return new STESActionResponse(success, errorMessage, doc);
			}
			
			Get docToGet = (Get) JestUtils.convert(action);
			
			try {
				JestResult result = client.execute(docToGet);
				
				success = result.isSucceeded();
				
				if (success) {
					doc = result.getSourceAsObject(payload.getJsonClass());
					
					if (doc == null) {
						success = false;
						
						String className = payload.getJsonClass().getSimpleName();
						errorMessage = "Unable to create document for class: " + className;
					}
				}else {
					errorMessage = result.getErrorMessage();
				}
			}catch (Exception e) {
				System.err.println("Error getting document " + index + "/" + type + "/" + id + ": ");
				e.printStackTrace();
				
				success = false;
				errorMessage = e.getMessage();
			}
		}else if (ActionType.DELETE.value().equalsIgnoreCase(actionToPerform) && !StringUtils.isEmpty(query)) {
			DeleteByQuery deleteByQuery = (DeleteByQuery) JestUtils.convert(action);
			
			try {
				JestResult result = client.execute(deleteByQuery);
				
				success = result.isSucceeded();
				
				if (!success) {
					errorMessage = result.getErrorMessage();
				}
			}catch (Exception e) {
				System.err.println("Error deleting by query for " + index + "/" + type + " : ");
				e.printStackTrace();
			}
		}else if (ActionType.SEARCH.value().equalsIgnoreCase(actionToPerform) ) {
			
			Search search = (Search) JestUtils.convert(action);
			try {
				JestResult result = client.execute(search);
				success = result.isSucceeded();
				Class xx = action.getJsonClass();
				List lst = result.getSourceAsObjectList(xx);
				if (!success) {
					errorMessage = result.getErrorMessage();
				}
				doc = lst;
			}catch (Exception e) {
				System.err.println("Error deleting by query for " + index + "/" + type + " : ");
				e.printStackTrace();
			}
		}
		
		
		else {
			BulkableAction actionToTake = (BulkableAction) JestUtils.convert(action);
			
			try {
				JestResult result = client.execute(actionToTake);
				
				success = result.isSucceeded();
				
				if (!success) {
					errorMessage = result.getErrorMessage();
				}
			}catch (Exception e) {
				System.err.println("Error " + actionToPerform + " document " + index + "/" + type + "/" + id + ": ");
				e.printStackTrace();
			}
		}
		
		return new STESActionResponse(success, errorMessage, doc);
	}
	
	@SuppressWarnings("rawtypes")
	public STESActionResponse executeActions(List<STESAction> actions) {
		boolean success = false;
		String errorMessage = null;
		Object doc = null;
		
		boolean actionAdded = false;
		
		Builder bulkOperations = new Bulk.Builder();
		
		for (STESAction action : actions) {
			String actionToPerform = action.getAction();
			
			if (!ActionType.GET.value().equalsIgnoreCase(actionToPerform)) {
				BulkableAction jestAction = (BulkableAction) JestUtils.convert(action);
				
				bulkOperations.addAction(jestAction);
				actionAdded = true;
			}
		}
		
		if(!actionAdded) {
			return new STESActionResponse(actionAdded, "Only GET requests were sent",doc);
		}
		
		try {
			JestResult result = client.execute(bulkOperations.build());
			
			success = result.isSucceeded();
			
			if (!success) {
				errorMessage = result.getErrorMessage();
			}
		}catch (Exception e) {
			System.err.println("Error performing Bulk operations: ");
			e.printStackTrace();
		}
		
		return new STESActionResponse(success, errorMessage, doc);
	}
	
	public SearchResult execute(Search query) {
		SearchResult result = null;
		
		try {
			result = client.execute(query);
		}catch (Exception e) {
			System.err.println("Error performing Search query: ");
			e.printStackTrace();
		}
		
		return result;
	}
}
