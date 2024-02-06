package api.test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;

public class TC_002_UserWithArray {

	Faker faker;
	User[] userPayLoad;

	@BeforeClass
	public void CreateArray() {
		userPayLoad = new User[5];
		faker = new Faker();
		for (int i = 0; i < userPayLoad.length; i++) {
			User user = new User();
			user.setEmail(faker.internet().safeEmailAddress());
			user.setFirstName(faker.name().firstName());
			user.setLastName(faker.name().lastName());
			user.setPassword(faker.internet().password(5, 10, true, true, true));
			user.setPhone(faker.phoneNumber().cellPhone());
			user.setUsername(faker.name().username());
			userPayLoad[i] = user;
		}
	}

	@Test(priority = 1)
	public void Post_WithArray() {
		System.out.println(userPayLoad[1].getUsername());
		System.out.println(userPayLoad[1].getPassword());
		Response response = UserEndpoints.PostWithArray(userPayLoad);
		response.then().statusCode(200).log().all();

		Assert.assertEquals(response.body().jsonPath().get("message"), "ok");
	}

	@Test(priority = 2)
	public void GetLogin() {
		Response response;
		for (User user : userPayLoad) {
			response = UserEndpoints.GetLogin(user.getUsername(), user.getPassword());
			response.then().statusCode(200).log().all();
			Assert.assertEquals(response.body().jsonPath().get("message").toString().contains("logged in"), true);
		}

	}

}
