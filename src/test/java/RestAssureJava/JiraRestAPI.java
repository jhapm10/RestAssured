package RestAssureJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class JiraRestAPI {
	
	@Test(enabled=false)
	
	public void CreateSession() {
		
		RestAssured.baseURI = "http://localhost:8080/rest/auth/1/session";
		RequestSpecification RS = RestAssured.given();
	
		
		RS.header("Content-Type", "application/json");

		JSONObject JSon = new JSONObject();
		
		JSon.put("username", "jhaanjali107");
		JSon.put("password", "Jira@123");
		
		RS.body(JSon.toJSONString());		
		Response rp = RS.request(Method.POST);		
		String ResponseSessionValue = rp.jsonPath().get("session.value");
		System.out.println(ResponseSessionValue);

}
	@Test(enabled=true)
	public void CreateBug() throws ParseException, FileNotFoundException {
		
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/issue";
		RequestSpecification RS = RestAssured.given();
		RS.header("Content-Type", "application/json");
		RS.header("Cookie", "JSESSIONID=F6996755D2017A517B671E81A8A7CD82");
//		FileInputStream file = new FileInputStream("E:/Anjali/New folder/Anjali.json");
//	
//		RS.body(file);
	
	RS.body("{\r\n" + 
			"    \"fields\": {\r\n" + 
			"       \"project\":\r\n" + 
			"       {\r\n" + 
			"          \"key\": \"RES\"\r\n" + 
			"       },\r\n" + 
			"       \"summary\": \"Bug_11_JIRARestAssured_FromEclipseAssure\",\r\n" + 
			"       \"description\": \"Creating of an Bug using project keys\",\r\n" + 
			"       \"issuetype\": {\r\n" + 
			"          \"name\": \"Bug\"\r\n" + 
			"        \r\n" + 
			"       }\r\n" + 
			"       \r\n" + 
			"   }\r\n" + 
			"}");
		
	Response rp = RS.request(Method.POST);
	String ResponseSessionValue = rp.jsonPath().get("key");
	System.out.println(ResponseSessionValue);
	
		}
	
	
}
