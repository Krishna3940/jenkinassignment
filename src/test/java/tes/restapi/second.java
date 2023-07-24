package tes.restapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class second {
	 public static void main(String[] args) {
	        // TODO Auto-generated method stub
	        
	        //Step 1 - Define BASE URL
	                RestAssured.baseURI = "https://reqres.in/";
	                
	                //Step 2 - Create Request
	                RequestSpecification req = RestAssured.given();
	                
	                //Define Parameters
	                //BODY
	                //Change Method to POST()
	                
	                String data = "{\r\n"
	                        + "    \"name\": \"morpheus\",\r\n"
	                        + "    \"job\": \"leader\"\r\n"
	                        + "}";
	                
	                //Hitting the req for response
	                
	                Response res = req.header("Accept", "application/json")
	                        .header("Content-Type", "application/json")
	                        .body(data)
	                        .when().post("api/users");
	                
	                res.prettyPeek();



	   }

}
