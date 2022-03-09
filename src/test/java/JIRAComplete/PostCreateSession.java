package JIRAComplete;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostCreateSession {
	
	static String ResponseSessionValue;
	static String Key;
	static String ResponseId;
	
	
@Test(enabled=true)
	
	public void ACreateSession() {
		
		RestAssured.baseURI = "http://localhost:8080/rest/auth/1/session";
		RequestSpecification RS = RestAssured.given();
		
	
		
		RS.header("Content-Type", "application/json");

		JSONObject JSon = new JSONObject();
		
		JSon.put("username", "jhaanjali107");
		JSon.put("password", "Jira@123");
		
		RS.body(JSon.toJSONString());		
		Response rp = RS.request(Method.POST);		
		 ResponseSessionValue = rp.jsonPath().get("session.value");
		System.out.println("\"JSESSIONID=+"+ResponseSessionValue);

}
@Test(enabled=true)
public void BCreateIssue() {
	RestAssured.baseURI = "http://localhost:8080/rest/api/2/issue";
	RequestSpecification RS = RestAssured.given();
	RS.header("Content-Type", "application/json");
	RS.header("Cookie", "JSESSIONID=" + ResponseSessionValue + "");
//	RS.header("Cookie","JSESSIONID=F6996755D2017A517B671E81A8A7CD82");
	System.out.println("method +" +ResponseSessionValue);
RS.body("{\r\n" + 
		"    \"fields\": {\r\n" + 
		"       \"project\":\r\n" + 
		"       {\r\n" + 
		"          \"key\": \"RES\"\r\n" + 
		"       },\r\n" + 
		"       \"summary\": \"Bug_11_JIRARestAssured_for Attachment\",\r\n" + 
		"       \"description\": \"Creating of an Bug using project keys\",\r\n" + 
		"       \"issuetype\": {\r\n" + 
		"          \"name\": \"Bug\"\r\n" + 
		"        \r\n" + 
		"       }\r\n" + 
		"       \r\n" + 
		"   }\r\n" + 
		"}");
	
Response rp = RS.request(Method.POST);
 Key = rp.jsonPath().get("key");
System.out.println(rp.getStatusCode());
System.out.println("key is:"+Key);
System.out.println(rp.getBody().asString());
int statusCode= rp.getStatusCode();
assertEquals(statusCode,201);


}

@Test(enabled=true)
public void CAddComment() {
	
	System.out.println("key is:"+Key);
	
	RestAssured.baseURI = "http://localhost:8080/rest/api/2/issue/" + Key + "/comment";
	System.out.println(RestAssured.baseURI);
	RequestSpecification RS = RestAssured.given();
	RS.header("Cookie", "JSESSIONID=" + ResponseSessionValue + "");
	RS.header("Content-Type", "application/json");
	//RS.pathParam("key", Key);
	RS.header("Cookie", ResponseSessionValue);
	
	JSONObject JSon = new JSONObject();
	
	JSon.put("body", "AddingComment using RestAssured");
		
	RS.body(JSon.toJSONString());
	
Response rp = RS.request(Method.POST);
ResponseId =rp.jsonPath().get("id");
int statusCode= rp.getStatusCode();
System.out.println(rp.getBody().asString());
assertEquals(statusCode,201);

		

}

@Test(enabled=true)
public void dPutUpdateComment() {
	RestAssured.baseURI = "http://localhost:8080/rest/api/2/issue/" + Key + "/comment/"+ResponseId+"";
	RequestSpecification RS = RestAssured.given();
	RS.header("Content-Type", "application/json");
	RS.header("Cookie", "JSESSIONID=" + ResponseSessionValue + "");
//	RS.pathParam("key", Key);
	//RS.pathParam("id", ResponseId);
	RS.header("Cookie", ResponseSessionValue);
	
	JSONObject JSon = new JSONObject();
	
	JSon.put("body", "Updating Comment using RestAssured");
		
	RS.body(JSon.toJSONString());
	
Response rp = RS.request(Method.PUT);
System.out.println(rp.getStatusCode());
int statusCode= rp.getStatusCode();
assertEquals(statusCode,200);
}

@Test(enabled=true)
public void ePostAddAttachment() {
	RestAssured.baseURI = "http://localhost:8080/rest/api/2/issue/" + Key + "/attachments";
	RequestSpecification RS = RestAssured.given();
	RS.header("Cookie", "JSESSIONID=" + ResponseSessionValue + "");
	//RS.pathParam("key", Key);
	//RS.header("Content-Type", "multipart/form-data");
	RS.header("X-Atlassian-Token","nocheck");
	RS.header("Cookie", ResponseSessionValue);
	RS.multiPart("file",new File("E:/Anjali/New folder/Anjali.json"));
	Response rp = RS.request(Method.POST);
	int statusCode= rp.getStatusCode();
	assertEquals(statusCode,200);

}

@Test(enabled=true)
public void fGetFetchIssue() {
	RestAssured.baseURI = "http://localhost:8080/rest/api/2/issue/" + Key + "";
	RequestSpecification RS = RestAssured.given();	
	//RS.pathParam("key", Key);
	RS.header("Cookie", "JSESSIONID=" + ResponseSessionValue + "");
	Response rp = RS.request(Method.GET);
	int statusCode= rp.getStatusCode();
	assertEquals(statusCode,200);

}
//
//@Test(enabled=false)
//public void DeleteIssue() {
//	RestAssured.baseURI = "http://localhost:8080/rest/api/3/issue/10003";
//	RequestSpecification RS = RestAssured.given();
//	RS.header("Content-Type", "application/json");
//	RS.header("Cookie", ResponseSessionValue);
//	
//	JSONObject JSon = new JSONObject();
//	
//	JSon.put("body", "AddingComment using RestAssured");
//		
//	RS.body(JSon.toJSONString());
//	
//Response rp = RS.request(Method.POST);
//
//}

}
