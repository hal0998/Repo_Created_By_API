package Get_API_Testing_BDD_Approach;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Extract_Response {

	public static void main(String[] args) throws Exception {

		JSONParser parser = new JSONParser();
		JSONObject resobj = (JSONObject) parser.parse(Git_Post_response.post_Resonse());
		System.out.println(resobj.get("full_name"));

	}
}
