package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class TC_001_UserTest {
	Faker faker;
	User userPayload;

	@BeforeClass
	public void SetupData() {
		faker = new Faker();
		userPayload = new User();
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}

	@Test(priority = 1)
	public void TestPostUser() {
		Response res = UserEndpoints.CreateUser(userPayload);
		res.then().log().all();

		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void TestGetUser() {
		Response response = UserEndpoints.GetUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void TestUpdateUser() {
		userPayload.setFirstName(faker.name().firstName());
		Response response = UserEndpoints.UpdateUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 4)
	public void GetUpdateUser() {
		TestGetUser();
	}

	@Test(priority = 5)
	public void TestDeleteUser() {
		Response response = UserEndpoints.DeleteUser(this.userPayload.getUsername());
		response.then().statusCode(200).log().all();
	}

	@Test(priority = 6)
	public void TestCheckUserDeleted() {
		Response response = UserEndpoints.GetUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 404);
	}

}
