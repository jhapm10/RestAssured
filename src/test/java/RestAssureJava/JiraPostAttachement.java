package RestAssureJava;

import java.io.File;
import java.io.FileInputStream;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JiraPostAttachement {
	@Test
	public void testPostAttachement() {
		
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/issue/RES-7/attachments";
		RequestSpecification RS = RestAssured.given();
		//RS.header("Content-Type", "multipart/form-data");
		RS.header("X-Atlassian-Token","nocheck");
		RS.header("Cookie", "JSESSIONID=F6996755D2017A517B671E81A8A7CD82");
		RS.multiPart("file",new File("E:/Anjali/New folder/Anjali.json"));
		Response rp = RS.request(Method.POST);
		
System.out.println(rp.getBody().asString());
System.out.println(rp.getStatusCode());
	}

	
}
