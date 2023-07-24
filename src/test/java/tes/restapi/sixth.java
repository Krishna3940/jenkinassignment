package tes.restapi;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class sixth {
    
    int id = 0;
    
    @Test
    (priority =1)
    public void postData() {
        // TODO Auto-generated method stub
                // 1. Defining BASE URL
                
                RestAssured.baseURI = "https://gorest.co.in";



               // 2. Create a Req
                //RequestSpecification req = RestAssured.given();
                
                //3. Define parameters
                
                Map<String,String> mp = new HashMap<String,String>();
                mp.put("name", "Tenali Ramakrishna");
                mp.put("gender", "male");
                mp.put("email", "dfddddddhs@15ce.com");
                mp.put("status", "active");
                
//                String data = "{\"name\":\"Tenali Ramakrishna\", \"gender\":\"male\", \"email\":\"abh896dheh@15ce.com\", \"status\":\"active\"}";
                
                Response res = RestAssured.given().header("Content-Type","application/json")
                        .header("Accept","application/json")
                        .header("Authorization","Bearer 9b51de806a935c22556ebd63476235872a88b94fb8981819cb9148e29841796f")
                        .body(mp)
                        .when().post("public/v2/users").then().log().headers().assertThat().statusCode(422).extract().response();
                
                String strResp = res.asString();
                JsonPath js = new JsonPath(strResp);
                id = js.get("id");
                String name = js.get("name");
                System.out.println("NAme is "+name);
                
                Assert.assertEquals(name, "Tenali Ramakrishna");
                
                
                
            



   }
    @Test
    (priority =2)
    public void updateUser()
    {
        RestAssured.baseURI="http://gorest.co.in/";
       
        Map<String,String>mp= new HashMap<String,String>();
        mp.put("name", "Tenali Ramakrishna");
        mp.put("gender", "male");
        mp.put("email", "fjssjfrghajrk@15ce.com");
        mp.put("status", "active");
        
        Response res = RestAssured.given().header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorization","Bearer 9b51de806a935c22556ebd63476235872a88b94fb8981819cb9148e29841796f")
                .body(mp)
                .when().put("public/v2/users/"+id).then().log().body().extract().response();
        
        
    }



}