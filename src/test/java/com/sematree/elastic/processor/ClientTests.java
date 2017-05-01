package com.sematree.elastic.processor;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sematree.elastic.data.STESAction;
import com.sematree.elastic.data.STESActionResponse;
import com.sematree.elastic.data.CreateRequest;
import com.sematree.elastic.data.DeleteRequest;
import com.sematree.elastic.data.GetRequest;
import com.sematree.elastic.data.STDocument;
import com.sematree.elastic.data.SearchRequest;
import com.sematree.elastic.data.UpdateRequest;
import com.sematree.elastic.processor.STESClient;

public class ClientTests {
	STESClient client = null;
	private static String INDEXNAME="tests";
	
	@Before
	public void setup() {
		client = new STESClient();
	}
	
	@After
	public void tearDown() {
		client.close();
		client = null;
	}
	
	@Test
	public void createNewDoc() {
		String payload = getExamplePayload();
		
		String index = INDEXNAME;
		String type = "client";
		String id = "Test1";
		
		CreateRequest createRequest = new CreateRequest();
		
		createRequest.setPayload(payload);
		createRequest.setIndex(index);
		createRequest.setType(type);
		createRequest.setId(id);
		
		STESActionResponse response = client.executeAction(createRequest);
		
		boolean isCreated = response.isSucceeded();
		
		System.out.println("Is Created? " + isCreated);
		assertTrue(response.errorMessage(), isCreated);
	}
	
	@Test
	public void updateExistingDoc() {
		String payload = getExamplePayload();
		
		// Update a value
		payload = payload.replace("Derek Kaczmarek", "Mikey Judd");
		
		String index = INDEXNAME;
		String type = "client";
		String id = "Test1";
		
		UpdateRequest updateRequest = new UpdateRequest();
		
		updateRequest.setPayload(payload);
		updateRequest.setIndex(index);
		updateRequest.setType(type);
		updateRequest.setId(id);
		
		STESActionResponse response = client.executeAction(updateRequest);
		
		boolean isUpdated = response.isSucceeded();
		
		System.out.println("Is Updated? " + isUpdated);
		assertTrue(response.errorMessage(), isUpdated);
	}
	
	@Test
	public void deleteExistingDoc() {
		String index = INDEXNAME;
		String type = "client";
		String id = "Test1";
		
		DeleteRequest deleteRequest = new DeleteRequest();
		
		deleteRequest.setIndex(index);
		deleteRequest.setType(type);
		deleteRequest.setId(id);
		
		STESActionResponse response = client.executeAction(deleteRequest);
		
		boolean isDeleted = response.isSucceeded();
		
		System.out.println("Is Deleted? " + isDeleted);
		assertTrue(response.errorMessage(), isDeleted);
	}
	
	@Test
	public void getExistingDoc() {
		String index = INDEXNAME;
		String type = "client";
		String id = "Test1";
		
		GetRequest getRequest = new GetRequest();
		
		getRequest.setIndex(index);
		getRequest.setType(type);
		getRequest.setId(id);
		getRequest.setJsonClass(STDocument.class);
		
		STESActionResponse response = client.executeAction(getRequest);
		
		boolean gotDoc = response.isSucceeded();
		
		System.out.println("Got doc? " + gotDoc);
		
		assertTrue(response.errorMessage(), gotDoc);
		
		STDocument doc = (STDocument) response.getDocument();
		
		System.out.println("Document Name: " + doc.getMetadata().getDocumentName());
		System.out.println("Applicant's Name: " + doc.getApplicantName());
		System.out.println("Address: " + doc.getApplicantAddress());
	}
	
	@Test
	public void bulkCreate() {
		List<STESAction> actions = new ArrayList<>();
		
		String payload = getExamplePayload();
		
		String index = INDEXNAME;
		String type = "client";
		String id = "Test1";
		
		CreateRequest createRequest = new CreateRequest();
		
		createRequest.setPayload(payload);
		createRequest.setIndex(index);
		createRequest.setType(type);
		createRequest.setId(id);
		
		actions.add(createRequest);
		
		id = "Test2";
		
		createRequest = new CreateRequest();
		
		createRequest.setPayload(payload);
		createRequest.setIndex(index);
		createRequest.setType(type);
		createRequest.setId(id);
		
		actions.add(createRequest);
		
		id = "Test3";
		
		createRequest = new CreateRequest();
		
		createRequest.setPayload(payload);
		createRequest.setIndex(index);
		createRequest.setType(type);
		createRequest.setId(id);
		
		//actions.add(createRequest);
		
		STESActionResponse response = client.executeAction(createRequest);
		
		boolean isSuccessful = response.isSucceeded();
		
		System.out.println("Executed bulk operations? " + isSuccessful);
		
		assertTrue(response.errorMessage(), isSuccessful);
		
		SearchRequest srch = new SearchRequest();
		srch.setIndex(index);
		srch.setType(type);
		srch.setJsonClass(PayloadPojo.class);
		
		
		
		String rn = "CER";
		String q = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : { \"testProcedure\" : \"" + rn + "\" }\n" +
                "    }\n" +
                "}";
		srch.setQuery(q);
		STESActionResponse rSrch = client.executeAction(srch);
		List<PayloadPojo> lst = (List<PayloadPojo>) rSrch.getDocument();
		assertTrue(rSrch.isSucceeded());
		assertTrue(lst != null && lst.size() > 0);
		
	}
	
	@Test
	public void bulkUpdate() {
		List<STESAction> actions = new ArrayList<>();
		
		String payload = getExamplePayload();
		
		// Update a value
		payload = payload.replace("Derek Kaczmarek", "Test1 Name");
		
		String index = INDEXNAME;
		String type = "client";
		String id1 = "Test1";
		String id2 = "Test2";
		String id3 = "Test3";
		
		UpdateRequest updateRequest = new UpdateRequest();
		
		updateRequest.setPayload(payload);
		updateRequest.setIndex(index);
		updateRequest.setType(type);
		updateRequest.setId(id1);
		
		actions.add(updateRequest);
		
		updateRequest = new UpdateRequest();
		
		payload = payload.replace("Test1 Name", "Test2 Name");
		
		updateRequest.setPayload(payload);
		updateRequest.setIndex(index);
		updateRequest.setType(type);
		updateRequest.setId(id2);
		
		actions.add(updateRequest);
		
		updateRequest = new UpdateRequest();
		
		payload = payload.replace("Test2 Name", "Test3 Name");
		
		updateRequest.setPayload(payload);
		updateRequest.setIndex(index);
		updateRequest.setType(type);
		updateRequest.setId(id3);
		
		actions.add(updateRequest);
		
		STESActionResponse response = client.executeActions(actions);
		
		boolean isSuccessful = response.isSucceeded();
		
		System.out.println("Executed bulk operations? " + isSuccessful);
		
		assertTrue(response.errorMessage(), isSuccessful);
	}
	
	@Test
	public void bulkDelete() {
		List<STESAction> actions = new ArrayList<>();
		
		String index = INDEXNAME;
		String type = "client";
		String id1 = "Test1";
		String id2 = "Test2";
		String id3 = "Test3";
		
		DeleteRequest deleteRequest = new DeleteRequest();
		
		deleteRequest.setIndex(index);
		deleteRequest.setType(type);
		deleteRequest.setId(id1);
		
		actions.add(deleteRequest);
		
		deleteRequest = new DeleteRequest();
		
		deleteRequest.setIndex(index);
		deleteRequest.setType(type);
		deleteRequest.setId(id2);
		
		actions.add(deleteRequest);
		
		deleteRequest = new DeleteRequest();
		
		deleteRequest.setIndex(index);
		deleteRequest.setType(type);
		deleteRequest.setId(id3);
		
		actions.add(deleteRequest);
		
		STESActionResponse response = client.executeActions(actions);
		
		boolean isSuccessful = response.isSucceeded();
		
		System.out.println("Executed bulk operations? " + isSuccessful);
		
		assertTrue(response.errorMessage(), isSuccessful);
	}
	
	@Test
	public void deleteByQuery() {
		String index = "mobiex";
		String type = "ForecastKPI";
		String kpiPeriodKey = "20170";
		
		String deleteQuery = "{\n" +
                "    \"query\" : {\n" +
                "        \"term\" : { \"KPIPeriodKey\" : " + kpiPeriodKey + " }\n" +
                "    }\n" +
                "}";
		
		DeleteRequest deleteRequest = new DeleteRequest();
		
		deleteRequest.setIndex(index);
		deleteRequest.setType(type);
		deleteRequest.setQuery(deleteQuery);
		
		STESActionResponse response = client.executeAction(deleteRequest);
		
		boolean isSuccessful = response.isSucceeded();
		
		System.out.println("Delete successful? " + isSuccessful);
		
		assertTrue(response.errorMessage(), isSuccessful);
	}
	
	private String getExamplePayload() {
		File file = new File("./src/test/java/com/sematree/elastic/processor/payload.txt");
		
		String payload = null;
		
		try {
			payload = FileUtils.readFileToString(file, "UTF-8");
			
			PayloadPojo pp = (PayloadPojo) fromJson(payload, PayloadPojo.class);
			String j = toJson(pp);
			System.out.println(j);
			
		}catch(Exception e) {
			System.err.println("Error reading file");
			e.printStackTrace();
		}
		
		return payload;
	}


	private static Gson gson;
	static {
		gson = new GsonBuilder()
//			.setDateFormat("yyyy-MM-dd")
			.setPrettyPrinting()
			.create();
	}
 	public static String toJson(Object obj) {
		Gson gson = new GsonBuilder()
		//.setExclusionStrategies(theExclusionStrategy)
		.setPrettyPrinting()
		.create();
		String json = null;
		try {
			if (obj == null) {
				System.err.println("Serializing obj is null");
			} else {
				json = gson.toJson(obj);
			}
		} catch (Throwable e) {
			System.err.println("Error ser to :" + obj.getClass().getName());
			System.err.println(json);
			System.err.println(e);
		}
		return json;
	}
	
	public static Object fromJson(String json, Class clazz) {
		Object obj = null;
		try {
			obj = gson.fromJson(json, clazz);
		} catch (Throwable e) {
			System.err.println("Error deserializing to :" + clazz.getName());
			System.err.println(json);
			System.err.println(e);
		}
		return obj;
	}

}
