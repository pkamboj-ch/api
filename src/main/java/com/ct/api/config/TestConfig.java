package com.ct.api.config;

import java.net.InetAddress;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeSuite;

import com.ct.api.utils.Utils;
/**
 * Created with Eclipse
 * Created By: Puneet Kamboj
 * Created Date: 15/05/2020
 * Modified By: Puneet
 * Modified Date: 18/05/2020
 * To change this template use File | Settings | File Templates.
 */
public class TestConfig
{


	//Declare class level variables
  
    public static  String DATABASE_DRIVER;
    public static  String DATABASE_URL;
    public static  String DATABASE_USERID;
    public static  String DATABASE_PASSWORD;
    
    protected static String MachineIP;
    private static Logger Log = Logger.getLogger(TestConfig.class.getName());
  
    // Run before Suite is executed
    /**
     * Include global properties file
     *
     * @param None
     * @return None
     */
    @BeforeSuite(alwaysRun = true)
    public synchronized static void loadConfigProperty()
    {
      try
        {
    	DOMConfigurator.configure("log4j.xml");
        InetAddress ownIP=InetAddress.getLocalHost();
  		MachineIP = ownIP.getHostAddress();
  		MDC.put("IP", MachineIP);
  		 Utils uti=new Utils();
  		 uti.loadPropertyFile("project.properties");
  		 uti.loadPropertyFile(Utils.getProjectBaseDirectory()+"\\src\\test\\resources\\defaultapicallsheader.properties");
         System.out.println("Global properties has been loaded Sucessfully");
         Log.info("Global properties file loaded sucessfully");
          
        } catch (Exception ex) {
            System.out.println("Error while reading properties file: " + ex.getMessage());
            Log.error("Error while reading properties file:"+ex);
            ex.printStackTrace();
        }
    }
    
}