package RestAssureJava;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import groovy.json.StringEscapeUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class PuT {
	
	@Test
	public void PutMethos() {
		//specify BASE URI
		RestAssured.baseURI = "http://fakerestapi.azurewebsites.net/api";
		//Request Object Created of class RequestSpecification
		RequestSpecification RS = RestAssured.given();
		RS.param("id", "9");
		
		RS.header("Content-Type", "application/json");

		JSONObject JSon = new JSONObject();
		
		JSon.put("ID", 1234);
		JSon.put("UserName", "PutAnjali");
		JSon.put("Password", "Putpass");
		RS.body(JSon.toJSONString());

		//Response Object (where Respons is class and RP is variable reference)
		Response rp = RS.request(Method.PUT, "/Users");
		RS.param("id", "9");
		//if param not passing then directly we can write as below
		//Response rp = RS.request(Method.PUT, "/Users?id=9");

		
		
		String ResponseBody = rp.getBody().asString();
		System.out.println(ResponseBody);
		
		String ResponseId = rp.jsonPath().get("UserName");
		Assert.assertEquals(ResponseId, "PutAnjali");
		
		System.out.println(rp.getStatusCode());
		
	}
	@Test(enabled=false)
	public void PutAndDeleteMethods() throws ParseException {

		RequestSpecification RS = RestAssured.given();
		RS.header("Content-Type", "application/json");

		System.out.println("{" + "Hi" + "}");

		RestAssuredPost RPost = new RestAssuredPost();

		RS.params("id", "9");

		Response rp = RS.put("http://fakerestapi.azurewebsites.net/api/Users");

		System.out.println(rp.getBody().asString() + rp.getStatusCode());

	}
}
