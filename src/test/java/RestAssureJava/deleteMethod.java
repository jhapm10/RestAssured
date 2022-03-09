package RestAssureJava;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class deleteMethod {
	@Test
	public void Delete() {
		
		RequestSpecification RS = RestAssured.given();
		RS.header("Content-Type", "application/json");
		RS.params("id", "9");
		Response rp = RS.delete("http://fakerestapi.azurewebsites.net/api/Users");
		System.out.println(rp.getBody().asString() + rp.getStatusCode());

	}

}
