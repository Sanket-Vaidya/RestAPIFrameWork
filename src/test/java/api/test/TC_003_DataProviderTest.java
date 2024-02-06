package api.test;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import api.Endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import api.utilities.ExcelUtility;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_003_DataProviderTest {
	ArrayList<User> userPayload;
	ExcelUtility excel;
	public Logger logger;

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void SetData(String id, String username, String fname, String lname, String email, String password,
			String phone) throws IOException {
		logger = LogManager.getLogger(this.getClass());
		userPayload = new ArrayList<User>();
		excel = new ExcelUtility(System.getProperty("user.dir") + "\\testdata\\Userdata.xlsx");
		int rownum = excel.getRowCount("Sheet1");
		User user;
		for (int i = 0; i < rownum; i++) {
			user = new User();
			user.setUsername(username);
			user.setFirstName(fname);
			user.setLastName(lname);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhone(phone);
			userPayload.add(user);

		}

	}

	@Test(priority = 2)
	public void PostUser() {
		logger.info("********************Run Messages*********************");
		for (User user : userPayload) {
			Response response = UserEndpoints.CreateUser(user);
			response.then().statusCode(200).log().all();
		}
	}

}
