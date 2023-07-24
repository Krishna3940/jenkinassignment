package tes.restapi;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class fifth {

	String token;
	@Test(priority=1)
	public void Token_created()
	{
		RestAssured.baseURI= "https://restful-booker.herokuapp.com/";
		RequestSpecification req= RestAssured.given();
		HashMap<String, String> data=new HashMap<String,String>();
		data.put("username","admin");
		 data.put("password", "password123");
		
		
		Response res = req.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.body(data)
				.when().post("auth").then().extract().response();
		String r= res.asString();
		//pass to JsonPath
		JsonPath js= new JsonPath(r);
		//use get() to read property you required
		token = js.get("token");
//		System.out.println("This is my token : " + token);
		
		res.prettyPeek();
	}
	int bookingID=0;
	@Test(priority=2)
	public void booking1()
	{
		RestAssured.baseURI= "https://restful-booker.herokuapp.com/";
		RequestSpecification req= RestAssured.given();
		/*
		  HashMap<String, String> data=new HashMap<String,String>();
		  data.put("firstname","Jim"); 
		  data.put("lastname", "Brown");
		  data.put("totalprice", "1101");
		  data.put("depositpaid", "true");
		  data.put("checkin", "2018-01-01");
		  data.put("checkout", "2019-01-01");
		*/ 
		String data="{\r\n"
				+ "    \"firstname\" : \"Akash\",\r\n"
				+ "    \"lastname\" : \"yadav\",\r\n"
				+ "    \"totalprice\" : 1111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2022-10-01\",\r\n"
				+ "        \"checkout\" : \"2023-10-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}";
		
		Response res = req.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.body(data)
				.when().post("booking").then().extract().response();
		//convert in string
		String r= res.asString();
		//pass to JsonPath
		JsonPath js= new JsonPath(r);
		//use get() to read property you required
		bookingID = js.get("bookingid");
		System.out.println("This is my booking ID: " + bookingID);
		System.out.println("This is my token : " + token);
		
		res.prettyPeek();
		
	}
	int bookingdate=0;
	@Test(priority=3)
	public void booking_update()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com/";
		RequestSpecification request=RestAssured.given();
		String data="{\r\n"
				+ "    \"firstname\" : \"Akash\",\r\n"
				+ "    \"lastname\" : \"yadav\",\r\n"
				+ "    \"totalprice\" : 11,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2019-10-01\",\r\n"
				+ "        \"checkout\" : \"2023-10-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}";
		Response response = request.header("Accept", "application/json").header("Content-Type","application/json")
				.header("cookie","token"+token)
				.body(data)
		.when().put("booking/:id"+bookingID).then().extract().response();
		String s=response.asString();
		JsonPath js=new JsonPath(s);
		bookingdate = js.get("checkin");
		System.out.println(bookingdate);
		response.prettyPeek();
		System.out.println("Hi there: " + bookingID +" " + token);
		}
//		RestAssured.baseURI= "https://restful-booker.herokuapp.com/";
//		RequestSpecification req= RestAssured.given();
//		HashMap<String, String> header = new HashMap<String, String>();
//		header.put("Accept", "application/json");
//		header.put("Content-Type", "application/json");
//		header.put("Cookie", "token");
//		
//		String data="{\r\n"
//				+ "    \"firstname\" : \"Akash\",\r\n"
//				+ "    \"lastname\" : \"yadav\",\r\n"
//				+ "    \"totalprice\" : 100,\r\n"
//				+ "    \"depositpaid\" : true,\r\n"
//				+ "    \"bookingdates\" : {\r\n"
//				+ "        \"checkin\" : \"2022-10-01\",\r\n"
//				+ "        \"checkout\" : \"2023-10-01\"\r\n"
//				+ "    },\r\n"
//				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
//				+ "}";
//		
//		Response res = req.header("Accept", "application/json")
//				.header("Content-Type", "application/json")
//				.body(data)
//				.when().put("booking/:8986");
//
//		res.prettyPeek();
//		
//	}
}