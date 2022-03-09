package RestAssureJava;

import org.testng.Assert;
import org.testng.annotations.Test;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestAssuredGet {
	@Test
	public void getMethod() {
	
		RestAssured.baseURI="http://fakerestapi.azurewebsites.net/api";
		RequestSpecification RS = RestAssured.given();
		Response rp = RS.request(Method.GET,"/Users");
	   String ResponseBody=	rp.getBody().asString();
	   System.out.println("Response is "+ ResponseBody);
	   System.out.println(rp.getStatusCode());

	}



@Given("^I hit the request for get$")
public void i_hit_the_request_for_get(){
	Response resp = 	RestAssured.get("http://fakerestapi.azurewebsites.net/api/Users");
	int code = resp.getStatusCode();
	ResponseBody body = resp.getBody();
	System.out.println("satus code is " +code);
	System.out.println((body.asString()+resp.getTime()+resp.getStatusLine()));
	Assert.assertEquals(code, 200);
    
}
}
