package api.Endpoints;

public class Routes {

	public static String url="https://petstore.swagger.io/v2/";
	
	//userModule
	
	public static String post_url=url+"user";
	public static String get_url=url+"user/{username}";
	public static String update_url=url+"user/{username}";
	public static String delete_url=url+"user/{username}";
	public static String post_withArray=url+"user/createWithArray";
	public static String GetLogin=url+"user/login";
}
