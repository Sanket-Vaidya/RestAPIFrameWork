package api.Endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	public static Response CreateUser(User payload) {

		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_url);

		return res;
	}

	public static Response GetUser(String username) {
		Response res = given().accept(ContentType.JSON).pathParam("username", username).when().get(Routes.get_url);

		return res;
	}

	public static Response UpdateUser(User payload) {

		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", payload.getUsername()).body(payload).when().put(Routes.update_url);

		return res;
	}

	public static Response DeleteUser(String username) {
		Response res = given().accept(ContentType.JSON).pathParam("username", username).when()
				.delete(Routes.delete_url);

		return res;
	}

	public static Response PostWithArray(User[] userPayLoad) {
		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(userPayLoad).when()
				.post(Routes.post_withArray);
		return res;
	}

	public static Response GetLogin(String username, String password) {
		Response res = given().accept(ContentType.JSON).queryParam("username", username)
				.queryParam("password", password).when().get(Routes.GetLogin);
		return res;
	}
}
