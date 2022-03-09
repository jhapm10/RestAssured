package RestAssureJava;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestAssuredPost {
	// public String b;

	// Rest Assure
	// TestNG
	// JsonSimple
	// ApachePOI
	@Test
	public void otherMethod() {
		RestAssured.baseURI = "http://fakerestapi.azurewebsites.net/api";
		RequestSpecification RS = RestAssured.given();

		JSONObject JSon = new JSONObject();
		// send request in json format
		JSon.put("ID", 1234);
		JSon.put("UserName", "Anjali");
		JSon.put("Password", "1234password");
		RS.header("Content-Type", "application/json");
		// to change json object into json format)
		RS.body(JSon.toJSONString());
		// Users is kind of path parameter
		Response rp = RS.request(Method.POST, "/Users");
		// get body as string
		String ResponseBody = rp.getBody().asString();
		System.out.println(ResponseBody);
		/// get response value
		//jsonPath JSONPath creates a uniform standard and
		//syntax to define different parts of a JSON document. JSONPath
		//defines expressions to traverse through a JSON document to reach to a subset of the JSON.
		int ResponseId = rp.jsonPath().get("ID");
		Assert.assertEquals(ResponseId, 1234);
	}

	@Test(enabled = false)
	@Given("^I hit Post Request by hitting Body param as \"([^\"]*)\" and \"([^\"]*)\" and response message as \"([^\"]*)\"$")
	public void i_hit_Post_Request_by_hitting_Body_param_as_and_and_response_message_as(String arg1, String arg2,
			String arg3) {
		// Write code here that turns the phrase above into concrete actions
		RequestSpecification RS = RestAssured.given();
		RS.header("Content-Type", "application/json");

		// Body
		JSONObject JSon = new JSONObject();
		JSon.put("ID", arg1);
		JSon.put("UserName", arg2);
		JSon.put("Password", arg3);

		RS.body(JSon.toString());

		Response rp = RS.post("http://fakerestapi.azurewebsites.net/api/Users");
		Headers allHeaders = rp.headers(); // get all headers
		for (Header AllHeaders : allHeaders) {
			// System.out.println(AllHeaders);
			System.out.println(AllHeaders.getName() + "=" + AllHeaders.getValue());
		}
		System.out.println(rp.getBody().asString() + rp.getStatusCode() + rp.getHeader("Content-Type"));// get specific
																										// header

	}

}
