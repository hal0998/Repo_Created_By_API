package API_Testing_BDD_Approach;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BDD_API_Requests {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://api.github.com";
		RestAssured.basePath = "/repos/hal0998/Testing";

		given().auth().oauth2("74c5640a0917aa402622152386775b99c2c99963")
		.when().get()
		.then().statusCode(200)
		.and().body("id", equalTo(188739720),"Validate repository Id")
		.log().headers()
		.and().log().body().extract();

	}

}
