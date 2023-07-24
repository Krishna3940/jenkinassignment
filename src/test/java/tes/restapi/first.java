package tes.restapi;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class first {

	 public static void main(String[] args)
	 {
		RestAssured.baseURI = "https://reqres.in/";
		 HashMap<String, String> headers= new  HashMap<String, String>();
         headers.put("Accept", "application/json");
         headers.put("Content-Type", "application/json");
	 
		RequestSpecification req = RestAssured.given();
		
		/*Response res = req.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.when().get("api/users");*/
		Response res = req.headers(headers)
				.when().get("api/users");
		
		    res.prettyPeek();;
		
		

	 }

}
