package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;



import api.endpoints.UserEndPoint;
import api.payload.User;
import api.utilities.DataPrividers;
import io.restassured.response.Response;

public class DDTest {
	
	
	
 @Test(priority = 1,dataProvider = "Data",dataProviderClass=DataPrividers.class)
	public void testPostUser(String UserID,String UserName,String fname,String lname,String useremail,String pwd,String ph ) {
		
		User payload=new User();
		
		
		payload.setId(Integer.parseInt(UserID));
	
		payload.setUsername(UserName);
		payload.setFirstName(fname);
		payload.setLastName(lname);
		payload.setEmail(useremail);
		payload.setPassword(pwd);
		payload.setPhone(ph);
		
		
Response response  = UserEndPoint.createUser(payload);
		
	Assert.assertEquals(response.getStatusCode(), 200);	
	}
	
	
	
	
	@Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataPrividers.class)
	public void testGetUser(String userName) {
		
	Response response=	UserEndPoint.readUser(userName);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	
	@Test(priority = 3,dataProvider = "UserNames",dataProviderClass = DataPrividers.class)
	public void testDeleteUser(String userName) {
		
	Response response=	UserEndPoint.deleteUser(userName);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	
	

}
