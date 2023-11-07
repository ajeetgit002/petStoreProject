package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint;

import api.payload.User;

import io.restassured.response.Response;

public class UserTest {
	
	Faker faker ;
	
	User payload;
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		
		faker=new Faker();
		payload =new User();
		
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPassword(faker.internet().password(5,10));
		payload.setPhone(faker.phoneNumber().cellPhone());
	     
		//logs 
		logger=LogManager.getLogger(this.getClass());
		
	
	}
	@Test(priority = 1)
	public void testPostUser(){
		logger.info("*************************  created User**************");
		
	Response response=	UserEndPoint.createUser(payload);
	response.then().log().all();
	
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("************************* User Is created**************");
	}
	
	
	
	@Test(priority = 2)
    public void	testGetUserByName() {
		logger.info("************************* Reading  user**************");
		
	Response respon =	UserEndPoint.readUser(payload.getUsername());
		
	respon.then().log().all();	
	Assert.assertEquals(respon.getStatusCode(), 200);
	logger.info("************************* user Is Read**************");
		
		}
		@Test(priority = 3)
		public void testUpdateByName() {
			
			
			//update
			
			payload.setFirstName(faker.name().firstName());
			payload.setLastName(faker.name().lastName());
			payload.setEmail(faker.internet().safeEmailAddress());
			
			Response response=	UserEndPoint.updateUser(this.payload.getUsername(),payload);
			response.then().log().body();
			
			Assert.assertEquals(response.getStatusCode(), 200);
			
			//checking data  after update 
			Response respon =	UserEndPoint.readUser(payload.getUsername());
			
			respon.then().log().all();	
			Assert.assertEquals(respon.getStatusCode(), 200);
				
			
		
		}
		
		
		
		@Test(priority = 4)
		public void testDeleteUserByName() {
			
			
			
	Response res=UserEndPoint.deleteUser(this.payload.getUsername());
			
	Assert.assertEquals(res.getStatusCode(),200);
		}
		
	
}

