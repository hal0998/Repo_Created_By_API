package Get_API_Testing_BDD_Approach;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Git_Hub_End_To_End_Testing {

	public static void main(String[] args) throws Exception {
		String repoName="created_By_restAssured2023";
		String rename="created_By_restAssured2024";
		
		String pathParam = "/user/repos";
		String Token="74c5640a0917aa402622152386775b99c2c99963";

		
		JSONObject body = new JSONObject();
		body.put("name",repoName);

		
		RestAssured.baseURI = "https://api.github.com";

		// create new repo
		
		String res = given().accept("application/json").contentType("application/json").auth()
				.oauth2(Token).body(body.toString())
				.when().post(pathParam).then()
				.statusCode(201)
				.and().log().body().extract().asString();
        System.out.println("The JSON Format Response Body is Below /n"+res);
		
		JSONParser parser = new JSONParser();
		JSONObject resobj = (JSONObject) parser.parse(res);
		pathParam=resobj.get("full_name").toString();
		
		System.out.println("The extracted repo full name is "+pathParam);
		Thread.sleep(10000);
		
	 // Get ind repo dtls
		
		given().auth().oauth2(Token)
		.when().get("/repos/"+pathParam)
		.then().statusCode(200)
		.and().body("full_name", equalTo(pathParam))
		.and().log().status()
		.and().log().body().extract();
		Thread.sleep(1000);
		
     // update repo name
		
		JSONObject reqobj=new JSONObject();
		reqobj.put("name", rename);
		
		String putres=given().contentType("application/json").accept("application/json")
		.auth().oauth2(Token).body(reqobj.toString())
		.when().patch("/repos/"+pathParam)
		.then().statusCode(200)
		.log().status().and().log().body().extract().asString();
		
	   JSONObject putresbody=(JSONObject)parser.parse(putres);
	   pathParam = putresbody.get("full_name").toString();
	   
		Thread.sleep(3000);
	 // delete individual repo
		
		given().auth().oauth2(Token)
		.when()
		.delete("repos/"+pathParam)
		.then().statusCode(204)
		.log().status();
		
		
		
	}

}
