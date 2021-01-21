package com.ct.api.module1.tests;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.lang.reflect.Method;


import com.ct.api.helper.RestAPIHelper;
import com.ct.api.module1.Resources;
import com.ct.api.utils.ExtentReporting;
import com.ct.api.utils.Utils;

import io.restassured.response.Response;
/**
 * Created with Eclipse
 * Created By: Puneet Kamboj
 * Created Date: 15/05/2020
 * Modified By: Puneet
 * Modified Date: 18/05/2020
 * To change this template use File | Settings | File Templates.
 */
public class SampleTest extends Resources {
	 String RunningMethodName=""; 
	 static Logger Log = Logger.getLogger(SampleTest.class);
	 RestAPIHelper apiHelper;
	 ExtentReporting ExtentReport;	// get
	 String filepath=Utils.getProjectBaseDirectory()+"/src/test/resources/sampleTest.xlsx";
	
	 
	@BeforeSuite
	 public void setup()
	 {
		 apiHelper = new RestAPIHelper(); 
		 ExtentReport = new ExtentReporting();
		 
	 }
	 
	 @BeforeMethod
	    public void nameBefore(Method method)
	    {
	        System.out.println("Test name: " + method.getName());
	        RunningMethodName = method.getName();
	    }
        
	   @AfterMethod(alwaysRun=true)
	    public  void updateTestStatusinExtentReport(ITestResult result)
	    {
	    	ExtentReport.getResult(result);
	    }
	  
	   @AfterTest
	   public void GenerateReport()
	   {
		   ExtentReport.endReport(); 
	   }
	   
	   @Test
	 public void check200ResponseAllEmployescall()
	 {
		 ExtentReport.startTestCase(RunningMethodName);
		 ExtentReport.test =  ExtentReport.extent.createTest(RunningMethodName);
		 System.out.println( Utils.getValueOf("baseURL"));
		 System.out.println( Utils.getValueOf("HEADERS_CONTENT_TYPE"));
		 System.out.println(Resources.allemploye);
		 Response response = apiHelper.restGet(Utils.getValueOf("baseURL")+Resources.commonResource+Resources.allemploye);
		 int statusCode = Utils.getResponseStatusCode(response);
		 Log.info("Request :=="+ Utils.getValueOf("baseURL")+Resources.commonResource+Resources.allemploye);
	     int actStatusCode = 200;
	     Assert.assertEquals(statusCode, actStatusCode);
	     System.out.println(statusCode);
	     System.out.println(Utils.getResponseAllData(response));
	     Log.info("Response :=="+ Utils.getResponseAllData(response));
	     
	     ExtentReport.endTestCase(RunningMethodName);
	 }
	 
	// post
	@Test
	public void check200ResponseCreateEmployeePost(JSONObject json)
	{
		 ExtentReport.startTestCase(RunningMethodName);
		 ExtentReport.test =  ExtentReport.extent.createTest(RunningMethodName);
		 String body=json.toString();
		 Response response = apiHelper.restPost(body,Utils.getValueOf("baseURL")+Resources.commonResource+Resources.postemploye);
		 Log.info("Request :=="+ Utils.getValueOf("baseURL")+Resources.commonResource+Resources.postemploye);
		 int statusCode = Utils.getResponseStatusCode(response);
	     int actStatusCode = 200;
	     Assert.assertEquals(statusCode, actStatusCode);
	     System.out.println(statusCode);
	     System.out.println(Utils.getResponseAllData(response));
	     Log.info("Response :=="+ Utils.getResponseAllData(response));
	     ExtentReport.endTestCase(RunningMethodName);
	}
	
	// put
	@Test
	public void check200ResponseCreateEmployeePut(JSONObject json)
	{
		 int employeeId=json.getInt("id");
		 ExtentReport.startTestCase(RunningMethodName);
		 ExtentReport.test =  ExtentReport.extent.createTest(RunningMethodName);
		 //json = read.removeEntryFromJson(json, "id");
		 String body=json.toString();
		 System.out.println(body);
		 Response response = apiHelper.restPut(body,Utils.getValueOf("baseURL")+Resources.commonResource+Resources.putemploye+employeeId);
		 Log.info("Request :=="+ Utils.getValueOf("baseURL")+Resources.commonResource+Resources.putemploye);
		 int statusCode = Utils.getResponseStatusCode(response);
	     int actStatusCode = 200;
	     Assert.assertEquals(statusCode, actStatusCode);
	     System.out.println(statusCode);
	     System.out.println(Utils.getResponseAllData(response));
	     Log.info("Response :=="+ Utils.getResponseAllData(response));
	     ExtentReport.endTestCase(RunningMethodName);
	}
	
	// delete
		@Test
		public void check200ResponseCreateEmployeeDelete(JSONObject json)
		{
			 int employeeId=json.getInt("id");
			 ExtentReport.startTestCase(RunningMethodName);
			 ExtentReport.test =  ExtentReport.extent.createTest(RunningMethodName);
			 System.out.println(Utils.getValueOf("baseURL")+Resources.commonResource+Resources.deleteemploye+employeeId);
			 Response response = apiHelper.restDelete(Utils.getValueOf("baseURL")+Resources.commonResource+Resources.deleteemploye+employeeId);
			 Log.info("Request :=="+ Utils.getValueOf("baseURL")+Resources.commonResource+Resources.deleteemploye);
			 int statusCode = Utils.getResponseStatusCode(response);
		     int actStatusCode = 200;
		     Assert.assertEquals(statusCode, actStatusCode);
		     System.out.println(statusCode);
		     System.out.println(Utils.getResponseAllData(response));
		     Log.info("Response :=="+ Utils.getResponseAllData(response));
		     ExtentReport.endTestCase(RunningMethodName);
		}		
		
		@Test
		public void getSingleEmployeeDetail(JSONObject json)
		{
			int employeeId = json.getInt("id");
			ExtentReport.startTestCase(RunningMethodName);
			ExtentReport.test=ExtentReport.extent.createTest(RunningMethodName);
			System.out.println(Utils.getValueOf("baseURL")+Resources.commonResource+Resources.singleemploye+employeeId);
			Response response = apiHelper.restGet(Utils.getValueOf("baseURL")+Resources.commonResource+Resources.singleemploye+employeeId);
			System.out.println(response.toString());
			Log.info("Request :=="+ Utils.getValueOf("baseURL")+Resources.commonResource+Resources.deleteemploye);
			int statusCode = Utils.getResponseStatusCode(response);
			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
		    System.out.println(Utils.getResponseAllData(response));
		    Log.info("Response :=="+ Utils.getResponseAllData(response));
		    ExtentReport.endTestCase(RunningMethodName);
		}
		
		
		
}
