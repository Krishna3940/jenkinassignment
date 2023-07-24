package tes.restapi;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class fourth {
	String token;
	@Test(priority=1)
	public void token() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com/";
		RequestSpecification request=RestAssured.given();
//		String data="{\r\n"
//		+ "    \"username\" : \"admin\",\r\n"
//		+ "    \"password\" : \"password123\"\r\n"
//		+ "}";
        String data="{\r\n" + 
        		"    \"username\" : \"admin\",\r\n" + 
        		"    \"password\" : \"password123\"\r\n" + 
        		"}";
//		HashMap<String, String> data=new HashMap<String,String>();
//		data.put("username","admin");
//		 data.put("password", "password123");
		Response res = request.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.body(data)
				.when().post("auth").then().extract().response();
		String r= res.asString();
		//pass to JsonPath
		JsonPath js= new JsonPath(r);
		//use get() to read property you required
		token = js.get("token");
	    System.out.println("This is my token : " + token);
		res.prettyPeek();
		
		}
	int bookingId=0;
	@Test(priority=2)
	public void createbooking() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com/";
		RequestSpecification request=RestAssured.given();
		String data="{\r\n" + 
				"    \"firstname\" : \"Jim\",\r\n" + 
				"    \"lastname\" : \"Brown\",\r\n" + 
				"    \"totalprice\" : 111,\r\n" + 
				"    \"depositpaid\" : true,\r\n" + 
				"    \"bookingdates\" : {\r\n" + 
				"        \"checkin\" : \"2018-01-01\",\r\n" + 
				"        \"checkout\" : \"2019-01-01\"\r\n" + 
				"    },\r\n" + 
				"    \"additionalneeds\" : \"Breakfast\"\r\n" + 
				"}";
		Response response = request.header("Content-Type","application/json")
				.body(data)
		.when().post("booking").then().extract().response();
		String r= response.asString();
		//pass to JsonPath
		JsonPath js= new JsonPath(r);
		//use get() to read property you required
		bookingId = js.get("bookingid");
		System.out.println("This is my booking ID: " + bookingId);
		System.out.println("This is my token : " + token);
		
		response.prettyPeek();
		
		}
	@Test(priority=3)
	public void updatebooking() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com/";
		RequestSpecification request=RestAssured.given();
		String data="{\r\n" + 
				"    \"firstname\" : \"James\",\r\n" + 
				"    \"lastname\" : \"Brown\",\r\n" + 
				"    \"totalprice\" : 111,\r\n" + 
				"    \"depositpaid\" : true,\r\n" + 
				"    \"bookingdates\" : {\r\n" + 
				"        \"checkin\" : \"2018-01-01\",\r\n" + 
				"        \"checkout\" : \"2020-01-01\"\r\n" + 
				"    },\r\n" + 
				"    \"additionalneeds\" : \"Breakfast\"\r\n" + 
				"}";
		Response response = request.header("Accept", "application/json")
				.header("Content-Type","application/json")
				.header("Cookies","token"+token)
				.body(data)
		.when().put("booking/:id"+bookingId).then().extract().response();
		String r= response.asString();
		//pass to JsonPath
		JsonPath js= new JsonPath(r);
		//use get() to read property you required
		bookingId = js.get("bookingid");
		System.out.println("This is my booking ID: " + bookingId);
		System.out.println("This is my token : " + token);
		
		response.prettyPeek();
		
		
		
		}
	
	


}