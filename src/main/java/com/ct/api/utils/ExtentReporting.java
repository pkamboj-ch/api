package com.ct.api.utils;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ct.api.utils.Utils;

/**
 * Created with Eclipse
 * Created By: Puneet Kamboj
 * Created Date: 16/05/2020
 * Modified By: Puneet
 * Modified Date: 18/05/2020
 * To change this template use File | Settings | File Templates.
 */
public class ExtentReporting{
	public  ExtentHtmlReporter htmlReporter;
	public  ExtentReports extent;
	public  ExtentTest test;
	static Logger Log = Logger.getLogger(ExtentReporting.class);


	public ExtentReporting() {
		
		
		htmlReporter = new ExtentHtmlReporter(Utils.getProjectBaseDirectory()+Utils.getValueOf("ExtentHtmlreportpath") + "\\" + Utils.getValueOf("ExtentHtmlReportName")+"_"+Utils.getValueOf("Environment")+"_"+java.time.Clock.systemUTC().instant().toString().replace(":", "-") +".html");
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name",  Utils.getValueOf("HostName"));
        extent.setSystemInfo("Environment", Utils.getValueOf("Environment"));
		htmlReporter.config().setDocumentTitle(Utils.getValueOf("ReportTitle")); 
		htmlReporter.config().setReportName(Utils.getValueOf("ExtentHtmlReportName")); 
		htmlReporter.config().setTheme(Theme.STANDARD);
		System.out.println("puneet"+ htmlReporter);
		System.out.println(Utils.getProjectBaseDirectory()+Utils.getValueOf("ExtentHtmlreportpath") + "\\" + Utils.getValueOf("ExtentHtmlReportName")+"_"+Utils.getValueOf("Environment")+"_"+java.time.Clock.systemUTC().instant().toString().replace(":", "-") +".html");
	}

    
	public  void startTestCase(String sTestCaseName){

		Log.info("****************************************************************************************");

		Log.info("$$$$$Start$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$Start$$$$$$$$$$$$");

		Log.info("****************************************************************************************");

		}
	
	
	public  void endTestCase(String sTestCaseName){

		Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");

		Log.info("X");

		Log.info("X");

		}
	
	public  void getResult(ITestResult result){
	
	try{
		 System.out.println ("---Started to update test case status in extent report \n");
		 Log.info("---Started to update test case status in extent report \n");
	if(result.getStatus() == ITestResult.FAILURE){
		//MarkupHelper is used to display the output in different colors
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
		//To add it in the extent report 
		System.out.println (result.getName() +"--- Failed \n");
		 Log.info(result.getName() +"--- Failed \n");

	}
	else if(result.getStatus() == ITestResult.SKIP){
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
		System.out.println (result.getName() +"--- Skip \n");
		    Log.info(result.getName() +"--- Skip \n");
	} 
	else if(result.getStatus() == ITestResult.SUCCESS)
	{
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		System.out.println (result.getName() +"--- Passed \n");
		    Log.info(result.getName() +"--- Passed \n");
	}
	
	} catch (Exception e){
	
		 System.out.println ("---ERROR during update test case status in extent report \n" + e.getMessage());
		 Log.info("---ERROR during update test case status in extent report \n" + e.getMessage());
		
	}
	
}
	
	public void endReport() {
		extent.flush();
	}
}