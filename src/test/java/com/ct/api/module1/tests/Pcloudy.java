package com.ct.api.module1.tests;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import org.testng.annotations.Test;

import com.ct.api.helper.RestAPIHelper;
import com.ct.api.module1.PcloudyResources;
import com.ct.api.utils.ExtentReporting;
import com.ct.api.utils.Utils;

import io.restassured.response.Response;


public class Pcloudy extends PcloudyResources{
	 String RunningMethodName=""; 
	 static Logger Log = Logger.getLogger(SampleTest.class);
	 RestAPIHelper apiHelper;
	 ExtentReporting ExtentReport;	
	 	 static Map<String, Object> datacollection = new HashMap< String,Object>();
	 
	 static String filePath = Utils.getProjectBaseDirectory()+"\\src\\test\\resources\\data.xlsx";
	 int id;
	 String idArr,profilename;
	 
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
	
	  @Test (priority=1)
	  
	 public void getTokencode()
	 {
		 ExtentReport.startTestCase(RunningMethodName);
		 ExtentReport.test =  ExtentReport.extent.createTest(RunningMethodName);
		 Response response = apiHelper.restGetWithBasicAuthentication(Utils.getValueOf("baseURL")+PcloudyResources.PcloudycommonResource+PcloudyResources.access,"","");
		 int statusCode = Utils.getResponseStatusCode(response);
	     int actStatusCode = 200;
	     Assert.assertEquals(statusCode, actStatusCode);
	     System.out.println(statusCode);
	     System.out.println(Utils.getResponseAllData(response));
		     ExtentReport.endTestCase(RunningMethodName);
	 }
     
	 
}